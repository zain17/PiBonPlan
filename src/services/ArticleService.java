/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
import entites.Article;
import entites.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aminos
 */
public class ArticleService  {
	private Connection con = DataSource.getInstance().getCon();

	
	public int ajouter(Article t) {
		int ret = -1;
			String req = "INSERT INTO article (texte, titre, auteur, auteurn, updated, created, vote)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)" ;
		PreparedStatement pre;
		try {
			pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, t.getTexte());
			pre.setString(2, t.getTitre());
			pre.setInt(3, t.getAuteur());
			pre.setString(4, t.getAuteurn());
			pre.setObject(5, t.getCreated());
			pre.setObject(6, t.getCreated());
			pre.setInt(7, t.getVote());
			
			pre.executeUpdate();
			ResultSet ks = pre.getGeneratedKeys();
			if (ks.next()) {
			ret = ks.getInt(1);
			t.setId(ret);
			}
		
		
		} catch (SQLException ex) {
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
		
	}
        

	public boolean supprimer(int id) {
		boolean ret = false;
		try {
			String req = "DELETE FROM article where id = ?";
			PreparedStatement pre = con.prepareStatement(req);
			pre.setInt(1, id);
			ret = pre.execute();
		} catch (SQLException ex) {
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
	}
	
	public boolean supprimer(Article t) {
		boolean ret = false;
		try {
			String req = "DELETE FROM article where id = ?";
			PreparedStatement pre = con.prepareStatement(req);
			pre.setInt(1, t.getId());
			ret = pre.execute();
		} catch (SQLException ex) {
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
	}

	public int modifier(Article t) {
		int ret = -1;
			String req = "UPDATE article SET"
				+ " texte = ?, titre = ?,"
				+ " auteur = ?, auteurn = ? ,"
				+ " updated = ?, created = ?,"
				+ " vote = ?"
				+ " WHERE id = ?" ;
		PreparedStatement pre;
		try {
			pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, t.getTexte());
			pre.setString(2, t.getTitre());
			pre.setInt(3, t.getAuteur());
			pre.setString(4, t.getAuteurn());
			pre.setObject(5, t.getCreated());
			t.setUpdated(java.sql.Date.valueOf(LocalDate.now()));
			pre.setObject(6, t.getUpdated());
			pre.setInt(7, t.getVote());
			pre.setInt(8, t.getId());
			
			pre.executeUpdate();
			ret = t.getId();
		
		
		} catch (SQLException ex) {
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
		
	}

	public ArrayList<Article> findAll() {
		ArrayList<Article> ret = null;
		try {
			String req = "SELECT * FROM article";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
		
			ResultSet rs = pre.executeQuery();
			ret = new ArrayList();
			Article x;
			while (rs.next()) {
				try {
					x = new Article();
					x.setId(rs.getInt(1));
					x.setTexte(rs.getString(2));
					x.setVote(rs.getInt(3));
					x.setTitre(rs.getString(4));
					x.setAuteur(rs.getInt(5));
					x.setAuteurn(rs.getString(6));
					x.setUpdated(java.sql.Date.valueOf(rs.getDate(7).toLocalDate()));
					x.setCreated(java.sql.Date.valueOf(rs.getDate(8).toLocalDate()));
					ret.add(x);
				} catch (SQLException ex) {
					Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		} catch (SQLException ex) {			
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
			
	}

	

	public Article find(int id) {
		Article ret = null;
		try {
			String req = "SELECT * FROM article WHERE id = ?";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				try {
					ret = new Article();
					ret.setId(rs.getInt(1));
					ret.setTexte(rs.getString(2));
					ret.setVote(rs.getInt(3));
					ret.setTitre(rs.getString(4));
					ret.setAuteur(rs.getInt(5));
					ret.setAuteurn(rs.getString(6));
					ret.setUpdated(java.sql.Date.valueOf(rs.getDate(7).toLocalDate()));
					ret.setCreated(java.sql.Date.valueOf(rs.getDate(8).toLocalDate()));
				} catch (SQLException ex) {
					Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		} catch (SQLException ex) {			
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
			
	}
        
        public ArrayList<Article> findByTag(Tag t) {
            ArrayList<Article> ret = new ArrayList();
                try {
			String req = "SELECT article.id, texte, vote, titre, auteur, auteurn, updated, created from article, article_tag, tag where article.id = article_tag.article_id AND tag_id = tag.id AND tag.name LIKE ?";

			PreparedStatement pre;
			pre = con.prepareStatement(req);
			pre.setString(1, "%"+t.getName()+"%");
			ResultSet rs = pre.executeQuery();
			
			while (rs.next()) {
				try {
					Article re = new Article();
					re.setId(rs.getInt(1));
					re.setTexte(rs.getString(2));
					re.setVote(rs.getInt(3));
					re.setTitre(rs.getString(4));
					re.setAuteur(rs.getInt(5));
					re.setAuteurn(rs.getString(6));
					re.setUpdated(java.sql.Date.valueOf(rs.getDate(7).toLocalDate()));
					re.setCreated(java.sql.Date.valueOf(rs.getDate(8).toLocalDate()));
                                        ret.add(re);
				} catch (SQLException ex) {
					Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		} catch (SQLException ex) {			
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		
            
            return ret;
        }
        
        
        public ArrayList find(String texte) {
		ArrayList<Article> ret = null;
		try {
			String req = "SELECT * FROM article WHERE texte LIKE ? OR titre LIKE ?";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
                        pre.setString(1, "%"+texte+"%");
                        pre.setString(2, "%"+texte+"%");
		
			ResultSet rs = pre.executeQuery();
			ret = new ArrayList();
			Article x;
			while (rs.next()) {
				try {
					x = new Article();
					x.setId(rs.getInt(1));
					x.setTexte(rs.getString(2));
					x.setVote(rs.getInt(3));
					x.setTitre(rs.getString(4));
					x.setAuteur(rs.getInt(5));
					x.setAuteurn(rs.getString(6));
					x.setUpdated(java.sql.Date.valueOf(rs.getDate(7).toLocalDate()));
					x.setCreated(java.sql.Date.valueOf(rs.getDate(8).toLocalDate()));
					ret.add(x);
				} catch (SQLException ex) {
					Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		} catch (SQLException ex) {			
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
			
	}

	

	
	
	
	
}
