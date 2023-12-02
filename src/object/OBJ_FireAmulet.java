package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FireAmulet extends Entity{
	GamePanel gp;
	public static final String objName = "Fire Amulet";
	public OBJ_FireAmulet(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_amulet;
		name = objName;
		down1 = createImage("objects", "equips/fire_amulet");
		description = "[" + name + "]" + "\nA powerful magic item!\nAllows you to cast\nfireballs with your hands!";
		coin = 150;
	}
}
