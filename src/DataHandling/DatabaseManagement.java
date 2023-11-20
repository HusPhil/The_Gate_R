package DataHandling;

import java.io.FileOutputStream;
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

	static String url = "jdbc:mysql://localhost:3306/gamedb";
	static String username = "root";
	static String password = "";
	
	public static String generatePlayerID() {
		// Generate a random UUID (Universally Unique Identifier)
        UUID uuid = UUID.randomUUID();

        // Convert the UUID to a string and limit its length to 12 characters
        String uuidString = uuid.toString().replaceAll("-", "");
        String truncatedUUID = uuidString.substring(0, 5);

        String player_id = "pl_" + truncatedUUID;
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

	public static boolean checkUserExist(String column, String user) {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection connection = DriverManager.getConnection(url, username, password);

		    String query = "SELECT " + column +" FROM player";
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery(query);

		    while (resultSet.next()) {
		    	String data = resultSet.getString(column);
		    	if(data.equals(user)) return true;
		    }
		    connection.close();
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return false;
	}
	
	public void createPlayerData() {
		

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(url, username, password);

	        String query = "INSERT IGNORE INTO player (player_id, player_name, player_score, player_progress, player_savedata) VALUES (?,?,null,null,null)";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, gp.player.ID);
	        preparedStatement.setString(2, gp.player.name);

	        
	        preparedStatement.executeUpdate();

	        System.out.println("Content has been inserted into the database.");
	        connection.close();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	

	}
	
	public void updatePlayerData(String saveFile) {
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(url, username, password);
	        
	        byte[] playerSavedData = null;
	        
			try {
				playerSavedData = Files.readAllBytes(Paths.get(saveFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        String query = "UPDATE player SET player_score = ?, player_progress = ?, player_savedata = ? WHERE player_id = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, gp.player.score);
	        preparedStatement.setInt(2, gp.player.progress);
	        preparedStatement.setBytes(3, playerSavedData);	        
	        preparedStatement.setString(4, gp.player.ID);

	        preparedStatement.executeUpdate();

	        System.out.println("Player data has been updated in the database.");
	        connection.close();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadPlayerData() {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection connection = DriverManager.getConnection(url, username, password);

		    String query = "SELECT * FROM player WHERE player_id = '" + gp.player.ID + "'";
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery(query);

		    if (resultSet.next()) {
		        gp.player.ID = resultSet.getString("player_id");
		        gp.player.name = resultSet.getString("player_name");
		        gp.player.score = resultSet.getInt("player_score");
		        gp.player.progress = resultSet.getInt("player_progress");
		        
		        byte[] content = resultSet.getBytes("player_savedata");
		        FileOutputStream fileOutputStream = new FileOutputStream("save_data.dat");
		        fileOutputStream.write(content);
		        fileOutputStream.close();
		        System.out.println("Content from the database has been saved to save_data.dat.");
		    } else {
		        System.out.println("No data found in the database.");
		    }
		    connection.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
