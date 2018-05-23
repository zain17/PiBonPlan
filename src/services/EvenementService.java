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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import security.Authenticator;

/**
 *
 * @author Sboula
 */
public class EvenementService implements EventsInterface<Evenements> {
	private Connection con = DataSource.getInstance().getCon();
                PreparedStatement pre;

 
    @Override
    public void ajouter(Evenements t) 
    {
            String req="INSERT INTO evenements (nom,description,date,adresse,tel,prix,brochure,lieu,type,utilisateur_id,dateF) VALUES(?,?,?,?,?,?,?,?,?,?,?)" ; 
		try {
			pre = con.prepareStatement(req);
                        pre.setString(1, t.getNom());
                        pre.setString(2, t.getDescription());
			pre.setDate(3, (Date) t.getDate());
			pre.setString(4,t.getAdresse());    
			pre.setString(5, t.getTel());
			pre.setFloat(6, t.getPrix());
			pre.setString(7, t.getBrochure());
                        pre.setString(8,t.getLieu());
                        pre.setString(9,t.getType());
                        pre.setInt(10, t.getU().getId());
                        pre.setString(11,t.getDateF());
                         pre.executeUpdate();
			
		
		
		} catch (SQLException ex) {
                    			Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);

		}
		
    }

    @Override
    public void supprimer(int id) {
         String req="DELETE  from evenements where  id =?" ; 
        try { 
            
            
            PreparedStatement ste = con.prepareStatement(req) ;
             
            
            ste.setInt(1,id) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
                        Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void modifier( Evenements t) {
                     
			String req = "UPDATE evenements SET"
				+ " nom = ?, "
                                + "description = ?,"
				+ " lieu= ?,"
                                + " date = ? ,"
				+ "  adresse = ?,"
				+ " tel = ?,"
                                +"prix = ?,"
                                + "brochure= ?,"
                               
				+ " WHERE id = ?" ;   
                        try {
			pre = con.prepareStatement(req);
                        
			pre.setString(1, t.getNom());
			pre.setString(2, t.getDescription());
			pre.setString(3, t.getLieu());
			pre.setDate(4, (Date) t.getDate());
			pre.setString(5,t.getAdresse());
			pre.setString(6, t.getTel());
                      
			pre.setInt(7, t.getPrix());
                        pre.setString(8, t.getBrochure());
                        pre.setInt(9, t.getId() );
                        
			
			
			pre.executeUpdate();
	
		
		
		} catch (SQLException ex) {
			Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
		}
                     
    }
    
    

    @Override
    public ObservableList<Evenements> selectAll()  {

       
        ObservableList<Evenements> eve = FXCollections.observableArrayList();
        
            try {
                        
                       String req = "SELECT * FROM evenements WHERE utilisateur_id = ? ";
                       
			PreparedStatement pre;
                        
			pre = con.prepareStatement(req);
                        
                        pre.setInt(1,Authenticator.getCurrentAuth().getId());
                        
		
			ResultSet rs = pre.executeQuery();
		
			

                  while (rs.next())
                  {
                      Evenements e = new Evenements();
                      
                      
                       e.setId(rs.getInt(1));
                       e.setNom(rs.getString(2));
                        e.setDescription(rs.getString(3));
                        e.setDate(rs.getDate(4));
                        e.setNbPlace(rs.getInt(5));
			e.setLieu(rs.getString(6));
                        e.setPrix((int) rs.getFloat(7));
                        e.setBrochure(rs.getString(8));
			e.setAdresse(rs.getString(9));
                        e.setTel(rs.getString(10));

                        e.setDateF(rs.getString(11));
                        System.out.println( rs.getInt(12));
                        
                       e.getU().setId(rs.getInt(12));
                        e.setType(rs.getString(13));


                      eve.add(e);
                                
                        
                        
                        
                        
                        
                        
                  }
            } catch (SQLException ex) {
                Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            }
	                  return eve;

                 }
    
    
   /* @Override
    public ArrayList<Gou> selectAllGov()  {

       
        ArrayList<Evenements> eve = new ArrayList<>();
        
            try {
            
                        
                       String req = "SELECT * FROM evenements";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
		
			ResultSet rs = pre.executeQuery();
		
			

                  while (rs.next())
                  {
                      Evenements e = new Evenements();
                       e.setId(rs.getInt(1));
                        e.setDescription(rs.getString(2));
			e.setLieu(rs.getString(3));
			e.setDate(rs.getDate(4));
                        e.setDateF(rs.getDate(5));
		        e.setAdresse(rs.getString(7));
			e.setTel(rs.getString(8));
                        e.setRating(rs.getInt(9));
			e.setPrix((int) rs.getFloat(10));
                        e.setBrochure(rs.getString(11));
                        e.setNbPlace(rs.getInt(12));
                        e.setType(rs.getString(13));


                      eve.add(e);
                                
                        
                        
                        
                        
                        
                        
                  }
            } catch (SQLException ex) {
                Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            }
	                  return eve;

                 }
    
    */



  

    @Override
    public Evenements find(int id) {
       
		
		Evenements e = null;
		try {
			String req = "SELECT * FROM evenements WHERE id = ?";
			PreparedStatement pre;
			pre = con.prepareStatement(req);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				try {
                                    e= new Evenements();
                                     e.setId(rs.getInt(1));
                       e.setNom(rs.getString(2));
                        e.setDescription(rs.getString(3));
                        e.setDate(rs.getDate(4));
                        e.setNbPlace(rs.getInt(5));
			e.setLieu(rs.getString(6));
                        e.setPrix((int) rs.getFloat(7));
                        e.setBrochure(rs.getString(8));
			e.setAdresse(rs.getString(9));
                        e.setTel(rs.getString(10));

                        e.setDateF(rs.getString(11));
                        System.out.println( rs.getInt(12));
                        
                       e.getU().setId(rs.getInt(12));
                        e.setType(rs.getString(13));
					
                                    
                                    
				} catch (SQLException ex) {
					Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
                        
                        
		} catch (SQLException ex) {			
			Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return e;
			
	}
    
    

    @Override
    public ObservableList<Evenements> selectALL()  {

       
        ObservableList<Evenements> eve = FXCollections.observableArrayList();
        
            try {
                        
                       String req = "SELECT * FROM evenements  ";
                       
			PreparedStatement pre;
                        
			pre = con.prepareStatement(req);
                        
                        
		
			ResultSet rs = pre.executeQuery();
		
			

                  while (rs.next())
                  {
                      Evenements e = new Evenements();
                      
                      
                       e.setId(rs.getInt(1));
                       e.setNom(rs.getString(2));
                        e.setDescription(rs.getString(3));
                        e.setDate(rs.getDate(4));
                        e.setNbPlace(rs.getInt(5));
			e.setLieu(rs.getString(6));
                        e.setPrix((int) rs.getFloat(7));
                        e.setBrochure(rs.getString(8));
			e.setAdresse(rs.getString(9));
                        e.setTel(rs.getString(10));

                        e.setDateF(rs.getString(11));
                        
                       e.getU().setId(rs.getInt(12));
                        e.setType(rs.getString(13));


                      eve.add(e);
                                
                        
                        
                        
                        
                        
                        
                  }
            } catch (SQLException ex) {
                Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            }
	                  return eve;

                 }
    
   
    
}
