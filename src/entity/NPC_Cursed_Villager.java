package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Cursed_Villager extends Entity{
	//DialogueSets
	public static final int cursed_talk = 0;

	//SearchPaths
	public static final int oldManFreed = 1;
	public static final int oldManExplain = 2;
	
	public static final String NPC_Name = "Cursed Villager";
	public NPC_Cursed_Villager(GamePanel gp) {
		super(gp);
		int i = rN.nextInt(4)+1;
		type = type_npc;
		direction = "down";
//		direction = "dwo";
		
		name = NPC_Name;
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(gp.tileSize/4, gp.tileSize/4, gp.tileSize/2, gp.tileSize-(gp.tileSize/4));
//		solidArea = new Rectangle(8, 10, 16, 22);
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		pathAI = true;
		currentSearchPath = pathOFF;
		
		speed = 1;
		dialogueSet = cursed_talk;
		getNpcImage();
		setDialouge();
	}

	private void getNpcImage() {
		up1 = createImage("npc", "villager_cursed/up0");
		up2 = createImage("npc", "villager_cursed/up1");
		up3 = createImage("npc", "villager_cursed/up2");
		up4 = createImage("npc", "villager_cursed/up3");

		down1 = createImage("npc", "villager_cursed/down0");
		down2 = createImage("npc", "villager_cursed/down1");
		down3 = createImage("npc", "villager_cursed/down2");
		down4 = createImage("npc", "villager_cursed/down3");

		right1 = createImage("npc", "villager_cursed/right0");
		right2 = createImage("npc", "villager_cursed/right1");
		right3 = createImage("npc", "villager_cursed/right2");
		right4 = createImage("npc", "villager_cursed/right3");

		left1 = createImage("npc", "villager_cursed/left0");
		left2 = createImage("npc", "villager_cursed/left1");
		left3 = createImage("npc", "villager_cursed/left2");
		left4 = createImage("npc", "villager_cursed/left3");

	}
	
	public void setAction() {
		switch (currentSearchPath) {
		case pathOFF: 
			
			actionDelay++;
			if(actionDelay == 120 ) {
				int n = rN.nextInt(100)+1;
				
				if(n<=25) direction = "up";
				if(n>=25 && n<=50) direction = "down";
				if(n>=50 && n<=75) direction = "left";
				if(n>=75 && n<=100) direction = "right";
				actionDelay = 0;
			}
			break;
		case oldManFreed: searchPath(gp.player.getPlayerWordlX(), gp.player.getPlayerWordlY()); break;
		case oldManExplain: searchPath(27,27); break;
		}
		
	}
	public void projectileAction() {
		if(gp.keys.fireAway && !projectile.alive && 
				   shotCounter == 30 && projectile.sufficientResource(this)) {
					projectile.set(worldX, worldY, true, direction, this);
					for(int i = 0; i < gp.projectiles[1].length; i++) {
						if(gp.projectiles[gp.currentMap][i] == null) {
							gp.projectiles[gp.currentMap][i] = projectile;
							break;
						}
					}
					projectile.useResource(this);
					shotCounter = 0;
				} 
	}
	public void setDialouge() {
		int i = 0;
		dialogues[cursed_talk][i] = "mekus mekus.."; i++;
		dialogues[cursed_talk][i] = "mekus mekus.. mekuss mekuss...!"; i++;
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	


}
