package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wooden_Sword extends Entity{
	public static final String objName = "Wooden Sword";
	public OBJ_Wooden_Sword(GamePanel gp) {
		super(gp);
		coin = 888888880;
		type = type_sword;
		dropChance = 12;
		name = objName;
		knockBackPower = 2;
		down1 = createImage("objects", "equips/sword_wood");
		description = "["+name+"]" + "\nA sword you picked \nup on the way. Made of \nwood, does not seem so \nstrong" + "\n-SWORD-";
		
		//attributes
//		atk = 20;
		atkVal = 5;
		
		attackAreaX.width = gp.tileSize;
		attackAreaX.height = 25;
		
		attackAreaY.width = 30;
		attackAreaY.height = gp.tileSize;
		
		motion_duration1 = 5;
		motion_duration2 = 10;
	}

}
