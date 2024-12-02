package database;

import models.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {
    private Connection connection;

    public ActorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addActor(Actor actor) throws SQLException {
        String query = "INSERT INTO actors (idActor, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, actor.getIdActor());
            stmt.setString(2, actor.getName());
            stmt.executeUpdate();
        }
    }

    public Actor getActorById(int id) throws SQLException {
        String query = "SELECT * FROM actors WHERE idActor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Actor(rs.getInt("idActor"), rs.getString("name"));
            }
        }
        return null;
    }

    public List<Actor> getAllActors() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM actors";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                actors.add(new Actor(rs.getInt("idActor"), rs.getString("name")));
            }
        }
        return actors;
    }

    public void updateActor(Actor actor) throws SQLException {
        String query = "UPDATE actors SET name = ? WHERE idActor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, actor.getName());
            stmt.setInt(2, actor.getIdActor());
            stmt.executeUpdate();
        }
    }

    public void deleteActor(int id) throws SQLException {
        String query = "DELETE FROM actors WHERE idActor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
