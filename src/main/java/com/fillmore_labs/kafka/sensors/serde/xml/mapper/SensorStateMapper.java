package com.fillmore_labs.kafka.sensors.serde.xml.mapper;

import com.fillmore_labs.kafka.sensors.model.SensorState;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.SensorStateXml;
import com.google.errorprone.annotations.Immutable;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = ReadingMapper.class)
/* package */ abstract class SensorStateMapper implements BiMapper<SensorState, SensorStateXml> {}
