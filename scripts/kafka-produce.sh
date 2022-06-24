#!/bin/bash

KAFKA_VERSION="3.2.0"
KAFKA_DIR=".kafka"

KAFKA_PREFIX="kafka_2.13-$KAFKA_VERSION"
KAFKA_PRODUCE="$KAFKA_DIR/$KAFKA_PREFIX/bin/kafka-console-producer.sh"

[ -x "$KAFKA_PRODUCE" ] || exit 1

SENSOR_ID="$RANDOM"

"$KAFKA_PRODUCE" --broker-list 127.0.0.1:9092 --topic input-topic << _INPUT
{"id":"$SENSOR_ID", "time":"1984-01-22T15:45:00Z", "position":"off"}
{"id":"$SENSOR_ID", "time":"1984-01-22T15:45:10Z", "position":"off"}
{"id":"$SENSOR_ID", "time":"1984-01-22T15:45:30Z", "position":"on"}
{"id":"$SENSOR_ID", "time":"1984-01-22T15:46:30Z", "position":"off"}
_INPUT
