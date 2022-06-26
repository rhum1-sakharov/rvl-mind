package org.rvl.kafka.stream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class UserProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // acks
        config.put(ProducerConfig.ACKS_CONFIG, "all");
        config.put(ProducerConfig.RETRIES_CONFIG, "3");
        config.put(ProducerConfig.LINGER_MS_CONFIG, "1");
        // leverage idempotent producer
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true"); // ensure we don't push duplicates
        Producer<String, String> producer = new KafkaProducer<String, String>(config);

        System.out.println("Example 1 - new user");
        producer.send(dataRecord("john", String.format("First=%s, Last=%s, Email=%s", "john", "doe", "john.doe@gmail.com"))).get();
        producer.send(purchaseRecord("john", "Apples and bananas (1)")).get();

        Thread.sleep(10000);

        System.out.println("Example 2 - non existing user");
        producer.send(purchaseRecord("bob", "Kafka udemy course (2)")).get();

        Thread.sleep(10000);

        System.out.println("Example 3 - update user");
        producer.send(dataRecord("john", String.format("First=%s, Last=%s, Email=%s", "johnny", "doe", "johnny.doe@gmail.com"))).get();
        producer.send(purchaseRecord("john", "Oranges (3)")).get();
    }

    private static ProducerRecord<String, String> purchaseRecord(String key, String value) {
        return new ProducerRecord<>("aks.user-purchase.event", key, value);
    }

    private static ProducerRecord<String, String> dataRecord(String key, String value) {
        return new ProducerRecord<>("aks.user-data.event", key, value);
    }
}

