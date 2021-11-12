package com.fillmore_labs.kafka.sensors.serde.confluent.reflect.serialization;

import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.InstantNanosHelper;
import com.fillmore_labs.kafka.sensors.serde.avro.logicaltypes.TimestampNanosConversion;
import java.io.IOException;
import java.time.Instant;
import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.reflect.CustomEncoding;

/**
 * This encoder/decoder writes a java.time.Instant object as a long to avro and reads an Instant
 * object from long. The long stores the number of nanoseconds since January 1, 1970, 00:00:00 GMT
 * represented by the Instant object.
 */
public final class InstantAsLongEncoding extends CustomEncoding<Instant> {
  public InstantAsLongEncoding() {
    super();
    super.schema =
        TimestampNanosConversion.timestampNanos().addToSchema(Schema.create(Schema.Type.LONG));
  }

  @Override
  protected void write(Object datum, Encoder out) throws IOException {
    var value = InstantNanosHelper.instant2Nanos((Instant) datum);
    out.writeLong(value);
  }

  @Override
  protected Instant read(Object reuse, Decoder in) throws IOException {
    var value = in.readLong();
    return InstantNanosHelper.nanos2Instant(value);
  }
}
