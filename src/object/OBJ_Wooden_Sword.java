package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wooden_Sword extends Entity{

	public OBJ_Wooden_Sword(GamePanel gp) {
		super(gp);
		type = type_sword;
		dropChance = 12;
		name = "Wooden Sword";
		down1 = createImage("objects", "equips/sword_wood");
		description = "["+name+"]" + "\nA sword you picked \nup on the way. Made of \nwood, does not seem so \nstrong" + "\n-SWORD-";
		
		//attributes
		atkVal = 2;
		
		attackAreaX.width = 38;
		attackAreaX.height = 25;
		
		attackAreaY.width = 18;
		attackAreaY.height = 38;
	}

}
