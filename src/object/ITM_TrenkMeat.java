package object;


import entity.Entity;
import main.GamePanel;

public class ITM_TrenkMeat extends Entity{
	GamePanel gp;
	public static final String  objName = "Trenk Meat";
	public ITM_TrenkMeat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_materials;
		stackable = true;
		dropChance = 90;
		name = objName;
		down1 = createImage("objects", "items/trenkmeat");
		collision = false;
		description = "["+name+"]" + "\nYou got it from a trenklin! \nIt smells so bad!\n"
				+ "*Material*";
	}
	
}
