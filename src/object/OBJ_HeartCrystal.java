package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HeartCrystal extends Entity{

	GamePanel gp;
	public static final String objName = "Heart Crystal";
	public OBJ_HeartCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		coin = 25;
		dropChance = 50;
		itm_id = "ITM14";
		plus = 2;
		type = type_consumables;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "heart_crystal");
		description = "["+name+"]" + "\nA heart in crystal form. It \nIncreases your max life!" + "\n-CONSUMABLE-";
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You used the " + name + ". You feel more lively!";
	}
	public void use(Entity ent) {
		startDialogue(this, 0);
		gp.player.maxLife+=2;
		ammount--;
		if(ammount <= 0) gp.player.inventory.remove(this);
	}
}
