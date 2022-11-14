docker exec kafka1 kafka-topics --bootstrap-server localhost:9092 --create --topic input --partitions 3 --replication-factor 3
docker exec kafka1 kafka-topics --bootstrap-server localhost:9092 --create --topic output --partitions 3 --replication-factor 3
