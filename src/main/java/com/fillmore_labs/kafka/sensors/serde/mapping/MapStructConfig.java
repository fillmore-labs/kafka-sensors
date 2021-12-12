package com.fillmore_labs.kafka.sensors.serde.mapping;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = ComponentModel.JSR330,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    suppressTimestampInGenerated = true)
public interface MapStructConfig {}
