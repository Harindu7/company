package com.microservices.company.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class DatabaseMigration {

    // Migration 1: Add userCount field
    @ChangeUnit(id = "add-user-count-field-v1", order = "001", author = "Harindu")
    public static class AddUserCountField {

        @Execution
        public void addUserCountField(MongoTemplate mongoTemplate) {
            Query query = new Query(Criteria.where("userCount").exists(false));
            Update update = new Update().set("userCount", 0);
            mongoTemplate.updateMulti(query, update, "companies");
        }

        @RollbackExecution
        public void rollbackAddUserCountField(MongoTemplate mongoTemplate) {
            Update update = new Update().unset("userCount");
            mongoTemplate.updateMulti(new Query(), update, "companies");
        }
    }

}