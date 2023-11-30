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
		description = "["+name+"]" + "\nThe remnants of the water Golem!\nMaybe it can be combined!\n with something.."
				+ "*Material*";
	}
	
}
