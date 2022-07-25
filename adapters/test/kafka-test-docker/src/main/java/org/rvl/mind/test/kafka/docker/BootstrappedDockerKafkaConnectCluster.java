package org.rvl.mind.test.kafka.docker;

import me.alexpanov.net.FreePortFinder;

public enum BootstrappedDockerKafkaConnectCluster {
  INSTANCE;

  private static final DockerKafkaCluster kafkaCluster;

  static {
    try {
      kafkaCluster = createInternalDockerKafkaCluster();
    } catch (Throwable e) {
      throw new IllegalStateException(e);
    }
  }

  private static DockerKafkaCluster createInternalDockerKafkaCluster() {
    InternalDockerKafkaConnectCluster internalDockerKafkaCluster = new InternalDockerKafkaConnectCluster(FreePortFinder.findFreeLocalPort(),
                                                                                                         FreePortFinder.findFreeLocalPort());
    Runtime.getRuntime().addShutdownHook(new Thread(() -> internalDockerKafkaCluster.stop()));
    internalDockerKafkaCluster.start();
    return internalDockerKafkaCluster;
  }

  public DockerKafkaCluster getCluster() {
    return kafkaCluster;
  }

}
