package com.jobtracker.job.dto.response;

import com.jobtracker.job.enums.ContactRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponse {

    private Long id;
    private String name;
    private ContactRole role;
    private String email;
    private String phone;
    private String notes;

    // getters & setters
}
