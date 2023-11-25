package object;



import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Castle extends Entity{
	
	GamePanel gp;
	public static final String objName = "Castle";
	public OBJ_Castle(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_interactiveObjects;
		name = objName;
		down1 = createImage("objects", "castle", gp.tileSize*3, gp.tileSize*3);
		collision = true;
		solidArea = new Rectangle(0, gp.tileSize*3/5, gp.tileSize*3, (gp.tileSize*3)-(3*gp.tileSize/5));
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
