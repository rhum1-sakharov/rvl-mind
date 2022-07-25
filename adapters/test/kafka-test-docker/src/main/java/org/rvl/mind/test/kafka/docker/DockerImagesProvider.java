package org.rvl.mind.test.kafka.docker;

import java.util.HashMap;
import java.util.Map;

public class DockerImagesProvider {
  public static Map<String, String> getOsArchSpecificDockerImages() {
    Map<String, String> dockerImages = new HashMap<>();

    if (System.getProperty("os.arch").equals("aarch64")) {
      dockerImages.put("ZOOKEEPER_DOCKER_IMAGE", "watershine/cp-zookeeper:6.1.0-arm64");
      dockerImages.put("KAFKA_DOCKER_IMAGE", "watershine/cp-kafka:6.1.0-arm64");
      dockerImages.put("SCHEMA_REGISTRY_DOCKER_IMAGE", "watershine/cp-schema-registry:6.1.0-arm64");
      //      dockerImages.put("CONNECT_DOCKER_IMAGE",
      //                       "registry.gitlab.int.bell.ca/vt-platform/docker-images/docker-kafka-connect-mongodb:6.1.0_1.7.0-vt-0.1-arm64");
    } else {
      dockerImages.put("ZOOKEEPER_DOCKER_IMAGE", "confluentinc/cp-zookeeper:6.2.1");
      dockerImages.put("KAFKA_DOCKER_IMAGE", "confluentinc/cp-kafka:6.2.1");
      dockerImages.put("SCHEMA_REGISTRY_DOCKER_IMAGE", "confluentinc/cp-schema-registry:6.2.1");
      //      dockerImages.put("CONNECT_DOCKER_IMAGE",
      //                       "registry.gitlab.int.bell.ca/vt-platform/docker-images/docker-kafka-connect-mongodb:6.2.1-1-ubi8_1.7.0");
    }
    return dockerImages;
  }
}
