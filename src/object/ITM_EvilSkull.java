package object;


import entity.Entity;
import main.GamePanel;

public class ITM_EvilSkull extends Entity{
	GamePanel gp;
	public static final String  objName = "Evil Skull";
	public ITM_EvilSkull(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_questItem;
		stackable = true;
		dropChance = 90;
		name = objName;
		down1 = createImage("objects", "items/skull");
		collision = false;
		description = "["+name+"]" + "\nYou got it by slaying the\nEvil Skeleton Lord!\nIt's a very eerie item..";
	}
	
}
