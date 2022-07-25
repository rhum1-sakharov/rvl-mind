package org.rvl.mind.test.kafka.docker;

import static org.rvl.mind.test.kafka.docker.DockerImagesProvider.*;

import java.io.File;
import java.io.InputStream;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.shaded.com.google.common.io.Files;

public class InternalDockerKafkaConnectCluster extends DockerKafkaCluster {

  private static final Logger logger = LoggerFactory.getLogger(InternalDockerKafkaConnectCluster.class);
  private static final long HTTP_REGISTRY_WAIT_DELAY = 300;
  private final DockerComposeContainer dockerContainer;

  public InternalDockerKafkaConnectCluster(int kafkaBrokerPort, int schemaRegistryPort) {
    super(kafkaBrokerPort, schemaRegistryPort);
    File dockerFile = extractDockerCompose();
    dockerContainer = new DockerComposeContainer(dockerFile);
    dockerContainer.withLocalCompose(true);
    setupLogger();
    setupEnvironmentVariables();
    setupWaitingStrategies();
  }

  public String getDockerComposeFileName() {
    return "kafka-connect-cluster-docker-compose.yml";
  }

  private File extractDockerCompose() {
    try {
      new File("target/docker").mkdirs();
      InputStream initialStream = ClassLoader.getSystemResourceAsStream(getDockerComposeFileName());

      byte[] buffer = new byte[initialStream.available()];
      initialStream.read(buffer);

      File targetFile = new File("target/docker/" + getDockerComposeFileName());
      Files.write(buffer, targetFile);
      return targetFile;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void setupWaitingStrategies() {
    dockerContainer.waitingFor("broker",
                               Wait.forListeningPort()
                                   .withStartupTimeout(Duration.ofSeconds(HTTP_REGISTRY_WAIT_DELAY)));
    dockerContainer.waitingFor("schema-registry",
                               Wait.forHttp("")
                                   .withStartupTimeout(Duration.ofSeconds(HTTP_REGISTRY_WAIT_DELAY)));
  }

  private void setupEnvironmentVariables() {
    dockerContainer.withEnv("TEST_CONTAINER_HOST", testContainerHost)
                   .withEnv("KAFKA_BROKER_PORT", String.valueOf(kafkaBrokerPort))
                   .withEnv("SCHEMA_REGISTRY_PORT", String.valueOf(schemaRegistryPort))
                   .withEnv(getOsArchSpecificDockerImages());
  }

  private void setupLogger() {
    dockerContainer.withLogConsumer("broker",
                                    new DockerContainerFileOutputConsumer("target/docker/",
                                                                          "broker"));
    dockerContainer.withLogConsumer("zookeeper",
                                    new DockerContainerFileOutputConsumer("target/docker/",
                                                                          "zookeeper"));
    dockerContainer.withLogConsumer("schema-registry",
                                    new DockerContainerFileOutputConsumer("target/docker/",
                                                                          "schema-registry"));
  }

  public void start() {
    logger.info(
      "Starting kafka connect cluster docker with env : [testContainerHost={}, kafkaBrokerPort={}, schemaRegistryPort={}]",
      testContainerHost,
      kafkaBrokerPort,
      schemaRegistryPort);
    dockerContainer.start();
  }

  public void stop() {
    logger.info("Stopping kafka connect cluster docker container");
    dockerContainer.stop();
  }

}
