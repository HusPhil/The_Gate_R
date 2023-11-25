package interactive_tiles;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;
import object.ITM_Coin;

public class IT_FeebleWall extends InteractiveTiles{

GamePanel gp;
	
	public IT_FeebleWall(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		debugOn = true;
		
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		solidArea.x = 0;
		solidArea.y = 0;
		
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
		life = 2;
		reqItem = type_axe;
		down1 = createImage("interactive_tiles", "feeble_wall");
		destroyOn = true;
	}
	public boolean checkReqItem(Entity item) {
		boolean _item = false;
			if(item.type == type_pickaxe) {
				_item = true;
			}
		return _item;
	}
	public void playSE() {
		gp.playSE(6);
	}
	public InteractiveTiles destroyedForm() {
		InteractiveTiles tile = null;
		dropItem(new ITM_Coin(gp));
		return tile;
	}
	public Color getParticleColor() {
 		Color c = new Color(65, 65, 65);
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
