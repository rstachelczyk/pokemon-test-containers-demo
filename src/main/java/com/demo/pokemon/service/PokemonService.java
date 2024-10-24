package com.demo.pokemon.service;

import com.demo.pokemon.dto.PokemonDto;
import com.demo.pokemon.entity.Pokemon;
import com.demo.pokemon.mapper.PokemonMapper;
import com.demo.pokemon.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

  private final PokemonRepository repository;
  private final PokemonMapper mapper;

  public PokemonService(final PokemonRepository repository, final PokemonMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public List<PokemonDto> findAllPokemon() {
    List<Pokemon> pokemonList = this.repository.findAll();

    return pokemonList.stream()
        .map(mapper::toDto)
        .toList();
  }
}
