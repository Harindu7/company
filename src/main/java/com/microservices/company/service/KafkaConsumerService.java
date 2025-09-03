package com.microservices.company.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final KafkaEventRouterService router;

    // You can list all topics here, or later dynamically subscribe to all keys in router
    @KafkaListener(topics = {"user-created-topic"}, groupId = "company-service-group")
    public void listen(Map<String, Object> payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Received message on topic {}: {}", topic, payload);
        router.route(topic, payload);
    }
}

