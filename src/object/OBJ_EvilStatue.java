package object;


import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_EvilStatue extends Entity{
	GamePanel gp;
	Graphics2D g2;
	public static final String objName = "Statue";
	public OBJ_EvilStatue(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_interactiveObjects;
		name = objName;
		
		collision = true;
		description = "["+name+"]" + "\nIt feels heavy, maybe it \ncontains a lot of items!";
		down1 = createImage("objects", "statue", gp.tileSize*3, gp.tileSize*3);
		solidArea = new Rectangle(0, gp.tileSize*3/5, gp.tileSize*3, (gp.tileSize*3)-(3*gp.tileSize/5));

		defaultSolidAreaX = solidArea.x; 
		defaultSolidAreaY = solidArea.y; 
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
		
		dialogues[3][0] = "You opened the chest. ";
		dialogues[3][1] = "You opened the chest. " + "You found a " + loot.name + "!";
		dialogues[3][2] = "You opened the chest. You found a " + loot.name + "!\n..You obtained a 100 coins.";
	}
	
	public void reaction() {
		
		
		
		if(!opened) {
			if(!gp.player.itemObtainable(loot)) {
				startDialogue(this, 0);
			}
			else if(loot.name.equals(ITM_Coin.objName)) {
				gp.player.coin += 100;
				startDialogue(this, 3);
				down1 = image2;
				opened = true;
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
