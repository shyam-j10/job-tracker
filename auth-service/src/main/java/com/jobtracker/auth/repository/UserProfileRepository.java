package com.jobtracker.auth.repository;

import com.jobtracker.auth.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
}
