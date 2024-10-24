package com.demo.pokemon.controller;

import com.demo.pokemon.AbstractPostgresContainer;
import com.demo.pokemon.dto.PokemonDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = {"/sql/setup-pokemon.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/teardown-pokemon.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PokemonControllerIntegrationTest extends AbstractPostgresContainer {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  @DisplayName("""
      When retrieving all pokemon,
      then the response status should be OK and the list of pokemon should be returned""")
  void whenFindingAllPokemon_thenResponseStatusShouldBeOkAndPokemonReturned()
      throws JsonProcessingException {
    Response response = given()
        .header("Content-Type", "application/json")
        .and()
//        .header("Authorization", "Bearer " + JWT).and()
        .when()
        .get("/pokemon")
        .then()
        .extract()
        .response();
    // when then
    response.prettyPrint();
    List<PokemonDto> pokemonDtoList =
        objectMapper.readValue(response.getBody().asString(), new TypeReference<>() {});
    assertThat(response.statusCode())
        .isEqualTo(HttpStatus.OK.value());
    assertThat(pokemonDtoList.size()).isEqualTo(9);
    assertThat(pokemonDtoList.get(0))
        .usingRecursiveAssertion()
        .isNotNull();
  }
}
