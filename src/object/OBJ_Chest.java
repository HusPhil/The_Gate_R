package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		name = "Chest";
		down1 = createImage("objects", "items/chest");
		collision = true;
		description = "["+name+"]" + "\nIt feels heavy, maybe it \ncontains a lot of items!";
		
	}
}
