package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{
	
	GamePanel gp;
	public static final String objName = "Door";
	public OBJ_Door(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_interactiveObjects;
		name = objName;
		down1 = createImage("objects", "items/door");
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
		dialogues[0][0] = "This door is locked! It seems that you have to use \na key to open it!"; 
	}
	public void reaction() {
		startDialogue(this, 0);
	}
}
