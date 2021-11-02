package com.fillmore_labs.kafka.sensors.serde.ion.mapper;

import com.fillmore_labs.kafka.sensors.model.StateDuration;
import com.fillmore_labs.kafka.sensors.serde.ion.serialization.StateDurationIon;
import com.fillmore_labs.kafka.sensors.serde.mapping.MapStructConfig;
import com.fillmore_labs.kafka.sensors.serde.mapping.TimeNanoMapper;
import com.fillmore_labs.kafka.sensors.serde.serializer.mapped.BiMapper;
import com.google.errorprone.annotations.Immutable;
import org.mapstruct.Mapper;

@Immutable
@Mapper(
    config = MapStructConfig.class,
    uses = {ReadingMapper.class, TimeNanoMapper.class})
/* package */ abstract class StateDurationMapper
    implements BiMapper<StateDuration, StateDurationIon> {}
