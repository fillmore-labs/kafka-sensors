#!/bin/sh

AVRO_VERSION="1.11.1"
LIB_DIR=".lib"

AVRO_TOOLS="avro-tools-$AVRO_VERSION.jar"
AVRO_URL="https://dlcdn.apache.org/avro/avro-$AVRO_VERSION/java/$AVRO_TOOLS"

[ -d "$LIB_DIR" ] || mkdir "$LIB_DIR"

[ -f "$LIB_DIR/$AVRO_TOOLS" ] || curl -Lo "$LIB_DIR/$AVRO_TOOLS" "$AVRO_URL"

shasum -a 256 -c - << _SHASUM || exit 1
b954e75976c24b72509075b1a298b184db9efe2873bee909d023432f9826db88 *$LIB_DIR/$AVRO_TOOLS
_SHASUM

java -jar "$LIB_DIR/$AVRO_TOOLS" "$@"
