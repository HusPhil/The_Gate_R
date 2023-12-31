package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wooden_Shield extends Entity{
	public static final String objName = "Wooden Shield";
	public OBJ_Wooden_Shield(GamePanel gp) {
		super(gp);
		coin = 150;
		type = type_shield;
		motion_duration1 = 15;
		motion_duration2 = 50;
		dropChance = 15;
		name = objName;
		down1 = createImage("objects", "equips/shield_wood");
		defVal = 1;
		description = "["+name+"]" + "\nA wooden shield, made \nby wood. It does not \nseem like it can last long." + "\n-SHIELD-";
	}

}
