package com.hy.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hy.pojos.Invite;

/**
 *
 * @author Harold
 */
public class Dao {
	
	Connection con = null;
    PreparedStatement pst = null;
    Statement statement = null;
	
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
	
	
	public void  executeQuery(String query) throws Exception{
		try {
            con = getConnection();
            statement = con.createStatement();
         // execute statement
    		System.out.println("executing query: "+ query);
    		statement.executeUpdate(query);
    		
            
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
	
	public Object executeSelect(String query, String keyword){
		try {
			con = getConnection();
			pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery(query);
			switch (keyword) {
			case "all_invites":
				return doHandleAllInvites(rs);

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}


	private List<Invite> doHandleAllInvites(ResultSet rs) {
		try {
				List<Invite> listInvites = new ArrayList<>();
				
				while(rs.next()){
					Invite inv = new Invite();
					inv.setId(rs.getInt("invite_id"));
					inv.setNomInvite(rs.getString("nom_invite"));
					inv.getGroupe().setId(rs.getInt("groupe_id"));
					inv.getGroupe().setNomGroupe("nom_groupe");
					inv.getTable().setNumeroTable(rs.getInt("num_table"));
					inv.getTable().setNomTable(rs.getString("nom_table"));	
					
					listInvites.add(inv);
				}
				return listInvites;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	
}
	
