/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Reservation {
    private Integer id;
    private Integer nbPlace;
    private Double prix;
    
    public Reservation(Integer id, Integer nbPlace, Double prix) {
        this.id = id;
        this.nbPlace = nbPlace;
        this.prix = prix;
    }

    public Reservation(Integer nbPlace, Double prix) {
        this.nbPlace = nbPlace;
        this.prix = prix;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "nbPlace=" + nbPlace + ", prix=" + prix + '}';
    }
    
    
}
