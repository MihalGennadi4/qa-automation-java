import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class ApiDemoTest {
    @DisplayName("Авторизация")
    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @DisplayName("Включаем логи")
    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @DisplayName("Поиск одной существующей записи")
    @Test
    public void shouldGetLocationsWhenPopulatedDb() {
        when()
                .get("/api/countries/5")
                .then()
                .statusCode(200)
                .body(
                        "id", is(5),
                        "countryName",is("Ac"),
                        "locations", not(empty())
                );
    }
    @DisplayName("Поиск всех существующих записей")
    @Test
    public void shouldGetAllLocationsWhenPopulatedDb() {
        when()
                .get("/api/countries")
                .then()
                .statusCode(200)
                .body(
                        "size()", is(11),
                        "[5].countryName", is("Pl"),
                        "[5].id", is(6),
                        "[5].locations", not(empty())

                );
    }

    @DisplayName("Создание новой записи")
    @Test
    public void shouldCreateCountryWhenUnique() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"countryName\": \"pp\"\n" +
                        "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(201)
                .body("id", not(empty()));
    }

    @DisplayName("Дублирущая запись не создается")
    @Test
    public void shouldCreateCountryWhenNotUnique() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"countryName\": \"pp\"\n" +
                        "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(500)
                .body("countryName", not(empty()));
    }
    @DisplayName("Удаление известной страны")
    @Test
    public void shouldDeleteCountryWhenCountryAlreadyExist(){
        int id =  (int) Math.round(Math.random() * 10 + 1);
        when()
                .delete("/api/countries/{id}", id )
                .then()
                .statusCode(204)
                .header("x-appapp-params", not(empty()));


    }
    @DisplayName("Обновление CountryName через put")
    @Test
    public void shouldPutCountryNameWhenCountryAlreadyExist() {
        int id =  (int) Math.round(Math.random() * 10 + 1);
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": "+id+ ",\n \"countryName\": \"Qq\"\n" +
                        "}" )
                .when()
                .put("/api/countries/{id}", id)
                .then()
                .statusCode(200)
                .body("countryName", not(empty()));
    }

    @DisplayName("Обновление CountryName через path")
    @Test
    public void shouldPathCountryWhenCountryAlreadyExist() {
        int id =  (int) Math.round(Math.random() * 10 + 1);
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": "+id+ ",\n \"countryName\": \"Qq\"\n" +
                        "}" )
                .when()
                .patch("/api/countries/{id}", id)
                .then()
                .statusCode(200)
                .body("countryName", not(empty()));
    }
    @DisplayName("Счетчик считает")
    @Test
    public void shouldCounterWhenCountryArrayNotNull() {
        when()
                .get("/api/countries/count")
                .then()
                .statusCode(200)
                .body(is("10"));
    }

}