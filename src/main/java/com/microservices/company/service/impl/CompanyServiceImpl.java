package com.microservices.company.service.impl;

import com.microservices.company.model.dto.CompanyRequest;
import com.microservices.company.model.entity.Company;
import com.microservices.company.repository.CompanyRepository;
import com.microservices.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(CompanyRequest request) {
        log.info("Creating new company with name: {}", request.getName());
        Company company = Company.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .description(request.getDescription())
                .active(true)
                .userCount(0)
                .build();
        Company savedCompany = companyRepository.save(company);
        log.info("Successfully created company with ID: {}", savedCompany.getId());
        return savedCompany;
    }

    @Override
    public List<Company> getAllActiveCompanies() {
        log.info("Fetching all active companies");
        List<Company> companies = companyRepository.findByActive(true);
        log.info("Found {} active companies", companies.size());
        return companies;
    }

    @Override
    public Optional<Company> getCompanyById(String id) {
        log.info("Fetching company with ID: {}", id);
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            log.info("Found company: {}", company.get().getName());
        } else {
            log.warn("Company not found with ID: {}", id);
        }
        return company;
    }

    @Override
    public Company updateCompany(String id, CompanyRequest request) {
        log.info("Updating company with ID: {}", id);
        return companyRepository.findById(id)
                .map(existingCompany -> {
                    existingCompany.setName(request.getName());
                    existingCompany.setAddress(request.getAddress());
                    existingCompany.setPhone(request.getPhone());
                    existingCompany.setEmail(request.getEmail());
                    existingCompany.setDescription(request.getDescription());
                    Company updatedCompany = companyRepository.save(existingCompany);
                    log.info("Successfully updated company with ID: {}", id);
                    return updatedCompany;
                })
                .orElseGet(() -> {
                    log.error("Failed to update company - not found with ID: {}", id);
                    return null;
                });
    }

    @Override
    public Company deactivateCompany(String id) {
        log.info("Deactivating company with ID: {}", id);
        return companyRepository.findById(id)
                .map(company -> {
                    company.setActive(false);
                    Company deactivatedCompany = companyRepository.save(company);
                    log.info("Successfully deactivated company with ID: {}", id);
                    return deactivatedCompany;
                })
                .orElseGet(() -> {
                    log.error("Failed to deactivate company - not found with ID: {}", id);
                    return null;
                });
    }

    @Override
    public Company incrementUserCount(String companyId) {
        log.info("Incrementing user count for company {}", companyId);
        return companyRepository.findById(companyId)
                .map(company -> {
                    company.setUserCount(company.getUserCount() + 1);
                    return companyRepository.save(company);
                }).orElseGet(() -> {
                    log.warn("Company not found: {}", companyId);
                    return null;
                });
    }
}
