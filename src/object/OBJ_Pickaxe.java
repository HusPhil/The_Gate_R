package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{

	public static final String objName = "Pickaxe";
	public OBJ_Pickaxe (GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		knockBackPower = 1;
		coin = 150;
		type = type_pickaxe;
		dropChance = 0;
		name = objName;
		down1 = createImage("objects", "equips/pickaxe");
		description = "["+name+"]" + "\nA very old pickaxe, \ncan it knock down walls?" + "\n-PICKAXE-";
		
		//attributes
		atkVal = 3;
		attackAreaX.width = 30;
		attackAreaX.height = 30;
		
		attackAreaY.width = 30;
		attackAreaY.height = 30;
		
		motion_duration1 = 10;
		motion_duration2 = 20;
	}

}
