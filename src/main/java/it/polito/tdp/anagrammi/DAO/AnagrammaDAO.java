package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammaDAO {
	
	public boolean isCorrect(String anagramma) {
		
		final String sql = "SELECT COUNT(*) AS n "
				+ "FROM parola "
				+ "WHERE parola.nome=?";
		
		int n=0;
		
		try{
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet res = st.executeQuery();
			
			res.next();
			
			n = res.getInt("n");
			
			res.close();
			st.close();
			conn.close();
			
			if(n==1)
				return true;
			else
				return false;
			
		}catch(SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
