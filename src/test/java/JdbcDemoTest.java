import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JdbcDemoTest {
    private static Connection connection;

    @BeforeAll
    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/app-db",
                "app-db-admin",
                "P@ssw0rd"
        );
    }

    @AfterAll
    public static void disconnect() throws SQLException {
        connection.close();
    }




    @Test
    public void shouldGetExistCountriesWhenPopulatedDb() throws SQLException {
        Collection<String> countryNames = new ArrayList<>();

        Statement sql = connection.createStatement();
        ResultSet resultSet = sql.executeQuery("SELECT * FROM country");
        while (resultSet.next()) {
            countryNames.add(resultSet.getString(2));
        }

        assertThat(countryNames, hasItems("vo", "vi"));
    }

    @Test
    public void shouldSaveCountryWhenUnique() throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "INSERT INTO country(id, country_name) VALUES(?,?)"
        );
        sql.setInt(1, 101);
        sql.setString(2, "11");
        sql.executeUpdate();
    }

    /**
     *  id bigint generated always as identity primary key
     */
    @Test
    public void shouldSaveWhenAutogeneratedKey() throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "INSERT INTO country(country_name) VALUES(?)",
                Statement.RETURN_GENERATED_KEYS
        );
        sql.setString(1, UUID.randomUUID().toString().substring(0,2));
        sql.executeUpdate();

        ResultSet keys = sql.getGeneratedKeys();
        assertThat(keys.next(), is(true));
        assertThat(keys.getLong(1), greaterThanOrEqualTo(1L));
    }
}