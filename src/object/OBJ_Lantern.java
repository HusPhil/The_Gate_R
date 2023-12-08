package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{
	GamePanel gp;
	public static final String objName = "Lantern";
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_lightSource;
		name = objName;
		itm_id = "ITM18";
		down1 = createImage("objects", "equips/lantern");
		description = "[" + name + "]" + "\nIt gives off light; very \nuseful in the darkness.";
		coin = 150;
		lightRadius = 200;
	}
}
