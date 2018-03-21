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

public class Commentaire   {
    private Integer id;
    //Champs ajouter :: il faut ajouter une column dans la base des données aussi
    private String texte;
    private Revue revueId;

    public Commentaire(String texte, Revue revueId) {
        this.texte = texte;
        this.revueId = revueId;
    }

    public Commentaire(Integer id, String texte, Revue revueId) {
        this.id = id;
        this.texte = texte;
        this.revueId = revueId;
    }
    
    
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Commentaire() {
    }

    public Commentaire(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Revue getRevueId() {
        return revueId;
    }

    public void setRevueId(Revue revueId) {
        this.revueId = revueId;
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
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bonplan.entities.Commentaire[ id=" + id + " ]";
    }
    
}
