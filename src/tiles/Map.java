package tiles;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Map extends TileManager{
	GamePanel gp;
	BufferedImage worldMap[];
	public boolean minimapON = false;
	
	public Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		createWorldMap();
	}
	public void createWorldMap() {
		worldMap = new BufferedImage[gp.maxMap];
		int worldMapW = (gp.tileSize)*gp.maxWorldCol;
		int worldMapH = (gp.tileSize)*gp.maxWorldRow;
		
		for(int i = 0; i < gp.maxMap; i++) {
			worldMap[i] = new BufferedImage(worldMapW, worldMapH, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 =  (Graphics2D)worldMap[i].createGraphics();
			
			int col = 0; int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				int tileNum = mapTileNum[i][col][row];
				int x = gp.tileSize*col; int y = gp.tileSize*row;
				g2.drawImage(tile[tileNum].tileIMG, x, y, null);
				
				col++;
				if(col == gp.maxWorldCol) {
					col =  0;
					row++;
				}
			}
			g2.dispose();
		}
	}
	public void drawFullMapScreen(Graphics2D g2) {
		//background color
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		//draw map
		int width = 700;
		int height = 700;
		int x = gp.screenWidth/2 - (width/2);
		int y = gp.screenHeight/2 - (height/2);
		g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);
		//draw player
		double scale = (double)(gp.tileSize*gp.maxWorldCol)/width;
		int playerX = (int)(x+gp.player.worldX/scale);
		int playerY = (int)(y+gp.player.worldY/scale);
		int playerSize = (int)(gp.tileSize/scale)*2	;
		g2.drawImage(gp.player.getCurrentImage(), playerX, playerY, playerSize, playerSize, null);
	}
	public void drawMiniMap(Graphics2D g2) {
		if(minimapON) {
			//draw map
			int width = 200;
			int height = 200;
			int x = gp.screenWidth - ((width/2) + gp.tileSize*3);
			int y = gp.tileSize;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
			g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);
			//draw player
			double scale = (double)(gp.tileSize*gp.maxWorldCol)/width;
			int playerX = (int)(x+gp.player.worldX/scale);
			int playerY = (int)(y+gp.player.worldY/scale);
			int playerSize = (int)(gp.tileSize/scale);
			g2.drawImage(gp.player.getCurrentImage(), playerX, playerY, playerSize, playerSize, null);
		}
	}
}
