package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Health_Potion extends Entity{

	GamePanel gp;
	public OBJ_Health_Potion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		dropChance = 30;
		plus = 4;
		type = type_consumables;
		name = "Health Potion";
		down1 = createImage("objects", "potion_health");
		description = "["+name+"]" + "\nA red mystical liquid. It \nsmells awful." + "\n-CONSUMABLE-";
	}
	public void use(Entity ent) {
		gp.gameState = gp.dialougeState;
		gp.gui.cD = "You drank the " + name + ". You \nfeel relieved of your pain.";
		gp.player.life+=plus;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
	}
}
