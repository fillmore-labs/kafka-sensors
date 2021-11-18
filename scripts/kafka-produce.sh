#!/bin/bash

KAFKA_VERSION="3.0.0"
KAFKA_DIR=".kafka"

KAFKA_PREFIX="kafka_2.13-$KAFKA_VERSION"
KAFKA_PRODUCE="$KAFKA_DIR/$KAFKA_PREFIX/bin/kafka-console-producer.sh"

[ -x "$KAFKA_PRODUCE" ] || exit 1

SENSOR_ID="$RANDOM"

"$KAFKA_PRODUCE" --broker-list 127.0.0.1:9092 --topic input-topic << _INPUT
{"id":"$SENSOR_ID", "time":443634300.0, "position":"off"}
{"id":"$SENSOR_ID", "time":443634310.0, "position":"off"}
{"id":"$SENSOR_ID", "time":443634330.0, "position":"on"}
{"id":"$SENSOR_ID", "time":443634390.0, "position":"off"}
_INPUT
