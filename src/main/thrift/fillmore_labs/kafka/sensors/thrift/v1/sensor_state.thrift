namespace java com.fillmore_labs.kafka.sensors.thrift.v1

// Position of a sensor.
enum Position {
  OFF
  ON
}

// Measurement.
struct Reading {
  1: required i64 time
  2: required Position position
}

// State of a sensor.
struct SensorState {
  1: required string id
  2: required Reading reading
}
