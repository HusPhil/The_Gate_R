package interactive_tiles;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import object.ITM_Coin;
import object.OBJ_TerraBlade;

public class IT_TrenkHeart extends InteractiveTiles{
	GamePanel gp;
	public static String IT_Name = "Trenk Heart";
	public IT_TrenkHeart(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
//		debugOn = true;
		
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		solidArea.x = 0;
		solidArea.y = 0;
		name = IT_Name;
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
		life = 4000;
		reqItem = type_axe;
		down1 = createImage("interactive_tiles", "trenkheart", gp.tileSize*3, gp.tileSize*3);
		solidArea = new Rectangle(0, gp.tileSize*3/5, gp.tileSize*3, (gp.tileSize*3)-(3*gp.tileSize/5));
		destroyOn = true;
	}
	public boolean checkReqItem(Entity item) {
		boolean _item = false;
			if(item.name.equals(OBJ_TerraBlade.objName)) {
				_item = true;
			}
		return _item;
	}
	public void playSE() {
		gp.playSE(4);
	}
	public InteractiveTiles destroyedForm() {
		InteractiveTiles tile = null;
		return tile;
	}
	public Color getParticleColor() {
 		Color c = new Color(65, 50, 30);
		return c;
	}
	public int getParticleSize() {
		int size = 6;
		return size;
	}
	public int getParticleSpeed(){
		int pSpeed = 1;
		return pSpeed;
	}
	public int getParticleLife() { 
		int maxLife = 20;
		return maxLife;
	}
}











