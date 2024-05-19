package com.ltbanking.user.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.ltbanking.user.client.AccountClient;
import com.ltbanking.user.domain.AccountDto;
import com.ltbanking.user.domain.UserAccountDto;
import com.ltbanking.user.domain.UserDataPayload;
import com.ltbanking.user.domain.UserPayload;
import java.util.UUID;

import com.ltbanking.user.repository.UserBankingRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "test")
@SpringBootTest
public class UserServiceTest {

  @MockBean private AccountClient accountClient;

  @Autowired private UserService userService;

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
  public void when_userRegister_returnAccountData() throws Exception {
    UserDataPayload userDataPayload = new UserDataPayload(UUID.randomUUID());
    AccountDto accountDto = new AccountDto("12312312312", "1234545465654566", 1234);
    when(accountClient.register(userDataPayload)).thenReturn(accountDto);
    UserPayload userPayload =
        new UserPayload("User Complet Name", "CI", "4376706", "user.test", "1234");
    UserAccountDto userAccountDto = userService.createUserAccount(userPayload);
    assertNotNull(userAccountDto.getId());
  }
}
