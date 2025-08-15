package com.microservices.company.controller;

import com.microservices.company.model.dto.CompanyRequest;
import com.microservices.company.model.entity.Company;
import com.microservices.company.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Tag(name = "Company Management", description = "APIs for managing company operations")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @Operation(summary = "Create a new company")
    @ApiResponse(responseCode = "200", description = "Company created successfully")
    public ResponseEntity<Company> createCompany(@RequestBody CompanyRequest request) {
        return ResponseEntity.ok(companyService.createCompany(request));
    }

    @GetMapping
    @Operation(summary = "Get all active companies")
    @ApiResponse(responseCode = "200", description = "List of all active companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllActiveCompanies());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get company by ID")
    @ApiResponse(responseCode = "200", description = "Company found")
    @ApiResponse(responseCode = "404", description = "Company not found")
    public ResponseEntity<Company> getCompanyById(@PathVariable String id) {
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update company details")
    @ApiResponse(responseCode = "200", description = "Company updated successfully")
    @ApiResponse(responseCode = "404", description = "Company not found")
    public ResponseEntity<Company> updateCompany(@PathVariable String id, @RequestBody CompanyRequest request) {
        Company updatedCompany = companyService.updateCompany(id, request);
        return updatedCompany != null ? ResponseEntity.ok(updatedCompany) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a company")
    @ApiResponse(responseCode = "200", description = "Company deactivated successfully")
    @ApiResponse(responseCode = "404", description = "Company not found")
    public ResponseEntity<Company> deactivateCompany(@PathVariable String id) {
        Company deactivatedCompany = companyService.deactivateCompany(id);
        return deactivatedCompany != null ? ResponseEntity.ok(deactivatedCompany) : ResponseEntity.notFound().build();
    }
}
