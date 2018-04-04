/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author aminos
 */

public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String texte;

	private int vote;

	private String titre;

	private int auteur;

	private String auteurn;

	private Date updated;

	private Date created;

	private Collection<Tag> tagCollection;
	private Collection<CommentaireB> commentaireBCollection;

	public Article() {
	}

	public Article(Integer id) {
		this.id = id;
	}

	public Article(String texte, int vote, String titre, int auteur, String auteurn, Date updated, Date created) {
		
		this.texte = texte;
		this.vote = vote;
		this.titre = titre;
		this.auteur = auteur;
		this.auteurn = auteurn;
		this.updated = updated;
		this.created = created;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getAuteur() {
		return auteur;
	}

	public void setAuteur(int auteur) {
		this.auteur = auteur;
	}

	public String getAuteurn() {
		return auteurn;
	}

	public void setAuteurn(String auteurn) {
		this.auteurn = auteurn;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Collection<Tag> getTagCollection() {
		return tagCollection;
	}

	public void setTagCollection(Collection<Tag> tagCollection) {
		this.tagCollection = tagCollection;
	}

	public Collection<CommentaireB> getCommentaireBCollection() {
		return commentaireBCollection;
	}

	public void setCommentaireBCollection(Collection<CommentaireB> commentaireBCollection) {
		this.commentaireBCollection = commentaireBCollection;
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
		if (!(object instanceof Article)) {
			return false;
		}
		Article other = (Article) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entites.Article[ id=" + id + " ]";
	}
	
}
