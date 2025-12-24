package com.jobtracker.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfileEntity {

    @Id
    private Long userId; // same as UserEntity.id

    private String name;
    private String phone;
    private String resumeUrl;

    // getters and setters

    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getResumeUrl() {
        return resumeUrl;
    }
    
    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }
}
