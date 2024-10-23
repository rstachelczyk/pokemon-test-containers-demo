package com.demo.pokemon;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

/**
 * <p>
 * {@code AbstractPostgreSqlRepository} defines an Abstract Jpa Test Class Template required by the
 * JPA Test Cases.
 * </p>
 */
public abstract class AbstractPostgresContainer {

  @Container
  public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

  @DynamicPropertySource
  public static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @BeforeAll
  static void beforeAll() {
    postgres.withDatabaseName("pokemon");
    postgres.withInitScript("init.sql");
    postgres.start();
  }
}
