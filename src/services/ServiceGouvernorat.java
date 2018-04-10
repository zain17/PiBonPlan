package services;

import Util.DataSource;
import entites.Gouvernorat;
import entites.Ville;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceGouvernorat {
    private Statement ste=null;
    private Connection connection = DataSource.getInstance().getCon();

    public ServiceGouvernorat() {
        try {
            ste=DataSource.getInstance().getCon().createStatement();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Gouvernorat> selectAll(){

        ArrayList<Gouvernorat> gouvernorats = new ArrayList<>();
        ResultSet rs;
        try {
            rs = ste.executeQuery("SELECT * FROM Gouvernorat");
            gouvernorats = new ArrayList<>();
            while (rs.next()){
                gouvernorats.add(new Gouvernorat(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  gouvernorats;
    }
    public ArrayList<Gouvernorat> selectAllEager(){
        ServiceVille serviceVille=new ServiceVille();
        ArrayList<Gouvernorat> gouvernorats = new ArrayList<>();
        ResultSet rs;
        try {
            rs = ste.executeQuery("SELECT * FROM Gouvernorat");
            gouvernorats = new ArrayList<>();
            while (rs.next()){
                serviceVille.selectAllByGouvernorat(rs.getInt(1));
                gouvernorats.add(new Gouvernorat(rs.getInt(1),rs.getString(2),serviceVille.selectAllByGouvernorat(rs.getInt(1))));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  gouvernorats;
    }
    public int getGouvernoratIdByHerName(String gouvname){
        ServiceVille serviceVille=new ServiceVille();
        ArrayList<Gouvernorat> gouvernorats = new ArrayList<>();
        ResultSet rs;
        try {
            rs = ste.executeQuery("SELECT * FROM Gouvernorat where name= '"+gouvname+"'");
            gouvernorats = new ArrayList<>();
            while (rs.next()){
                serviceVille.selectAllByGouvernorat(rs.getInt(1));
                gouvernorats.add(new Gouvernorat(rs.getInt(1),rs.getString(2),serviceVille.selectAllByGouvernorat(rs.getInt(1))));
           }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  gouvernorats.get(0).getId();
    }
}
