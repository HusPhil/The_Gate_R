package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Health_Potion extends Entity{

	GamePanel gp;
	public static final String objName = "Health Potion";
	public OBJ_Health_Potion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		coin = 80;
		dropChance = 50;
		plus = 2;
		type = type_consumables;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "potion_health");
		description = "["+name+"]" + "\nA red mystical liquid. It \nsmells awful." + "\n-CONSUMABLE-";
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You drank the " + name + ". You feel relieved of your \npain.";
	}
	public void use(Entity ent) {
		startDialogue(this, 0);
		gp.player.life+=plus;
		ammount--;
		if(ammount <= 0) gp.player.inventory.remove(this);
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
	}
}
