package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Hermit extends Entity{


	public NPC_Hermit(GamePanel gp) {
		super(gp);
		int i = rN.nextInt(4)+1;
		type = type_npc;
		if(i ==1) direction = "up";
		if(i ==2) direction = "down";
		if(i ==3) direction = "left";
		if(i ==4) direction = "right";
		direction = "up";
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(12, 18, 24, 30);
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		pathAI = true;
		
		projectile = new SKL_MudBall(gp);
		speed = 1;
		getNpcImage();
		setDialouge();
	}

	private void getNpcImage() {
		up1 = createImage("npc", "up0");
		up2 = createImage("npc", "up1");
		up3 = createImage("npc", "up2");
		up4 = createImage("npc", "up3");
		
		down1 = createImage("npc", "down0");
		down2 = createImage("npc", "down1");
		down3 = createImage("npc", "down2");
		down4 = createImage("npc", "down3");
		
		right1 = createImage("npc", "left0");
		right2 = createImage("npc", "left1");
		right3 = createImage("npc", "left2");
		right4 = createImage("npc", "left3");
		
		left1 = createImage("npc", "right0");
		left2 = createImage("npc", "right1");
		left3 = createImage("npc", "right2");
		left4 = createImage("npc", "right3");
	}
	
	public void setAction() {
		if(pathAI) {
			searchPath(15, 27);
		}
		else {
			actionDelay++;
			if(actionDelay == 120 ) {
				int n = rN.nextInt(100)+1;
				
				if(n<=25) direction = "up";
				if(n>=25 && n<=50) direction = "down";
				if(n>=50 && n<=75) direction = "left";
				if(n>=75 && n<=100) direction = "right";
				actionDelay = 0;
			}
//			if(!projectile.alive && shotCounter == 30) {
//				projectile.set(worldX, worldY, true, direction, this);
//				for(int i = 0; i < gp.projectiles[1].length; i++) {
//					if(gp.projectiles[gp.currentMap][i] == null) {
//						gp.projectiles[gp.currentMap][i] = projectile;
//						break;
//					}
//				}
//				shotCounter = 0;
//				projectile.setDamage();
//			}
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
		dialogues[0][0] = "And with that, the year has ended and we now enter a \nnew year! HA!";
		dialogues[0][1] = "What was that? Everyone was so loud you can barely \nenjoy the celebration?";
		dialogues[0][2] = "Idiot. Those annoying sound was the celebration itself.";
		dialogues[0][3] = "Well, so many things happened last year. Lots of sad \nand happy memories passed.";
		dialogues[0][4] = "That said, a happy new year to you lad! Now  go take \ncare of those slimes!";
		dialogues[0][19] = "Hakdogs on your adventure boya..";
		
		dialogues[1][0] = "Some says no one has ever returned sane..";
		dialogues[1][1] = ".. after coming to this island";
		dialogues[1][2] = "What's that? You're scared already?";
		
		dialogues[2][0] = "Well, Good luck. Be well on your adventure boya..";
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	


}
