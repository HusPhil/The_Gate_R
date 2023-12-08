package object;


import entity.Entity;
import main.GamePanel;

public class ITM_Key extends Entity{
	GamePanel gp;
	public static final String  objName = "Key";
	
	public ITM_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_questItem;
		stackable = true;
		itm_id = "ITM04";
		name = objName;
		down1 = createImage("objects", "items/key");
		collision = true;
		description = "["+name+"]" + "\nA glistesning item; seems \nlike it is used to open \nsomething.";
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][1] = "You opened the locked door!";
		
		dialogues[1][0] = "Hmm.. this key might be able to open locked doors!";
	}
	public void use(Entity user) {
		gp.gameState = gp.dialogueState;
		int objIndex = getDetectedItem(user, gp.gameObjs, "Door");
		if(objIndex != 777) {
			startDialogue(this, 0);
			ammount--;
			if(ammount <= 0) {
				gp.player.inventory.remove(this);
				gp.gameObjs[gp.currentMap][objIndex] = null;
			}
		} else {
			startDialogue(this, 1);
		}
	}
}
