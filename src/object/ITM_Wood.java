package object;


import entity.Entity;
import main.GamePanel;

public class ITM_Wood extends Entity{
	GamePanel gp;
	public static final String  objName = "Wood";
	public ITM_Wood(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_materials;
		dropChance = 90;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "wood");
		description = "["+name+"]" + "\nAcquire by cutting dry trees! \nIs it still usable?"
				+ "\n*Material*";
	}
	
}
