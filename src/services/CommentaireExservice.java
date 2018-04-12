/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
import entites.CommentaireEx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.events.Comment;

/**
 *
 * @author azerty
 */
public class CommentaireExservice {
    
     private Connection con = DataSource.getInstance().getCon();
    PreparedStatement pre;
    
 public void add(CommentaireEx a) {
     System.out.println("a" + a.getIduser());
          System.out.println("c"  + a.getContenu());
     System.out.println("d" + a.getIdexp());

         String req="insert into commentaireex (iduser,contenu,idex) values (?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(1, a.getIduser());
            ps.setString(2, a.getContenu());
            ps.setInt(3, a.getIdexp());                        
      ps.executeUpdate();
           
        } catch (SQLException ex) {
             System.out.println(ex);
        }
    }
 
  public ArrayList<CommentaireEx> findByAnnonce(int id) {
        String req = "SELECT * FROM `commentaireex` WHERE idex = ?";
        ArrayList<CommentaireEx> l = new ArrayList<>();
                try{
                    PreparedStatement ps = con.prepareStatement(req);
                    ps.setInt(1, id);
                    ResultSet r = ps.executeQuery();

                    while (r.next())
                   l.add(new CommentaireEx(r.getInt("id"),r.getInt("iduser"),r.getString("contenu"),r.getInt("idex")));
                    
                }
                catch(SQLException ex){
                   System.out.println(ex);
                }
        return l;
    }
  
   public boolean delete(CommentaireEx a) {
     
             String req = "Delete from commentaireex where id = ?";
                try{
                    PreparedStatement ps = con.prepareStatement(req);
                    ps.setInt(1, a.getId());
                    ps.execute();
                   return true; 
                }
                catch(SQLException ex){
                       System.out.println(ex);
                        return false;
                }
                
       
              
    }
    
}
