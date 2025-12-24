package com.jobtracker.auth.dto;

public class UpdateProfileRequest {

    private String name;
    private String phone;
    private String resumeUrl;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }
}
