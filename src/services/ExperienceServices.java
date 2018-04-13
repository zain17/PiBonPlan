/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
import entites.CommentaireEx;
import entites.Experiences;
import entites.Jaime;
import entites.Revueee;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nizar Elhraiech
 */
public class ExperienceServices {

    private Connection con = DataSource.getInstance().getCon();
    PreparedStatement pre;

    public void ajouter(Experiences e) throws FileNotFoundException {
        String req = "INSERT INTO experiences (description,image,iduser) VALUES(?,?,?)";
        try {
            InputStream is = new FileInputStream(e.getImage());
            pre = con.prepareStatement(req);
            pre.setString(1, e.getDescription());
            pre.setBlob(2, is);
            pre.setInt(3, e.getIduser());
            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
    
    
    
    ///////////////////////////////////////////////
      public void ajouterreve(Revueee e) throws FileNotFoundException {
        String req = "INSERT INTO revueee (description,image,iduser) VALUES(?,?,?)";
        try {
            InputStream is = new FileInputStream(e.getImage());
            pre = con.prepareStatement(req);
            pre.setString(1, e.getDescription());
            pre.setBlob(2, is);
            pre.setInt(3, e.getIduser());
            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
        public ArrayList<Revueee> getAllrevue() {
        ArrayList<Revueee> listArticle = new ArrayList<>();
        try {
            Statement ste = con.createStatement();
            String req = "SELECT * FROM `revueee`  ";
            ResultSet res = ste.executeQuery(req);
            PreparedStatement preparedStmt = con.prepareStatement(req);
            preparedStmt.execute();
            while (res.next()) {
                byte[] binaryStream = res.getBytes("image");
                //  Date date = res.getDate("datear");
                String corps = res.getString("description");
                int id = res.getInt("id");
               
                int idu = res.getInt("iduser");

                Revueee experience = new Revueee( id, idu,binaryStream,corps);
                listArticle.add(experience);

            }
            return listArticle;
        } catch (SQLException ex) {
        }
        return listArticle;
    }
      
      
     ////////////////////////////////////// 
      
      
      
      
      
      
      
      
      
      
    public boolean edit(String des ,int id ) {
        String req="UPDATE experiences set description = ? where id = ?";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setString(1, des);
           
            ps.setInt(2, id);
            
            
           if(ps.executeUpdate()!=0) return true;
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    

    public ArrayList<Experiences> delete(int a) {
        ArrayList<Experiences> listArticle = new ArrayList<>();
        String req = "Delete from experiences where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, a);
            ps.execute();
            Experiences article = new Experiences(a);
            listArticle.remove(article);

            return listArticle;
        } catch (SQLException ex) {
            System.out.println(ex);
            return listArticle;
        }

    }

    public ArrayList<Experiences> getAllArticle() {
        ArrayList<Experiences> listArticle = new ArrayList<>();
        try {
            Statement ste = con.createStatement();
            String req = "SELECT * FROM `experiences`  ";
            ResultSet res = ste.executeQuery(req);
            PreparedStatement preparedStmt = con.prepareStatement(req);
            preparedStmt.execute();
            while (res.next()) {
                byte[] binaryStream = res.getBytes("image");
                //  Date date = res.getDate("datear");
                String corps = res.getString("description");
                int id = res.getInt("id");
                int nbj = res.getInt("nbjaime");
                int nbc = res.getInt("nbcomment");
                int idu = res.getInt("iduser");

                Experiences experience = new Experiences(corps, binaryStream, nbj, nbc, idu, id);
                listArticle.add(experience);

            }
            return listArticle;
        } catch (SQLException ex) {
        }
        return listArticle;
    }

    //codo :D
    public ArrayList<Jaime> getAllLikeToday(int iduser) {
        ArrayList<Jaime> jaimeListTodat = new ArrayList<>();
        try {
            // // java.sql.Date get date now
            Calendar calendar = Calendar.getInstance();
            java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
            Statement ste = con.createStatement();
            String req = "SELECT * FROM `jaime` WHERE  date = '" + ourJavaDateObject + "' ";
            ResultSet res = ste.executeQuery(req);
            PreparedStatement preparedStmt = con.prepareStatement(req);
            preparedStmt.execute();
            while (res.next()) {
                //  Date date = res.getDate("datear");
                int id = res.getInt("id");
                int idusere = res.getInt("id_user");
                int idexperience = res.getInt("id_experiences");
                Date date = res.getDate("date");

                Jaime jaime = new Jaime(id, idusere, idexperience, date);
                jaimeListTodat.add(jaime);

            }
            return jaimeListTodat;
        } catch (SQLException ex) {
        }
        return jaimeListTodat;
    }

    public ArrayList<Jaime> getAllMyLike(int iduser) {
        ArrayList<Jaime> jaimeList = new ArrayList<>();
        try {
            Statement ste = con.createStatement();
            String req = "SELECT * FROM `jaime` WHERE  id_user = '" + iduser + "' ";
            ResultSet res = ste.executeQuery(req);
            PreparedStatement preparedStmt = con.prepareStatement(req);
            preparedStmt.execute();
            while (res.next()) {
                //  Date date = res.getDate("datear");
                int id = res.getInt("id");
                int idusere = res.getInt("id_user");
                int idexperience = res.getInt("id_experiences");

                Jaime jaime = new Jaime(id, idusere, idexperience);
                jaimeList.add(jaime);

            }
            return jaimeList;
        } catch (SQLException ex) {
        }
        return jaimeList;
    }

    public int likeExperience(int idexp, int iduser) {
        int etat = 0;
        int totaljaime = 0;
        try {
            Statement ste = con.createStatement();
            String req = "SELECT * FROM `jaime` WHERE id_experiences = '" + idexp + "' and id_user = '" + iduser + "' ";
            ResultSet res = ste.executeQuery(req);
            PreparedStatement preparedStmt = con.prepareStatement(req);
            preparedStmt.execute();

            if (!res.isBeforeFirst()) {
                try {
                    etat = 1;
                    // set like in table jaime
                    Calendar calendar = Calendar.getInstance();
                    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());

                    String request = "INSERT INTO `jaime` (`id_experiences`, `id_user`,`date`) "
                            + "VALUES ('" + idexp + "', '" + iduser + "', '" + ourJavaDateObject + "')";
                    PreparedStatement preparedStmt1 = con.prepareStatement(request);
                    preparedStmt1.execute();
                    //get nb jaime
                    String req2 = "SELECT COUNT(*) AS total FROM `jaime` WHERE id_experiences = '" + idexp + "' ";

                    ResultSet resulteset1 = ste.executeQuery(req2);
                    PreparedStatement preparedStmt2 = con.prepareStatement(req2);
                    preparedStmt2.execute();

                    while (resulteset1.next()) {
                        totaljaime = resulteset1.getInt("total");
                    }
                    //set nb jaime in table experience
                    System.out.println(totaljaime);

                    String req3 = "UPDATE `experiences` SET nbjaime = '" + totaljaime + "' WHERE id = '" + idexp + "'";
                    PreparedStatement preparedStmt3 = con.prepareStatement(req3);
                    preparedStmt3.execute();

                } catch (SQLException ex) {
                    System.out.println(ex);

                }
            } else {
                try {
                    
                    // set like in table jaime
                    Calendar calendar = Calendar.getInstance();
                    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());

                    String request = "DELETE FROM `jaime` WHERE id_experiences = '" + idexp + "' and id_user = '" + iduser + "' ";
                    PreparedStatement preparedStmt2 = con.prepareStatement(request);
                    preparedStmt2.execute();
                    
                    //get nb jaime
                    String req2 = "SELECT COUNT(*) AS total FROM `jaime` WHERE id_experiences = '" + idexp + "' ";

                    ResultSet resulteset = ste.executeQuery(req2);
                    PreparedStatement preparedStmt3 = con.prepareStatement(req2);
                    preparedStmt3.execute();

                    while (resulteset.next()) {
                        totaljaime = resulteset.getInt("total");
                    }

                    //set nb jaime in table experience
                    System.out.println(totaljaime);

                    String req3 = "UPDATE `experiences` SET nbjaime = '" + totaljaime + "' WHERE id = '" + idexp + "'";
                    PreparedStatement preparedStmt4 = con.prepareStatement(req3);
                    preparedStmt4.execute();

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            return etat;


            /*while (res.next()) {
            }*/
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return etat;
    }

}
