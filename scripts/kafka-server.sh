#!/bin/sh

KAFKA_VERSION="3.0.0"
KAFKA_DIR=".kafka"

KAFKA_PREFIX="kafka_2.13-$KAFKA_VERSION"
KAFKA_URL="https://dlcdn.apache.org/kafka/$KAFKA_VERSION/$KAFKA_PREFIX.tgz"

[ -d "$KAFKA_DIR" ] || mkdir "$KAFKA_DIR"

[ -f "$KAFKA_DIR/$KAFKA_PREFIX.tgz" ] || curl -Lo "$KAFKA_DIR/$KAFKA_PREFIX.tgz" "$KAFKA_URL"

shasum -a 256 -c - << _SHASUM || exit 1
a82728166bbccf406009747a25e1fe52dbcb4d575e4a7a8616429b5818cd02d1 *$KAFKA_DIR/$KAFKA_PREFIX.tgz
_SHASUM

tar -xf "$KAFKA_DIR/$KAFKA_PREFIX.tgz" -C "$KAFKA_DIR"

LOG_DIR="$(mktemp -d "$KAFKA_DIR/kafka-log.XXXXXX")"

SERVER_PROPERTIES="$KAFKA_DIR/server.properties"

cat > "$SERVER_PROPERTIES" << _SERVER_PROPERTIES
process.roles=broker,controller
node.id=1
controller.quorum.voters=1@127.0.0.1:9093
listeners=BROKER://:9092,CONTROLLER://:9093
inter.broker.listener.name=BROKER
advertised.listeners=BROKER://localhost:9092
controller.listener.names=CONTROLLER
listener.security.protocol.map=BROKER:PLAINTEXT,CONTROLLER:PLAINTEXT
log.dirs=$LOG_DIR
offsets.topic.replication.factor=1
offsets.topic.num.partitions=1
transaction.state.log.num.partitions=1
auto.create.topics.enable=false
_SERVER_PROPERTIES

"$KAFKA_DIR/$KAFKA_PREFIX/bin/kafka-storage.sh" format -t RekDY7vOQa6A40NvhNA4Ag \
  -c "$SERVER_PROPERTIES"

"$KAFKA_DIR/$KAFKA_PREFIX/bin/kafka-server-start.sh" "$SERVER_PROPERTIES"

rm -Rf "$LOG_DIR"
