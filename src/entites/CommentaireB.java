/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aminos
 */
@Entity
@Table(name = "commentaire_b")
@NamedQueries({
	@NamedQuery(name = "CommentaireB.findAll", query = "SELECT c FROM CommentaireB c")})
public class CommentaireB implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id")
	private Integer id;
	@Basic(optional = false)
        @Lob
        @Column(name = "text")
	private String text;
	@Basic(optional = false)
        @Column(name = "auteurn")
	private String auteurn;
	@Basic(optional = false)
        @Column(name = "auteur")
	private int auteur;
	@JoinColumn(name = "article_id", referencedColumnName = "id")
        @ManyToOne
	private Article articleId;

	public CommentaireB() {
	}

	public CommentaireB(Integer id) {
		this.id = id;
	}

	public CommentaireB(Integer id, String text, String auteurn, int auteur) {
		this.id = id;
		this.text = text;
		this.auteurn = auteurn;
		this.auteur = auteur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuteurn() {
		return auteurn;
	}

	public void setAuteurn(String auteurn) {
		this.auteurn = auteurn;
	}

	public int getAuteur() {
		return auteur;
	}

	public void setAuteur(int auteur) {
		this.auteur = auteur;
	}

	public Article getArticle() {
		return articleId;
	}

	public void setArticle(Article articleId) {
		this.articleId = articleId;
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
		if (!(object instanceof CommentaireB)) {
			return false;
		}
		CommentaireB other = (CommentaireB) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entites.CommentaireB[ id=" + id + " ]";
	}
	
}
