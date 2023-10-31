package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Iron_Axe extends Entity{
	public static final String objName = "Iron Axe";
	public OBJ_Iron_Axe(GamePanel gp) {

		super(gp);
		// TODO Auto-generated constructor stub
		knockBackPower = 6;
		coin = 100;
		type = type_axe;
		dropChance = 5;
		name = objName;
		down1 = createImage("objects", "equips/axe_iron");
		description = "["+name+"]" + "\nAn old-looking axe, made \nby wood. It might still \nbe able to cut some trees." + "\n-AXE-";
		
		//attributes
		atkVal = 3;
		
		attackAreaX.x = 0;
		attackAreaX.width = 32;
		attackAreaX.height = 25;
		
		attackAreaY.x = 20;
		attackAreaY.width = 18;
		attackAreaY.height = 32;
		
		motion_duration1 = 8;
		motion_duration2 = 18;
	}
}