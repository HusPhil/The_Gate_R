package main;

import DataHandling.GameProgress;
import entity.Entity;
import entity.NPC_Witch;
import object.OBJ_Iron_Axe;

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
		setEventRectangle(23,23,5,5);
		
	}
	
	public void setEventRectangle(int _x, int _y, int _width, int _height) {
		eventRect = new EventRectangle[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];	
		int map = 0; int row = 0; int col = 0;
		//LOOP THE 2D ARRAY OF EVENT RECT
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) { 
			eventRect[map][col][row]= new EventRectangle();
			eventRect[map][col][row].x = _x; 
			eventRect[map][col][row].y = _y;
			eventRect[map][col][row].defaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].defaultY = eventRect[map][col][row].y;
			eventRect[map][col][row].width = _width;
			eventRect[map][col][row].height = _height;
			
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
			if(eventCollision(gp.worldMapA, 33, 29, "any")) {
				gp.player.startDialogue(gp.player, 2);
				
				if(gp.gameState == gp.dialogueState && !gp.keys.yesOn) {
					dialogue_type = dt_save;
						
					touchEventON = false;
				}
				
			}
			//GOING INSIDE AND OUTSIDE MERCHANT HOUSE
			else if(eventCollision(gp.worldMapA, 14, 39, "up")) transition(gp.merchantHouse, 24, 23, gp.indoor);
			else if(eventCollision(gp.merchantHouse, 24, 24, "down")) transition(gp.silvioVillage, 35, 15, gp.outside);
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
			else if(eventCollision(gp.silvioVillage, 28, 12, "any") && GameProgress.intro_done) {
				transition(gp.silvioHouse, 24, 23, gp.indoor);
			}
			else if(eventCollision(gp.silvioHouse, 24, 24, "any")) {
				transition(gp.silvioVillage, 28, 12, gp.outside);
			}
			else if(eventCollision(gp.silvioVillage, 37, 38, "any")) {
				transition(gp.silvioHouse, 24, 40, gp.indoor);
			}
			else if(eventCollision(gp.silvioHouse, 24, 41, "any")) {
				transition(gp.silvioVillage, 36, 38, gp.outside);
			}
			else if(eventCollision(gp.silvioHouse, 30, 34, "any")) {
				transition(gp.silvioHouse, 17, 6, gp.dungeon);
			}
			else if(eventCollision(gp.silvioHouse, 16, 6, "any")) {
				transition(gp.silvioHouse, 29, 34, gp.indoor);
			}
			
			//CUTSCENES
			///////////////////////////////////////////////
			else if(eventCollision(gp.dungeonMap_F2, 27, 29, "any")) CS_skeletonLord();
			else if(eventCollision(gp.corrupted1, 27, 25, "down")) {
				CS_oldManEncounter();
				touchEventON = false;
			}
			else if(eventCollision(gp.corrupted1, 14, 29, "down", 0,0,48,48)) {
				CS_oldManExplain();
			}
			else if(eventCollision(gp.silvioVillage, 22, 22, "any")) {
				CS_axeHint();
				touchEventON = false;
			}
			else if(eventCollision(gp.silvioHouse, 18, 37, "any")) {
				CS_witchEncounter();
				touchEventON = false;
			}
			
			
			//////////////////////////////////////////////
		}
	}
	public boolean eventCollision(int map, int eventCol, int eventRow, String reqDirection) {
		setEventRectangle(23,23,5,5);
		
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
	
	public boolean eventCollision(
			int map, int eventCol, int eventRow, String reqDirection,
			int x, int y, int width, int height
			) {
		
		setEventRectangle(x,y,width,height);
		
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

	public void loadingScreen(int map, int x, int y, int area) {
		gp.gameState = gp.loadingState;
		gp.nextArea = area;
		tempMap = map;
		tempCol = x;
		tempRow = y;
	}

	//CUTSCENES
	public void CS_skeletonLord() {
		if(!gp.bossBattleOn && !GameProgress.defeatedSkeletonLord) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.bossSkeletonLord;
			gp.player.worldX = 27*48; gp.player.worldY = 29*48;
		}
	}
	public void CS_oldManEncounter() {
		if(!GameProgress.encounterOldMan) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.oldManEncounter;
			gp.player.worldX = 27*48; gp.player.worldY = 25*48;
		}
	}
	public void CS_oldManExplain() {
		if(!GameProgress.oldManExplained) {
			Entity obj = null;
			for(int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[gp.currentMap][i] != null && gp.gameObjs[gp.currentMap][i].cs_id.equals("001")) {
					obj = gp.gameObjs[gp.currentMap][i];
				}
			}
			
			if(obj == null) {
				gp.gameState = gp.cutSceneState;
				gp.csHandler.sceneNum = gp.csHandler.oldManExplain;
			}
		}
	}
	public void CS_axeHint() {
		if(!gp.player.itemIsInsideInventory(OBJ_Iron_Axe.objName) && GameProgress.intro_done) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.axeHint;
		}
	}
	
	public void CS_witchEncounter() {
		if(!GameProgress.witchEncountered) {
		}
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.witchEncounter;
	}

}
