package br.com.palerique;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PiadaResourceTest {

  @Test
  void testHelloEndpoint() {
    given()
        .when()
        .get("/piadas")
        .then()
        .statusCode(200)
        .body(is("Piada do dia: O que o pato disse para a pata? R: Vem Quá!"));
  }
}
