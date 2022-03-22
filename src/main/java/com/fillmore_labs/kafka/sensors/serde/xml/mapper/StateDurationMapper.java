package com.fillmore_labs.kafka.sensors.serde.xml.mapper;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.fillmore_labs.kafka.sensors.serde.xml.serialization.StateDurationXml;
import com.google.errorprone.annotations.Immutable;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = ReadingMapper.class)
/* package */ abstract class StateDurationMapper
    implements BiMapper<StateDuration, StateDurationXml> {}
