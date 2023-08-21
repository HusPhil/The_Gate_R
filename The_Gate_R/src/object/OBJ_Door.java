package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{
	
	public OBJ_Door(GamePanel gp) {
		super(gp);
		name = "Door";
		down1 = createImage("objects", "items/door_iron");
		collision = true;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height =32;
		defaultSolidAreaX = solidArea.x; 
		defaultSolidAreaY = solidArea.y; 
	}
}