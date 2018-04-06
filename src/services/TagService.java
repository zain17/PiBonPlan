/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
import entites.Tag;
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
public class TagService {
    	private Connection con = DataSource.getInstance().getCon();

    public int ajouter(Tag t, int articleId) {
        	int ret = -1;
			String req = "INSERT INTO tag (name)"
				+ " VALUES (?)" ;
		PreparedStatement pre;
		try {
			pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, t.getName());
			ArticleService aS = new ArticleService();
                        if (aS.find(articleId) == null)
                            return -1;
                        if (find(t) == -1) {
                            pre.executeUpdate();
			ResultSet ks = pre.getGeneratedKeys();
			if (ks.next()) {
			ret = ks.getInt(1);
                        }
                        }
                        else
                            ret = find(t);
                        System.out.println("TagS: " + ret);
			t.setId(ret);
                        req = "INSERT INTO article_tag (article_id, tag_id)" +
                                " VALUES (?, ?)";
                        pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
                        pre.setInt(1, articleId);
                        pre.setInt(2, t.getId());
                        pre.executeUpdate();
			
		
		
		} catch (SQLException ex) {
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
    }
    
    public int find(Tag t) {
        int ret = -1;
        	
		try {
			String req = "SELECT * FROM tag WHERE name = ?";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
			pre.setString(1, t.getName());
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				try {
					ret = rs.getInt(1);										
				} catch (SQLException ex) {
					Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		} catch (SQLException ex) {			
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
    }
    
    	public boolean supprimer(int id) {
		boolean ret = false;
		try {
			String req = "DELETE FROM tag where id = ?";
			PreparedStatement pre = con.prepareStatement(req);
			pre.setInt(1, id);
			ret = pre.execute();
		} catch (SQLException ex) {
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
	}
	
	public boolean supprimer(Tag t) {
		boolean ret = false;
		try {
			String req = "DELETE FROM tag where id = ?";
			PreparedStatement pre = con.prepareStatement(req);
			pre.setInt(1, t.getId());
			ret = pre.execute();
		} catch (SQLException ex) {
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
	}
        
        	public boolean supprimer(String name) {
		boolean ret = false;
		try {
			String req = "DELETE FROM tag where id = ?";
			PreparedStatement pre = con.prepareStatement(req);
                        Tag t = new Tag(name);
                        System.out.println(find(t));
			pre.setInt(1, find(t));
			ret = pre.execute();
		} catch (SQLException ex) {
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
	}

    public boolean isValidTags(String tagsStr) {
        for (String s : tagsStr.split(",")) {
            if (s.isEmpty())
                return false;
        }
        return true;
    }

    public ArrayList<Tag> tagsStringToTags(String tagsStr) {
        ArrayList<Tag> t = new ArrayList<>();
        System.out.println("TAGGGs " + tagsStr.split(",").length);
        for (String s : tagsStr.split(",")) {
            Tag tg = new Tag(s);
            t.add(tg);
        }
        return t;
    }
	
	
}
