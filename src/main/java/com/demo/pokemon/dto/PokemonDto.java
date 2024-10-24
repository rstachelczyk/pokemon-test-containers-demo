package com.demo.pokemon.dto;

import com.demo.pokemon.views.PokemonView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PokemonDto(

    @JsonView(PokemonView.Read.class)
    String name,

    @JsonView(PokemonView.Read.class)
    String species,

    @JsonView(PokemonView.Read.class)
    int pokedexNumber,

    @JsonView(PokemonView.Read.class)
    BigDecimal height,

    @JsonView(PokemonView.Read.class)
    BigDecimal weight

) implements Serializable { }
