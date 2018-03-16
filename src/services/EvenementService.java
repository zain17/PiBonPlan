/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, coose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
import entites.Evenements;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sboula
 */
public class EvenementService implements IService<Evenements> {
	private Connection con = DataSource.getInstance().getCon();
                PreparedStatement pre;

 
    @Override
    public void ajouter(Evenements t) 
    {
        String req="INSERT INTO Evenements (nom,description,lieu,date,dateF,adresse,tel,rating,prix,brochure,nbPlace) VALUES(?,?,?,?,?,?,?,?,?,?,?)" ; 
		try {
			pre = con.prepareStatement(req);
			pre.setString(1, t.getNom());
			pre.setString(2, t.getDescription());
			pre.setString(3, t.getLieu());
			pre.setDate(4, (Date) t.getDate());
                        pre.setDate(5, (Date) t.getDateF());
			pre.setString(6,t.getAdresse());
			pre.setString(7, t.getTel());
                        pre.setInt(8, t.getRating());
			pre.setFloat(9, t.getPrix());
                        pre.setString(10, t.getBrochure());
                        pre.setInt(11, t.getNbPlace());
                        
			
			pre.executeUpdate();
			
		
		
		} catch (SQLException ex) {
		}
		
    }

    @Override
    public void supprimer(int id) {
         String req="DELETE  from evenements where  id =?" ; 
        try { 
            PreparedStatement ste = pre.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }

    }

    @Override
    public void modifier(int id, Evenements t) {
                        int ret = -1;
			String req = "UPDATE evenements SET"
				+ " nom = ?, description = ?,"
				+ " lieu= ?, date = ? ,"
				+ " dateF = ?, adresse = ?,"
				+ " tel = ?, rating = ?,"
                                +"prix = ?,brochure= ?,nbPlace=?"
				+ " WHERE id = ?" ;   
                        try {
			pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1, t.getNom());
			pre.setString(2, t.getDescription());
			pre.setString(3, t.getLieu());
			pre.setDate(4, (Date) t.getDate());
                        pre.setDate(5, (Date) t.getDateF());
			pre.setString(6,t.getAdresse());
			pre.setString(7, t.getTel());
                        pre.setInt(8, t.getRating());
			pre.setFloat(9, t.getPrix());
                        pre.setString(10, t.getBrochure());
                        pre.setInt(11, t.getNbPlace());
                        
			
			pre.executeUpdate();
			ResultSet ks = pre.getGeneratedKeys();
			if (ks.next())
			ret = ks.getInt(1);
		
		
		} catch (SQLException ex) {
			Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
		}
                        //No Return here 
    }

    @Override
    public List<Evenements> selectAll()  {
ResultSet rs;
    List<Evenements> eve = new ArrayList<>();
            try {
                rs = pre.executeQuery("SELECT * FROM Evenements");
            
                  while (rs.next())
                  {
		
                        eve.add(new Evenements());
                  }
            } catch (SQLException ex) {
                Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            }
	                  return eve;

                 }

   
    
}
