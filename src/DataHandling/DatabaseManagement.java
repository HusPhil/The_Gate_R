package DataHandling;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import main.GamePanel;

public class DatabaseManagement {
	GamePanel gp;
	public DatabaseManagement(GamePanel gp) {
		this.gp = gp;
	}

	String url = "jdbc:mysql://localhost:3306/gamedb";
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
	
	public void storeFileContentToDatabase(String filePath) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));

            String query = "INSERT INTO player (player_id, player_name, player_score, player_progress, player_savedata) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, generatePlayerID());
            preparedStatement.setString(2, gp.player.name);
            preparedStatement.setInt(3, 200); // Change this to the appropriate integer value
            preparedStatement.setInt(4, 10); // Change this to the appropriate integer value
            preparedStatement.setBytes(5, fileContent);

            preparedStatement.executeUpdate();

            System.out.println("Content from save.dat has been inserted into the database.");
            connection.close();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


	public String retrieveFileContentFromDatabase() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(url, username, password);

	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT player_savedata FROM gamedb");

	        StringBuilder contentBuilder = new StringBuilder();

	        while (resultSet.next()) {
	            String content = resultSet.getString("player_savedata");
	            // Append the retrieved content to the StringBuilder
	            contentBuilder.append(content).append("\n");
	        }

	        connection.close();

	        // Return the accumulated content as a string
	        return contentBuilder.toString();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    return null; // Return null if an exception occurs
	}
	

	public void retrieveAndSaveToFile() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT player_savedata FROM player WHERE player_id = 'PL_58dd5be90'");

            if (resultSet.next()) {
                byte[] content = resultSet.getBytes("player_savedata");
                FileOutputStream fileOutputStream = new FileOutputStream("save03.dat");
                fileOutputStream.write(content);
                fileOutputStream.close();
                System.out.println("Content from the database has been saved to save3.dat.");
            } else {
                System.out.println("No data found in the database.");
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
