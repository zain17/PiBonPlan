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

public class Revue   {


    private Integer id;


    private Utilisateur utilisateurId;


    private Etablissement etablissementId;

    private Commentaire commentaire;

    public Revue() {
    }

    public Revue(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Utilisateur getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Utilisateur utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Etablissement getEtablissementId() {
        return etablissementId;
    }

    public void setEtablissementId(Etablissement etablissementId) {
        this.etablissementId = etablissementId;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
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
        if (!(object instanceof Revue)) {
            return false;
        }
        Revue other = (Revue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bonplan.entities.Revue[ id=" + id + " ]";
    }
    
}
