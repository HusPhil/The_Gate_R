package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	public static final String objName = "Key";
	public OBJ_Key(GamePanel gp) {
		super(gp);
		name = objName;
		stackable = true;
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/objects/items/key.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		collision = true;
	}
}
