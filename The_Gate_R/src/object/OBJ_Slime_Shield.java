package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Slime_Shield extends Entity{

	public OBJ_Slime_Shield(GamePanel gp) {
		super(gp);
		type = type_shield;
		dropChance = 10;
		name = "Slime Shield";
		down1 = createImage("objects", "equips/shield_slime");
		defVal = 5;
		description = "["+name+"]" + "\nA slimy-looking shield. \nIt's so sticky, but \nseems like it absorb \ndamage well." + "\n-SHIELD-";
	}

}
