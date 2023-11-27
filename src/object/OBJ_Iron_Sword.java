package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Iron_Sword extends Entity{
	public static final String objName = "Iron Sword";
	public OBJ_Iron_Sword(GamePanel gp) {
		super(gp);
		coin = 1000;
		type = type_sword;
		dropChance = 12;
		name = objName;
		knockBackPower = 4;
		down1 = createImage("objects", "equips/sword_iron");
		description = "["+name+"]" + "\nA sword that is much \nsturdier and sharper. \nIt makes you stronger. \n-SWORD-";
		
		//attributes
//		atk = 20;
		atkVal = 10;
		
		attackAreaX.width = 36;
		attackAreaX.height = 25;
		
		attackAreaY.width = 18;
		attackAreaY.height = 36;
		
		motion_duration1 = 8;
		motion_duration2 = 10;
	}

}
