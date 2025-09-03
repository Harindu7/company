package com.microservices.company.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventRouterService {

    private final CompanyService companyService;

    private final Map<String, Consumer<Map<String, Object>>> topicHandlers = new HashMap<>();

    @PostConstruct
    public void init() {
        // Map topic -> service method
        topicHandlers.put("user-created-topic", payload -> {
            String companyId = (String) payload.get("companyId");
            companyService.incrementUserCount(companyId);
        });

        // Add more topics easily
        // topicHandlers.put("order-created-topic", payload -> companyService.incrementOrderCount(...));
    }

    public void route(String topic, Map<String, Object> payload) {
        Consumer<Map<String, Object>> handler = topicHandlers.get(topic);
        if (handler != null) {
            handler.accept(payload);
        } else {
            log.warn("No handler registered for topic {}", topic);
        }
    }
}

