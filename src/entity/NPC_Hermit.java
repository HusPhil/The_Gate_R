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
	public static final int intro_end = 8;
	public static final int intro_end_2 = 9;
	public static final int intro_end_3 = 10;
	public static final int oldManQ2a = 11;
	public static final int oldManQ2b = 12;
	public static final int oldManQ2c = 13;

	//SearchPaths
	public static final int find_player = 1;
	public static final int oldManExplain = 2;
	public static final int oldManFindHome = 3;
	
	public static final String NPC_Name = "Silvio";
	public NPC_Hermit(GamePanel gp) {
		super(gp);
		int i = rN.nextInt(4)+1;
		type = type_npc;
		name = NPC_Name;
		if(i ==1) direction = "up";
		if(i ==2) direction = "down";
		if(i ==3) direction = "left";
		if(i ==4) direction = "right";
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(gp.tileSize/4, gp.tileSize/4, gp.tileSize/2, gp.tileSize-(gp.tileSize/4)); //for ts = 48
//		solidArea = new Rectangle(8, 10, 16, 22); //for ts = 32
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
		up1 = createImage("npc", "old_man/up0");
		up2 = createImage("npc", "old_man/up1");
		up3 = createImage("npc", "old_man/up2");
		up4 = createImage("npc", "old_man/up3");
		
		down1 = createImage("npc", "old_man/down0");
		down2 = createImage("npc", "old_man/down1");
		down3 = createImage("npc", "old_man/down2");
		down4 = createImage("npc", "old_man/down3");
		
		right1 = createImage("npc", "old_man/left0");
		right2 = createImage("npc", "old_man/left1");
		right3 = createImage("npc", "old_man/left2");
		right4 = createImage("npc", "old_man/left3");
		
		left1 = createImage("npc", "old_man/right0");
		left2 = createImage("npc", "old_man/right1");
		left3 = createImage("npc", "old_man/right2");
		left4 = createImage("npc", "old_man/right3");
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
		case find_player: searchPath(gp.player.getPlayerWordlX(), gp.player.getPlayerWordlY()); break;
		case oldManExplain: searchPath(27,27); break;
		case oldManFindHome: searchPath(28,12); break;
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
		dialogues[explaining_3][i] = "Thank you young man!"; i++;
		dialogues[explaining_3][i] = "Here's an old sword and old shield of mine!"; i++;
		dialogues[explaining_3][i] = "Go ahead and take care of these monsters!"; i++;
		dialogues[explaining_3][i] = "(Press the enter key to attack!)"; i++;
		dialogues[explaining_3][i] = "(Press letter C to view your inventory!)"; i++;
		
		i = 0;
		dialogues[intro_end][i] = "Thank you again, young man!"; i++;
		dialogues[intro_end][i] = "You saved the village, and owe my life to you!"; i++;
		dialogues[intro_end][i] = "Oh, by the way, my name is Silvio! I'm sorry for"
				+ "\nthe late introduction..."; i++;
		
		i = 0;
		dialogues[intro_end_2][i] = "What is this !!?"; i++;
		dialogues[intro_end_2][i] = "The people, what happened to them!?"; i++;
		dialogues[intro_end_2][i] = "...!"; i++;
		dialogues[intro_end_2][i] = "Don't tell me, are they cursed!?"; i++;
		dialogues[intro_end_2][i] = "Young man! I shall now give you your first quest!"; i++;
		dialogues[intro_end_2][i] = "Fulfill SDG #3 and provide the people with"
				+ "\n'Good Health and Well-being'!"; i++;
		dialogues[intro_end_2][i] = "There seems to be a witch living down south!"
				+ "\nask her for further instruction!"; i++;
		dialogues[intro_end_2][i] = "I have to rest in my home for the day.."; i++;
		dialogues[intro_end_2][i] = "Hurry now! Good luck on your adventure, young man!"; i++;
		
		i = 0;
		dialogues[intro_end_3][i] = "Good luck on your adventure young man!"; i++;
		
		
		i = 0;
		dialogues[oldManQ2a][i] = "You're back, young man!"; i++;
		dialogues[oldManQ2a][i] = "I'm very glad to see you in one piece!"; i++;
		dialogues[oldManQ2a][i] = "So.. How was you adventure to the witch?"; i++;
		
		i = 0;
		dialogues[oldManQ2b][i] = "Th-this is.."; i++;
		dialogues[oldManQ2b][i] = "This is a Trenk Amulet!, you say!? And it might break\n"
				+ "break the curse of the villagers!?"; i++;
		dialogues[oldManQ2b][i] = "I can't thank you enough young man! I truly owe a lot to you!"; i++;
		
		i = 0;
		dialogues[oldManQ2c][i] = "I see, she told you about the 17 Harmonial Principles, huh?"; i++;
		dialogues[oldManQ2c][i] = "And, about the tree monster, what she said was also true.\n"
				+ "That monster is the root of all of this. Hence,\n"
				+ "you must hurry and defeat that monster as soon\n"
				+ "as possible!"; i++;
		
		dialogues[oldManQ2c][i] = "Well, for now, let us try and break the curse first!"; i++;
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	


}
