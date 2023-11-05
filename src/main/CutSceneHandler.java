package main;

import java.awt.Color;
import java.awt.Graphics2D;

import DataHandling.GameProgress;
import entity.Entity;
import entity.NPC_Hermit;
import entity.NPC_PlayerDummy;
import monster.BOSS_SkeletonLord;
import object.OBJ_IronDoor;

public class CutSceneHandler {
	GamePanel gp;
	Graphics2D g2;
	public int scenePhase;
	public int sceneNum;
	
	//Scenes' Number
	public final int NONE = 0;
	public final int bossSkeletonLord = 1;
	public final int introduction = 2;
	public final int oldManEncounter = 3;
	public final int oldManExplain = 4;
	
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
					gp.gameObjs[gp.currentMap][i].worldX = 27*48;
					gp.gameObjs[gp.currentMap][i].worldY = 28*48;
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
			if(gp.player.worldY >= 35*48) scenePhase++; 
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
			gp.gui.dialougeScreen(false);
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
		//PHASE 0
		if(scenePhase == 0) {
//			gp.gameState = gp.loadingState;
//			gp.gui.fadeIn();
			gp.eventHandler.loadingScreen(gp.corrupted1, 25, 12, gp.outside);
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
			
			gp.gui.dialougeScreen(false);
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
			if(gp.player.worldX <= 14*48) scenePhase++; 
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
			gp.gui.dialougeScreen(false);
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
			gp.gui.npc.currentSearchPath = NPC_Hermit.oldManFreed;
			scenePhase++;
			
		}
		if(scenePhase == 2) {
//			gp.gui.dialougeScreen(false);
			gp.gameState = gp.playState;
			if(gp.gui.npc.currentSearchPath == Entity.pathOFF) {
				scenePhase++;
				gp.gameState = gp.cutSceneState;
			}
			
		}
		if(scenePhase == 3) {
			gp.gui.npc.facePlayer();
			gp.gui.dialougeScreen(false);
		}
		
		//PHASE 3
		if(scenePhase == 4) {
			GameProgress.oldManExplained = true;
//			g2.setColor(Color.DARK_GRAY);
//			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.worldMapA][i] == null) {
					gp.npc[gp.worldMapA][i] = new NPC_Hermit(gp);
					gp.npc[gp.worldMapA][i].name = "Silvio";
					gp.npc[gp.worldMapA][i].speed = 0;
					gp.npc[gp.worldMapA][i].worldX = 18*48;
					gp.npc[gp.worldMapA][i].worldY = 11*48;
					gp.gui.npc = gp.npc[gp.worldMapA][i];
					break;
				}
			}
			scenePhase++;
		}
		if(scenePhase == 5) {
			gp.eventHandler.transition(gp.worldMapA, 17, 10, gp.outside);
			gp.gui.npc.direction = "down";
			scenePhase++;
		}
		if(gp.gameState == gp.gameState && scenePhase == 6) {
			if(gp.currentMap == gp.worldMapA) {
				gp.player.direction = "down";
				scenePhase++;
			}
		}
		if(scenePhase == 7) {
			gp.gameState = gp.fadeOUT;
			scenePhase++;
		}
		if(scenePhase == 8 && gp.gameState == gp.playState) scenePhase++;
		if(scenePhase == 9) {
			gp.gameState = gp.cutSceneState;
			gp.gui.npc.dialogueSet = NPC_Hermit.explaining;
			gp.gui.dialougeScreen(false);
		}
		if(scenePhase == 10) {
			gp.gameState = gp.playState;
			scenePhase++;
		}
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {
		case bossSkeletonLord: scene_SkeletonLord(); break;
		case introduction: scene_Intro(); break;
		case oldManEncounter: oldManEncounter(); break; 
		case oldManExplain: oldManExplain(); break;
		}
	}
}
