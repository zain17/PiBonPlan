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
                    if (System.getProperty("os.name").equals("Linux") )
                        Class.forName("org.mariadb.jdbc.Driver");
                    else
                        Class.forName("org.mysql.jdbc.Driver");

                   con = DriverManager.getConnection(url, user, password);
                   System.out.println("Connexion établie!");
		}

		catch (SQLException e)  {
			Logger.getLogger("DataSource: "+ e.getMessage());

		} catch (ClassNotFoundException e) {
            e.printStackTrace();
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
