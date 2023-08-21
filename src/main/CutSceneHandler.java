package main;

import java.awt.Graphics2D;

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
		if(scenePhase == 0) {
			gp.bossBattleOn = true;
			
			for(int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[gp.currentMap][i] == null) {
					gp.gameObjs[gp.currentMap][i] = new OBJ_IronDoor(gp);
					gp.gameObjs[gp.currentMap][i].worldX = 36*48;
					gp.gameObjs[gp.currentMap][i].worldY = 27*48;
					gp.gameObjs[gp.currentMap][i].temp = true;
					gp.playSE(7);
					break;
				}
			}
			scenePhase++;
		}
	}
}
