package com.ltbanking.user.service;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ltbanking.user.model.UserBankingEntity;
import com.ltbanking.user.repository.UserBankingRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(value = "test")
public class UserServiceTest {

  @LocalServerPort private Integer port;

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
    RestAssured.baseURI = "http://localhost:8005";
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

    String bodyPayload =
        "{\n"
            + "    \"identificationNumber\": \"1231321234\",\n"
            + "    \"oldPassword\": \"pwd123\",\n"
            + "    \"newPassword\": \"Abc.12345\"\n"
            + "}";
    given()
        .contentType(ContentType.JSON)
        .body(bodyPayload)
        .when()
        .put("http://localhost:8005/user/update-password")
        .then()
        .statusCode(200);
  }
}
