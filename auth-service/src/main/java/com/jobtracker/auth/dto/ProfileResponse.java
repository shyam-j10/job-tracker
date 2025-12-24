package com.jobtracker.auth.dto;

public class ProfileResponse {

    private String name;
    private String phone;
    private String resumeUrl;

    public ProfileResponse(String name, String phone, String resumeUrl) {
        this.name = name;
        this.phone = phone;
        this.resumeUrl = resumeUrl;
    }

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
