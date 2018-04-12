/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
import entites.Article;
import entites.CommentaireB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aminos
 */
public class CommentaireBService {
	public Connection con = DataSource.getInstance().getCon();

	
	public int ajouter(CommentaireB c) {
		int ret = -1;
			String req = "INSERT INTO commentaire_b (article_id, text, auteur, auteurn)"
				+ " VALUES (?, ?, ?, ?)" ;
		PreparedStatement pre;
		try {
			pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pre.setString(2, c.getText());
			pre.setInt(1, c.getArticle().getId());
			pre.setString(4, c.getAuteurn());
			pre.setInt(3, c.getAuteur());
			
			pre.executeUpdate();
			ResultSet ks = pre.getGeneratedKeys();
			if (ks.next()) {
			ret = ks.getInt(1);
			c.setId(ret);
			}
		
		
		} catch (SQLException ex) {
			Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
		
	}

	public boolean supprimer(int id) {
		boolean ret = false;
		try {
			String req = "DELETE FROM commentaire_b where id = ?";
			PreparedStatement pre = con.prepareStatement(req);
			pre.setInt(1, id);
			ret = pre.execute();
		} catch (SQLException ex) {
			Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
	}
	
	public boolean supprimer(CommentaireB c) {
		boolean ret = false;
		try {
			String req = "DELETE FROM commentaire_b where id = ?";
			PreparedStatement pre = con.prepareStatement(req);
			pre.setInt(1, c.getId());
			ret = pre.execute();
		} catch (SQLException ex) {
			Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
	}

	public int modifier(CommentaireB c) {
		int ret = -1;
                
			String req = "UPDATE commentaire_b SET"
				+ " text = ?"
				+ " WHERE id = ?" ;
		PreparedStatement pre;
		try {
                   // System.out.println(c.getId());
			pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, c.getText());
			pre.setInt(2, c.getId());
			
			pre.executeUpdate();
			ret = c.getId();

                      
		
		
		} catch (SQLException ex) {
			Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
		
	}

	public ArrayList findAll() {
		ArrayList<CommentaireB> ret = null;
		try {
			String req = "SELECT * FROM commentaire_b";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
		
			ResultSet rs = pre.executeQuery();
			ret = new ArrayList();
			CommentaireB x;
			while (rs.next()) {
				try {
					x = new CommentaireB();
					x.setId(rs.getInt(1));
					ArticleService s = new ArticleService();
					x.setArticle(s.find(rs.getInt(2)));
					x.setText(rs.getString(3));
					x.setAuteur(rs.getInt(4));
					x.setAuteurn(rs.getString(5));
					ret.add(x);
				} catch (SQLException ex) {
					Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		} catch (SQLException ex) {			
			Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
			
	}

	public ArrayList<CommentaireB> findByArticle(Article article) {
            ArrayList<CommentaireB> ret = null;
		try {
			String req = "SELECT c.id,c.article_id,c.text,c.auteurn,c.auteur FROM commentaire_b c, article a where a.id = c.article_id and a.id = ? ";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
                        pre.setInt(1, article.getId());
			ResultSet rs = pre.executeQuery();
			ret = new ArrayList();
			CommentaireB x;
			while (rs.next()) {
				try {
					x = new CommentaireB();
					x.setId(rs.getInt(1));
					ArticleService s = new ArticleService();
					x.setArticle(s.find(rs.getInt(2)));
					x.setText(rs.getString(3));
					x.setAuteur(rs.getInt(5));
					x.setAuteurn(rs.getString(4));
					ret.add(x);
				} catch (SQLException ex) {
					Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		} catch (SQLException ex) {			
			Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
        }

	public CommentaireB find(int id) {
		CommentaireB ret = null;
		try {
			String req = "SELECT * FROM commentaire_b WHERE id = ?";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				try {
					ret = new CommentaireB();
					ret.setId(rs.getInt(1));
					ArticleService s = new ArticleService();
					ret.setArticle(s.find(rs.getInt(2)));
					ret.setText(rs.getString(3));
					
					ret.setAuteur(rs.getInt(5));
					ret.setAuteurn(rs.getString(4));
					
				} catch (SQLException ex) {
					Logger.getLogger(CommentaireBService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		} catch (SQLException ex) {			
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
			
	}
}
