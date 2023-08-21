package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class ITM_Key extends Entity{
	
	public ITM_Key(GamePanel gp) {
		super(gp);
		name = "Key";
		down1 = createImage("objects", "items/key");
		collision = true;
		description = "["+name+"]" + "\nA glistesning item; seems \nlike it is used to open \nsomething.";
	}
}
