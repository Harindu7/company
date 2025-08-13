package com.microservices.company.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "companies")
public class Company {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean active = true;
    private String description;
}
