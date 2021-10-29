namespace java com.fillmore_labs.kafka.sensors.thrift.v1

include "fillmore_labs/kafka/sensors/thrift/v1/sensor_state.thrift"

typedef sensor_state.Event Event

// Duration a sensor was in this position.
struct StateDuration {
  1: required string id
  2: required Event event
  3: required i64 duration
}
