package dbCreateTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tableCreate {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/java";
		String user = "dipayan";
		String password = "nodeMachine";
		
		int id = 8;
		String author = "Nicolai M.";
		String query = "INSERT INTO authors (id,name) VALUES(?,?)";
		
		try(
				Connection con= DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(query);
				)
		{
			pst.setInt(1, id);
			pst.setString(2, author);
			boolean result = (pst.executeUpdate() != 0);
			if (result) {
				System.out.println("Database Table updated!");
			}
			else {
				System.out.println("unable to update Database Table");
			}
		}catch(Exception ex) {
			Logger log = Logger.getLogger(tableCreate.class.getName());
			log.log(Level.SEVERE,ex.getMessage(),ex);
		}
		
	}
}
