package org.rvl.mind.test.kafka.docker;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

public class DockerKafkaCluster {

  protected int kafkaBrokerPort;
  protected int schemaRegistryPort;
  protected static String testContainerHost = "localhost";

  public DockerKafkaCluster(int kafkaBrokerPort, int schemaRegistryPort) {
    this.kafkaBrokerPort = kafkaBrokerPort;
    this.schemaRegistryPort = schemaRegistryPort;
  }

  public int getKafkaBrokerPort() {
    return kafkaBrokerPort;
  }

  public void setKafkaBrokerPort(int kafkaBrokerPort) {
    this.kafkaBrokerPort = kafkaBrokerPort;
  }

  public int getSchemaRegistryPort() {
    return schemaRegistryPort;
  }

  public void setSchemaRegistryPort(int schemaRegistryPort) {
    this.schemaRegistryPort = schemaRegistryPort;
  }

  public String getKafkaBrokerUrl() {
    return testContainerHost + ":" + kafkaBrokerPort;
  }

  public String getSchemaRegistryUrl() {
    return "http://" + testContainerHost + ":" + schemaRegistryPort;
  }

  public void createTopic(String topic) {
    Properties properties = new Properties();

    properties.put("bootstrap.servers", testContainerHost + ":" + kafkaBrokerPort);
    properties.put("group.id", "topicCreator");

    AdminClient adminClient = AdminClient.create(properties);
    NewTopic newTopic = new NewTopic(topic, 1, (short) 1);

    List<NewTopic> newTopics = new ArrayList<>();
    newTopics.add(newTopic);

    try {
      adminClient.createTopics(newTopics).values().get(topic).get();
    } catch (InterruptedException | ExecutionException e) {
      if (!e.getMessage().contains("already exists")) {
        throw new RuntimeException("error while creating topic", e);
      }
    }
    adminClient.close();
  }
}
