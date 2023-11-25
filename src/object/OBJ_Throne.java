package object;



import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Throne extends Entity{
	
	GamePanel gp;
	public static final String objName = "Throne";
	public OBJ_Throne(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_interactiveObjects;
		name = objName;
		down1 = createImage("objects", "throne", gp.tileSize, gp.tileSize*2);
		collision = true;
		solidArea = new Rectangle(0, gp.tileSize*4/5, gp.tileSize, (gp.tileSize*2)-(7*gp.tileSize/5));
		defaultSolidAreaX = solidArea.x; 
		defaultSolidAreaY = solidArea.y; 
		setDialogue();
//		debugOn = true;
	}
	public void setDialogue() {
//		dialogues[0][0] = "This door is locked! It seems that you have to use \na key to open it!"; 
	}
	public void reaction() {
//		startDialogue(this, 0);
	}
}
