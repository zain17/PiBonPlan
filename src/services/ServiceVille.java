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

public class ServiceVille {
    private Statement ste=null;
    private Connection connection = DataSource.getInstance().getCon();

    public ServiceVille() {
        try {
            ste=DataSource.getInstance().getCon().createStatement();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Ville> selectAllByGouvernorat(int gouvId){
        ArrayList<Ville> villes = new ArrayList<>();
        ResultSet rs;
        try {
            rs = ste.executeQuery("SELECT * FROM ville where id_gouvernorat="+gouvId);
            villes = new ArrayList<>();
            while (rs.next()){
                villes.add(new Ville(rs.getInt(1),rs.getString(2),new Gouvernorat(gouvId)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  villes;
    }
//    public ArrayList<Ville> selectAllByGouvernoratId(String stringg){
//        ArrayList<Ville> villes = new ArrayList<>();
//        ResultSet rs;
//        try {
//            rs = ste.executeQuery("SELECT * FROM Ville where id_gouvernorat=ville='"+stringg+"'");
//            villes = new ArrayList<>();
//            while (rs.next()){
//                villes.add(new Ville(rs.getInt(1),rs.getString(2)));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return  villes;
//    }
}
