package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana_Potion extends Entity{

	GamePanel gp;
	public static final String objName = "Mana Potion";
	public OBJ_Mana_Potion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		coin = 200;
		itm_id = "ITM19";
		dropChance = 50;
		plus = 80;
		type = type_consumables;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "potion_mana");
		description = "["+name+"]" + "\nA blue mystical liquid. It \nlooks awful." + "\n-CONSUMABLE-";
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You drank the " + name + ". You feel surge of powerful \nmagical energy.";
	}
	public void use(Entity ent) {
		startDialogue(this, 0);
		gp.player.mana+=plus;
		ammount--;
		if(ammount <= 0) gp.player.inventory.remove(this);
		if(gp.player.mana > gp.player.maxMana) {
			gp.player.mana = gp.player.maxMana;
		}
	}
}
