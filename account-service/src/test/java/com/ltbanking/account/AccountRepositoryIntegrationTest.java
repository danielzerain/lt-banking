package com.ltbanking.account;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.ltbanking.account.model.AccountBankingEntity;
import com.ltbanking.account.repository.AccountBankingRepository;
import com.ltbanking.account.utils.Utils;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
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
public class AccountRepositoryIntegrationTest {

  @Autowired private AccountBankingRepository accountBankingRepository;

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
  void testFindById() {
    AccountBankingEntity entity = new AccountBankingEntity();
    AccountBankingEntity entityPersist = new AccountBankingEntity();
    entity.setIdUserBanking(UUID.randomUUID());
    entity.setBalance(100.0);
    entity.setAccountNumber(Utils.generateCode(11));
    entity.setCreationDate(LocalDateTime.now());
    entityPersist = accountBankingRepository.save(entity);
    Optional<AccountBankingEntity> result =
        accountBankingRepository.findById(entityPersist.getId());
    assertTrue(result.isPresent());
  }
}
