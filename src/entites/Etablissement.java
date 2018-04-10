/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author ASUS
 */

public class Etablissement   {

    private Integer id;
    private String nom;
    private String adresse;
    private String gouvernorat;
    private String ville;
    private Double note;
    private Date horraire;
    private Double longitude;
    private Double latitude;
    private Boolean estActive;
    private String type;    
    private String description;
    private String photo;
    private Date horraire_f;
    private ArrayList<Revue> revues;
    private ArrayList<Experience> experiences;
    private ArrayList<Catalogue> catalogues;

    public Etablissement() {
    }

    public Etablissement(String nom, String adresse, String gouvernorat, String ville, Double note, Date horraire, Double longitude, Double latitude, Boolean estActive, String type, String description, String photo) {
        this.nom = nom;
        this.adresse = adresse;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.note = note;
        this.horraire = horraire;
        this.longitude = longitude;
        this.latitude = latitude;
        this.estActive = estActive;
        this.type = type;
        this.description = description;
        this.photo = photo;
    }
    
    public Etablissement(Integer id, String nom, String adresse, String gouvernorat, String ville, Double note, Date horraire, Double longitude, Double latitude, Boolean estActive, String type, String description, String photo,Date horrairef) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.note = note;
        this.horraire = horraire;
        this.longitude = longitude;
        this.latitude = latitude;
        this.estActive = estActive;
        this.type = type;
        this.description = description;
        this.photo = photo;
        this.horraire_f=horrairef;
    }
    
    public Etablissement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Date getHorraire() {
        return horraire;
    }

    public void setHorraire(Date horraire) {
        this.horraire = horraire;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getEstActive() {
        return estActive;
    }

    public void setEstActive(Boolean estActive) {
        this.estActive = estActive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getHorraire_f() {
        return horraire_f;
    }

    public void setHorraire_f(Date horraire_f) {
        this.horraire_f = horraire_f;
    }

    public ArrayList<Revue> getRevues() {
        return revues;
    }

    public void setRevues(ArrayList<Revue> revues) {
        this.revues = revues;
    }

    public ArrayList<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(ArrayList<Experience> experiences) {
        this.experiences = experiences;
    }

    public ArrayList<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(ArrayList<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etablissement)) {
            return false;
        }
        Etablissement other = (Etablissement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Etablissement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", gouvernorat='" + gouvernorat + '\'' +
                ", ville='" + ville + '\'' +
                ", note=" + note +
                ", horraire=" + horraire +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", estActive=" + estActive +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", horraire_f=" + horraire_f +
                ", revues=" + revues +
                ", experiences=" + experiences +
                ", catalogues=" + catalogues +
                '}';
    }
}
