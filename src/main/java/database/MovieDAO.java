package database;

import models.Movie;
import models.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private Connection connection;

    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    public void addMovie(Movie movie) throws SQLException {
        String query = "INSERT INTO movies (idMovie, title, genre, duration, director) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, movie.getIdMovie());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getGenre().getName()); // Assuming Genre is a related entity
            stmt.setInt(4, movie.getDuration());
            stmt.setString(5, movie.getDirector());
            stmt.executeUpdate();
        }
    }

    public Movie getMovieById(int id) throws SQLException {
        String query = "SELECT * FROM movies WHERE idMovie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Movie(rs.getInt("idMovie"), rs.getString("title"),
                        new Genre(rs.getInt("idGenre"), rs.getString("genre")), rs.getInt("duration"),
                        rs.getString("director"));
            }
        }
        return null;
    }

    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                movies.add(new Movie(rs.getInt("idMovie"), rs.getString("title"),
                        new Genre(rs.getInt("idGenre"), rs.getString("genre")), rs.getInt("duration"),
                        rs.getString("director")));
            }
        }
        return movies;
    }

    public void updateMovie(Movie movie) throws SQLException {
        String query = "UPDATE movies SET title = ?, genre = ?, duration = ?, director = ? WHERE idMovie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre().getName());
            stmt.setInt(3, movie.getDuration());
            stmt.setString(4, movie.getDirector());
            stmt.setInt(5, movie.getIdMovie());
            stmt.executeUpdate();
        }
    }

    public void deleteMovie(int id) throws SQLException {
        String query = "DELETE FROM movies WHERE idMovie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
