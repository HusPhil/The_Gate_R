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

import entity.NPC_Cursed_Villager;
import entity.NPC_Hermit;
import entity.NPC_Knight;
import entity.NPC_Princess;
import entity.NPC_VillagerBoy;
import entity.NPC_VillagerGirl;
import entity.NPC_Witch;
import main.GamePanel;
import monster.BOSS_SkeletonLord;
import monster.BOSS_WaterGolem;

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

	public void loadGameProgress() {
		
		
		if(GameProgress.intro_done) {
			int mapNum = gp.silvioVillage;
			
			if(GameProgress.oldManQuest2Explained) {
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(gp.npc[mapNum][i] == null) {
						
						gp.npc[mapNum][i] = new NPC_VillagerBoy(gp);
						gp.npc[mapNum][i].worldX = 23*gp.tileSize;
						gp.npc[mapNum][i].worldY = 11*gp.tileSize; i++;

						gp.npc[mapNum][i] = new NPC_VillagerGirl(gp);
						gp.npc[mapNum][i].worldX = 16*gp.tileSize;
						gp.npc[mapNum][i].worldY = 16*gp.tileSize; i++;
						
						gp.npc[mapNum][i] = new NPC_VillagerBoy(gp);
						gp.npc[mapNum][i].worldX = 23*gp.tileSize;
						gp.npc[mapNum][i].worldY = 20*gp.tileSize; i++;
						
						gp.npc[mapNum][i] = new NPC_VillagerGirl(gp);
						gp.npc[mapNum][i].worldX = 28*gp.tileSize;
						gp.npc[mapNum][i].worldY = 19*gp.tileSize; i++;
						
						gp.npc[mapNum][i] = new NPC_VillagerBoy(gp);
						gp.npc[mapNum][i].worldX = 34*gp.tileSize;
						gp.npc[mapNum][i].worldY = 20*gp.tileSize; i++;
						
						gp.npc[mapNum][i] = new NPC_VillagerGirl(gp);
						gp.npc[mapNum][i].worldX = 30*gp.tileSize;
						gp.npc[mapNum][i].worldY = 21*gp.tileSize; i++;
						break;
					}
				}
				for(int i = 0; i < gp.IT_Manager[1].length; i++) {
					if(gp.IT_Manager[gp.forest][i] != null && gp.IT_Manager[gp.forest][i].name.equals("cs_sect1")) {
						gp.IT_Manager[gp.forest][i] = null;
					}
				}
				
			}
			else {
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(gp.npc[mapNum][i] == null) {
						gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
						gp.npc[mapNum][i].worldX = 23*gp.tileSize;
						gp.npc[mapNum][i].worldY = 11*gp.tileSize; i++;

						gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
						gp.npc[mapNum][i].worldX = 16*gp.tileSize;
						gp.npc[mapNum][i].worldY = 16*gp.tileSize; i++;
						
						gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
						gp.npc[mapNum][i].worldX = 23*gp.tileSize;
						gp.npc[mapNum][i].worldY = 20*gp.tileSize; i++;
						
						gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
						gp.npc[mapNum][i].worldX = 28*gp.tileSize;
						gp.npc[mapNum][i].worldY = 19*gp.tileSize; i++;
						
						gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
						gp.npc[mapNum][i].worldX = 34*gp.tileSize;
						gp.npc[mapNum][i].worldY = 20*gp.tileSize; i++;
						
						gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
						gp.npc[mapNum][i].worldX = 30*gp.tileSize;
						gp.npc[mapNum][i].worldY = 21*gp.tileSize; i++;
						
						break;
					}
					
				}
			}
			}
		else {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.introduction;
		}
			
			
		if(GameProgress.witchQuest1Complete) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioHouse][i] != null && gp.npc[gp.silvioHouse][i].name.equals(NPC_Witch.NPC_Name)) {
					gp.npc[gp.silvioHouse][i].dialogueSet = NPC_Witch.quest1e;
				}
			}
		}
		
		if(GameProgress.waterGolemDefeated) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.sacredRiver][i] != null && gp.monsters[gp.sacredRiver][i].name.equals(BOSS_WaterGolem.monName)) {
					gp.monsters[gp.sacredRiver][i] = null;
					System.out.println("WHYYYY!");
				}
			}
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioHouse][i] != null && gp.npc[gp.silvioHouse][i].name.equals(NPC_Hermit.NPC_Name)) {
					gp.npc[gp.silvioHouse][i].dialogueSet = NPC_Hermit.defeatedGolemA;
				}
			}
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioHouse][i] != null && gp.npc[gp.silvioHouse][i].name.equals(NPC_Witch.NPC_Name)) {
					gp.npc[gp.silvioHouse][i].dialogueSet = NPC_Witch.quest1e;
				}
			}
		}
		if(GameProgress.knightEncountered) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioHouse][i] != null && gp.npc[gp.silvioHouse][i].name.equals(NPC_Witch.NPC_Name)) {
					gp.npc[gp.silvioHouse][i].dialogueSet = NPC_Witch.princessInfo;
				}
			}
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] == null) {
					gp.npc[gp.forest][i] = new NPC_Knight(gp);
					gp.npc[gp.forest][i].dialogueSet = NPC_Knight.princessInfoC;
					gp.npc[gp.forest][i].speed = 0;
					gp.npc[gp.forest][i].worldX = 33*gp.tileSize;
					gp.npc[gp.forest][i].worldY = 15*gp.tileSize; i++;
					break;
				}
			}
			for(int i = 0; i < gp.IT_Manager[1].length; i++) {
				if(gp.IT_Manager[gp.forest][i] != null && gp.IT_Manager[gp.forest][i].name.equals("cs_sect2")) {
					gp.IT_Manager[gp.forest][i] = null;
				}
			}
		}
		if(GameProgress.defeatedSkeletonLord) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.dungeonMap_F2][i] != null && gp.monsters[gp.dungeonMap_F2][i].name.equals(BOSS_SkeletonLord.monName)) {
					gp.monsters[gp.dungeonMap_F2][i] = null;
				}
			}
		}
		if(GameProgress.princessEncountered) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] != null && 
				gp.npc[gp.forest][i].name.equals(NPC_Knight.NPC_Name)) {
					gp.npc[gp.forest][i] = null;
				}
			}
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.princessCage][i] != null && 
				gp.npc[gp.princessCage][i].name.equals(NPC_Princess.NPC_Name)) {
					gp.npc[gp.princessCage][i] = null;
				}
			}
		}
		if(GameProgress.princessReunited) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioHouse][i] == null) {
					gp.npc[gp.silvioHouse][i] = new NPC_Princess(gp);
					gp.npc[gp.silvioHouse][i].dialogueSet = NPC_Princess.playerRequestD;
					gp.npc[gp.silvioHouse][i].worldX = 19*gp.tileSize;
					gp.npc[gp.silvioHouse][i].worldY =  22*gp.tileSize;
					gp.npc[gp.silvioHouse][i].speed = 0;
					gp.npc[gp.silvioHouse][i].currentSearchPath = NPC_Princess.pathOFF;
					gp.npc[gp.silvioHouse][i].direction = "left";
					gp.npc[gp.silvioHouse][i].lockDirection = true;
					break;
				}
			}
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioHouse][i] != null && gp.npc[gp.silvioHouse][i].name.equals(NPC_Hermit.NPC_Name)) {
					gp.npc[gp.silvioHouse][i].dialogueSet = NPC_Hermit.princessReunitedA;
					gp.npc[gp.silvioHouse][i].direction = "right";
					gp.npc[gp.silvioHouse][i].lockDirection = true;
				}
			}
		}
	}
}
































