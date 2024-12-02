package database;

import models.Seance;
import models.Movie;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class SeanceDAO {
    private final Connection connection;

    public SeanceDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new seance to the database
    public void addSeance(Seance seance) throws SQLException {
        String query = "INSERT INTO seances (idMovie, time, date, room, totalSeats, availableSeats) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, seance.getMovie().getIdMovie());
            stmt.setTime(2, Time.valueOf(seance.getHoraire()));
            stmt.setDate(3, Date.valueOf(seance.getDate()));
            stmt.setString(4, seance.getSalle());
            stmt.setInt(5, seance.getTotalSeats());
            stmt.setInt(6, seance.getAvailableSeats());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    seance.setIdSeance(rs.getInt(1));
                }
            }
        }

        addReservedSeats(seance.getIdSeance(), seance.getReservedSeats());
    }

    // Retrieve a seance by its ID
    public Seance getSeanceById(int id) throws SQLException {
        String query = "SELECT * FROM seances WHERE idSeance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Movie movie = new MovieDAO(connection).getMovieById(rs.getInt("idMovie"));
                    Seance seance = new Seance(
                            rs.getInt("idSeance"),
                            movie,
                            rs.getTime("time").toLocalTime(),
                            rs.getDate("date").toLocalDate(),
                            rs.getString("room"),
                            rs.getInt("totalSeats")
                    );
                    seance.setAvailableSeats(rs.getInt("availableSeats"));
                    seance.setReservedSeats(getReservedSeats(rs.getInt("idSeance")));
                    return seance;
                }
            }
        }
        return null;
    }

    // Retrieve all seances
    public List<Seance> getAllSeances() throws SQLException {
        List<Seance> seances = new ArrayList<>();
        String query = "SELECT * FROM seances";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Movie movie = new MovieDAO(connection).getMovieById(rs.getInt("idMovie"));
                Seance seance = new Seance(
                        rs.getInt("idSeance"),
                        movie,
                        rs.getTime("time").toLocalTime(),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("room"),
                        rs.getInt("totalSeats")
                );
                seance.setAvailableSeats(rs.getInt("availableSeats"));
                seance.setReservedSeats(getReservedSeats(rs.getInt("idSeance")));
                seances.add(seance);
            }
        }
        return seances;
    }

    // Update a seance
    public void updateSeance(Seance seance) throws SQLException {
        String query = "UPDATE seances SET idMovie = ?, time = ?, date = ?, room = ?, totalSeats = ?, availableSeats = ? WHERE idSeance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, seance.getMovie().getIdMovie());
            stmt.setTime(2, Time.valueOf(seance.getHoraire()));
            stmt.setDate(3, Date.valueOf(seance.getDate()));
            stmt.setString(4, seance.getSalle());
            stmt.setInt(5, seance.getTotalSeats());
            stmt.setInt(6, seance.getAvailableSeats());
            stmt.setInt(7, seance.getIdSeance());
            stmt.executeUpdate();
        }

        // Update reserved seats
        updateReservedSeats(seance.getIdSeance(), seance.getReservedSeats());
    }

    // Delete a seance
    public void deleteSeance(int id) throws SQLException {
        String deleteSeatsQuery = "DELETE FROM reserved_seats WHERE idSeance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteSeatsQuery)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        String deleteSeanceQuery = "DELETE FROM seances WHERE idSeance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteSeanceQuery)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Add reserved seats for a seance
    private void addReservedSeats(int idSeance, Set<Integer> reservedSeats) throws SQLException {
        String query = "INSERT INTO reserved_seats (idSeance, seatNumber) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int seat : reservedSeats) {
                stmt.setInt(1, idSeance);
                stmt.setInt(2, seat);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Retrieve reserved seats for a seance
    private Set<Integer> getReservedSeats(int idSeance) throws SQLException {
        Set<Integer> reservedSeats = new HashSet<>();
        String query = "SELECT seatNumber FROM reserved_seats WHERE idSeance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSeance);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservedSeats.add(rs.getInt("seatNumber"));
                }
            }
        }
        return reservedSeats;
    }

    // Update reserved seats for a seance
    private void updateReservedSeats(int idSeance, Set<Integer> reservedSeats) throws SQLException {
        deleteReservedSeats(idSeance);
        addReservedSeats(idSeance, reservedSeats);
    }

    // Delete all reserved seats for a seance
    private void deleteReservedSeats(int idSeance) throws SQLException {
        String query = "DELETE FROM reserved_seats WHERE idSeance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSeance);
            stmt.executeUpdate();
        }
    }
}
