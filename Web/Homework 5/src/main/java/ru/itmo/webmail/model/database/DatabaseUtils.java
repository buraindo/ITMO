package ru.itmo.webmail.model.database;

import javafx.util.Pair;
import org.mariadb.jdbc.MariaDbDataSource;
import ru.itmo.webmail.model.exception.RepositoryException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseUtils {

    public enum QueryType {
        FIND, INSERT
    }

    public static DataSource getDataSource() {
        return DataSourceHolder.INSTANCE;
    }

    public static ResultSet process(DataSource source, String query, String errorMessage, QueryType type, String... params) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    statement.setString(i + 1, params[i]);
                }
                switch (type) {
                    case FIND:
                        return statement.executeQuery();
                    case INSERT:
                        statement.executeUpdate();
                        return null;
                    default:
                        return null;
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(errorMessage, e);
        }
    }

    public static Pair<ResultSet, ResultSetMetaData> process(DataSource source, String query, String errorMessage, String... params) {
        try (Connection connection = source.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    statement.setString(i + 1, params[i]);
                }
                return new Pair<>(statement.executeQuery(), statement.getMetaData());
            }
        } catch (SQLException e) {
            throw new RepositoryException(errorMessage, e);
        }
    }

    private static final class DataSourceHolder {
        private static final DataSource INSTANCE;
        private static final Properties PROPERTIES = new Properties();

        static {
            try {
                PROPERTIES.load(DataSourceHolder.class.getResourceAsStream("/application.properties"));
            } catch (IOException e) {
                throw new RuntimeException("Can't load application.properties.", e);
            }

            try {
                MariaDbDataSource dataSource = new MariaDbDataSource();
                dataSource.setUrl(PROPERTIES.getProperty("database.url"));
                dataSource.setUser(PROPERTIES.getProperty("database.user"));
                dataSource.setPassword(PROPERTIES.getProperty("database.password"));
                INSTANCE = dataSource;
            } catch (SQLException e) {
                throw new RuntimeException("Can't initialize DB.", e);
            }

            try (Connection connection = INSTANCE.getConnection()) {
                if (connection == null) {
                    throw new RuntimeException("Can't get testing connection from DB.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Can't get testing connection from DB.", e);
            }
        }
    }
}
