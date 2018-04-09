/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
import Util.SerializedPhpParser;
import entites.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class UtilisateurService implements IServiceUtilisateur{
    public static int lastInsertedId=0;
    private Connection connection = DataSource.getInstance().getCon();
    private Statement ste=null;
    public UtilisateurService() {

        try {
            ste=DataSource.getInstance().getCon().createStatement();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void ajouter(Utilisateur user) {
        String req = "INSERT INTO Utilisateur (photo_profil,langitude,latitude,username,username_canonical,email,email_canonical,enabled,salt,password,roles,prenom) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" ;
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(req);
            pre.setString(1,user.getPhotoProfil());
            if(user.getLangitude()==null)
                pre.setNull(2,Types.DOUBLE);
            else
                pre.setDouble(2,user.getLangitude());
            if(user.getLatitude()==null)
                pre.setDouble(3,Types.NULL);
            else
                pre.setDouble(3,user.getLatitude());
            pre.setString(4, user.getUsername());
            pre.setString(5, user.getUsername());
            pre.setString(6, user.getEmail());
            pre.setString(7, user.getEmailCanonical());
            pre.setShort(8, user.getEnabled());
            pre.setString(9, user.getSalt());
            pre.setString(10, user.getPassword());
            if(user.getRoles().equals("ROLE_CLIENT"))
                pre.setString(11,"a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
            else
            if(user.getRoles().equals("ROLE_ETABLISSEMENT"))
                pre.setString(11,"a:1:{i:0;s:18:\"ROLE_ETABLISSEMENT\";}");
            pre.setString(12, user.getPrenom());
            pre.executeUpdate();
            System.out.println("Utilisateur ajouter avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        String SQL = "DELETE FROM Utilisateur WHERE id = ? ";
        PreparedStatement pre = null;
        try {
            // get a connection and then in your try catch for executing your delete...
            pre = connection.prepareStatement(SQL);
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void modifier(int id, Utilisateur user) {
        String requete="UPDATE Utilisateur set photo_profil=?,langitude=?,latitude=?,username=?,username_canonical=?,email=?,email_canonical=?,enabled=?,salt=?,password=?,roles=?,prenom=? where id=?";
        PreparedStatement pre=null;
        try {
            System.out.println(user);
            pre = connection.prepareStatement(requete);
            pre.setString(1,user.getPhotoProfil());
            if(user.getLangitude()==0)
                pre.setNull(2,Types.DOUBLE);
            else
                pre.setDouble(2,user.getLangitude());
            if(user.getLatitude()==0)
                pre.setDouble(3,Types.DOUBLE);
            else
                pre.setDouble(3,user.getLatitude());
            pre.setString(4, user.getUsername());
            pre.setString(5, user.getUsername());
            pre.setString(6, user.getEmail());
            pre.setString(7, user.getEmail());
            pre.setShort(8, user.getEnabled());
            pre.setString(9, user.getSalt());
            pre.setString(10, user.getPassword());
            if(user.getRoles().equals("ROLE_CLIENT"))
                pre.setString(11,"a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
            if(user.getRoles().equals("ROLE_ETABLISSEMENT"))
                pre.setString(11,"a:1:{i:0;s:18:\"ROLE_ETABLISSEMENT\";}");
            pre.setString(12, user.getPrenom());
            if(id==user.getId())
            pre.setInt(13, id);
            else System.out.println("be carfull error id ");
            pre.executeUpdate();
            System.out.println("Utilisateur Modifier avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public ArrayList<Utilisateur> selectAll() {
        ArrayList<Utilisateur> users = new ArrayList<>();
        ResultSet rs;
        try {

            //rs = ste.executeQuery("SELECT (id,photo_profil,langitude,latitude,username,username_canonical,email,email_canonical,enabled,salt,password) FROM `utilisateur`");
            rs = ste.executeQuery("SELECT * FROM utilisateur");
            users = new ArrayList<>();
            while (rs.next()){
                users.add(new Utilisateur(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getShort(10),rs.getString(11),rs.getString(12),rs.getDate(13),rs.getString(14),rs.getDate(15),serializePHPtoJava(rs.getString(16)),rs.getString(17)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  users;
    }
    @Override
    public ArrayList<Utilisateur> selectAllEnabled() {
        ArrayList<Utilisateur> users = new ArrayList<>();
        ResultSet rs;
        try {
            rs = ste.executeQuery("SELECT * FROM utilisateur where enabled=1");
            users = new ArrayList<>();
            while (rs.next()){
                users.add(new Utilisateur(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getShort(10),rs.getString(11),rs.getString(12),rs.getDate(13),rs.getString(14),rs.getDate(15),serializePHPtoJava(rs.getString(16)),rs.getString(17)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  users;
    }
    @Override
    public Utilisateur selectOne(int id) {
        Statement ste=null;
        Utilisateur user=new Utilisateur();
        ResultSet rs=null;
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery("SELECT * from Utilisateur where id="+id);
            if(rs.next()) {
                user=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getShort(10),rs.getString(11),rs.getString(12),rs.getDate(13),rs.getString(14),rs.getDate(15),serializePHPtoJava(rs.getString(16)),rs.getString(17));
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    @Override
    public Utilisateur selectOne(String username) {
        Statement ste=null;
        Utilisateur user=new Utilisateur();
        ResultSet rs=null;
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery("SELECT * from Utilisateur where username="+username);
            if(rs.next()) {
                user=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getShort(10),rs.getString(11),rs.getString(12),rs.getDate(13),rs.getString(14),rs.getDate(15),serializePHPtoJava(rs.getString(16)),rs.getString(17));
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public ArrayList<Utilisateur> selectByName(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIdAdded() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nbRevues(int iduser) {
        int count=0;
            ResultSet rs;
        count = getCount(iduser, count, "SELECT count(*) FROM Revue where utilisateur_id=");
        return count;
    }


    @Override
    public int nbExperiences(int iduser) {
        int count=0;
        ResultSet rs;
        count = getCount(iduser, count, "SELECT count(*) FROM Experience where utilisateur_id=");
        return count;
    }
    @Override
    public int  nbEvents(int iduser) {
        int count=0;
        ResultSet rs;
        count = getCount(iduser, count, "SELECT count(*) FROM Evenements where utilisateur_id=");
        return count;
    }
    //Méthode pour compter l'apparition d'une id dans un tableau
    private int getCount(int iduser, int count, String s) {
        ResultSet rs;
        try {
            rs = ste.executeQuery(s +iduser);
            while (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    public String serializePHPtoJava(String role_role){
        String strResultat ="";
        if (role_role!=null) {
            SerializedPhpParser serializedPhpParser = new SerializedPhpParser(role_role);
            Object result = serializedPhpParser.parse();
             strResultat = result.toString();
            strResultat= strResultat.substring(3,strResultat.length()-1);
        }
        return strResultat;
    }
}
