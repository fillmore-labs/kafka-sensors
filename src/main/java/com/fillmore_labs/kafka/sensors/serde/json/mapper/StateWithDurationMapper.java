package com.fillmore_labs.kafka.sensors.serde.json.mapper;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.json.serialization.StateWithDurationJson;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.mapstruct.Mapper;

@Immutable
@Mapper(config = MapStructConfig.class, uses = ReadingMapper.class)
public abstract class StateWithDurationMapper
    implements BiMapper<StateDuration, StateWithDurationJson> {}
