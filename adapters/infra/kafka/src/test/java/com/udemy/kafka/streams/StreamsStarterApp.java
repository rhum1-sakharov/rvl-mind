package com.udemy.kafka.streams;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;

public class StreamsStarterApp {

  public static void main(String[] args) {
    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

    StreamsBuilder builder = new StreamsBuilder();

    KStream<String, String> wordCountInput = builder.stream("word-count-input");

    KTable<String, Long> wordCounts = wordCountInput.mapValues((ValueMapper<String, String>) String::toLowerCase)
                                                    .flatMapValues(value -> Arrays.asList(value.split(" ")))
                                                    .selectKey((key, value) -> value)
                                                    .groupByKey()
                                                    .count(Named.as("Counts"));

    wordCounts.toStream(Named.as("wordCountStream"))
              .to("word-count-output", Produced.with(Serdes.String(), Serdes.Long()));

    KafkaStreams streams = new KafkaStreams(builder.build(), config);
    streams.start();

    // print the topology
    System.out.println(streams.toString());

    // shutdown gracefully
    Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
  }
}
