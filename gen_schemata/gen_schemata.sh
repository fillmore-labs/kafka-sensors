#!/bin/sh

SCRIPT_DIR="$(dirname "$0")"
cd "$SCRIPT_DIR" || exit

./avro-tools.sh idl2schemata ./state_duration.avdl ../src/main/avro/com/fillmore_labs/kafka/sensors/avro

echo "Remove duplicate definitions."
