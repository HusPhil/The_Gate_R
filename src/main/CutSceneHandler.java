package main;

import java.awt.Graphics2D;

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
	
	public CutSceneHandler(GamePanel gp) {
		this.gp = gp;
		
		
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {
		case bossSkeletonLord: scene_SkeletonLord(); break;
		}
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
			gp.gui.dialougeScreen();
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
}
