package object;


import entity.Entity;
import main.GamePanel;

public class ITM_WaterCrystal extends Entity{
	GamePanel gp;
	public static final String  objName = "Water Crystal";
	public ITM_WaterCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_questItem;
		stackable = true;
		dropChance = 90;
		name = objName;
		down1 = createImage("objects", "items/water_crystal");
		collision = false;
		description = "["+name+"]" + "\nCrafted by the witch!\nIt feels very cold.";
	}
	
}
