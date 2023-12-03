package object;

import entity.Entity;
import main.GamePanel;

public class ITM_Map extends Entity{

	GamePanel gp;
	public static final String objName = "Map";
	public ITM_Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		dropChance = 50;
		itm_id = "ITM24";
		plus = 2;
		type = type_questItem;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "items/map");
		description = "["+name+"]" + "\nAn old map found in\nthe dungeon! Very\n"
				+ "useful when travelling" + "\n-CONSUMABLE-";
	}
	public void use(Entity ent) 
	{
		gp.gameState = gp.viewMapState;
	}

}
