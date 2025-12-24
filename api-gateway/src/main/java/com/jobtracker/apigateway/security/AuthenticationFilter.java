package com.jobtracker.apigateway.security;

import io.jsonwebtoken.Claims;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    public AuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    String path = exchange.getRequest().getURI().getPath();
    log.info("Incoming request path: {}", path);

    // 1️⃣ Allow auth routes
    if (path.startsWith("/api/auth")) {
        log.info("Public auth route, skipping JWT validation");
        return chain.filter(exchange);
    }

    String authHeader = exchange.getRequest()
            .getHeaders()
            .getFirst(HttpHeaders.AUTHORIZATION);

    log.info("Authorization header: {}", authHeader);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        log.warn("Missing or invalid Authorization header");
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    String token = authHeader.substring(7);
    log.info("JWT token extracted", token);

    try {
        Claims claims = jwtUtil.validateToken(token);
        log.info("JWT validated successfully. Claims: {}", claims);

        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(builder -> builder
                        .header("X-User-Id", claims.get("userId").toString())
                )
                .build();

        return chain.filter(mutatedExchange);

    } catch (Exception e) {
        log.error("JWT validation failed", e);
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}


}
