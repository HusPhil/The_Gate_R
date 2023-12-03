package object;


import entity.Entity;
import main.GamePanel;

public class ITM_FireGel extends Entity{
	GamePanel gp;
	public static final String  objName = "Fire Gel";
	public ITM_FireGel(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_materials;
		itm_id = "ITM03";
		dropChance = 90;
		stackable = true;
		coin = 80;
		name = objName;
		down1 = createImage("objects", "items/fire_gel");
		description = "["+name+"]" + "\nDropped by a fire slime! \nIt's squishy but so hot!"
				+ "\n*Material*";
	}
	
}
