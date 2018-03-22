/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
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
        System.out.println("Utilisateur Service");
        try {
            ste=DataSource.getInstance().getCon().createStatement();
            System.out.println("Connection établie");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void ajouter(Utilisateur user) {
        String req = "INSERT INTO Utilisateur (photo_profil,langitude,latitude,username,username_canonical,email,email_canonical,enabled,salt,password,roles) VALUES (?,?,?,?,?,?,?,?,?,?,?)" ;
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(req);
            pre.setString(1,user.getPhotoProfil());
            pre.setDouble(2,user.getLangitude());
            pre.setDouble(3,user.getLatitude());
            pre.setString(4, user.getUsername());
            pre.setString(5, user.getUsernameCanonical());
            pre.setString(6, user.getEmail());
            pre.setString(7, user.getEmailCanonical());
            pre.setShort(8, user.getEnabled());
            //Some thing wrong : exp in database {username:Zain,salt:'0Yi3LZANkpfMsnhbn2XHA00cASLCGVfWc7TJWNOjXsk')
            pre.setString(9, user.getSalt());
            //Some thing wrong : exp in database {username:Zain,passowrd:'qXSSYBDXWQA/ZcbPVOoBKzd5oshTkQP0Q3AeEilnh47Mcrc9uUZYDYwmRJiMKc7nRPvRx6k0eEJrc6HrrDvZtQ==')
            pre.setString(10, user.getPassword());
            //This Role must be unserialised(the equivalent unserialize method in php)
            pre.setString(11,user.getRoles());

            pre.executeUpdate();
            System.out.println("Utilisateur ajouter avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
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
        String requete="UPDATE Utilisateur set id=?,photoProfil=?,langitude=?,latitude=?,username=?,usernameCanonical=?,email=?,emailCanonical=?,enabled=?,salt=?,password=?,roles where id=?";
        PreparedStatement pre=null;
        try {
            pre = connection.prepareStatement(requete);
            pre.setString(1,user.getPhotoProfil());
            pre.setDouble(2,user.getLangitude());
            pre.setDouble(3,user.getLatitude());
            pre.setString(4, user.getUsername());
            pre.setString(5, user.getUsernameCanonical());
            pre.setString(6, user.getEmail());
            pre.setString(7, user.getEmailCanonical());
            pre.setShort(8, user.getEnabled());
            //Some thing wrong : exp in database {username:Zain,salt:'0Yi3LZANkpfMsnhbn2XHA00cASLCGVfWc7TJWNOjXsk')
            pre.setString(9, user.getSalt());
            //Some thing wrong : exp in database {username:Zain,passowrd:'qXSSYBDXWQA/ZcbPVOoBKzd5oshTkQP0Q3AeEilnh47Mcrc9uUZYDYwmRJiMKc7nRPvRx6k0eEJrc6HrrDvZtQ==')
            pre.setString(10, user.getPassword());
            //This Role must be unserialised(the equivalent unserialize method in php)
            pre.setString(11,user.getRoles());
            pre.setInt(12,id);
        } catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Utilisateur> selectAll() {
        List<Utilisateur> etablissements = new ArrayList<>();
        ResultSet rs;
        try {
            rs = ste.executeQuery("SELECT (id,nom,adresse,gouvernorat,ville,note,horraire,longitude,latitude,estActive,type,description,photo) FROM Etablissement");
            etablissements = new ArrayList<>();
            while (rs.next()){

                etablissements.add(new Utilisateur(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getShort(9),rs.getString(10),rs.getString(11),rs.getString(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etablissements;
    }

    @Override
    public Utilisateur selectOne(int id) {
        Statement ste=null;
        Utilisateur user=new Utilisateur();
        ResultSet rs=null;
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery("SELECT * from Etablissement where id="+id);
            if(rs.next())
                user=new Utilisateur(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getShort(9),rs.getString(10),rs.getString(11),rs.getString(12));
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return user;
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
    public List<Utilisateur> selectByName(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> selectByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
