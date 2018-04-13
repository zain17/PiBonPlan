/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Collection;
import java.util.Date;
import security.Authenticator;


/**
 *
 * @author Sboula
 */

public class Evenements   { 
    
    private Integer id;   
    private String nom;  
    private String description;   
    private Date date;   
    private int nbPlace; 
    private String lieu;   
    private int prix;
    private String brochure;   
    private Date dateF;  
    private String adresse;    
    private String tel;
    private Integer rating;
    private String type ;
    private Utilisateur u;

    public void setU(Utilisateur u) {
        this.u = u;
    }

    public Utilisateur getU() {
        return u;
    }

    public Evenements(Utilisateur u) {
        this.u = u;
    }
    private Collection<CommentaireE> commentaireECollection;

    public Evenements() {
        this.u=Authenticator.getCurrentAuth(); 
    }

    public Evenements(Integer id) {
        this.id = id;
    }

    public Evenements(Integer id, String nom, String description, Date date, int nbPlace, String lieu, int prix, String brochure, Date dateF, String adresse, String tel,String type , Utilisateur U) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.nbPlace = nbPlace;
        this.lieu = lieu;
        this.prix = prix;
        this.brochure = brochure;
        this.dateF = dateF;
        this.adresse = adresse;
        this.tel = tel;
        this.type=type ;
        this.u=new Utilisateur();
        
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getType(){
        return type ;
       
    }
    
    public void setType(String type){
        this.type=type ;
    }

    public Collection<CommentaireE> getCommentaireECollection() {
        return commentaireECollection;
    }

    public void setCommentaireECollection(Collection<CommentaireE> commentaireECollection) {
        this.commentaireECollection = commentaireECollection;
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
        if (!(object instanceof Evenements)) {
            return false;
        }
        Evenements other = (Evenements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Evenements_1[ id=" + id + " ]";
    }
    
}
