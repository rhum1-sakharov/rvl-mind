package com.example;

import org.junit.jupiter.api.Disabled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

class HelloTest {

    private static final Logger logger = LoggerFactory.getLogger(HelloTest.class);
    private static final DockerImageName KAFKA_TEST_IMAGE = DockerImageName.parse("confluentinc/cp-kafka:6.2.1");

    @Disabled
    public void testUsage() {
        try (KafkaContainer kafka = new KafkaContainer(KAFKA_TEST_IMAGE)) {
            kafka.start();
            logger.info(kafka.getBootstrapServers());
        }
    }
}