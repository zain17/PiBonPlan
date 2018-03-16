/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;


/**
 *
 * @author ASUS
 */

public class Catalogue  {
    private Integer id;
    private String nom;
    private String description;
    private Short enService;
    private Produit produit;
    private Etablissement etablissementId;

    public Catalogue(Integer id, String nom, String description, Short enService, Produit produit, Etablissement etablissementId) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.enService = enService;
        this.produit = produit;
        this.etablissementId = etablissementId;
    }

    public Catalogue(String nom, String description, Short enService, Produit produit, Etablissement etablissementId) {
        this.nom = nom;
        this.description = description;
        this.enService = enService;
        this.produit = produit;
        this.etablissementId = etablissementId;
    }
    
    
    public Catalogue() {
    }

    public Catalogue(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getEnService() {
        return enService;
    }

    public void setEnService(Short enService) {
        this.enService = enService;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Etablissement getEtablissementId() {
        return etablissementId;
    }

    public void setEtablissementId(Etablissement etablissementId) {
        this.etablissementId = etablissementId;
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
        if (!(object instanceof Catalogue)) {
            return false;
        }
        Catalogue other = (Catalogue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bonplan.entities.Catalogue[ id=" + id + " ]";
    }
    
}
