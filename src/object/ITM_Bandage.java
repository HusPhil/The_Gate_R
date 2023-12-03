package object;


import entity.Entity;
import main.GamePanel;

public class ITM_Bandage extends Entity{
	GamePanel gp;
	public static final String  objName = "Bandage";
	public ITM_Bandage(GamePanel gp) {
		super(gp);
		this.gp = gp;
		itm_id = "ITM01";
		type = type_materials;
		stackable = true;
		dropChance = 90;
		name = objName;
		down1 = createImage("objects", "items/bandage");
		collision = false;
		coin = 80;
		description = "["+name+"]" + "\nDropped by a mummy!\nIt smells so bad!\n"
				+ "*Material*";
	}
	
}
