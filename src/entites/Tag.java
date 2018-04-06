/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Collection;


/**
 *
 * @author aminos
 */

public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private Collection<Tag> tagCollection;
	
	private Collection<Article> articleCollection;

	public Tag() {
	}

	public Tag(String name) {
		this.name = name;
                
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
