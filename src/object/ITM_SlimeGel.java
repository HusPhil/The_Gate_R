package object;


import entity.Entity;
import main.GamePanel;

public class ITM_SlimeGel extends Entity{
	GamePanel gp;
	public static final String  objName = "Slime Gel";
	public ITM_SlimeGel(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_materials;
		dropChance = 90;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "items/slime_gel");
		collision = true;
		description = "["+name+"]" + "\nYou got it from a slime! \nIts so squishy!"
				+ "\n*Material*";
	}
	
}
