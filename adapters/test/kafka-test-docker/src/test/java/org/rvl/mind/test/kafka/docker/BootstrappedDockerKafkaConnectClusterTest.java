package org.rvl.mind.test.kafka.docker;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class BootstrappedDockerKafkaConnectClusterTest {

  private static final Logger logger = Logger.getLogger(BootstrappedDockerKafkaConnectClusterTest.class.getName());
  private static final BootstrappedDockerKafkaConnectCluster kafkaConnectCluster = BootstrappedDockerKafkaConnectCluster.INSTANCE;

  @Test
  void givenKafkaConnectCluster_whenStart_thenStarted() {
    logger.info(String.format("kafka broker port = %s", kafkaConnectCluster.getCluster().getKafkaBrokerPort()));
  }

}