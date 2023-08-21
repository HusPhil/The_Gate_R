package object;


import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	GamePanel gp;
	Graphics2D g2;
	public static final String objName = "Chest";
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		
		image2 = createImage("objects", "items/OpenedChest");
		this.gp = gp;
		type = type_interactiveObjects;
		name = objName;
		
		
		down1 = createImage("objects", "items/ClosedChest");
		collision = true;
		
		description = "["+name+"]" + "\nIt feels heavy, maybe it \ncontains a lot of items!";
		
	}
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You opened the chest. ";
		dialogues[0][1] = "You opened the chest. " + "You found a " + loot.name + "!";
		dialogues[0][2] = "You opened the chest. " + "You found a " + loot.name + "!\n..You tried to pick it up but your bag is full.";
		
		dialogues[1][0] = "You opened the chest. ";
		dialogues[1][1] = "You opened the chest. " + "You found a " + loot.name+ "!";
		dialogues[1][2] = "You opened the chest. You found a " + loot.name + "!\n..You obtained a " + loot.name +" .";
		
		dialogues[2][0] = "The chest was opened.";
		dialogues[2][1] = "The chest was opened. It's empty.";
		
	}
	public void reaction() {
		if(!opened) {
			if(!gp.player.itemObtainable(loot)) {
				startDialogue(this, 0);
			}
			else {
				startDialogue(this, 1);
				down1 = image2;
				opened = true;
			}
		}
		else startDialogue(this, 2);
	}
}
