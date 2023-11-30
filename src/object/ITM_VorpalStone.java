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
		type = type_questItem;
		stackable = true;
		name = objName;
		down1 = createImage("objects", "vorpal_stone");
		description = "["+name+"]" + "\nA magical crystal.\n It looks cursed..!" + "\n-CONSUMABLE-";
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You used the " + name + ". It shimmered with an otherworldy glow!";
	}
	public void use(Entity ent) {
		System.out.println("test");
		
		int itemIndex = gp.player.searchItemInInventory(OBJ_Lantern.objName);
		
		gp.player.inventory.get(itemIndex).lightRadius = 275;
		gp.eventHandler.transition(gp.finalStage, 34, 37, gp.dungeon);
		
		ammount--;
		if(ammount <= 0) gp.player.inventory.remove(this);
	}
}
