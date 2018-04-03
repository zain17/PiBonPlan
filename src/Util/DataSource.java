/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
/**
 *
 * @author AmineAmri
 */
public class DataSource {
        private static DataSource data;
	private Connection con;
	public String user = "root";
        //BE Carful password is empty here
	public String password = "";
	public String url = "jdbc:mysql://localhost:3306/bonplan";
        
	private DataSource() {          
		try {                    
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(url, user, password);
                    }
                    catch (ClassNotFoundException e)  {
                        System.out.println("Connexion Failed ClassNotFoundException");
                    }
                    catch (SQLException e)  {
			Logger.getLogger("DataSource loading error");
                        System.out.println("Connexion Failed SQLException");
                    }
                
	}

	public static DataSource getInstance() {		
		if (data == null)
			data = new DataSource();
		return data;

	}

	public Connection getCon() {
		return con;
	}
}
