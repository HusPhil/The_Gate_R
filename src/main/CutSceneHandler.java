package main;

import java.awt.Color;
import java.awt.Graphics2D;

import DataHandling.GameProgress;
import entity.Entity;
import entity.NPC_Knight;
import entity.NPC_Cursed_Villager;
import entity.NPC_Hermit;
import entity.NPC_Narrator;
import entity.NPC_PlayerDummy;
import entity.NPC_Princess;
import entity.NPC_VillagerBoy;
import entity.NPC_VillagerGirl;
import entity.NPC_Witch;
import interactive_tiles.IT_TempTree;
import interactive_tiles.IT_TrenkHeart;
import monster.BOSS_SkeletonLord;
import monster.BOSS_TrenkLord;
import monster.BOSS_WaterGolem;
import monster.MON_Bat;
import monster.MON_FloatingSkull;
import monster.MON_Mummy;
import monster.MON_Trenklin;
import object.ITM_Bandage;
import object.ITM_EvilSkull;
import object.ITM_FireGel;
import object.ITM_Key;
import object.ITM_SlimeGel;
import object.ITM_TrenkAmulet;
import object.ITM_TrenkMeat;
import object.ITM_VorpalGem;
import object.ITM_VorpalStone;
import object.ITM_WaterCrystal;
import object.ITM_WaterEssence;
import object.OBJ_Chest;
import object.OBJ_FireAmulet;
import object.OBJ_IronDoor;
import object.OBJ_Iron_Sword;
import object.OBJ_Lantern;
import object.OBJ_TerraBlade;
import object.OBJ_Wooden_Shield;
import object.OBJ_Wooden_Sword;

public class CutSceneHandler {
	GamePanel gp;
	Graphics2D g2;
	Entity npc;
	public int scenePhase;
	public int sceneNum;
	
	//Scenes' Number
	public final int NONE = 0;
	public final int bossSkeletonLord = 1;
	public final int introduction = 2;
	public final int oldManEncounter = 3;
	public final int oldManExplain = 4;
	public final int axeHint = 5;
	public final int witchEncounter = 6;
	public final int witchQuest1Complete = 7;
	public final int oldManQuest2 = 8;
	public final int waterGolem = 9;
	public final int witchGolemDefeated = 10;
	public final int waterCrystal = 11;
	public final int witchPrincessInfo = 12;
	public final int knightEncounter = 13;
	public final int princessEncounter = 14;
	public final int reportWarning = 15;
	public final int craftWarning = 21;
	public final int witchReport = 16;
	public final int princessReunited = 17;
	public final int princessCraft = 18;
	public final int trenkLordBattle = 19;
	public final int ending = 20;
	
	private int gelAmmount = 0;
	private int meatAmmount = 0;
	
	private int essenceAmmount = 0;
	private int bandageAmmount = 0;
	
	private int fireGelAmmount = 0;
	private int skullAmmount = 0;
	
 	public CutSceneHandler(GamePanel gp) {
		this.gp = gp;
		
		
	}
	
	
	
