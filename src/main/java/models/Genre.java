package models;

public class Genre {
    private int idGenre;
    private String name;

    // Constructor
    public Genre(int idGenre, String name) {
        this.idGenre = idGenre;
        this.name = name;
    }

    // Getters and setters
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
