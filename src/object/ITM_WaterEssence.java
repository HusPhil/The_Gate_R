package object;


import entity.Entity;
import main.GamePanel;

public class ITM_WaterEssence extends Entity{
	GamePanel gp;
	public static final String  objName = "Water Essence";
	public ITM_WaterEssence(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_questItem;
		stackable = true;
		dropChance = 90;
		name = objName;
		down1 = createImage("objects", "items/water_essence");
		collision = false;
		description = "["+name+"]" + "\nRemnants of the Water\nGolem! Maybe it can be\ncombined with something.."
				+ "\n*Material*";
	}
	
}
