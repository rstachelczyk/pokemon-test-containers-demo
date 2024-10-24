package com.demo.pokemon.mapper;

import com.demo.pokemon.dto.PokemonDto;
import com.demo.pokemon.entity.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface PokemonMapper {

  PokemonDto toDto(Pokemon pokemon);
}
