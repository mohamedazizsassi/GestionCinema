package database;

import models.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private Connection connection;

    public GenreDAO(Connection connection) {
        this.connection = connection;
    }

    public void addGenre(Genre genre) throws SQLException {
        String query = "INSERT INTO genres (idGenre, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, genre.getIdGenre());
            stmt.setString(2, genre.getName());
            stmt.executeUpdate();
        }
    }

    public Genre getGenreById(int id) throws SQLException {
        String query = "SELECT * FROM genres WHERE idGenre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Genre(rs.getInt("idGenre"), rs.getString("name"));
            }
        }
        return null;
    }

    public List<Genre> getAllGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM genres";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                genres.add(new Genre(rs.getInt("idGenre"), rs.getString("name")));
            }
        }
        return genres;
    }

    public void updateGenre(Genre genre) throws SQLException {
        String query = "UPDATE genres SET name = ? WHERE idGenre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, genre.getName());
            stmt.setInt(2, genre.getIdGenre());
            stmt.executeUpdate();
        }
    }

    public void deleteGenre(int id) throws SQLException {
        String query = "DELETE FROM genres WHERE idGenre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