	public void scene_SkeletonLord() {
		//PHASE 0
		gp.player.checkGameOver();
		
		if(scenePhase == 0) {
			gp.bossBattleOn = true;
			
			//place a temp door
			for(int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[gp.currentMap][i] == null) {
					gp.gameObjs[gp.currentMap][i] = new OBJ_IronDoor(gp);
					gp.gameObjs[gp.currentMap][i].worldX = 27*gp.tileSize;
					gp.gameObjs[gp.currentMap][i].worldY = 28*gp.tileSize;
					gp.gameObjs[gp.currentMap][i].temp = true;
					gp.playSE(7);
					break;
				}
			}
			
			//place player dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		//PHASE 1
		if(scenePhase == 1) {
			if(gp.player.worldY >= 35*gp.tileSize) scenePhase++; 
			gp.player.worldY += 2; 
		}
		//PHASE 2 
		if(scenePhase == 2) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] != null && gp.monsters[gp.currentMap][i].name.equals(BOSS_SkeletonLord.monName)) {
					gp.monsters[gp.currentMap][i].asleep = false;
					gp.gui.npc = gp.monsters[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		//PHASE 3
		if(scenePhase == 3) {
			gp.gui.dialogueScreen(false);
		}
		//PHASE 4
		if(scenePhase == 4) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase = sceneNum = NONE;
			gp.gameState = gp.playState;
			gp.stopMusic();
			gp.playMusic(SoundHandler.boss);
			//change the music [stop current music and play new music]
		}
	}
	
	public void scene_Intro() {
		
		gp.fxHandler.lighting.resetDay();
		
		//PHASE 0
		if(scenePhase == 0) {
			
			gp.eventHandler.loadingScreen(gp.corrupted1, 25, 12, gp.outside);
//			gp.eventHandler.transition(gp.forest, 21, 36, gp.outside);
//			gp.eventHandler.loadingScreen(gp.princessKingdom, 24, 23, gp.indoor);
//			gp.eventHandler.loadingScreen(gp.dungeonMap_F1, 19, 28, gp.outside);
//			gp.eventHandler.loadingScreen(gp.forest, 28, 12, gp.outside);
//			gp.eventHandler.loadingScreen(gp.sacredRiver, 15, 36, gp.outside);
//			gp.eventHandler.loadingScreen(gp.silvioHouse, 18, 38, gp.indoor);
//			gp.eventHandler.loadingScreen(gp.silvioHouse, 20, 20, gp.indoor);
//			GameProgress.waterCrystalActivated = true;
//			GameProgress.witchReported = true;
//			GameProgress.oldManExplained = true;
//			GameProgress.witchQuest1Complete = true;
//			GameProgress.waterGolemDefeated = true;
//			GameProgress.defeatedSkeletonLord = true;
			scenePhase++;
		}
		
		//PHASE 1
		if(gp.gameState == gp.playState) scenePhase++;
		
		//PHASE 2
		if(scenePhase == 2) {
			g2.setColor(Color.DARK_GRAY);
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			gp.gameState = gp.fadeOUT;
			scenePhase++;
		}
		
		//PHASE 3
		if(gp.gameState == gp.playState && scenePhase == 3) {
			scenePhase++;
		}
		
		//PHASE 4
		if(scenePhase == 4) {
			gp.gameState = gp.cutSceneState;
			
			gp.gui.npc = gp.player;
			gp.player.dialogueSet = 3;
			
			gp.gui.dialogueScreen(false);
		}
		//PHASE 5
		if(scenePhase == 5) {
			System.out.println(gp.player.dialogueSet);
			gp.gameState = gp.playState;
			scenePhase = NONE;
			sceneNum = NONE;
		}
	}
	
	public void oldManEncounter() {
		gp.player.checkGameOver();
		gp.fxHandler.lighting.resetDay();
		if(scenePhase == 0) {
			//place player dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			if(gp.player.worldX <= 14*gp.tileSize) scenePhase++; 
			gp.player.worldX -= 3; 
		}
		if(scenePhase == 2) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] != null &&
				gp.npc[gp.currentMap][i].name.equals("Silvio")) {
					gp.gui.npc = gp.npc[gp.currentMap][i];
					gp.gui.npc.direction = "right";
					gp.gui.npc.dialogueSet = NPC_Hermit.encounter;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 3) {
			gp.gui.dialogueScreen(false);
			gp.gui.npc.speed = 1;
//			gp.gui.npc.pathAI = true;
		
//			gp.gui.npc.direction = "up";
//			gp.gui.npc.pathAI = false;
		}
		
		if(scenePhase == 4) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			GameProgress.encounterOldMan = true;
			gp.player.drawing = true;
			scenePhase = NONE;
			sceneNum = NONE;
			gp.gameState = gp.playState;
			
			//change the music [stop current music and play new music]
		}
	}
	
	public void oldManExplain() {
		gp.player.checkGameOver();
		gp.fxHandler.lighting.resetDay();
		if(scenePhase == 0) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] != null &&
				gp.npc[gp.currentMap][i].name.equals("Silvio")) {
					gp.gui.npc = gp.npc[gp.currentMap][i];
					gp.gui.npc.dialogueSet = NPC_Hermit.thanking;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 1) {
			gp.gui.npc.currentSearchPath = NPC_Hermit.find_player;
			scenePhase++;
			
		}
		if(scenePhase == 2) {
			gp.gameState = gp.playState;
			if(gp.gui.npc.currentSearchPath == Entity.pathOFF) {
				scenePhase++;
				gp.gameState = gp.cutSceneState;
			}
			
		}
		if(scenePhase == 3) {
			gp.gui.npc.facePlayer();
			gp.gui.dialogueScreen(false);
		}
		
		//PHASE 3
		if(scenePhase == 4) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioVillage][i] == null) {
					gp.npc[gp.silvioVillage][i] = new NPC_Hermit(gp);
					gp.npc[gp.silvioVillage][i].name = "Silvio";
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].worldX = 18*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = 11*gp.tileSize;
					gp.npc[gp.silvioVillage][i].currentSearchPath = NPC_Hermit.oldManFindHome;
					gp.gui.npc = gp.npc[gp.silvioVillage][i];
					break;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 5) {
			gp.eventHandler.transition(gp.silvioVillage, 17, 11, gp.outside);
//			gp.gui.npc.direction = "right";
			scenePhase++;
		}
		if(gp.gameState == gp.gameState && scenePhase == 6) {
			if(gp.currentMap == gp.silvioVillage) {
				gp.player.direction = "right";
				scenePhase++;
			}
		}
		if(scenePhase == 7) {
			
			int j;
			int mapNum = gp.silvioVillage;
			
			
			
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					
					j = i;
					
					gp.monsters[mapNum][j] = new MON_Trenklin(gp);
					gp.monsters[mapNum][j].worldX = 27*gp.tileSize;
					gp.monsters[mapNum][j].worldY = 11*gp.tileSize;
					gp.monsters[mapNum][j].cs_id = "mon0001";
					j++;
					
					gp.monsters[mapNum][j] = new MON_Trenklin(gp);
					gp.monsters[mapNum][j].worldX = 16*gp.tileSize;
					gp.monsters[mapNum][j].worldY = 16*gp.tileSize;
					gp.monsters[mapNum][j].cs_id = "mon0002";
					j++;
					
					gp.monsters[mapNum][j] = new MON_Trenklin(gp);
					gp.monsters[mapNum][j].worldX = 22*gp.tileSize;
					gp.monsters[mapNum][j].worldY = 18*gp.tileSize;
					gp.monsters[mapNum][j].cs_id = "mon0003";
					j++;
					
					gp.monsters[mapNum][j] = new MON_Trenklin(gp);
					gp.monsters[mapNum][j].worldX = 30*gp.tileSize;
					gp.monsters[mapNum][j].worldY = 21*gp.tileSize;
					gp.monsters[mapNum][j].cs_id = "mon0004";
					j++;
					
					gp.monsters[mapNum][j] = new MON_Trenklin(gp);
					gp.monsters[mapNum][j].worldX = 33*gp.tileSize;
					gp.monsters[mapNum][j].worldY = 11*gp.tileSize;
					gp.monsters[mapNum][j].cs_id = "mon0005";
					j++;
					
					break;
				}
			}
			
			gp.gameState = gp.fadeOUT;
			scenePhase++;
		}
		if(scenePhase == 8 && gp.gameState == gp.playState) scenePhase++;
		if(scenePhase == 9) {
			gp.gameState = gp.cutSceneState;
			gp.gui.npc.dialogueSet = NPC_Hermit.explaining;
			gp.gui.dialogueScreen(false);
		}
		//in  the village
		if(scenePhase == 10) {
//			gp.gui.npc.currentSearchPath = NPC_Hermit.oldManFindHome;
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
			 
		}
		if(scenePhase == 11) {
			if(gp.player.worldX <= 34*gp.tileSize && gp.player.worldY <= 15*gp.tileSize) {
				gp.player.worldX += 4;
				gp.player.worldY += 3;
			}
			else {
				
				gp.gui.npc = gp.narrator;
				gp.gui.npc.dialogueSet = NPC_Narrator.village_monster;
				gp.gui.informationScreen();
			}
		}
		if(scenePhase == 12) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		if(scenePhase == 13) {
			setGuiNpc("Silvio");
			gp.gui.npc.dialogueSet = NPC_Hermit.explaining_2;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase ==  14) {
			gp.gui.npc = gp.narrator;
			gp.gui.npc.dialogueSet = NPC_Narrator.player_agree;
			gp.gui.informationScreen();
		}
		if(scenePhase ==  15) {
			setGuiNpc("Silvio");
			gp.gui.npc.dialogueSet = NPC_Hermit.explaining_3;
			gp.gui.dialogueScreen(false);

		}
		if(scenePhase == 16) {
			
			showInfoScreen(NPC_Narrator.player_acquiredWS);
			GameProgress.oldManExplained = true;
		}
		if(scenePhase == 17) {
			
			
			setGuiNpc(NPC_Hermit.NPC_Name);
			
			gp.keys.talkOn = false;
			gp.gui.npc.talking = false;
			
			gp.gameState = gp.playState;
			gp.player.checkGameOver();
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("mon0001") || csId.equals("mon0002") || csId.equals("mon0003") || csId.equals("mon0004") || csId.equals("mon0005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
				gp.player.inventory.add(new OBJ_Wooden_Sword(gp));
				gp.player.inventory.add(new OBJ_Wooden_Shield(gp)); 
			    scenePhase++;
			}
		}

		
		if(scenePhase == 18) {
			gp.gameState = gp.cutSceneState;
			showInfoScreen(NPC_Narrator.defeated_all_enemy);
		}
		if(scenePhase == 19) {
			gp.player.currentWeapon = gp.player.inventory.get(gp.player.searchItemInInventory(OBJ_Wooden_Sword.objName));
			gp.player.currentShield = gp.player.inventory.get(gp.player.searchItemInInventory(OBJ_Wooden_Shield.objName));
			gp.gameState = gp.playState;
			scenePhase++;
		}
		if(scenePhase == 20) {
			gp.gameState = gp.playState;
			setGuiNpc("Silvio");
			gp.gui.npc.dialogueSet =  NPC_Hermit.intro_end;
			if(gp.gui.npc.talking) scenePhase++; 
		}
		if(scenePhase == 21) {
			gp.gameState = gp.cutSceneState;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 22) {
			int mapNum = gp.silvioVillage;
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[mapNum][i] == null) {
					gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
					gp.npc[mapNum][i].worldX = 23*gp.tileSize;
					gp.npc[mapNum][i].worldY = 11*gp.tileSize; i++;

					gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
					gp.npc[mapNum][i].worldX = 16*gp.tileSize;
					gp.npc[mapNum][i].worldY = 16*gp.tileSize; i++;
					
					gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
					gp.npc[mapNum][i].worldX = 23*gp.tileSize;
					gp.npc[mapNum][i].worldY = 20*gp.tileSize; i++;
					
					gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
					gp.npc[mapNum][i].worldX = 28*gp.tileSize;
					gp.npc[mapNum][i].worldY = 19*gp.tileSize; i++;
					
					gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
					gp.npc[mapNum][i].worldX = 34*gp.tileSize;
					gp.npc[mapNum][i].worldY = 20*gp.tileSize; i++;
					
					gp.npc[mapNum][i] = new NPC_Cursed_Villager(gp);
					gp.npc[mapNum][i].worldX = 30*gp.tileSize;
					gp.npc[mapNum][i].worldY = 21*gp.tileSize; i++;
					
					break;
				}
				
			}
			scenePhase++;
		}
		
		if(scenePhase == 23 ) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 24 ) {
			if(gp.player.worldX <= 34*gp.tileSize && gp.player.worldY <= 15*gp.tileSize) {
				gp.player.worldX += 4;
				gp.player.worldY += 3;
			}
			else {
				
				gp.gui.npc = gp.narrator;
				gp.gui.npc.dialogueSet = NPC_Narrator.villager_comeout;
				gp.gui.informationScreen();
			}
		}
		
		if(scenePhase == 25 ) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		if(scenePhase == 26) {
			setGuiNpc("Silvio");
			gp.gui.npc.dialogueSet = NPC_Hermit.intro_end_2;
			gp.gui.npc.speed = 1;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase ==  27) {
			
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManGoodluck;
			gp.gameState = gp.playState;
			scenePhase++;
		}
		if(scenePhase == 28) {
			
			scenePhase++;
		}
		if(scenePhase == 29) {
			if(gp.gui.npc.currentSearchPath == Entity.pathOFF) {
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(gp.npc[gp.currentMap][i] != null &&
					gp.npc[gp.currentMap][i].name.equals("Silvio")) {
						gp.npc[gp.currentMap][i] = null;
						scenePhase++;
						break;
					}
				}
			}
		}
		if(scenePhase == 30) {
			
			int mapNum = gp.silvioVillage;
			for(int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[mapNum][i] == null) {
					gp.gameObjs[mapNum][i] = new ITM_Key(gp);
					gp.gameObjs[mapNum][i].worldX = 38*gp.tileSize;
					gp.gameObjs[mapNum][i].worldY = 9*gp.tileSize; i++;
					break;
				}
			}
			GameProgress.intro_done = true;
			endScene();
			gp.saverLoader.saveData();
		}
	}

	public void axeHint() {
		gp.player.checkGameOver();
		if(scenePhase == 0) {
			showInfoScreen(NPC_Narrator.axeHint_1);
		}
		if(scenePhase == 1) endScene();
	}
	
	public void witchEncounter() {
		gp.player.checkGameOver();
		if(!GameProgress.witchQuest1Complete) {
			if(scenePhase == 0) {
				setGuiNpc(NPC_Witch.NPC_Name);
				scenePhase++;
			}
			if(scenePhase == 1) {
				System.out.println(gp.gui.npc.name);
				gp.gui.npc.dialogueSet = NPC_Witch.quest1a;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 2) {
				showInfoScreen(NPC_Narrator.witchEncounter);
			}
			if(scenePhase == 3) {
				setGuiNpc(NPC_Witch.NPC_Name);
				gp.gui.npc.dialogueSet = NPC_Witch.quest1b;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 4) {
				endScene();
			}
		}
		else {
			if(scenePhase == 0) {
				setGuiNpc(NPC_Witch.NPC_Name);
				gp.gui.npc.dialogueSet = NPC_Witch.quest1e;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 1) {
				endScene();
			}
		}
	}
	
	public void witchQuest1Complete() {
		
		if(scenePhase == 0) {
			int gelIndex = gp.player.searchItemInInventory(ITM_SlimeGel.objName);
			int trenkIndex = gp.player.searchItemInInventory(ITM_TrenkMeat.objName);
			
			gelAmmount = gp.player.inventory.get(gelIndex).ammount;
			meatAmmount = gp.player.inventory.get(trenkIndex).ammount;
			
			scenePhase++;
		}
		
		if(gelAmmount < 5 || meatAmmount < 5) {
			if(scenePhase == 1) {
				gp.gui.npc = gp.narrator;
				showInfoScreen(NPC_Narrator.witchQuest1Incomplete);
			}
			if(scenePhase == 2) {
				endScene();
			}
		}
		else {

			if(scenePhase == 1) {
				setGuiNpc(NPC_Witch.NPC_Name);
				gp.gui.npc.dialogueSet = NPC_Witch.quest1c;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 2) {
				gp.gui.npc = gp.narrator;
				showInfoScreen(NPC_Narrator.witchQuest1Complete);
			}
			if(scenePhase == 3) {
				int gelIndex = gp.player.searchItemInInventory(ITM_SlimeGel.objName);
				gp.player.inventory.remove(gelIndex);
				scenePhase++;
			}
			if(scenePhase == 4) {
				int trenkIndex = gp.player.searchItemInInventory(ITM_TrenkMeat.objName);
				gp.player.inventory.remove(trenkIndex);
				scenePhase++;
			}
			if(scenePhase == 5) {
				gp.player.inventory.add(new ITM_TrenkAmulet(gp));
				scenePhase++;
			}
			if(scenePhase == 6) {
				setGuiNpc(NPC_Witch.NPC_Name);
				gp.gui.npc.dialogueSet = NPC_Witch.quest1d;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 7) {
				gp.gui.npc.dialogueSet = NPC_Witch.quest1e;
				GameProgress.witchQuest1Complete = true;
				gp.saverLoader.saveData();
				endScene();
			}
		}
		
	}
	
	public void oldManQuest2(){
		
		// talk to the old man 
		// give the old man the amulet
		// transition outside, people will go back to normal
		// old man will thank you and tell you about the tree monster
		
		//player will talk to the old man
		if(scenePhase == 0) {
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2a;
			scenePhase++;
		}
		if(scenePhase == 1) {
			gp.gameState = gp.playState;
			if(gp.gui.npc.talking) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			gp.gameState = gp.cutSceneState;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 3) {
			showInfoScreen(NPC_Narrator.oldManQ2a);
		}
		if(scenePhase == 4) {
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2b;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 5) {
			
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioVillage][i] != null && gp.npc[gp.silvioVillage][i].name.equals(NPC_Cursed_Villager.NPC_Name)) {
					int x = 30;
					int y = 18;
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					y++; x=30;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					break;
				}
			}
			
			showInfoScreen(NPC_Narrator.oldManQ2b);
		}
		if(scenePhase == 6) {
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2c;
			gp.gui.dialogueScreen(false);
		}
		
		if(scenePhase == 7) {
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManGoodluck;
			gp.eventHandler.transition(gp.silvioVillage, 29, 16, gp.outside);
			gp.fxHandler.lighting.resetDay();
			scenePhase++;
			
		}
		if(scenePhase == 8) {
			if(gp.gameState == gp.playState)
				scenePhase++;
		}
		if(scenePhase == 9) {
			gp.player.direction = "right";
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] =  new NPC_Hermit(gp);
					gp.npc[gp.currentMap][i].worldX =  31*gp.tileSize;
					gp.npc[gp.currentMap][i].worldY =  16*gp.tileSize;
					gp.npc[gp.currentMap][i].direction = "down";
					gp.npc[gp.currentMap][i].speed = 0;
					gp.gui.npc = gp.npc[gp.currentMap][i];
					break;
				}
			}
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioVillage][i] != null && gp.npc[gp.silvioVillage][i].name.equals(NPC_Cursed_Villager.NPC_Name)) {
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					 i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					 i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					break;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 10) {
			gp.gameState = gp.cutSceneState;
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2d;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 11) {
			if(gp.player.itemIsInsideInventory(ITM_TrenkAmulet.objName)) {
				int itemIndex = gp.player.searchItemInInventory(ITM_TrenkAmulet.objName);
				gp.player.inventory.remove(itemIndex);
			}
			showInfoScreen(NPC_Narrator.oldManQ2c);
		}
		if(scenePhase == 12) {
			gp.gameState = gp.fadeOUT;
			scenePhase++;
		}
		if(scenePhase == 13) {
			if(gp.gameState == gp.playState)
				scenePhase++;
		}

		if (scenePhase >= 14 && scenePhase <= 19) {
			int newWorldX, newWorldY; 
		    Entity npcToCreate;
		    if (scenePhase == 14 || scenePhase == 16 || scenePhase == 18) {
		        npcToCreate = new NPC_VillagerGirl(gp);
		    } else {
		        npcToCreate = new NPC_VillagerBoy(gp);
		    }

		    for (int i = 0; i < gp.npc[1].length; i++) {
		        if (gp.npc[gp.currentMap][i].name.equals(NPC_Cursed_Villager.NPC_Name)) {
		            newWorldX = gp.npc[gp.currentMap][i].worldX;
		            newWorldY = gp.npc[gp.currentMap][i].worldY;
		            gp.npc[gp.currentMap][i] = npcToCreate;
		            gp.npc[gp.currentMap][i].worldX = newWorldX;
		            gp.npc[gp.currentMap][i].worldY = newWorldY;
		            break;
		        }
		    }
		    scenePhase++;
		}
		
		if(scenePhase == 20) {
			gp.gameState = gp.cutSceneState;
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2e;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 21) {
			gp.gui.npc.facePlayer();
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2f;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 22) {
			showInfoScreen(NPC_Narrator.oldManQ2d);
		}
		if(scenePhase == 23) {
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2g;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 24) {
			
			showInfoScreen(NPC_Narrator.oldManQ2e);
		}
		if(scenePhase == 25) {
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2h;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 26) {
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManGoodluck;
			gp.player.inventory.add(new OBJ_Lantern(gp));
			gp.gameState = gp.playState;
			gp.gui.npc.speed = 1;
			gp.gui.npc.currentSearchPath = NPC_Hermit.oldManFindHome;
			scenePhase++;
		}
		if(scenePhase == 27) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioVillage][i] != null && gp.npc[gp.silvioVillage][i].name.equals(NPC_Cursed_Villager.NPC_Name)) {
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					 i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					 i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					break;
				}
			}
			gp.gameState = gp.playState;
			if(gp.gui.npc.currentSearchPath == Entity.pathOFF) {
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(gp.npc[gp.currentMap][i] != null &&
					gp.npc[gp.currentMap][i].name.equals("Silvio")) {
						gp.npc[gp.currentMap][i] = null;
						scenePhase++;
						break;
					}
				}
			}
		}
		if(scenePhase == 28) {
			for(int i = 0; i < gp.IT_Manager[1].length; i++) {
				if(gp.IT_Manager[gp.forest][i] != null && gp.IT_Manager[gp.forest][i].name.equals("cs_sect1")) {
					gp.IT_Manager[gp.forest][i] = null;
				}
			}
			GameProgress.oldManQuest2Explained = true;
			gp.saverLoader.saveData();
			endScene();
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManGoodluck;
		}
		System.out.print("CALLED");
	}
	
	public void waterGolem() {
		gp.bossBattleOn = true;
		gp.stopMusic();
		gp.playMusic(SoundHandler.boss);
		if(scenePhase == 0) {
			for(int i = 0; i < gp.IT_Manager[1].length; i++) {
				if(gp.IT_Manager[gp.currentMap][i] == null) {
					gp.IT_Manager[gp.currentMap][i] = new IT_TempTree(gp, 11, 32);
					gp.IT_Manager[gp.currentMap][i].temp = true; i++;
					gp.IT_Manager[gp.currentMap][i] = new IT_TempTree(gp, 11, 31);
					gp.IT_Manager[gp.currentMap][i].temp = true; i++;
					break;
				}
			}
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			if(gp.player.worldX <= 25*gp.tileSize && gp.player.worldY <= 37*gp.tileSize) {
				gp.player.worldX += 4;
				gp.player.worldY += 3;
			}
			else {
				showInfoScreen(NPC_Narrator.waterGolem);
			} 
		}
		
		if(scenePhase == 2 ) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase == 3) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] != null && gp.monsters[gp.currentMap][i].name.equals(BOSS_WaterGolem.monName)) {
					gp.monsters[gp.currentMap][i].asleep = false;
					gp.gui.npc = gp.monsters[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 4 ) {
			endScene();
		}
	}
	
	public void witchGolemDefeated() {
		
		if(scenePhase == 0) {
			int essenceIndex = gp.player.searchItemInInventory(ITM_WaterEssence.objName);
			int bandageIndex = gp.player.searchItemInInventory(ITM_Bandage.objName);
			
			essenceAmmount = gp.player.inventory.get(essenceIndex).ammount;
			bandageAmmount = gp.player.inventory.get(bandageIndex).ammount;
			
			scenePhase++;
		}
		
		if(scenePhase == 1) {
			setGuiNpc(NPC_Witch.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Witch.defeatedGolemA;
			gp.gui.dialogueScreen(false);
		}
		
		if(essenceAmmount < 1 || bandageAmmount < 10) {
			if(scenePhase == 2) {
				gp.gui.npc.dialogueSet = NPC_Witch.defeatedGolemB;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 3) {
				endScene();
			}
		}
		else {

			if(scenePhase == 2) {
				setGuiNpc(NPC_Witch.NPC_Name);
				gp.gui.npc.dialogueSet = NPC_Witch.defeatedGolemC;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 3) {
				gp.gui.npc = gp.narrator;
				showInfoScreen(NPC_Narrator.receiveWaterCrystal);
			}
			if(scenePhase == 4) {
				int essenceIndex = gp.player.searchItemInInventory(ITM_WaterEssence.objName);
				gp.player.inventory.remove(essenceIndex);
				scenePhase++;
			}
			if(scenePhase == 5) {
				int bandageIndex = gp.player.searchItemInInventory(ITM_Bandage.objName);
				gp.player.inventory.remove(bandageIndex);
				scenePhase++;
			}
			if(scenePhase == 6) {
				gp.player.inventory.add(new ITM_WaterCrystal(gp));
				scenePhase++;
			}
			if(scenePhase == 7) {
				
				gp.gui.npc.dialogueSet = NPC_Witch.defeatedGolemD;
				GameProgress.witchQuest1Complete = true;
				gp.saverLoader.saveData();
				endScene();
			}
		}
		
	}
	
	public void waterCrystal() {
		if(scenePhase == 0) {
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.waterCrystalA;
			scenePhase++;
		}
		if(scenePhase == 1) {
			gp.gameState = gp.playState;
			if(gp.gui.npc.talking) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			gp.gameState = gp.cutSceneState;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 3) {
			if(gp.player.itemIsInsideInventory(ITM_WaterCrystal.objName)) {
				int itemIndex = gp.player.searchItemInInventory(ITM_WaterCrystal.objName);
				gp.player.inventory.remove(itemIndex);
			}
			showInfoScreen(NPC_Narrator.oldManWaterCrystalA);
		}
		if(scenePhase == 4) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioVillage][i] != null && 
					(gp.npc[gp.silvioVillage][i].name.equals(NPC_VillagerBoy.NPC_Name) || 
					gp.npc[gp.silvioVillage][i].name.equals(NPC_VillagerBoy.NPC_Name))) {
					int x = 30;
					int y = 18;
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					y++; x=30;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					
					gp.npc[gp.silvioVillage][i].speed = 0;
					gp.npc[gp.silvioVillage][i].direction = "up";
					gp.npc[gp.silvioVillage][i].worldX = x*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY = y*gp.tileSize; i++;
					x++;
					break;
				}
			}
			
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.waterCrystalB;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 5) {
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManGoodluck;
			gp.eventHandler.transition(gp.silvioVillage, 29, 16, gp.outside);
			gp.fxHandler.lighting.resetDay();
			scenePhase++;
			
		}
		if(scenePhase == 6) {
			if(gp.gameState == gp.playState)
				scenePhase++;
		}
		if(scenePhase == 7) {
			gp.player.direction = "right";
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] =  new NPC_Hermit(gp);
					gp.npc[gp.currentMap][i].worldX =  31*gp.tileSize;
					gp.npc[gp.currentMap][i].worldY =  16*gp.tileSize;
					gp.npc[gp.currentMap][i].direction = "down";
					gp.npc[gp.currentMap][i].speed = 0;
					gp.gui.npc = gp.npc[gp.currentMap][i];
					break;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 8) {
			gp.gameState = gp.cutSceneState;
			gp.gui.npc.dialogueSet = NPC_Hermit.waterCrystalC;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 9) {
			showInfoScreen(NPC_Narrator.oldManWaterCrystalB);
		}
		if(scenePhase == 10) {
			gp.gameState = gp.fadeOUT;
			scenePhase++;
		}
		if(scenePhase == 11) {
			if(gp.gameState == gp.playState)
				scenePhase++;
		}
		if(scenePhase == 12) {
			gp.gameState = gp.cutSceneState;
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.waterCrystalD;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 13) {
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManGoodluck;
			gp.gameState = gp.playState;
			gp.gui.npc.speed = 1;
			gp.gui.npc.currentSearchPath = NPC_Hermit.oldManFindHome;
			scenePhase++;
		}
		if(scenePhase == 14) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioVillage][i] != null && 
						(gp.npc[gp.silvioVillage][i].name.equals(NPC_VillagerBoy.NPC_Name) || 
						gp.npc[gp.silvioVillage][i].name.equals(NPC_VillagerBoy.NPC_Name))) {
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					 i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					 i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					gp.npc[gp.silvioVillage][i].speed = 1;
					i++;
					break;
				}
			}
			gp.gameState = gp.playState;
			if(gp.gui.npc.currentSearchPath == Entity.pathOFF) {
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(gp.npc[gp.currentMap][i] != null &&
					gp.npc[gp.currentMap][i].name.equals("Silvio")) {
						gp.npc[gp.currentMap][i] = null;
						scenePhase++;
						break;
					}
				}
			}
		}
		if(scenePhase == 15) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] == null) {
					gp.npc[gp.forest][i] = new NPC_Knight(gp);
					gp.npc[gp.forest][i].speed = 0;
					gp.npc[gp.forest][i].worldX = 33*gp.tileSize;
					gp.npc[gp.forest][i].worldY = 15*gp.tileSize; i++;
					break;
				}
			}
			
			GameProgress.waterCrystalActivated = true;
			endScene();
		}
	}
	
	public void witchPrincessInfo() {
		if(scenePhase == 0) {
			setGuiNpc(NPC_Witch.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Witch.princessInfo;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 1) {
			for(int i = 0; i < gp.IT_Manager[1].length; i++) {
				if(gp.IT_Manager[gp.forest][i] != null && gp.IT_Manager[gp.forest][i].name.equals("cs_sect2")) {
					gp.IT_Manager[gp.forest][i] = null;
				}
			}
			endScene();
		}
	}
	
	public void knightEncounter() {
		if(scenePhase == 0) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			if(gp.player.worldY >= 15*gp.tileSize) {
				gp.player.worldY -= 3;
			}
			else {
				setGuiNpc(NPC_Knight.NPC_Name);
				gp.gui.npc.dialogueSet = NPC_Knight.princessInfoA;
				gp.gui.npc.direction = "down";
				gp.gui.dialogueScreen(false);
			} 
		}
		
		if(scenePhase == 2) {
			gp.gameState = gp.fadeIN;
			scenePhase++;
			
		}	
		if(gp.gameState == gp.playState) scenePhase++;
		
		if(scenePhase ==  4) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		if(scenePhase == 5) {
			setGuiNpc(NPC_Knight.NPC_Name);
			gp.gui.npc.direction = "down";
			gp.player.worldX = 33*gp.tileSize;
			gp.player.worldY = 16*gp.tileSize;
			gp.player.direction = "up";
			gp.gameState = gp.cutSceneState;
			scenePhase++;
		}
		if(scenePhase == 6) {
			showInfoScreen(NPC_Narrator.knightEncounterA);
		}
		if(scenePhase == 7) {
			setGuiNpc(NPC_Knight.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Knight.princessInfoB;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 8) showInfoScreen(NPC_Narrator.knightEncounterB);
		if(scenePhase == 9) {
			setGuiNpc(NPC_Knight.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Knight.princessInfoC;
			gp.gui.dialogueScreen(false);
		}
		
		
		if(scenePhase == 10) {
			GameProgress.knightEncountered = true;
			gp.saverLoader.saveData();
			endScene();
		}
	}
	
	public void princessEncounter() {

		if(scenePhase == 0) {
			setGuiNpc(NPC_Princess.NPC_Name);
			scenePhase++;
		}
		if(scenePhase == 1) {
			gp.gui.npc.currentSearchPath = NPC_Princess.find_player;
			scenePhase++;
		}
		if(scenePhase == 2) {
			gp.gameState = gp.playState;
			if(gp.gui.npc.currentSearchPath == Entity.pathOFF) {
				scenePhase++;
				gp.gameState = gp.cutSceneState;
			}
		}
		if(scenePhase == 3) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.thankPlayerA;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 4) {
			showInfoScreen(NPC_Narrator.princessEncounterA);
		}
		if(scenePhase == 5) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.thankPlayerB;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 6) {
			showInfoScreen(NPC_Narrator.princessEncounterB);
		}
		if(scenePhase == 7) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.thankPlayerC;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 8) {
			showInfoScreen(NPC_Narrator.princessEncounterC);
		}
		if (scenePhase == 9) {
			gp.keys.keyFreeze = true;
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] == null) {
					gp.npc[gp.forest][i] = new NPC_Princess(gp);
					gp.npc[gp.forest][i].speed = 0;
					gp.npc[gp.forest][i].direction = "down";
					gp.npc[gp.forest][i].worldX = 22*gp.tileSize;
					gp.npc[gp.forest][i].worldY = 37*gp.tileSize;
					break;
				} 
			}
			scenePhase++;
		}
		if(scenePhase == 10) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Knight.NPC_Name)) {
					gp.npc[gp.forest][i].direction = "down";
					gp.npc[gp.forest][i].worldX = 21*gp.tileSize;
					gp.npc[gp.forest][i].worldY = 37*gp.tileSize;
					break;
				}
			}
			scenePhase++;
		}
		if (scenePhase == 11) {//endScene();	
			
			gp.fxHandler.lighting.resetDay();
			gp.eventHandler.transition(gp.forest, 21, 36, gp.outside);
			gp.player.worldY -= 5;
			scenePhase++;
		}
		if(scenePhase  == 12) {
			
			if(gp.gameState == gp.playState) scenePhase++;
		}
		if(scenePhase == 13) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Princess.NPC_Name)) {
					gp.npc[gp.forest][i].direction = "left";
					break;
				}
			}
			gp.gameState = gp.cutSceneState;
			gp.player.direction = "down";
			gp.player.defaultSpeed = 0;
			gp.player.worldX = 1030;
			scenePhase++;
		}
		
		if(scenePhase == 14) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Knight.NPC_Name)) {
					gp.npc[gp.forest][i].direction = "right";
					gp.gui.npc = gp.npc[gp.forest][i];
					gp.npc[gp.forest][i].worldX = 21*gp.tileSize;
					gp.npc[gp.forest][i].worldY = 37*gp.tileSize;
					break;
				}
			}
			gp.gui.npc.dialogueSet = NPC_Knight.princessBackA;
			gp.gui.dialogueScreen(false);
			
		}
		
		if(scenePhase == 15) {
//			for(int i = 0; i < gp.npc[1].length; i++) {
//				if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Knight.NPC_Name)) {
//					gp.npc[gp.forest][i].direction = "left";
//					gp.gui.npc = gp.npc[gp.forest][i];
//					gp.npc[gp.forest][i].worldX = 21*gp.tileSize;
//					gp.npc[gp.forest][i].worldY = 37*gp.tileSize;
//					break;
//				}
//			}
//			setGuiNpc(NPC_Knight.NPC_Name);
//			gp.gui.npc.direction = "right";
//			gp.gui.npc.dialogueSet = NPC_Knight.princessBackA;
//			gp.gui.dialogueScreen(false);
			scenePhase++;
		}
		if(scenePhase == 16) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Knight.NPC_Name)) {
					gp.npc[gp.forest][i].direction = "up";
					break;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 17) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.direction = "up";
			gp.gui.npc.dialogueSet = NPC_Princess.thankPlayerD;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 18) {
			gp.gui.npc.speed = 1;
			gp.gui.npc.currentSearchPath = NPC_Princess.find_home;
			scenePhase++;
		};
		if(scenePhase == 19) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Knight.NPC_Name)) {
					gp.npc[gp.forest][i].direction = "up";
					gp.npc[gp.forest][i].speed = 1;
					gp.npc[gp.forest][i].currentSearchPath = NPC_Knight.find_home;
					break;
				}
			}
			scenePhase+=2;
		};
		if(scenePhase == 20) {
			
				scenePhase++;
			
		}
		if(scenePhase == 21) {
			gp.gameState = gp.playState;
			setGuiNpc(NPC_Knight.NPC_Name);
			if(gp.gui.npc.currentSearchPath == Entity.pathOFF) {
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Knight.NPC_Name)) {
						gp.npc[gp.forest][i] = null;
					}
				}
				scenePhase++;
			}
		}
		if(scenePhase == 22) {
			setGuiNpc(NPC_Princess.NPC_Name);
			if(gp.gui.npc.currentSearchPath == Entity.pathOFF) {
				for(int i = 0; i < gp.npc[1].length; i++) {
					if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Princess.NPC_Name)) {
						gp.npc[gp.forest][i] = null;
						break;
					}
				}
				scenePhase++;
			}
		
		}
		if(scenePhase == 23) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.princessCage][i] != null && gp.npc[gp.princessCage][i].name.equals(NPC_Princess.NPC_Name)) {
					gp.npc[gp.princessCage][i] = null;
					break;
				}
			}
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioHouse][i] != null && gp.npc[gp.silvioHouse][i].name.equals(NPC_Hermit.NPC_Name)) {
					gp.npc[gp.silvioHouse][i].dialogueSet = NPC_Hermit.defeatedSkeletonA;
					break;
				}
			}
			gp.gameState = gp.cutSceneState;
			showInfoScreen(NPC_Narrator.princessEncounterD);
		}
		if(scenePhase == 24) {
			gp.keys.keyFreeze = false;
			gp.player.defaultSpeed = 5;
			GameProgress.princessEncountered = true;
			gp.saverLoader.saveData();
			endScene();
		}
		System.out.println("sinpeys: " + scenePhase);
	}
	
	public void reportWarning() {
		if(scenePhase == 0) {
			showInfoScreen(NPC_Narrator.witchReportedA);
		}
		if(scenePhase == 1) {
			gp.eventHandler.transition(gp.forest, 37, 11, gp.outside);
			scenePhase++;
		}
		if(gp.gameState == gp.playState) scenePhase++;
		if(scenePhase == 3) endScene();
	}
	
	public void craftWarning() {
		if(scenePhase == 0) {
			showInfoScreen(NPC_Narrator.craftWarning);
		}
		if(scenePhase == 1) {
			gp.eventHandler.transition(gp.forest, 37, 11, gp.outside);
			scenePhase++;
		}
		if(gp.gameState == gp.playState) scenePhase++;
		if(scenePhase == 3) endScene();
	}
	
	public void witchReport() {

		
		if(scenePhase == 0) {
			int fireGelIndex = gp.player.searchItemInInventory(ITM_FireGel.objName);
			int skullIndex = gp.player.searchItemInInventory(ITM_EvilSkull.objName);
			
			fireGelAmmount = gp.player.inventory.get(fireGelIndex).ammount;
			skullAmmount = gp.player.inventory.get(skullIndex).ammount;
			
			scenePhase++;
		}
		
		if(scenePhase == 1) {
			setGuiNpc(NPC_Witch.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Witch.defeatedSkeletonA;
			gp.gui.dialogueScreen(false);
		}
		
		if(fireGelAmmount < 10 || skullAmmount < 1) {
			if(scenePhase == 2) {
				gp.gui.npc.dialogueSet = NPC_Witch.defeatedSkeletonB;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 3) {
				endScene();
			}
		}
		else {

			if(scenePhase == 2) {
				setGuiNpc(NPC_Witch.NPC_Name);
				gp.gui.npc.dialogueSet = NPC_Witch.defeatedSkeletonC;
				gp.gui.dialogueScreen(false);
			}
			if(scenePhase == 3) {
				gp.gui.npc = gp.narrator;
				showInfoScreen(NPC_Narrator.receiveFireAmulet);
			}
			if(scenePhase == 4) {
				int fireGelIndex = gp.player.searchItemInInventory(ITM_FireGel.objName);
				gp.player.inventory.remove(fireGelIndex);
				scenePhase++;
			}
			if(scenePhase == 5) {
				int skullIndex = gp.player.searchItemInInventory(ITM_EvilSkull.objName);
				gp.player.inventory.remove(skullIndex);
				scenePhase++;
			}
			if(scenePhase == 6) {
				gp.player.inventory.add(new OBJ_FireAmulet(gp));
				gp.player.mana = gp.player.maxMana;
				scenePhase++;
			}
			if(scenePhase == 7) {
				gp.gui.npc.dialogueSet = NPC_Witch.defeatedSkeletonD;
				GameProgress.witchReported = true;
				endScene();
				gp.saverLoader.saveData();
			}
		}
		
	
	}
	
	public void princessReunited() {
		if(scenePhase == 0) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		if(scenePhase == 1) {
			if(gp.player.worldY >= 12*gp.tileSize) {
				gp.player.worldY -= 2;
			}
			else {
				setGuiNpc(NPC_Knight.NPC_Name);
				gp.gui.npc.dialogueSet = NPC_Knight.princessReunitedA;
				gp.gui.npc.direction = "right";
				gp.gui.dialogueScreen(false);
			} 
		}
		
		if(scenePhase == 2) {
			gp.gui.npc.dialogueSet = NPC_Knight.princessReunitedB;
			gp.gui.npc.direction = "down";
			gp.gui.dialogueScreen(false);
			
		}	
		
		if(scenePhase ==  3) {
			scenePhase++;
		}
		if(scenePhase == 4) {
			gp.gameState = gp.fadeIN;
			scenePhase++;
			
		}
		if(scenePhase == 5) {
			if(gp.gameState == gp.playState) scenePhase++;
		}
		
		if(scenePhase ==  6) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		if(scenePhase == 7) {
			setGuiNpc(NPC_Knight.NPC_Name);
			gp.gui.npc.direction = "down";
			gp.player.worldX = 24*gp.tileSize;
			gp.player.worldY = 13*gp.tileSize;
			gp.player.direction = "up";
			gp.gameState = gp.cutSceneState;
			scenePhase++;
		}
		if(scenePhase == 8) {
			gp.gameState = gp.cutSceneState;
			gp.gui.npc.dialogueSet = NPC_Knight.princessReunitedC;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 9) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.playerRequestA;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 10) {
			showInfoScreen(NPC_Narrator.playerRequestA);
		}
		if(scenePhase == 11) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.playerRequestB;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 12) {
			setGuiNpc(NPC_Knight.NPC_Name);
			gp.gui.npc.direction = "right";
			gp.gui.npc.dialogueSet = NPC_Knight.princessReunitedD;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 13) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.playerRequestC;
			gp.gui.npc.direction = "left";
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 14) {
			
			gp.gui.npc.direction = "down";
			gp.gui.npc.dialogueSet = NPC_Princess.playerRequestD;
			gp.gui.dialogueScreen(false);
			
		}
		if(scenePhase == 15) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] == null) {
					gp.npc[gp.forest][i] = new NPC_Princess(gp);
					gp.npc[gp.forest][i].speed = 1;
					gp.npc[gp.forest][i].dialogueSet = NPC_Princess.playerRequestD;
					gp.npc[gp.forest][i].worldX =22*gp.tileSize;
					gp.npc[gp.forest][i].worldY =  37*gp.tileSize;
					gp.npc[gp.forest][i].direction = "up";
					break;
				}
			}
			gp.eventHandler.transition(gp.forest, 21, 37, gp.outside);
			scenePhase++;
		}
		if(scenePhase == 16) {
			if(gp.gameState == gp.playState) scenePhase++;
		}
		
		if(scenePhase == 17) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.speed = 1;
			gp.gui.npc.currentSearchPath = NPC_Princess.chase_player;
			if(gp.currentMap == gp.princessCage) scenePhase++;
		}
		if(scenePhase == 18) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Princess.NPC_Name)) {
					gp.npc[gp.forest][i].worldX =37*gp.tileSize;
					gp.npc[gp.forest][i].worldY =  11*gp.tileSize;
					gp.npc[gp.forest][i].direction = "left";
					gp.npc[gp.forest][i].speed = 1;
					break;
				}
			}
			gp.eventHandler.transition(gp.forest, 36, 11, gp.outside);
			scenePhase++;
		}
		if(scenePhase == 19) {
			
			if(gp.currentMap == gp.silvioHouse) scenePhase++;
		}
		if(scenePhase == 20) {
			
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioVillage][i] == null) {
					gp.npc[gp.silvioVillage][i] = new NPC_Princess(gp);
					gp.npc[gp.silvioVillage][i].dialogueSet = NPC_Princess.playerRequestD;
					gp.npc[gp.silvioVillage][i].worldX = 37*gp.tileSize;
					gp.npc[gp.silvioVillage][i].worldY =  38*gp.tileSize;
					gp.npc[gp.silvioVillage][i].speed = 1;
					gp.npc[gp.silvioVillage][i].currentSearchPath = NPC_Princess.chase_player;
					gp.npc[gp.silvioVillage][i].direction = "left";
					break;
				}
			}
			gp.eventHandler.transition(gp.silvioVillage, 36, 38, gp.outside);
			scenePhase++;
		}
		if(scenePhase == 21) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.forest][i] != null && gp.npc[gp.forest][i].name.equals(NPC_Princess.NPC_Name)) {
					gp.npc[gp.forest][i]= null;
					break;
				}
			}
		}
		if(scenePhase == 22) {
//			gp.gameState = gp.cutSceneState;
			gp.player.direction = "down";
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioHouse][i] == null) {
					gp.npc[gp.silvioHouse][i] = new NPC_Princess(gp);
					gp.npc[gp.silvioHouse][i].dialogueSet = NPC_Princess.playerRequestD;
					gp.npc[gp.silvioHouse][i].worldX = 19*gp.tileSize;
					gp.npc[gp.silvioHouse][i].worldY =  22*gp.tileSize;
					gp.npc[gp.silvioHouse][i].speed = 0;
					gp.npc[gp.silvioHouse][i].currentSearchPath = NPC_Princess.pathOFF;
					gp.npc[gp.silvioHouse][i].direction = "down";
					break;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 23) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.silvioVillage][i] != null && gp.npc[gp.silvioVillage][i].name.equals(NPC_Princess.NPC_Name)) {
					gp.npc[gp.silvioVillage][i]= null;
					break;
				}
			}
			if(gp.gameState == gp.playState) scenePhase++;
		}
		if(scenePhase == 24) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.direction = "left";
			gp.gui.npc.lockDirection = true;
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.direction = "right";
			gp.gui.npc.lockDirection = true;
			gp.gameState = gp.cutSceneState;
			gp.gui.npc.dialogueSet = NPC_Hermit.princessReunitedA;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 25) {
			gp.gameState = gp.cutSceneState;
			showInfoScreen(NPC_Narrator.playerRequestB);
		}
		if(scenePhase == 26) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.playerRequestE;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 27) {
			gp.gameState = gp.fadeIN;
			scenePhase++;
			
		}
		if(scenePhase == 28) {
			if(gp.gameState == gp.playState) 
				scenePhase++;
		}
		if(scenePhase == 29) {
			gp.gameState = gp.cutSceneState;
			showInfoScreen(NPC_Narrator.playerRequestC);
		}
		if(scenePhase == 30) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.playerRequestF;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 31) {
			GameProgress.princessReunited = true;
			endScene();
			gp.saverLoader.saveData();
		}
		
		
		
		
