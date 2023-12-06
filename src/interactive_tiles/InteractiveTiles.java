package interactive_tiles;

import entity.Entity;
import main.GamePanel;

public abstract class InteractiveTiles extends Entity{
	GamePanel gp;
	public int reqItem;
	public boolean destroyOn = false;
	
	public InteractiveTiles(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
	}
	public void update() {
		if(invincible) {
			invincibleTime++;
			if(invincibleTime == 20) {
				invincible = false;
				invincibleTime = 0; 					
			}
		}
	}
	public abstract boolean checkReqItem(Entity item);
	public void playSE() {
		
	}
	public InteractiveTiles destroyedForm() {
		InteractiveTiles tile = null;
		return tile;
	}
}
