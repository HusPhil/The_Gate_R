package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wooden_Shield extends Entity{
	public static final String objName = "Wooden Shield";
	public OBJ_Wooden_Shield(GamePanel gp) {
		super(gp);
		coin = 1000;
		type = type_shield;
		motion_duration1 = 15;
		
		itm_id = "ITM22";
		motion_duration2 = 50;
		dropChance = 15;
		name = objName;
		down1 = createImage("objects", "equips/shield_wood");
		defVal = 5;
		description = "["+name+"]" + "\nA shield made of wood.\nIt's old but still useful!" + "\n-SHIELD-";
	}

}
