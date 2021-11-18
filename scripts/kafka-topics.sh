#!/bin/sh

KAFKA_VERSION="3.0.0"
KAFKA_DIR=".kafka"

KAFKA_PREFIX="kafka_2.13-$KAFKA_VERSION"
KAFKA_TOPICS="$KAFKA_DIR/$KAFKA_PREFIX/bin/kafka-topics.sh"

[ -x "$KAFKA_TOPICS" ] || exit 1

"$KAFKA_TOPICS" --bootstrap-server 127.0.0.1:9092 --if-not-exists --create --topic input-topic \
  --partitions 1 --replication-factor 1
"$KAFKA_TOPICS" --bootstrap-server 127.0.0.1:9092 --if-not-exists --create --topic result-topic \
  --partitions 1 --replication-factor 1
