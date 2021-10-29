package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.DurationNanosConversion;
import java.io.IOException;
import java.time.Duration;
import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.reflect.CustomEncoding;

/**
 * This encoder/decoder writes a java.time.Duration object as a long to avro and reads a Duration
 * object from long. The long stores the number of microseconds represented by the Duration object.
 */
public final class DurationAsLongEncoding extends CustomEncoding<Duration> {
  public DurationAsLongEncoding() {
    super();
    super.schema =
        DurationNanosConversion.durationNanos().addToSchema(Schema.create(Schema.Type.LONG));
  }

  @Override
  protected void write(Object datum, Encoder out) throws IOException {
    var value = ((Duration) datum).toNanos();
    out.writeLong(value);
  }

  @Override
  protected Duration read(Object reuse, Decoder in) throws IOException {
    var value = in.readLong();
    return Duration.ofNanos(value);
  }
}
