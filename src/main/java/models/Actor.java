package models;

public class Actor {
    private int idActor;
    private String name;

    public Actor(int idActor, String name) {
        this.idActor = idActor;
        this.name = name;
    }

    public int getIdActor() {
        return idActor;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "idActor=" + idActor +
                ", name='" + name + '\'' +
                '}';
    }
}
