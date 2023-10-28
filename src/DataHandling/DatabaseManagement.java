package DataHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import main.GamePanel;

public class DatabaseManagement {
	GamePanel gp;
	public DatabaseManagement(GamePanel gp) {
		this.gp = gp;
	}

	String url = "jdbc:mysql://localhost:3306/batstateu";
	String username = "root";
	String password = "";
	
	public static String generatePlayerID() {
		// Generate a random UUID (Universally Unique Identifier)
        UUID uuid = UUID.randomUUID();

        // Convert the UUID to a string and limit its length to 12 characters
        String uuidString = uuid.toString().replaceAll("-", "");
        String truncatedUUID = uuidString.substring(0, 9);

        String player_id = "PL_" + truncatedUUID;
        System.out.println(player_id.length());
        // Return the truncated UUID as the player ID
        return player_id;
    }
	
	public void testPrintData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * FROM Program");
			
//			resultSet.next();
//			System.out.println(resultSet.getString(1));
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1) +" "+resultSet.getString(2)+" ");
			}
			
			connection.close();
		}
		catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public DatabaseManagement() {
	}

}
