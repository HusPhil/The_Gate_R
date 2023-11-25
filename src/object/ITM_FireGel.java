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
		dropChance = 90;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "items/fire_gel");
		description = "["+name+"]" + "\nYou got it from a fire slime! \nIts so squishy but so hot!"
				+ "\n*Material*";
	}
	
}
