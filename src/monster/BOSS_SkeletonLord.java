package monster;

import DataHandling.GameProgress;
import entity.Entity;
import main.GamePanel;
import main.SoundHandler;
import object.ITM_EvilSkull;
import object.ITM_WaterEssence;
import object.OBJ_IronDoor;

public class BOSS_SkeletonLord extends Entity{
	GamePanel gp;
	
	public static String monName = "Skeleton Lord";
	public BOSS_SkeletonLord(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//Stats
		type = type_monster;
		name = monName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 1200;
		attacking = !true;
		
		type_boss = true;
		life = maxLife;
		atk = 10;
		def = 6;
		exp = 50;
		
		//SolidArea
		int size = 5 * gp.tileSize;
		
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48*2;
		solidArea.height = size - 48;
		
		attackArea.width = 170;
		attackArea.height = 170;
		
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		knockBackPower = 6;
		
		motion_duration1 = 25;
		motion_duration2 = 50;
		
		asleep = true;
		//Set the image
		getWalkingImage();
		getAttackingImage();
		setDialogue();
	}

	public void getWalkingImage() {
		int scale = 5;
		 
		up1 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_up_1", gp.tileSize*scale, gp.tileSize*scale);
		up2 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_up_2", gp.tileSize*scale, gp.tileSize*scale);
		up3 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_up_1", gp.tileSize*scale, gp.tileSize*scale);
		up4 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_up_2", gp.tileSize*scale, gp.tileSize*scale);
		
		down1 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_down_2", gp.tileSize*scale, gp.tileSize*scale);
		down3 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down4 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_down_2", gp.tileSize*scale, gp.tileSize*scale);
		
		right1 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_right_1", gp.tileSize*scale, gp.tileSize*scale);
		right2 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_right_2", gp.tileSize*scale, gp.tileSize*scale);
		right3 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_right_1", gp.tileSize*scale, gp.tileSize*scale);
		right4 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_right_2", gp.tileSize*scale, gp.tileSize*scale);
		
		left1 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_left_1", gp.tileSize*scale, gp.tileSize*scale);
		left2 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_left_2", gp.tileSize*scale, gp.tileSize*scale);
		left3 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_left_1", gp.tileSize*scale, gp.tileSize*scale);
		left4 = createImage("monsters", "boss_skeletonlord/walking/skeletonlord_left_2", gp.tileSize*scale, gp.tileSize*scale);
		
		
		if(enraged) {
			up1 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_up_1", gp.tileSize*scale, gp.tileSize*scale);
			up2 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_up_2", gp.tileSize*scale, gp.tileSize*scale);
			up3 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_up_1", gp.tileSize*scale, gp.tileSize*scale);
			up4 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_up_2", gp.tileSize*scale, gp.tileSize*scale);
			
			down1 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_down_1", gp.tileSize*scale, gp.tileSize*scale);
			down2 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_down_2", gp.tileSize*scale, gp.tileSize*scale);
			down3 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_down_1", gp.tileSize*scale, gp.tileSize*scale);
			down4 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_down_2", gp.tileSize*scale, gp.tileSize*scale);
			
			right1 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_right_1", gp.tileSize*scale, gp.tileSize*scale);
			right2 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_right_2", gp.tileSize*scale, gp.tileSize*scale);
			right3 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_right_1", gp.tileSize*scale, gp.tileSize*scale);
			right4 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_right_2", gp.tileSize*scale, gp.tileSize*scale);
			
			left1 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_left_1", gp.tileSize*scale, gp.tileSize*scale);
			left2 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_left_2", gp.tileSize*scale, gp.tileSize*scale);
			left3 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_left_1", gp.tileSize*scale, gp.tileSize*scale);
			left4 = createImage("monsters", "boss_skeletonlord/walking/phase2/skeletonlord_phase2_left_2", gp.tileSize*scale, gp.tileSize*scale);
			
		}
	}
	public void getAttackingImage() {
		int scale = 5;
		
		attackUp1 = createImage("monsters", "boss_skeletonlord/attacking/skeletonlord_attack_up_1", gp.tileSize*scale, gp.tileSize*scale*2);
		attackUp2 = createImage("monsters", "boss_skeletonlord/attacking/skeletonlord_attack_up_2", gp.tileSize*scale, gp.tileSize*scale*2);
		
		
		attackDown1 = createImage("monsters", "boss_skeletonlord/attacking/skeletonlord_attack_down_1", gp.tileSize*scale, gp.tileSize*scale*2);
		attackDown2 = createImage("monsters", "boss_skeletonlord/attacking/skeletonlord_attack_down_2", gp.tileSize*scale, gp.tileSize*scale*2);
		
		
		attackLeft1 = createImage("monsters", "boss_skeletonlord/attacking/skeletonlord_attack_left_1", gp.tileSize*scale*2, gp.tileSize*scale);
		attackLeft2 = createImage("monsters", "boss_skeletonlord/attacking/skeletonlord_attack_left_2", gp.tileSize*scale*2, gp.tileSize*scale);
		
		attackRight1 = createImage("monsters", "boss_skeletonlord/attacking/skeletonlord_attack_right_1", gp.tileSize*scale*2, gp.tileSize*scale);
		attackRight2 = createImage("monsters", "boss_skeletonlord/attacking/skeletonlord_attack_right_2", gp.tileSize*scale*2, gp.tileSize*scale);

		
		
		if(enraged) {
			attackUp1 = createImage("monsters", "boss_skeletonlord/attacking/phase2/skeletonlord_phase2_attack_up_1", gp.tileSize*scale, gp.tileSize*scale*2);
			attackUp2 = createImage("monsters", "boss_skeletonlord/attacking/phase2/skeletonlord_phase2_attack_up_2", gp.tileSize*scale, gp.tileSize*scale*2);
			
			
			attackDown1 = createImage("monsters", "boss_skeletonlord/attacking/phase2/skeletonlord_phase2_attack_down_1", gp.tileSize*scale, gp.tileSize*scale*2);
			attackDown2 = createImage("monsters", "boss_skeletonlord/attacking/phase2/skeletonlord_phase2_attack_down_2", gp.tileSize*scale, gp.tileSize*scale*2);
			
			
			attackLeft1 = createImage("monsters", "boss_skeletonlord/attacking/phase2/skeletonlord_phase2_attack_left_1", gp.tileSize*scale*2, gp.tileSize*scale);
			attackLeft2 = createImage("monsters", "boss_skeletonlord/attacking/phase2/skeletonlord_phase2_attack_left_2", gp.tileSize*scale*2, gp.tileSize*scale);
			
			attackRight1 = createImage("monsters", "boss_skeletonlord/attacking/phase2/skeletonlord_phase2_attack_right_1", gp.tileSize*scale*2, gp.tileSize*scale);
			attackRight2 = createImage("monsters", "boss_skeletonlord/attacking/phase2/skeletonlord_phase2_attack_right_2", gp.tileSize*scale*2, gp.tileSize*scale);

		}
		
		attackUp3 = attackUp1;
		attackUp4 = attackUp2;
		attackDown3 = attackDown1;
		attackDown4 = attackDown2;
		attackLeft3 = attackLeft1;
		attackLeft4 = attackLeft2;
		attackRight3 = attackRight1;
		attackRight4 = attackRight2;
	}
	public void setAction() {
		
		if(!enraged && life < maxLife/2) {
			enraged = true;
			
			motion_duration1 = 20;
			motion_duration2 = 35;
			
			defaultSpeed = 6;
			speed = defaultSpeed;
			atk *= 1.5;
			getAttackingImage();
			getWalkingImage();
		}
		
		if(getTileDistance(gp.player) < 10) huntPlayer(30);
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
			
		}
		
