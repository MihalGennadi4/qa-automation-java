import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;


import java.sql.*;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class ApiDemoTest {


    private static Connection connection;
    private int idInCountryTable;
    private String preparedCountry;
    private String changedCountry;


    @DisplayName("Генерация рандомной страны")
    public static String generateNewString() {
        String newCountry = RandomStringUtils.randomAlphanumeric(2);
        System.out.println(newCountry);
        return newCountry;
    }


    @DisplayName("Авторизация")
    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @DisplayName("Создаем коннект к БД")
    @BeforeAll
    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/app-db",
                "app-db-admin",
                "P@ssw0rd"
        );
    }

    @DisplayName("Включаем логи")
    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @DisplayName("Создание записи в Country")
    @BeforeEach
    public void createDataInCountry() throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "INSERT INTO country(country_name) VALUES(?)",
                Statement.RETURN_GENERATED_KEYS
        );
        sql.setString(1, UUID.randomUUID().toString().substring(0, 2));
        sql.executeUpdate();
        ResultSet keys = sql.getGeneratedKeys();
        keys.next();
        idInCountryTable = keys.getInt(1);
        preparedCountry = keys.getString(2);
    }

    @DisplayName("Удаление тестовой записи")
    @AfterEach
    public void deleteTestData() throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "DELETE from country (id) VALUES(?)");
        sql.setInt(1, idInCountryTable);
        sql.executeUpdate();

    }


    @DisplayName("Поиск одной существующей записи")
    @Test
    public void shouldGetLocationsWhenPopulatedDb() throws SQLException {
        createDataInCountry();
        when()
                .get("/api/countries/{id}", idInCountryTable)
                .then()
                .statusCode(200)
                .body(
                        "id", is(idInCountryTable),
                        "countryName", is(preparedCountry),
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
                        "[5].countryName", is("Fr"),
                        "[5].id", is(8),
                        "[5].locations", not(empty())

                );
    }

    @DisplayName("Создание новой записи")
    @Test
    @Disabled
    public void shouldCreateCountryWhenUnique() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"countryName\": \"" + ApiDemoTest.generateNewString() + "\"\n" +
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
                        "  \"countryName\": \"" + preparedCountry + "\"\n" +
                        "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(500)
                .body("countryName", not(empty()));
    }

    @DisplayName("Удаление известной страны")
    @Test
    public void shouldDeleteCountryWhenCountryAlreadyExist() {
        when()
                .delete("/api/countries/{id}", idInCountryTable)
                .then()
                .statusCode(204)
                .header("x-appapp-params", not(empty()));


    }

    @DisplayName("Обновление CountryName через put")
    @Test
    public void shouldPutCountryNameWhenCountryAlreadyExist() {
        changedCountry = ApiDemoTest.generateNewString();
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": \"" + idInCountryTable + "\" , \n" +
                        "  \"countryName\": \"" + changedCountry + "\"\n" +
                        "}")
                .when()
                .put("/api/countries/{id}", idInCountryTable)
                .then()
                .statusCode(200)
                .body("countryName", is(changedCountry));
    }

    @DisplayName("Обновление CountryName через path")
    @Test
    public void shouldPathCountryWhenCountryAlreadyExist() {

        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": \"" + idInCountryTable + "\" , \n" +
                        "  \"countryName\": \"" + ApiDemoTest.generateNewString() + "\"\n" +
                        "}")
                .when()
                .patch("/api/countries/{id}", idInCountryTable)
                .then()
                .statusCode(200)
                .body("countryName", not(empty()));
    }


}