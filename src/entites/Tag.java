/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aminos
 */
@Entity
@Table(name = "tag")
@NamedQueries({
	@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t")})
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id")
	private Integer id;
	@Basic(optional = false)
        @Column(name = "name")
	private String name;
	@JoinTable(name = "tag_tag", joinColumns = {
        	@JoinColumn(name = "tag_target", referencedColumnName = "id")}, inverseJoinColumns = {
        	@JoinColumn(name = "tag_source", referencedColumnName = "id")})
        @ManyToMany
	private Collection<Tag> tagCollection;
	@ManyToMany(mappedBy = "tagCollection")
	private Collection<Tag> tagCollection1;
	@ManyToMany(mappedBy = "tagCollection")
	private Collection<Article> articleCollection;

	public Tag() {
	}

	public Tag(Integer id) {
		this.id = id;
	}

	public Tag(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Tag> getTagCollection() {
		return tagCollection;
	}

	public void setTagCollection(Collection<Tag> tagCollection) {
		this.tagCollection = tagCollection;
	}

	public Collection<Tag> getTagCollection1() {
		return tagCollection1;
	}

	public void setTagCollection1(Collection<Tag> tagCollection1) {
		this.tagCollection1 = tagCollection1;
	}

	public Collection<Article> getArticleCollection() {
		return articleCollection;
	}

	public void setArticleCollection(Collection<Article> articleCollection) {
		this.articleCollection = articleCollection;
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
		if (!(object instanceof Tag)) {
			return false;
		}
		Tag other = (Tag) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entites.Tag[ id=" + id + " ]";
	}
	
}
