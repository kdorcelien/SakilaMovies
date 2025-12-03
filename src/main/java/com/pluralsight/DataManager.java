package com.pluralsight;

import com.pluralsight.models.Actor;
import com.pluralsight.models.Film;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    private Connection connection;

    public DataManager(String database, String username, String password) {
        loadConnection(database, username, password);
    }

    private void loadConnection(String database, String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(URL + database);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error when loading connection. Exiting application.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<Actor> getActor(String lastname) {
        String query = "SELECT * FROM actor WHERE last_name LIKE ? ";
        List<Actor> actors = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + lastname + "%");
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    Actor actor = new Actor(
                            results.getInt("actor_id"),
                            results.getString("first_name"),
                            results.getString("last_name")
                    );
                    actors.add(actor);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actors;
    }

    public List<Film> getFilmByActor(int id) throws SQLException {
        String query = "SELECT * FROM film f " +
                "JOIN film_actor fa ON (fa.film_id = f.film_id)" +
                " JOIN actor a ON (a.actor_id = fa.actor_id)" +
                " WHERE a.actor_id = ? ;";
        List<Film> movies = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (ResultSet results = statement.executeQuery()) {

                while (results.next()) {
                    Film movie = new Film(
                            results.getInt("film_id"),
                            results.getString("title"),
                            results.getString("description"),
                            results.getInt("release_year"),
                            results.getInt("length")
                    );
                    movies.add(movie);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
