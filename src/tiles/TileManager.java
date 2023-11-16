package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityClass;

public class TileManager {
	GamePanel gp; 
	public int mapTileNum[][][];
	public Tile[] tile;
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> colStat = new ArrayList<>();
	boolean drawPath;
	
	
	
	public TileManager(GamePanel gp) {
	    this.gp = gp;

	    // Read the tiledata file
	    loadTileData("/maps/WorldMapData.txt");

	    tile = new Tile[fileNames.size()];
	    getTileImage();

	    // Initialize map size
	    initializeMapSize("/maps/worldMapA.txt");

	    // Load maps
	    loadMap("/maps/worldMapA.txt", gp.worldMapA);
	    loadMap("/maps/dungeonMap_F1.txt", gp.dungeonMap_F1);
	    loadMap("/maps/merchantHouse.txt", gp.merchantHouse);
	    loadMap("/maps/dungeonMap_F2.txt", gp.dungeonMap_F2);
	    loadMap("/maps/corrupted_area1.txt", gp.corrupted1);
	    loadMap("/maps/silvioVillage.txt", gp.silvioVillage);
	    loadMap("/maps/silvioHouse.txt", gp.silvioHouse);
	    loadMap("/maps/forest.txt", gp.forest);
	    loadMap("/maps/sacredRiver.txt", gp.sacredRiver);
	}

