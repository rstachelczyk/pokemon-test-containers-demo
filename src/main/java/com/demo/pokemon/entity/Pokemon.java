package com.demo.pokemon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  private int id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "SPECIES", nullable = false)
  private String species;

  @Column(name = "POKEDEX_NUMBER", nullable = false)
  private int pokedexNumber;

  @Column(name = "HEIGHT")
  private Double height;

  @Column(name = "WEIGHT")
  private Double weight;
}
