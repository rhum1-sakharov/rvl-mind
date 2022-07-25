package org.rvl.mind.test.kafka.docker;

import org.junit.jupiter.api.Test;

class BootstrappedDockerKafkaConnectClusterTest {

  private static final BootstrappedDockerKafkaConnectCluster kafkaConnectCluster = BootstrappedDockerKafkaConnectCluster.INSTANCE;

  @Test
  void givenKafkaConnectCluster_whenStart_thenStarted() {
    kafkaConnectCluster.getCluster().createTopic("hello-topic");
  }

}