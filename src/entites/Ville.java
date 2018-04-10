package entites;

public class Ville {
    private int id;
    private String name;
    private Gouvernorat gouvernorat;

    public Ville(int id, String name, Gouvernorat gouvernorat) {
        this.id = id;
        this.name = name;
        this.gouvernorat = gouvernorat;
    }

    public Ville(int id) {
        this.id = id;
    }

    public Ville(int anInt, String string) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gouvernorat getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(Gouvernorat gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
