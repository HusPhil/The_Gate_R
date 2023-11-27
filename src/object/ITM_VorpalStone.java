package object;

import entity.Entity;
import main.GamePanel;

public class ITM_VorpalStone extends Entity{

	GamePanel gp;
	public static final String objName = "Vorpal Stone";
	public ITM_VorpalStone(GamePanel gp) {
		super(gp);
		this.gp = gp;
		coin = 25;
		dropChance = 50;
		plus = 2;
		type = type_consumables;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "vorpal_stone");
		description = "["+name+"]" + "\nA heart in crystal form. It \nIncreases your max life!" + "\n-CONSUMABLE-";
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You used the " + name + ". It shimmered with an otherworldy glow!";
	}
	public void use(Entity ent) {
		startDialogue(this, 0);
		gp.eventHandler.transition(gp.finalStage, 34, 37, gp.outside);
		ammount--;
		if(ammount <= 0) gp.player.inventory.remove(this);
	}
}
