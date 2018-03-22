/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.DataSource;
import entites.Etablissement;

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
 * @author ASUS
 */
public class EtablissementService implements IServiceEtablissement{
    public static int lastInsertedId=0;
    private final Connection con=DataSource.getInstance().getCon();
    private Statement ste=null;
    public EtablissementService() {
        System.out.println("EtablissementService");
                    try {
                             ste=DataSource.getInstance().getCon().createStatement();
                            System.out.println("Connection établie");
                    }
                    catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
    }
    @Override
    public void ajouter(Etablissement t) {
        String req = "INSERT INTO Etablissement (nom,adresse,gouvernorat,ville,note,horraire,longitude,latitude,est_active,type,description,photo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement pre;
        try {
            pre = con.prepareStatement(req);
            int i=1;
            pre.setString(i++, t.getNom());
            pre.setString(i++, t.getAdresse());
            pre.setString(i++, t.getGouvernorat());
            pre.setString(i++, t.getVille());
            pre.setDouble(i++, t.getNote());
            pre.setDate(i++, new Date(2017, 03, 16));
            pre.setDouble(i++, t.getLongitude());
            pre.setDouble(i++, t.getLatitude());
            pre.setBoolean(i++, t.getEstActive());
            pre.setString(i++, t.getType());
            pre.setString(i++, t.getDescription());
            pre.setString(i++, t.getPhoto());	
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
        }		
    }

    @Override
    public void supprimer(int id) {
        String SQL = "DELETE FROM Etablissement WHERE id = ? ";
        PreparedStatement pre = null;
        try {
            // get a connection and then in your try catch for executing your delete... 
            pre = con.prepareStatement(SQL);
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void modifier(int id, Etablissement t) {
        String SQL = "UPDATE Etablissement SET nom=?,adresse=?,gouvernorat=?,ville=?,note=?,horraire=?,longitude=?,latitude=?,est_active=?,type=?,description=?,photo WHERE id = ?";
        
        PreparedStatement pre=null;
        try {  
            pre=con.prepareStatement(SQL);
            int i=1;
            pre.setString(i++, t.getNom());
            pre.setString(i++, t.getAdresse());
            pre.setString(i++, t.getGouvernorat());
            pre.setString(i++, t.getVille());
            pre.setDouble(i++, t.getNote());
            pre.setDate(i++, (Date)t.getHorraire());
            pre.setDouble(i++, t.getLongitude());
            pre.setDouble(i++, t.getLatitude());
            pre.setBoolean(i++, t.getEstActive());
            pre.setString(i++, t.getType());
            pre.setString(i++, t.getDescription());
            pre.setString(i++, t.getPhoto());
            pre.setInt(i++, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    @Override
    public ArrayList<Etablissement> selectAll() {
        ArrayList<Etablissement> etablissements = new ArrayList<>();
        ResultSet rs;
        try {
            rs = ste.executeQuery("SELECT (id,nom,adresse,gouvernorat,ville,note,horraire,longitude,latitude,est_active,type,description,photo) FROM Etablissement");
            etablissements = new ArrayList<>();
		while (rs.next()){
		
		etablissements.add(new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getDate(7),rs.getDouble(8),rs.getDouble(9),rs.getBoolean(10),rs.getString(11),rs.getString(12),rs.getString(13)));
		}
        } catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
        }		
		return etablissements;
    }

    @Override
    public Etablissement selectOne(int id) {
        Statement ste=null;
        Etablissement etab=new Etablissement();

        try {
            ste=con.createStatement();
            final ResultSet rs=ste.executeQuery("SELECT * from etablissement where id="+id);
            System.out.println(rs.getRow());
            if(rs.next())
            {etab=new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getDate(7),rs.getDouble(8),rs.getDouble(9),rs.getBoolean(10),rs.getString(11),rs.getString(12),rs.getString(13));
                System.out.println("Etablissement construite");}
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return etab;
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
    public ArrayList<Etablissement> selectByNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Etablissement> selectByGouvernorat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Etablissement> selectByVille() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Etablissement> selectBest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Etablissement> selectBestByType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Etablissement> selectNear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}