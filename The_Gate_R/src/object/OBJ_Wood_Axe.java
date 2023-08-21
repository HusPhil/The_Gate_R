package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wood_Axe extends Entity{
	public OBJ_Wood_Axe(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		type = type_axe;
		dropChance = 5;
		name = "Wooden Axe";
		down1 = createImage("objects", "equips/axe_wood");
		description = "["+name+"]" + "\nAn old-looking axe, made \nby wood. It might still \nbe able to cut some trees." + "\n-AXE-";
		
		//attributes
		atkVal = 3;
		attackAreaX.width = 32;
		attackAreaX.height = 25;
		
		attackAreaY.width = 18;
		attackAreaY.height = 32;
	}
}
