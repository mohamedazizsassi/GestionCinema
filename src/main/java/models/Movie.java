package models;

import java.util.HashSet;
import java.util.Set;

public class Movie {
    private int idMovie;
    private String title;
    private Genre genre;
    private int duration; // in minutes
    private String director;
    private Set<String> actors; // Using Set to ensure unique actor names

    public Movie(int idMovie, String title, Genre genre, int duration, String director) {
        this.idMovie = idMovie;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.actors = new HashSet<>();
    }

    // Getters and Setters
    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void addActor(String actor) {
        this.actors.add(actor);
    }

    public void removeActor(String actor) {
        this.actors.remove(actor);
    }
}
