package com.demo.pokemon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
    name = "Pokemon",
    indexes = {
        @Index(
            name = "idx_pokedex_num",
            columnList = "pokedex_number"
        ),
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "un_pokedex_num",
            columnNames = "pokedex_number"
        )
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "species", nullable = false)
  private String species;

  @Column(name = "pokedex_number", nullable = false)
  private int pokedexNumber;

  @Column(name = "height", precision = 4, scale = 2)
  private BigDecimal height;

  @Column(name = "weight", precision = 5, scale = 2)
  private BigDecimal weight;
}
