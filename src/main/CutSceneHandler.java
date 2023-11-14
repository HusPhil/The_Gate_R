package main;

import java.awt.Color;
import java.awt.Graphics2D;

import DataHandling.GameProgress;
import entity.Entity;
import entity.NPC_Cursed_Villager;
import entity.NPC_Hermit;
import entity.NPC_Narrator;
import entity.NPC_PlayerDummy;
import entity.NPC_Witch;
import monster.BOSS_SkeletonLord;
import monster.MON_Trenklin;
import object.ITM_Key;
import object.ITM_SlimeGel;
import object.ITM_TrenkAmulet;
import object.ITM_TrenkMeat;
import object.OBJ_IronDoor;

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
	
	
	int gelAmmount = 0;
	int meatAmmount = 0;
	
	
	public CutSceneHandler(GamePanel gp) {
		this.gp = gp;
		
		
	}
	
	
	
	public void scene_SkeletonLord() {
		//PHASE 0
		
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
			
			//change the music [stop current music and play new music]
		}
	}
	
	public void scene_Intro() {
		gp.fxHandler.lighting.timeState = gp.fxHandler.lighting.day;
		//PHASE 0
		if(scenePhase == 0) {
//			gp.gameState = gp.loadingState;
//			gp.gui.fadeIn();
			gp.eventHandler.loadingScreen(gp.corrupted1, 25, 12, gp.outside);
//			gp.eventHandler.loadingScreen(gp.silvioHouse, 18, 38, gp.indoor);
//			gp.eventHandler.loadingScreen(gp.silvioHouse, 20, 20, gp.indoor);
//			GameProgress.oldManExplained = true;
//			GameProgress.witchQuest1Complete = true;
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
		gp.fxHandler.lighting.timeState = gp.fxHandler.lighting.day;
		if(scenePhase == 0) {
			GameProgress.encounterOldMan = true;
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
			gp.player.drawing = true;
			scenePhase = NONE;
			sceneNum = NONE;
			gp.gameState = gp.playState;
			
			//change the music [stop current music and play new music]
		}
	}
	
	public void oldManExplain() {
		gp.fxHandler.lighting.timeState = gp.fxHandler.lighting.day;
		
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
			gp.eventHandler.transition(gp.silvioVillage, 17, 10, gp.outside);
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
					gp.npc[gp.currentMap][i].worldY = 10*gp.tileSize;
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
			
			boolean monstersAlive = false;
			gp.gameState = gp.playState;

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
			    scenePhase++;
			}
		}

		
		if(scenePhase == 18) {
			gp.gameState = gp.cutSceneState;
			showInfoScreen(NPC_Narrator.defeated_all_enemy);
		}
		if(scenePhase == 19) {
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
			
			gp.gui.npc.dialogueSet = NPC_Hermit.intro_end_3;
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
		}
	}

	public void axeHint() {
		if(scenePhase == 0) {
			showInfoScreen(NPC_Narrator.axeHint_1);
		}
		if(scenePhase == 1) endScene();
	}
	
	public void witchEncounter() {
		
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
		System.out.println(gelAmmount + "::" + meatAmmount);
		
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
				GameProgress.witchQuest1Complete = true;
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
			showInfoScreen(NPC_Narrator.oldManQ2b);
		}
		if(scenePhase == 6) {
			setGuiNpc(NPC_Hermit.NPC_Name);
			gp.gui.npc.dialogueSet = NPC_Hermit.oldManQ2c;
			gp.gui.dialogueScreen(false);
		}
		if(scenePhase == 7) {
			gp.fxHandler.lighting.timeState = gp.fxHandler.lighting.day;
			gp.eventHandler.transition(gp.silvioVillage, 29, 16, gp.outside);
			scenePhase++;
			
			
		}
		if(scenePhase == 8) {
			if(gp.gameState == gp.playState)
				scenePhase++;
		}
		if(scenePhase == 9) {
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] =  new NPC_Hermit(gp);
					gp.npc[gp.currentMap][i].worldX =  31*gp.tileSize;
					gp.npc[gp.currentMap][i].worldY =  16*gp.tileSize;
					gp.gui.npc = gp.npc[gp.currentMap][i];
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
		if(scenePhase == 14) {
			endScene();
		}
		System.out.println("sinpeys: " + scenePhase);
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
		}
	}
}
