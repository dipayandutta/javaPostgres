import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlRetrieveProperties {
	
	public static Properties readProperties() throws IOException {
		
		Properties props = new Properties();
		Path myPath = Paths.get("src/database.properties");
		
		BufferedReader bf = Files.newBufferedReader(myPath,StandardCharsets.UTF_8);
		props.load(bf);
		
		return props;
		
	}
	public static void main(String[] args) {
		
		try {
			Properties props = readProperties();
			String url = props.getProperty("db.url");
			String username = props.getProperty("db.username");
			String password = props.getProperty("db.password");
			
			
			//System.out.println(url+":"+username+":"+password);
			
			String query = "SELECT * FROM authors";
			try(
					Connection con = DriverManager.getConnection(url,username,password);
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					){
				while(rs.next()) {
					System.out.println(rs.getInt(1));
					System.out.println(": ");
					System.out.println(rs.getString(2));
				}
			}
			catch(SQLException ex) {
				Logger lgr = Logger.getLogger(JavaPostgreSqlRetrieveProperties.class.getName());
				lgr.log(Level.SEVERE,ex.getMessage(),ex);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
