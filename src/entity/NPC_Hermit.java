package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Hermit extends Entity{
	//DialogueSets
	public static final int newYear = 0;
	public static final int warnDialogue = 1;
	public static final int goodLuck = 2;
	public static final int encounter = 3;
	public static final int thanking = 4;
	public static final int explaining = 5;
	public static final int explaining_2 = 6;
	public static final int explaining_3 = 7;

	//SearchPaths
	public static final int oldManFreed = 1;
	public static final int oldManExplain = 2;
	
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
		solidArea = new Rectangle(12, 18, 24, 26);
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		pathAI = true;
		currentSearchPath = pathOFF;
		
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
//			searchPath(15, 27);
			
			
////			if(worldX <= 15 || worldY == 26) 
//				pathAI = false;
		}
		
		
		
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
		
//		else {
//			actionDelay++;
//			if(actionDelay == 120 ) {
//				int n = rN.nextInt(100)+1;
//				
//				if(n<=25) direction = "up";
//				if(n>=25 && n<=50) direction = "down";
//				if(n>=50 && n<=75) direction = "left";
//				if(n>=75 && n<=100) direction = "right";
//				actionDelay = 0;
//			}
////			if(!projectile.alive && shotCounter == 30) {
////				projectile.set(worldX, worldY, true, direction, this);
////				for(int i = 0; i < gp.projectiles[1].length; i++) {
////					if(gp.projectiles[gp.currentMap][i] == null) {
////						gp.projectiles[gp.currentMap][i] = projectile;
////						break;
////					}
////				}
////				shotCounter = 0;
////				projectile.setDamage();
////			}
//		}
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
		dialogues[newYear][0] = "And with that, the year has ended and we now enter a \nnew year! HA!";
		dialogues[newYear][1] = "What was that? Everyone was so loud you can barely \nenjoy the celebration?";
		dialogues[newYear][2] = "Idiot. Those annoying sound was the celebration itself.";
		dialogues[newYear][3] = "Well, so many things happened last year. Lots of sad \nand happy memories passed.";
		dialogues[newYear][4] = "That said, a happy new year to you lad! Now  go take \ncare of those slimes!";
		dialogues[newYear][19] = "Hakdogs on your adventure boya..";
		
		dialogues[warnDialogue][0] = "Some says no one has ever returned sane..";
		dialogues[warnDialogue][1] = ".. after coming to this island";
		dialogues[warnDialogue][2] = "What's that? You're scared already?";
		
		dialogues[goodLuck][0] = "Well, Good luck. Be well on your adventure boya..";
		
		int i = 0;
		dialogues[encounter][i] = "Help, help meee!"; i++;
		dialogues[encounter][i] = "Help me, anyone..!"; i++;
		dialogues[encounter][i] = "I might die in here.. *sob *sob .."; i++;
		
		i = 0;
		dialogues[thanking][i] = "Thank you for saving me, young man."; i++;
		dialogues[thanking][i] = "However, you don't look like you're from here.."; i++;
		dialogues[thanking][i] = "Wait.."; i++;
		dialogues[thanking][i] = "Those clothes.. It can't be.."; i++;
		dialogues[thanking][i] = "Tell me, young man, where are you from? "; i++;
		dialogues[thanking][i] = "You wouldn't happen to be a student from BatStateU \nfrom the past are you?"; i++;
		dialogues[thanking][i] = "You are? Well, now it it makes sense why you look \nso confused."; i++;
		dialogues[thanking][i] = "You see, this is stil Earth.. 150 years from your age."; i++;
		dialogues[thanking][i] = "However, this area of the world has now became "
				+ "\ncorrupted because people failed to fulfill the SDGs."; i++;
		dialogues[thanking][i] = "Maybe it was destined that a young man, such as "
				+ "\nyourself, will save this now corrupted world and "
				+ "\ntell the tales of your adventure to your people"
				+ "\nwhen you have finished your quest!"; i++;
		dialogues[thanking][i] = "... ... ... .. ."; i++;
		dialogues[thanking][i] = "No! I have to hurry! My village, it's in danger!"; i++;
		dialogues[thanking][i] = "Will you help me, young man?"; i++;
		dialogues[thanking][i] = ".... .. .. ."; i++;
		dialogues[thanking][i] = "You will!? Thank you so much!!"; i++;
		dialogues[thanking][i] = "Now come, let's go save the village!"; i++;
		
		i = 0;
		dialogues[explaining][i] = "Oh noo...!"; i++;
		dialogues[explaining][i] = "The village! It's being attacked by monsters!"; i++;
		
		i = 0;
		dialogues[explaining_2][i] = "Oh no.. What do we do!?"; i++;
		dialogues[explaining_2][i] = "I want to deafeat these monsters but my already"
				+ "\nweak body cannot defeat these vile monsters.."; i++;
		dialogues[explaining_2][i] = "Young man, would you help me..?"; i++;
		
		
		
		i = 0;
		dialogues[explaining_3][i] = "Thank you young man! here's an old sword of mine!"; i++;
		dialogues[explaining_3][i] = "Go ahead and take care of these monsters!"; i++;
		
		
		
		
		
	}
	public void speak(int dialogueSetNum) {
		facePlayer();
		startDialogue(this, dialogueSetNum);
	}	


}
