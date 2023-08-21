package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	GamePanel gp;
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		dropChance = 20;
		type = non_inventory;
		down1 = createImage("objects", "heart_full", gp.tileSize-12, gp.tileSize-12);
		image = createImage("objects", "heart_blank");
		image1 = createImage("objects", "heart_half");
		image2 = createImage("objects", "heart_full");
		name = "Heart";
		plus = 2;
	}
	public void use(Entity user) {
		user.life += plus;
		if(gp.player.life < gp.player.maxLife) gp.gui.addMessage("Life+" + plus +  ".");
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
			gp.gui.addMessage("Life is already full.");
		} 
	}
}
