package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Soldier extends Entity{
	//DialogueSets
	public static final int warningA = 0;
	public static final int warningB = 1;

	//SearchPaths
	public static final int oldManFreed = 1;
	public static final int oldManExplain = 2;
	
	public static final String NPC_Name = "Soldier";
	public NPC_Soldier(GamePanel gp) {
		super(gp);
		type = type_npc;
		direction = "down";
		
		name = NPC_Name;
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(gp.tileSize/4, gp.tileSize/4, gp.tileSize/2, gp.tileSize-(gp.tileSize/4));
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		pathAI = true;
		currentSearchPath = pathOFF;
		
		speed = 0;
		getNpcImage();
		setDialouge();
	}

	private void getNpcImage() {
		up1 = createImage("npc", "soldier/up0");
		up2 = createImage("npc", "soldier/up0");
		up3 = createImage("npc", "soldier/up0");
		up4 = createImage("npc", "soldier/up0");

		down1 = createImage("npc", "soldier/down0");
		down2 = createImage("npc", "soldier/down0");
		down3 = createImage("npc", "soldier/down0");
		down4 = createImage("npc", "soldier/down0");

		right1 = createImage("npc", "soldier/right0");
		right2 = createImage("npc", "soldier/right0");
		right3 = createImage("npc", "soldier/right0");
		right4 = createImage("npc", "soldier/right0");

		left1 = createImage("npc", "soldier/left0");
		left2 = createImage("npc", "soldier/left0");
		left3 = createImage("npc", "soldier/left0");
		left4 = createImage("npc", "soldier/left0");

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
		dialogues[warningA][i] = "Hey..!"; i++;
		dialogues[warningA][i] = "You may not pass here!"; i++;
		
		i = 0;
		dialogues[warningB][i] = "You there!"; i++;
		dialogues[warningB][i] = "Give respect to her highness!"; i++;
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	
	public void update() {
			spriteNum = 1;
			speed = 0;
	}


}
