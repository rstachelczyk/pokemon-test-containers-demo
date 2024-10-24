package com.demo.pokemon.controller;

import com.demo.pokemon.dto.PokemonDto;
import com.demo.pokemon.service.PokemonService;
import com.demo.pokemon.views.PokemonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pokemon", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class PokemonController {

  private final PokemonService service;

  public PokemonController(final PokemonService service) {
    this.service = service;
  }

  @GetMapping
  @JsonView(PokemonView.Read.class)
  public ResponseEntity<List<PokemonDto>> findAllPokemon() {
    log.info("Received request to retrieve all pokemon");
    return ResponseEntity.ok(service.findAllPokemon());
  }
}
