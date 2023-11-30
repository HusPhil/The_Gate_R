package object;


import entity.Entity;
import main.GamePanel;

public class ITM_TrenkAmulet extends Entity{
	GamePanel gp;
	public static final String  objName = "Trenk Amulet";
	public ITM_TrenkAmulet(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_questItem;
		stackable = true;
		dropChance = 90;
		name = objName;
		down1 = createImage("objects", "items/trenk_amulet");
		description = "["+name+"]" + "\nGiven to you by the witch! \nIt might save the village!";
	}
	
}
