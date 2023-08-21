package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wooden_Shield extends Entity{

	public OBJ_Wooden_Shield(GamePanel gp) {
		super(gp);
		type = type_shield;
		dropChance = 15;
		name = "Wooden Shield";
		down1 = createImage("objects", "equips/shield_wood");
		defVal = 1;
		description = "["+name+"]" + "\nA wooden shield, made \nby wood. It does not \nseem like it can last long." + "\n-SHIELD-";
	}

}
