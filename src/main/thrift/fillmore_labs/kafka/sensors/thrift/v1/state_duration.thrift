namespace java com.fillmore_labs.kafka.sensors.thrift.v1

include "fillmore_labs/kafka/sensors/thrift/v1/sensor_state.thrift"

typedef sensor_state.Reading Reading

// Duration a sensor was in this position.
struct StateDuration {
  1: required string id
  2: required Reading reading
  3: required i64 duration
}
