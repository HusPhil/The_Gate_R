package object;

import entity.Entity;
import main.GamePanel;

public class ITM_VorpalGem extends Entity{

	GamePanel gp;
	public static final String objName = "Vorpal Gem";
	public ITM_VorpalGem(GamePanel gp) {
		super(gp);
		this.gp = gp;
		coin = 25;
		dropChance = 50;
		plus = 2;
		type = type_questItem;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "vorpal_gem");
		description = "["+name+"]" + "\nA magical crystal.\n It looks cursed..!" + "\n-CONSUMABLE-";
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You used the " + name + ". It shimmered with an otherworldy glow!";
	}
	public void use(Entity ent) {
		CS_ending();
		ammount--;
		if(ammount <= 0) gp.player.inventory.remove(this);
	}
	private void CS_ending() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.ending;
	}
}
