package com.udemy.kafka.streams;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Named;

public class FavouriteColourApp {

  private static final String INPUT_TOPIC = "user5.color";
  private static final String INTERMEDIATE_TOPIC = "user5.color.cleaned";
  private static final String OUTPUT_TOPIC = "favorite5.color";
  private static final String STREAM_APPLICATION_ID = "favorite5.color.application";

  public static void main(String[] args) {
    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, STREAM_APPLICATION_ID);
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    config.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,"1000");
    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

    StreamsBuilder builder = new StreamsBuilder();

    KStream<String, String> userColorStream = builder.stream(INPUT_TOPIC);
    userColorStream.filter((key, value) -> value.contains(","))
                   .selectKey((key, value) -> value.split(",")[0].toLowerCase())
                   .mapValues((readOnlyKey, value) -> value.split(",")[1].toLowerCase())
                   .filter((key, value) -> Arrays.asList("green", "red", "blue").contains(value))
                   .to(INTERMEDIATE_TOPIC);

    KTable<String, Long> userColorCleanedTable = builder.table(INTERMEDIATE_TOPIC)
                                                        .groupBy((key,
                                                                  value) -> new KeyValue<>(value.toString(),
                                                                                           value))
                                                        .count(Named.as("count.favorite.colors"));

    userColorCleanedTable.toStream(Named.as("stream."+OUTPUT_TOPIC)).to(OUTPUT_TOPIC);

    KafkaStreams streams = new KafkaStreams(builder.build(), config);
    streams.start();

    // print the topology
    System.out.println(streams.toString());

    // shutdown gracefully
    Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
  }

}
