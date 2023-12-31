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
		
		
		//Read the tiledata file
		InputStream is = getClass().getResourceAsStream("/maps/WorldMapData.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		//Get tile names  and collsion stats
		String line;
		
		try {
			while((line = br.readLine()) != null) {
				fileNames.add(line);
				colStat.add(br.readLine());
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tile = new Tile[fileNames.size()];
		getTileImage();
		
		is = getClass().getResourceAsStream("/maps/worldMapA.txt");
		br = new BufferedReader(new InputStreamReader(is));
		
		try {
			String line2 = br.readLine();
			String mapSize[] = line2.split(" ");
			
			gp.maxWorldCol = mapSize.length;
			gp.maxWorldRow = mapSize.length;
			
			mapTileNum = new int [gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
			
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		loadMap("/maps/worldMapA.txt", gp.worldMapA);
		loadMap("/maps/dungeonMap_F1.txt", gp.dungeonMap_F1);
		loadMap("/maps/merchantHouse.txt", gp.merchantHouse);
		loadMap("/maps/dungeonMap_F2.txt", gp.dungeonMap_F2);
		
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
	
	public void draw(Graphics2D g2) { 
		int worldCol = 0; 
		int worldRow = 0;
;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			int screenX = worldX - gp.player.worldX + gp.player.screenX; 
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(gp.player.screenX > gp.player.worldX) screenX = worldX;
			if(gp.player.screenY > gp.player.worldY) screenY = worldY;
			
			int rightOffset = gp.screenWidth - gp.player.screenX;
			if(rightOffset > (gp.worldWidth - gp.player.worldX))
				screenX = gp.screenWidth - (gp.worldWidth - worldX);
			
			int bottomOffset = gp.screenHeight - gp.player.screenY;			
			if(bottomOffset > (gp.worldHeight - gp.player.worldY))
				screenY = gp.screenHeight - (gp.worldHeight - worldY);
			
			
			
			if(gp.player.screenX > gp.player.worldX ||
					gp.player.screenY > gp.player.worldY ||
					rightOffset > (gp.screenWidth - gp.player.worldX) ||
					bottomOffset > (gp.screenHeight - gp.player.worldY))
				g2.drawImage(tile[tileNum].tileIMG, screenX, screenY, null);
			else
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				
				g2.drawImage(tile[tileNum].tileIMG, screenX, screenY, null);
			}
			
			
			worldCol++;
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
			
		}
//		g2.setColor(Color.red);
//		for(int i = 0; i < gp.pathFinder.pathList.size(); i++) {
//			int worldX = gp.pathFinder.pathList.get(i).col * gp.tileSize;
//			int worldY = gp.pathFinder.pathList.get(i).row * gp.tileSize;
//			
//			int screenX = worldX - gp.player.worldX + gp.player.screenX;
//			int screenY = worldY - gp.player.worldY + gp.player.screenY;
//			
//			g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//		if(!drawPath) {
//			
//			}
//		}
	}
	
}
