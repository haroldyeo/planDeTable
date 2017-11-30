package com.hy.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Harold
 */
public class Dao {
	
	Connection con = null;
    PreparedStatement pst = null;
	
	public static final String DB_DRIVER = "org.sqlite.JDBC";
	public static final String DB_CONNECTION = "jdbc:sqlite:C:/2017/planDeTable/pdtDB.sqlite";
			

	public Connection getConnection() throws Exception{
		Connection con = null;
		try{
			Class.forName(DB_DRIVER);
		}catch(ClassNotFoundException e){
			throw new Exception("Une erreur est survenue");
		}
		
		try {
			con = DriverManager.getConnection(DB_CONNECTION, "", "");
		} catch (SQLException e) {
			throw new Exception("Une erreur est survenue");
		}
		return con;
	}
	
	
	public void executeQuery(String query){
		try {
            con = getConnection();
            pst = con.prepareStatement(query);
         // execute statement
    		ResultSet rs = pst.executeQuery();
            
        } catch (Exception e) {
			e.getMessage();
			throw new Exception("Une erreur est survenue");
		} finally{
			if(pst!=null){
				pst.close();
			}				
			if(con != null){
				con.close();
			}
	}

}
	
	
}
	