		if (!attacking) {
			int rate = 45;
			setAttackState(gp.tileSize*3, gp.tileSize*2, rate);
			
		}
		
		
		
	}
	public void damageReaction() {
		actionDelay = 0;
		
		pathAI = true;
	}
	public void checkDrop() {
		gp.bossBattleOn = false;
		GameProgress.defeatedSkeletonLord = true;
		
		//stopTheBossMusic
		//playDungeonMusic
		dropItem(new ITM_EvilSkull(gp));
		//open iron doors
		
		for(int i = 0; i < gp.gameObjs[1].length; i++) {
			if(gp.gameObjs[gp.currentMap][i] != null) {
				
				if(gp.gameObjs[gp.currentMap][i].name.equals(OBJ_IronDoor.objName)) {
					gp.gameObjs[gp.currentMap][i] = null;
					gp.playSE(7);
				}
				
			}
		}
		
		
		gp.stopMusic();
		gp.playMusic(SoundHandler.story);
		gp.saverLoader.saveData();
	}
	public void setDialogue() {
		int dialogueSet, dialogueNum;
		
		dialogueSet = dialogueNum = 0;
		
		dialogues[dialogueSet][dialogueNum] = 
				"Ah, another brave soul, foolishly attempting\n"
				+ "to thwart my plans! You dare stand before me,\n"
				+ "seeking to challenge my might?!"; dialogueNum++;
		dialogues[dialogueSet][dialogueNum] = 
				"Know this, puny mortal, your efforts are in vain!\n"
				+ "The princess is under my command, and no\n"
				+ "one can break my power."; dialogueNum++;
		dialogues[dialogueSet][dialogueNum] = 
				"But, since you've come this far, witness the\n"
				+ "futile attempts of valor against my reign."; dialogueNum++;
		dialogues[dialogueSet][dialogueNum] = 
				"I shall crush your hope just as easily as I've\n"
				+ "conquered this land";
		dialogueSet++; dialogueNum = 0;
		
	}
	
	
	public void update() {
		super.update();
		
		
	}
}
