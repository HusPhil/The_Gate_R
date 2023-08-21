package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_IronDoor extends Entity{

	GamePanel gp;
	public static final String objName = "Iron Door";
	public OBJ_IronDoor(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_interactiveObjects;
		name = objName;
		down1 = createImage("objects", "items/door_iron");
		collision = true;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		defaultSolidAreaX = solidArea.x; 
		defaultSolidAreaY = solidArea.y; 
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "This door is so heavy! You see something connecting it\n"
				+ "to a metal plate!"; 
	}
	public void reaction() {
		startDialogue(this, 0);
	}

}
