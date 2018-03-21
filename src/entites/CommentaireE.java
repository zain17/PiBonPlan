/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;


/**
 *
 * @author Sboula
 */

public class CommentaireE   {

    private Integer id;    
    private Date date;    
    private String contenu;   
    private Evenements eveId;

    public CommentaireE() {
    }

    public CommentaireE(Integer id) {
        this.id = id;
    }

    public CommentaireE(Integer id, Date date, String contenu) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Evenements getEveId() {
        return eveId;
    }

    public void setEveId(Evenements eveId) {
        this.eveId = eveId;
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
        if (!(object instanceof CommentaireE)) {
            return false;
        }
        CommentaireE other = (CommentaireE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.CommentaireE[ id=" + id + " ]";
    }
    
}
