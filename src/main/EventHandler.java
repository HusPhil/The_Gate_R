package main;

import DataHandling.GameProgress;

public class EventHandler { 
	
	GamePanel gp;
	EventRectangle eventRect[][][];
	int tempMap, tempCol, tempRow;
	int dialogue_type;
	public final int dt_save = 1;
	public int prevEventX, prevEventY;
	public boolean touchEventON = !false;
	public boolean telOn = false;
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRectangle[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];	
		int map = 0; int row = 0; int col = 0;
		//LOOP THE 2D ARRAY OF EVENT RECT
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) { 
			eventRect[map][col][row]= new EventRectangle();
			eventRect[map][col][row].x = 23; 
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].defaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].defaultY = eventRect[map][col][row].y;
			eventRect[map][col][row].width = 5;
			eventRect[map][col][row].height = 5;
			
			col++;
			
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0; map++;
					}
				
				
			}
		}
	}

	public void checkEvent() {
		//CHECK THE PLAYER POS FROM PREV EVENT
		int distanceX = Math.abs(gp.player.worldX - prevEventX);
		int distanceY = Math.abs(gp.player.worldY - prevEventY);
		int realDistance = Math.max(distanceX, distanceY);
		if(realDistance > gp.tileSize) touchEventON = true;
		
		if(touchEventON) {
			//SAVING EVENT
			if(eventCollision(0, 33, 29, "any")) {
				gp.player.startDialogue(gp.player, 2);
				
				if(gp.gameState == gp.dialogueState && !gp.keys.yesOn) {
					dialogue_type = dt_save;
						
					touchEventON = false;
				}
				
			}
			//GOING INSIDE AND OUTSIDE MERCHANT HOUSE
			else if(eventCollision(gp.worldMapA, 14, 39, "up")) transition(gp.merchantHouse, 24, 23, gp.indoor);
			else if(eventCollision(gp.merchantHouse, 24, 24, "down")) transition(gp.worldMapA, 14, 39, gp.outside);
			//WORLDMAP-A to F1_Dungeon (ViceVersa)
			else if(eventCollision(gp.worldMapA, 35, 37, "right")) transition(gp.dungeonMap_F1, 13, 40, gp.dungeon);
			else if(eventCollision(gp.dungeonMap_F1, 12, 40, "left")) transition(gp.worldMapA, 34, 37, gp.outside);
			//F1_Dungeon to F2_Dungeon (ViceVersa)
			else if(eventCollision(gp.dungeonMap_F1, 12, 9, "left")) transition(gp.dungeonMap_F2, 13, 21, gp.dungeon);
			else if(eventCollision(gp.dungeonMap_F2, 12, 21, "left")) transition(gp.dungeonMap_F1, 13, 9, gp.dungeon);
			
			//MERCHANT HOUSE DIALOGUE
			else if(eventCollision(gp.merchantHouse, 18, 20, "up")) {
				gp.npc[2][gp.worldMapA].startDialogue(gp.npc[2][gp.worldMapA], 0);
				touchEventON = false;
			}
			
			//CUTSCENES
			///////////////////////////////////////////////
			else if(eventCollision(gp.dungeonMap_F2, 27, 29, "any")) CS_skeletonLord();
			
			
			//////////////////////////////////////////////
		}
	}
	public boolean eventCollision(int map, int eventCol, int eventRow, String reqDirection) {
		boolean collide = false;
		
		if (map == gp.currentMap) {
			//GET PLAYER'S SOLID AREA
			gp.player.solidArea.x += gp.player.worldX;
			gp.player.solidArea.y += gp.player.worldY;
			
			//GET EVENT'S SOLID AREA
			eventRect[map][eventCol][eventRow].x += eventCol*gp.tileSize;
			eventRect[map][eventCol][eventRow].y += eventRow*gp.tileSize;
			 
			//System.out.println(eventRect[eventCol][eventRow].y/48  + " m  " + eventRect[eventCol][eventRow].x/48);
			 
			if(gp.player.solidArea.intersects(eventRect[map][eventCol][eventRow])) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					collide = true;
					prevEventX = gp.player.worldX;
					prevEventY = gp.player.worldY;
				}
			}
			
			//RESET PLAYER'S SOLID AREA
			gp.player.solidArea.x = gp.player.defaultSolidAreaX;
			gp.player.solidArea.y = gp.player.defaultSolidAreaY;
			
			//RESET EVENT'S SOLID AREA
			eventRect[map][eventCol][eventRow].x = eventRect[map][eventCol][eventRow].defaultX;
			eventRect[map][eventCol][eventRow].y = eventRect[map][eventCol][eventRow].defaultY;
		}
		return collide;
	}
	
	public void saveScene(int map, int x, int y) {
		if(eventCollision(map, x, y, "any")) {
			
			
			
		}
	}
	public void transition(int map, int x, int y, int area) {
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		tempMap = map;
		tempCol = x;
		tempRow = y;
		touchEventON = false;
	}
	
	//CUTSCENES
	public void CS_skeletonLord() {
		if(!gp.bossBattleOn && !GameProgress.defeatedSkeletonLord) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.bossSkeletonLord;
			gp.player.worldX = 27*48; gp.player.worldY = 29*48;
		}
	}

}