//		setGuiNpc(NPC_Knight.NPC_Name);
//		gp.gui.npc.dialogueSet = NPC_Knight.princessReunitedA;
//		gp.gui.dialogueScreen(false);
	}
	
	public void princessCraft() {
		System.out.println("Sinpeys: " + scenePhase);
		System.out.println("from cutscene class");
		if(scenePhase == 0) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.playerCraftA;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 1) {
			int meatAmmount = 0;
			if(gp.player.itemIsInsideInventory(ITM_TrenkMeat.objName))
				meatAmmount = gp.player.inventory.get(gp.player.searchItemInInventory(ITM_TrenkMeat.objName)).ammount;
			boolean hasWoodSword = gp.player.itemIsInsideInventory(OBJ_Wooden_Sword.objName);
			boolean hasIronSword = gp.player.itemIsInsideInventory(OBJ_Iron_Sword.objName);
			
			if(hasWoodSword && hasIronSword && meatAmmount >= 25) {
				scenePhase+=3;
			} else scenePhase++;
		}
		if(scenePhase == 2) {
			//don't create
			gp.gui.npc.dialogueSet = NPC_Princess.playerCraftB;
			gp.gui.dialogueScreen(false);
			
		}
		if(scenePhase == 3) {
			gp.gui.npc.direction = "left";
			endScene();
		}
		if(scenePhase == 4) {
			//create
			gp.gui.npc.dialogueSet = NPC_Princess.playerCraftC;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 5) {
			gp.player.inventory.add(new OBJ_TerraBlade(gp));
			gp.player.inventory.add(new ITM_VorpalStone(gp)); 
			scenePhase++;
		}
		if(scenePhase == 6) showInfoScreen(NPC_Narrator.receiveTerra);
		if(scenePhase == 7) {
			int itemIndex = gp.player.searchItemInInventory(ITM_TrenkMeat.objName);
			gp.player.inventory.remove(itemIndex);
			scenePhase++;
		}
		if(scenePhase == 8) {
			int itemIndex = gp.player.searchItemInInventory(OBJ_Iron_Sword.objName);
			gp.player.inventory.remove(itemIndex);
			scenePhase++;
		}
		if(scenePhase == 9) {
			int itemIndex = gp.player.searchItemInInventory(OBJ_Wooden_Sword.objName);
			gp.player.inventory.remove(itemIndex);
			scenePhase++;
		}
		if(scenePhase == 10) {
			setGuiNpc(NPC_Princess.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Princess.playerCraftD;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 11) {
			GameProgress.princessCrafted = true;
			endScene();
			gp.saverLoader.saveData();
		}
		
		
	}
	
	public void trenkLordBattle() {
		
		if(scenePhase == 0) {
			gp.gameState = gp.fadeIN;
			scenePhase++;
		}
		if(scenePhase == 1) {
			System.out.println("sinpeys: " + gp.gameState);
			if (gp.gameState == gp.playState) {
				scenePhase++;
			}
		}
		if(scenePhase == 2) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = 20*gp.tileSize;
			scenePhase++;
		}
		if(scenePhase == 3) {
			
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 4) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		if(scenePhase == 5) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] != null && gp.monsters[gp.currentMap][i].name.equals(BOSS_TrenkLord.monName)) {
					gp.monsters[gp.currentMap][i].asleep = false;
					gp.gui.npc = gp.monsters[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 6) {
			gp.gui.dialogueScreen(false);
		}
		
		//first wave
		
		if(scenePhase == 7) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					break;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 8) {
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleA);
		}
		
		if(scenePhase == 9) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 10) {
			showInfoScreen(NPC_Narrator.finalBattleA);
		}
		
		
		if(scenePhase ==  11) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		if(scenePhase == 12) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 13) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		
		
		if(scenePhase ==  14) {
			scenePhase++;
		}
		

		if(scenePhase == 15) {
			gp.gameState = gp.cutSceneState;
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 16) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 17) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 18) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 19) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleC);
			
		}
		if(scenePhase == 20) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 21) {
			showInfoScreen(NPC_Narrator.finalBattleA);
		}
		
		if(scenePhase ==  22) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  23) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 24) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		if(scenePhase == 25) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 26) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 27) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 28) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
				gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
				gp.monsters[gp.currentMap][i].cs_id = "smonA001";
				gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
				gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
				gp.monsters[gp.currentMap][i].atk = 15;
				gp.monsters[gp.currentMap][i].def = 10;
				gp.monsters[gp.currentMap][i].direction = "down"; i++;
				
				gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
				gp.monsters[gp.currentMap][i].cs_id = "smonA002";
				gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
				gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
				gp.monsters[gp.currentMap][i].atk = 15;
				gp.monsters[gp.currentMap][i].def = 10;
				gp.monsters[gp.currentMap][i].direction = "down"; i++;
				
				gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
				gp.monsters[gp.currentMap][i].cs_id = "smonA003";
				gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
				gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
				gp.monsters[gp.currentMap][i].atk = 15;
				gp.monsters[gp.currentMap][i].def = 10;
				gp.monsters[gp.currentMap][i].direction = "down"; i++;
				
				gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
				gp.monsters[gp.currentMap][i].cs_id = "smonA004";
				gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
				gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
				gp.monsters[gp.currentMap][i].atk = 15;
				gp.monsters[gp.currentMap][i].def = 10;
				gp.monsters[gp.currentMap][i].direction = "down"; i++;
				
				gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
				gp.monsters[gp.currentMap][i].cs_id = "smonA005";
				gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
				gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
				gp.monsters[gp.currentMap][i].atk = 15;
				gp.monsters[gp.currentMap][i].def = 10;
				gp.monsters[gp.currentMap][i].direction = "down"; i++;
				break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 29) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleC);
			
		}
		if(scenePhase == 30) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 31) {
			showInfoScreen(NPC_Narrator.finalBattleA);
		}
		
		if(scenePhase ==  32) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  33) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 34) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		if(scenePhase == 35) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 36) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 37) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 38) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					break;
					}
			}
			scenePhase++;
		}
		
		if(scenePhase == 39) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleC);
			
		}
		if(scenePhase == 40) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 41) {
			showInfoScreen(NPC_Narrator.finalBattleC);
		}
		
		if(scenePhase ==  42) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  43) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 44) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		if(scenePhase == 45) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 46) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 47) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 48) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 49) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleD);
			
		}
		if(scenePhase == 50) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 51) {
			showInfoScreen(NPC_Narrator.finalBattleD);
		}
		
		if(scenePhase ==  52) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  53) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 54) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		if(scenePhase == 55) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 56) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 57) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 58) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {

					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					break;
					}
			}
			scenePhase++;
		}
		
		if(scenePhase == 59) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleD);
			
		}
		if(scenePhase == 60) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 61) {
			showInfoScreen(NPC_Narrator.finalBattleD);
		}
		
		if(scenePhase ==  62) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  63) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 64) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		if(scenePhase == 65) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 66) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 67) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 68) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 69) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleB);
			
		}
		if(scenePhase == 70) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 71) {
			showInfoScreen(NPC_Narrator.finalBattleB);
		}
		
		if(scenePhase ==  72) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  73) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 74) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		if(scenePhase == 75) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 76) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 77) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 78) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					break;
				
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 79) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleD);
			
		}
		if(scenePhase == 80) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 81) {
			showInfoScreen(NPC_Narrator.finalBattleD);
		}
		
		if(scenePhase ==  82) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  83) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 84) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		if(scenePhase == 85) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 86) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 87) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 88) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Trenklin(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 89) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleB);
			
		}
		if(scenePhase == 90) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 91) {
			showInfoScreen(NPC_Narrator.finalBattleB);
		}
		
		if(scenePhase ==  92) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  93) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 94) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		if(scenePhase == 95) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 96) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 97) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 98) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA001";
					gp.monsters[gp.currentMap][i].worldX = 22*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA002";
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA003";
					gp.monsters[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Mummy(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA004";
					gp.monsters[gp.currentMap][i].worldX = 25*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_Bat(gp);
					gp.monsters[gp.currentMap][i].cs_id = "smonA005";
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].atk = 15;
					gp.monsters[gp.currentMap][i].def = 10;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 99) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleD);
			
		}
		if(scenePhase == 100) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 101) {
			showInfoScreen(NPC_Narrator.finalBattleD);
		}
		
		if(scenePhase ==  102) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  103) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase == 104) {
			boolean monstersAlive = false;

			for (int i = 0; i < gp.monsters[1].length; i++) {
			    Entity currentMonster = gp.monsters[gp.currentMap][i];
			    if (currentMonster != null) {
			        String csId = currentMonster.cs_id;
			        if (csId.equals("smonA001") || csId.equals("smonA002") || csId.equals("smonA003") || csId.equals("smonA004") || csId.equals("smonA005")) {
			            monstersAlive = true;
			            break;
			        }
			    }
			}

			if (!monstersAlive) {
			    scenePhase++;
			}
		}
		
		if(scenePhase ==  105) {
			
			gp.eventHandler.transition(gp.finalStage, 24, 20, gp.dungeon);
			
			scenePhase++;
			
		}
		if(scenePhase == 106) {
			if(gp.gameState == gp.playState) {
				scenePhase++;
			}
		}
		if(scenePhase ==  107) {
			gp.gameState = gp.cutSceneState;
			//show the boss is wearing down
			
			showInfoScreen(NPC_Narrator.finalBattleE);
			
		}		
		if(scenePhase ==  108) {
			for(int i = 0; i < gp.IT_Manager[1].length; i++) {
				if(gp.IT_Manager[gp.currentMap][i] == null) {
					gp.IT_Manager[gp.currentMap][i] =  new IT_TrenkHeart(gp, 12, 15); i++;
					gp.IT_Manager[gp.currentMap][i] =  new IT_TrenkHeart(gp, 35, 15); 
					gp.bossBattleOn = true;
					break;
				}
			}
			scenePhase++;
			
		}		
		if(scenePhase ==  109) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		if(scenePhase ==  110) {
			
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] != null && gp.monsters[gp.currentMap][i].name.equals(BOSS_TrenkLord.monName)) {
					if(gp.monsters[gp.currentMap][i].life <= gp.monsters[gp.currentMap][i].maxLife * 0.75) scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 111) {
			gp.player.worldX = gp.tileSize*24;
			gp.player.worldY = gp.tileSize*20;
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 112) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 113) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 114) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 20*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 115) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleC);
			
		}
		if(scenePhase == 116) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 117) {
			showInfoScreen(NPC_Narrator.finalBattleA);
		}
		
		if(scenePhase ==  118) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  119) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		if(scenePhase ==  120) {
			
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] != null && gp.monsters[gp.currentMap][i].name.equals(BOSS_TrenkLord.monName)) {
					if(gp.monsters[gp.currentMap][i].life <= gp.monsters[gp.currentMap][i].maxLife * 0.5) scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 121) {
			gp.player.worldX = gp.tileSize*24;
			gp.player.worldY = gp.tileSize*20;
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 122) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 123) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 124) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 20*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 125) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleC);
			
		}
		if(scenePhase == 126) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 127) {
			showInfoScreen(NPC_Narrator.finalBattleA);
		}
		
		if(scenePhase ==  128) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  129) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		
		
		if(scenePhase ==  130) {
			
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] != null && gp.monsters[gp.currentMap][i].name.equals(BOSS_TrenkLord.monName)) {
					if(gp.monsters[gp.currentMap][i].life <= gp.monsters[gp.currentMap][i].maxLife * 0.25) scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 131) {
			gp.player.worldX = gp.tileSize*24;
			gp.player.worldY = gp.tileSize*20;
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new NPC_PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY =  gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 132) {
			gp.gameState = gp.cutSceneState;
			gp.player.worldX = 1140;
			gp.player.worldY = gp.tileSize*13;
			scenePhase++;
		}
		
		if(scenePhase == 133) {
			if(gp.player.worldY <= 11*gp.tileSize) scenePhase++;
			gp.player.worldY -= 2;
		}
		
		if(scenePhase == 134) {
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] == null) {
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 20*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 23*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					gp.monsters[gp.currentMap][i] = new MON_FloatingSkull(gp);
					gp.monsters[gp.currentMap][i].worldX = 26*gp.tileSize;
					gp.monsters[gp.currentMap][i].worldY = 16*gp.tileSize;
					gp.monsters[gp.currentMap][i].direction = "down"; i++;
					
					break;
				}
			}
			scenePhase++;
		}
		
		if(scenePhase == 135) {
			gp.gameState = gp.cutSceneState;
			if(gp.gui.npc.dialogueIndex == 1) scenePhase++;
			showInfoScreen(NPC_Narrator.finalBattleC);
			
		}
		if(scenePhase == 136) {
			if(gp.player.worldY >= 15*gp.tileSize) scenePhase++;
			gp.player.worldY += 2;
		}
		
		if(scenePhase == 137) {
			showInfoScreen(NPC_Narrator.finalBattleA);
		}
		
		if(scenePhase ==  138) {
			for(int  i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i].name.equals(NPC_PlayerDummy.NPC_Name) && gp.npc[gp.currentMap][i] != null) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			gp.player.drawing = true;
			scenePhase++;
		}
		
		if(scenePhase ==  139) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
		if(scenePhase ==  140) {
			
			for(int i = 0; i < gp.monsters[1].length; i++) {
				if(gp.monsters[gp.currentMap][i] != null && gp.monsters[gp.currentMap][i].name.equals(BOSS_TrenkLord.monName)) {
					if(gp.monsters[gp.currentMap][i].life <= gp.monsters[gp.currentMap][i].maxLife * 0) scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 141) {
			for(int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[gp.currentMap][i] == null) {
					gp.gameObjs[gp.currentMap][i] = new OBJ_Chest(gp);
					gp.gameObjs[gp.currentMap][i].worldX = 24*gp.tileSize;
					gp.gameObjs[gp.currentMap][i].worldY = 17*gp.tileSize;
					gp.gameObjs[gp.currentMap][i].setLoot(new ITM_VorpalGem(gp));
					gp.playSE(7);
					scenePhase++;
					break;
				}
			}
		}
		if(scenePhase == 142) {
			gp.gameState = gp.cutSceneState;
			gp.playSE(0);
			showInfoScreen(NPC_Narrator.finalBattleA);
		}
		if(scenePhase == 143) endScene();
		System.out.println("sinpeys: " + scenePhase);
//		System.out.println("sinpeys: " + gp.gui.npc.name);
	}
	
	public void ending() {
		System.out.println("sinpeys: " + scenePhase);
		if(scenePhase == 0) {
			gp.gameState = gp.fadeIN;
			scenePhase++;
		}
		if(scenePhase == 1) {
			if(gp.gameState == gp.playState) scenePhase++;
		}
		if(scenePhase == 2) {
			g2.setColor(Color.white);
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			gp.gameState = gp.cutSceneState;
			gp.gui.npc = gp.narrator;
			gp.gui.npc.dialogueSet = NPC_Narrator.endingA;
			gp.gui.dialogueScreen(true);
		}
		
		if(scenePhase == 3) {
			g2.setColor(Color.white);
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			gp.gameState = gp.fadeOUT;
			scenePhase++;
		}
		if(scenePhase == 4) {
			g2.setColor(Color.white);
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			if(gp.gameState == gp.playState) scenePhase++;
		}
		if(scenePhase == 5) {
			g2.setColor(Color.white);
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			GameProgress.ending = true;
			gp.saverLoader.saveData();
			gp.gameState = gp.ending;
			
		}
		
		
	}
	
	
	
	
	
	//UTILS
	public void showInfoScreen(int dialogueSet) {
		gp.gui.npc = gp.narrator;
		gp.gui.npc.dialogueSet = dialogueSet;
		gp.gui.informationScreen();
	}


	public void endScene() {
		gp.gameState = gp.playState;
		sceneNum = NONE;
		scenePhase = NONE;
	}
	public void setGuiNpc (String npcName) {
		for(int i = 0; i < gp.npc[1].length; i++) {
			if(gp.npc[gp.currentMap][i] != null &&
			gp.npc[gp.currentMap][i].name.equals(npcName)) {
				gp.gui.npc = gp.npc[gp.currentMap][i];
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {
		case bossSkeletonLord: scene_SkeletonLord(); break;
		case introduction: scene_Intro(); break;
		case oldManEncounter: oldManEncounter(); break; 
		case oldManExplain: oldManExplain(); break;
		case axeHint: axeHint(); break;
		case witchEncounter: witchEncounter(); break;
		case witchQuest1Complete: witchQuest1Complete(); break;
		case oldManQuest2: oldManQuest2(); break;
		case waterGolem: waterGolem(); break;
		case witchGolemDefeated: witchGolemDefeated(); break;
		case waterCrystal: waterCrystal(); break;
		case witchPrincessInfo: witchPrincessInfo(); break;
		case knightEncounter: knightEncounter(); break;
		case princessEncounter: princessEncounter(); break;
		case reportWarning: reportWarning(); break;
		case craftWarning: craftWarning(); break;
		case witchReport: witchReport(); break;
		case princessReunited: princessReunited(); break;
		case princessCraft: princessCraft(); break;
		case trenkLordBattle: trenkLordBattle(); break;		
		case ending: ending(); break;
		
		}
	}
}
