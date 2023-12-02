package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Iron_Shield extends Entity{
	public static final String objName = "Slime Shield";
	public OBJ_Iron_Shield(GamePanel gp) {
		super(gp);
		type = type_shield;
		dropChance = 11;
		coin = 2200;
		name = objName;
		down1 = createImage("objects", "equips/shield_slime");
		defVal = 3;
		description = "["+name+"]" + "\nA shield made of iron!\nIt's shiny and sturdy!" + "\n-SHIELD-";
	}

}
