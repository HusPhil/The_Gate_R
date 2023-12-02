package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TerraBlade extends Entity{
	public static final String objName = "Terra Blade";
	public OBJ_TerraBlade(GamePanel gp) {
		super(gp);
		coin = 1000;
		type = type_sword;
		dropChance = 12;
		name = objName;
		knockBackPower = 3;
		down1 = createImage("objects", "equips/terra");
		description = "["+name+"]" + "\nCrafted by the princess\nand imbued with magic. \nIt's very powerful\n-SWORD-";
		
		//attributes
//		atk = 20;
		atkVal = 10;
		
		attackAreaX.width = 36;
		attackAreaX.height = 25;
		
		attackAreaY.width = 18;
		attackAreaY.height = 36;
		
		motion_duration1 = 4;
		motion_duration2 = 8;
	}

}
