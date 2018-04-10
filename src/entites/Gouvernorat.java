package entites;

import java.util.ArrayList;

public class Gouvernorat {
    private int id;
    private String name;
    private ArrayList<Ville> villes;
    public Gouvernorat() {

    }

    public Gouvernorat(int id) {
        this.id = id;
    }

    public Gouvernorat(int id, String name, ArrayList<Ville> villes) {
        this.id = id;
        this.name = name;
        this.villes = villes;
    }

    public ArrayList<Ville> getVilles() {
        return villes;
    }

    public void setVilles(ArrayList<Ville> villes) {
        this.villes = villes;
    }

    public Gouvernorat(int id, String name) {
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

    @Override
    public String toString() {
        return "Gouvernorat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", villes=" + villes +
                '}';
    }
}
