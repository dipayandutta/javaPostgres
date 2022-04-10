package dbTableDataShow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlRetrieve {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/java";
		String username = "dipayan";
		String password = "nodeMachine";
		String query = "SELECT * FROM authors";
		
		try(
				Connection con = DriverManager.getConnection(url,username,password);
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				){
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(":");
				System.out.println(rs.getString(2));
			}
			
		}catch(SQLException ex) {
			Logger lgr = Logger.getLogger(JavaPostgreSqlRetrieve.class.getName());
			lgr.log(Level.SEVERE,ex.getMessage(),ex);
		}
	}
}
