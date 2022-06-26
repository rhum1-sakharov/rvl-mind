package org.rvl.kafka.stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Objects;
import java.util.Properties;

public class UserEnrich {
    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "user-enrich");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> userPurchaseStream = builder.stream("aks.user-purchase.event");
        GlobalKTable<String, String> userDataTable = builder.globalTable("aks.user-data.snapshot");

        // inner join
        KStream<String, String> userEnrichedInner = userPurchaseStream.join(userDataTable,
                (key, value) -> key,
                (purchase, data) -> String.format("Purchase=%s, Data=%s", purchase, data));
        userEnrichedInner.to("aks.user-enriched-inner.event");

        // left join
        KStream<String, String> userEnrichedLeft = userPurchaseStream.leftJoin(userDataTable,
                (key, value) -> key,
                (purchase, data) -> {
                    if (Objects.isNull(data)) {
                        return String.format("Purchase=%s, Data=null", purchase);
                    }
                    return String.format("Purchase=%s, Data=%s", purchase, data);
                });
        userEnrichedLeft.to("aks.user-enriched-left.event");

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.cleanUp(); // only do this on dev
        streams.start();

        // print the topology
        System.out.println(streams.toString());

        // shutdown gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
