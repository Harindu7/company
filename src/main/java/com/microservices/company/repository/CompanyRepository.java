package com.microservices.company.repository;

import com.microservices.company.model.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    List<Company> findByActive(boolean active);
}
