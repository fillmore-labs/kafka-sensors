#!/bin/sh

KAFKA_VERSION="3.3.2"
KAFKA_DIR=".kafka"

KAFKA_PREFIX="kafka_2.13-$KAFKA_VERSION"
KAFKA_CONSUME="$KAFKA_DIR/$KAFKA_PREFIX/bin/kafka-console-consumer.sh"

[ -x "$KAFKA_CONSUME" ] || exit 1

"$KAFKA_CONSUME" --bootstrap-server 127.0.0.1:9092 --topic result-topic
