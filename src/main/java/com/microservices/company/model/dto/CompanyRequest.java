package com.microservices.company.model.dto;

import lombok.Data;

@Data
public class CompanyRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
}
