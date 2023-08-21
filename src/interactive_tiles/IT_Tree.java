package interactive_tiles;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_Tree extends InteractiveTiles{
	GamePanel gp;
	
	public IT_Tree(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		life = 3;
		reqItem = type_axe;
		down1 = createImage("interactive_tiles", "tree_interactive");
		destroyOn = true;
	}
	public boolean checkReqItem(Entity item) {
		boolean _item = false;
			if(item.type == type_axe) {
				_item = true;
			}
		return _item;
	}
	public void playSE() {
		gp.playSE(4);
	}
	public InteractiveTiles destroyedForm() {
		InteractiveTiles tile = new IT_TrunkBroke(gp, worldX/gp.tileSize, worldY/gp.tileSize);
			
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











