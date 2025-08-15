package com.microservices.company.service;

import com.microservices.company.model.dto.CompanyRequest;
import com.microservices.company.model.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company createCompany(CompanyRequest request);
    List<Company> getAllActiveCompanies();
    Optional<Company> getCompanyById(String id);
    Company updateCompany(String id, CompanyRequest request);
    Company deactivateCompany(String id);
}
