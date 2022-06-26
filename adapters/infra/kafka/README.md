# Launch docker kafka cluster

go to src/main/resources and launch

    docker-compose up -d

# Create topics

    docker exec -ti resources_kafka-1_1 bash

    kafka-topics --bootstrap-server localhost:9092 --create --topic aks.user-data.snapshot --partitions 6 --replication-factor 2 --config cleanup.policy=compact
    kafka-topics --bootstrap-server localhost:9092 --create --topic aks.user-purchase.event --partitions 6 --replication-factor 2 
    kafka-topics --bootstrap-server localhost:9092 --create --topic aks.user-enriched-left.event --partitions 6 --replication-factor 2
    kafka-topics --bootstrap-server localhost:9092 --create --topic aks.user-enriched-inner.event --partitions 6 --replication-factor 2