	private void loadTileData(String filePath) {
	    try (InputStream is = getClass().getResourceAsStream(filePath);
	         BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

	        String line;
	        while ((line = br.readLine()) != null) {
	            fileNames.add(line);
	            colStat.add(br.readLine());
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void initializeMapSize(String filePath) {
	    try (InputStream is = getClass().getResourceAsStream(filePath);
	         BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

	        String line = br.readLine();
	        String[] mapSize = line.split(" ");

	        gp.maxWorldCol = mapSize.length;
	        gp.maxWorldRow = mapSize.length;

	        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void loadMap(String filePath, int[][] map) {
	    try (InputStream is = getClass().getResourceAsStream(filePath);
	         BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

	        for (int i = 0; i < gp.maxMap; i++) {
	            for (int j = 0; j < gp.maxWorldCol; j++) {
	                String[] tiles = br.readLine().split(" ");
	                for (int k = 0; k < gp.maxWorldRow; k++) {
	                    mapTileNum[i][j][k] = Integer.parseInt(tiles[k]);
	                }
	            }
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	public void getTileImage() {
		
		for(int i = 0; i < fileNames.size(); i++) {
			String fileName;
			boolean collision;
			
			fileName = fileNames.get(i);
			
			if(colStat.get(i).equals("true")) {
				collision = true;
			} else collision = false;
			
			createTile(fileName, i, collision);
			
		}
		
		
	}
	
	public void createTile(String imageName, int i, boolean col) {
		UtilityClass Utils = new UtilityClass();
		
		try {
			tile[i] = new Tile();
			tile[i].tileIMG = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
			tile[i].tileIMG = Utils.scaleImage(gp.tileSize, gp.tileSize, tile[i].tileIMG);
			tile[i].collisionOn = col;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapName, int map) {
		
		try {
			InputStream is = getClass().getResourceAsStream(mapName);
			BufferedReader br = new BufferedReader(new InputStreamReader (is));
			
			int col = 0;
			int row = 0;
			
			
			
			while  (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
			
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int number = Integer.parseInt(numbers[col]);
					mapTileNum[map][col][row] = number;
					col++;
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}	
			}

			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void draw(Graphics2D g2) {
//	    int tileSize = gp.tileSize;
//	    int screenWidth = gp.screenWidth;
//	    int screenHeight = gp.screenHeight;
//	    int worldWidth = gp.worldWidth;
//	    int worldHeight = gp.worldHeight;
//	    int maxWorldCol = gp.maxWorldCol;
//	    int maxWorldRow = gp.maxWorldRow;
//	    int currentMap = gp.currentMap;
//
//	    int playerWorldX = gp.player.worldX;
//	    int playerWorldY = gp.player.worldY;
//	    int playerScreenX = gp.player.screenX;
//	    int playerScreenY = gp.player.screenY;
//
//	    int worldCol = 0;
//	    int worldRow = 0;
//
//	    while (worldCol < maxWorldCol && worldRow < maxWorldRow) {
//	        int tileNum = mapTileNum[currentMap][worldCol][worldRow];
//
//	        int worldX = worldCol * tileSize;
//	        int worldY = worldRow * tileSize;
//
//	        int screenX = worldX - playerWorldX + playerScreenX;
//	        int screenY = worldY - playerWorldY + playerScreenY;
//
//	        if (screenX + tileSize >= 0 && screenX <= screenWidth && screenY + tileSize >= 0 && screenY <= screenHeight) {
//	            g2.drawImage(tile[tileNum].tileIMG, screenX, screenY, null);
//	        }
//
//	        worldCol++;
//	        if (worldCol == maxWorldCol) {
//	            worldCol = 0;
//	            worldRow++;
//	        }
//	    }
//	}

	
	
	public void draw(Graphics2D g2) {
	    int tileSize = gp.tileSize;
	    int screenWidth = gp.screenWidth;
	    int screenHeight = gp.screenHeight;

	    int playerWorldX = gp.player.worldX;
	    int playerWorldY = gp.player.worldY;
	    int playerScreenX = gp.player.screenX;
	    int playerScreenY = gp.player.screenY;

	    int startCol = Math.max(0, playerWorldX / tileSize - screenWidth / (1 * tileSize));
	    int endCol = Math.min(gp.maxWorldCol, playerWorldX / tileSize + screenWidth / (1 * tileSize) + 1);
	    int startRow = Math.max(0, playerWorldY / tileSize - screenHeight / (1 * tileSize));
	    int endRow = Math.min(gp.maxWorldRow, playerWorldY / tileSize + screenHeight / (1 * tileSize) + 1);

	    for (int worldCol = startCol; worldCol < endCol; worldCol++) {
	        for (int worldRow = startRow; worldRow < endRow; worldRow++) {
	            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

	            int worldX = worldCol * tileSize;
	            int worldY = worldRow * tileSize;

	            int screenX = worldX - playerWorldX + playerScreenX;
	            int screenY = worldY - playerWorldY + playerScreenY;

	            if (screenX + tileSize >= 0 && screenX <= screenWidth && screenY + tileSize >= 0 && screenY <= screenHeight) {
	                g2.drawImage(tile[tileNum].tileIMG, screenX, screenY, null);
	            }
	        }
	    }
	}


	
	
	
	
	
	
	
	
	
	
//	public void draw(Graphics2D g2) { 
//		int worldCol = 0; 
//		int worldRow = 0;
//
//		
//		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
//			
//			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
//			
//			int worldX = worldCol * gp.tileSize;
//			int worldY = worldRow * gp.tileSize;
//			
//			int screenX = worldX - gp.player.worldX + gp.player.screenX; 
//			int screenY = worldY - gp.player.worldY + gp.player.screenY;
//			
//			if(gp.player.screenX > gp.player.worldX) screenX = worldX;
//			if(gp.player.screenY > gp.player.worldY) screenY = worldY;
//			
//			int rightOffset = gp.screenWidth - gp.player.screenX;
//			if(rightOffset > (gp.worldWidth - gp.player.worldX))
//				screenX = gp.screenWidth - (gp.worldWidth - worldX);
//			
//			int bottomOffset = gp.screenHeight - gp.player.screenY;			
//			if(bottomOffset > (gp.worldHeight - gp.player.worldY))
//				screenY = gp.screenHeight - (gp.worldHeight - worldY);
//			
//			
//			
//			if(gp.player.screenX > gp.player.worldX ||
//					gp.player.screenY > gp.player.worldY ||
//					rightOffset > (gp.screenWidth - gp.player.worldX) ||
//					bottomOffset > (gp.screenHeight - gp.player.worldY)) {
//				
//				g2.drawImage(tile[tileNum].tileIMG, screenX, screenY, null);
//			}
////			else
////			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
////			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
////			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
////			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
////				
////				
////				g2.drawImage(tile[tileNum].tileIMG, screenX, screenY, null);
////			}
////			
//			
//			worldCol++;
//			if(worldCol == gp.maxWorldCol) {
//				worldCol = 0;
//				worldRow++;
//			}
//			
//		}
////		g2.setColor(Color.red);
////		for(int i = 0; i < gp.pathFinder.pathList.size(); i++) {
////			int worldX = gp.pathFinder.pathList.get(i).col * gp.tileSize;
////			int worldY = gp.pathFinder.pathList.get(i).row * gp.tileSize;
////			
////			int screenX = worldX - gp.player.worldX + gp.player.screenX;
////			int screenY = worldY - gp.player.worldY + gp.player.screenY;
////			
////			g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
////		if(!drawPath) {
////			
////			}
////		}
//	}
	
}
