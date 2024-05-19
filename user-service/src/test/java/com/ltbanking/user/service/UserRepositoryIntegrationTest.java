package com.ltbanking.user.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.ltbanking.user.model.UserBankingEntity;
import com.ltbanking.user.repository.UserBankingRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = NONE)
class UserRepositoryIntegrationTest {

  @Autowired private UserBankingRepository userBankingRepository;

  static MySQLContainer<?> mysql =
      new MySQLContainer<>("mysql:latest")
          .withDatabaseName("ltbanking")
          .withUsername("userapp")
          .withPassword("1serBkg24");

  @BeforeAll
  static void beforeAll() {
    mysql.start();
  }

  @AfterAll
  static void afterAll() {
    mysql.stop();
  }

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mysql::getJdbcUrl);
    registry.add("spring.datasource.username", mysql::getUsername);
    registry.add("spring.datasource.password", mysql::getPassword);
  }

  @Test
  public void testFindById() {
    // Inserta datos de prueba
    UserBankingEntity entity = new UserBankingEntity();
    UserBankingEntity entityPersist = new UserBankingEntity();
    entity.setUserName("user.test");
    entity.setPassword("pwd123");
    entity.setCompleteName("Usuario de Prueba");
    entity.setIdType("CI");
    entity.setIdentificationNumber("1231321234");
    entityPersist = userBankingRepository.save(entity);
    // Prueba la consulta
    Optional<UserBankingEntity> result = userBankingRepository.findById(entityPersist.getId());
    assertTrue(result.isPresent());
  }
}
