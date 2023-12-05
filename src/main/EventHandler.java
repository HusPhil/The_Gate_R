package main;

import java.util.HashMap;
import java.util.Map;

import DataHandling.GameProgress;
import entity.Entity;
import entity.NPC_Narrator;
import entity.NPC_Witch;
import object.ITM_Bandage;
import object.ITM_EvilSkull;
import object.ITM_SlimeGel;
import object.ITM_TrenkMeat;
import object.ITM_WaterCrystal;
import object.ITM_WaterEssence;
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
	
	
	Map<Integer, Runnable> eventsMap = new HashMap<>();
    
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		setEventRectangle(23,23,5,5);
		eventsMap.put(gp.worldMapA, this::worldMapAEvents);
	    eventsMap.put(gp.merchantHouse, this::merchantHouseEvents);
	    eventsMap.put(gp.finalStage, this::finalStageEvent);
	    eventsMap.put(gp.dungeonMap_F1, this::dungeonF1events);
	    eventsMap.put(gp.dungeonMap_F2, this::dungeonF2Events);
	    eventsMap.put(gp.corrupted1, this::corruptedArea1Events);
	    eventsMap.put(gp.silvioVillage, this::silvioVillageEvents);
	    eventsMap.put(gp.silvioHouse, this::silvioHouseEvents);
	    eventsMap.put(gp.forest, this::forestEvents);
	    eventsMap.put(gp.corrupted2, this::corruptedArea2Events);
	    eventsMap.put(gp.princessKingdom, this::princessKingdomEvents);
	    eventsMap.put(gp.sacredRiver, this::sacredRiverEvents);
	    eventsMap.put(gp.maze, this::mazeEvents);
	    eventsMap.put(gp.princessCage, this::princessCageEvents);
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

        // Create a map to associate each map with its corresponding event function
        
        // Add other mappings here...

        //CHECK THE PLAYER POS FROM PREV EVENT
        int distanceX = Math.abs(gp.player.worldX - prevEventX);
        int distanceY = Math.abs(gp.player.worldY - prevEventY);
        int realDistance = Math.max(distanceX, distanceY);
        if (realDistance > gp.tileSize) {
            touchEventON = true;
        }
        
        if (touchEventON) {
            // Check if the current map exists in the events map
            if (eventsMap.containsKey(gp.currentMap)) {
                // Execute the corresponding event function
            	eventsMap.get(gp.currentMap).run();
            }
        }
	}
	
	private void princessCageEvents() {

		if(eventCollision(gp.princessCage, 30, 22, "up", 0, 0, gp.tileSize, gp.tileSize)) {
			CS_princessEncounter();
		}
		else if(eventCollision(gp.princessCage, 20, 18, "right")) {
			transition(gp.forest, 21, 35, gp.outside);
		}
		else if(eventCollision(gp.princessCage, 30, 16, "right")) {
			transition(gp.dungeonMap_F2, 13, 12, gp.dungeon);
		}
			
	}

	private void mazeEvents() {

		if(eventCollision(gp.maze, 38, 9, "right")) {
			transition(gp.dungeonMap_F1, 13, 40, gp.dungeon);
		}
		else if(eventCollision(gp.maze, 11, 9, "left")) {
			transition(gp.forest, 37, 11, gp.outside);
		}
		
	
	}

	private void sacredRiverEvents() {

		if(eventCollision(gp.sacredRiver, 10, 13, "left")) {
			transition(gp.forest, 35, 32, gp.outside);
		}
		else if(eventCollision(gp.sacredRiver, 11, 34, "down")) {
			CS_waterGolem();
			touchEventON = false;
		}
		
			
	}

	private void princessKingdomEvents() {

		if(eventCollision(gp.princessKingdom, 24, 41, "down")) transition(gp.corrupted2, 23, 30, gp.outside);
		else if(eventCollision(gp.princessKingdom, 21, 20, "up", 0, 0, gp.tileSize*7, gp.tileSize )) {
			CS_princessReunited();
		}
			
	}

	private void corruptedArea2Events() {

		if(eventCollision(gp.corrupted2, 11, 12, "up")) transition(gp.forest, 21, 38, gp.outside);
		else if(eventCollision(gp.corrupted2, 23, 29, "up", 0, 0, gp.tileSize, gp.tileSize + 5)) {
				transition(gp.princessKingdom, 24, 40, gp.indoor);
		}
			
	}

	private void forestEvents() {

		if(eventCollision(gp.forest, 11, 10, "left")) transition(gp.silvioHouse, 29, 6, gp.dungeon);
		else if(eventCollision(gp.forest, 36, 32, "right", 0, 0, gp.tileSize, gp.tileSize)) {
			transition(gp.sacredRiver, 11, 13, gp.dungeon);
		}
		else if(eventCollision(gp.forest, 33, 23, "up")) {
			CS_knightEncounter();
		}
		else if(eventCollision(gp.forest, 38, 11, "right")) {
			transition(gp.maze, 12, 9, gp.dungeon);
		}
		else if(eventCollision(gp.forest, 32, 40, "right")) {
			transition(gp.merchantHouse, 24, 23, gp.indoor);
		}
		else if(eventCollision(gp.forest, 20, 35, "left")) {
			transition(gp.princessCage, 19, 18, gp.dungeon);
		}
		else if(eventCollision(gp.forest, 21, 41, "down")) {
			if(GameProgress.witchReported && !GameProgress.princessReunited)
				transition(gp.corrupted2, 11, 13, gp.outside);
			else if(GameProgress.princessReunited && !GameProgress.princessCrafted) {
				CS_craftWarning();
			}
			else CS_reportWarning();
		}
			
	}

	private void silvioHouseEvents() {

		if(eventCollision(gp.silvioHouse, 30, 6, "right")) transition(gp.forest, 12, 10, gp.outside);
		else if(eventCollision(gp.silvioHouse, 24, 24, "any")) {
			transition(gp.silvioVillage, 28, 12, gp.outside);
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
		else if(eventCollision(gp.silvioHouse, 18, 37, "any")) {
			if(!GameProgress.witchQuest1Complete) {
				if(gp.player.itemIsInsideInventory(ITM_SlimeGel.objName) && gp.player.itemIsInsideInventory(ITM_TrenkMeat.objName)) {
					CS_witchQuest1Complete();
				}
				else CS_witchEncounter();
				touchEventON = false;
			}
			else {
				if(GameProgress.waterGolemDefeated && !GameProgress.waterCrystalActivated) {
					if(gp.player.itemIsInsideInventory(ITM_Bandage.objName) ) {
						if(!gp.player.itemIsInsideInventory(ITM_WaterEssence.objName))
							gp.csHandler.scenePhase = 1;
						CS_witchGolemDefeated();
					}
					touchEventON = false;
				}
				else if(GameProgress.waterCrystalActivated && !GameProgress.princessEncountered) {
					CS_witchPrincessInfo();
					touchEventON = false;
				}
				else if(GameProgress.defeatedSkeletonLord && GameProgress.princessEncountered) {
					if(gp.player.itemIsInsideInventory(ITM_EvilSkull.objName))
						CS_witchReport();
					touchEventON = false;
				}
				
				else {
					for(int i = 0; i < gp.npc[1].length; i++) {
						if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(NPC_Witch.NPC_Name)) {
							gp.npc[gp.currentMap][i].startDialogue(gp.npc[gp.currentMap][i], gp.npc[gp.currentMap][i].dialogueSet);
							break;
						}
					}
				}
					
			}
			touchEventON = false;
			
		}
		else if(eventCollision(gp.silvioHouse, 22, 19, "any")) {
			if(!GameProgress.witchReported) {
				if(GameProgress.witchQuest1Complete && !GameProgress.oldManQuest2Explained) CS_oldManQuest2();
				else if(GameProgress.waterGolemDefeated && !GameProgress.waterCrystalActivated) {
					if(gp.player.itemIsInsideInventory(ITM_WaterCrystal.objName))
					CS_waterCrystal();
				}
			}
			touchEventON = false;
		}
		
		
			
	}

	public void silvioVillageEvents() {

		if(eventCollision(gp.silvioVillage, 28, 12, "any") && GameProgress.intro_done) {
			if(gp.gameState != gp.cutSceneState)
			transition(gp.silvioHouse, 24, 23, gp.indoor);
		}
		else 
			if(eventCollision(gp.silvioVillage, 37, 38, "right")) {
			transition(gp.silvioHouse, 24, 40, gp.indoor);
			
		}
		else if(eventCollision(gp.silvioVillage, 22, 22, "any")) {
			if(gp.csHandler.sceneNum == gp.csHandler.princessReunited) {
				transition(gp.silvioHouse, 18, 21, gp.indoor);
				gp.csHandler.scenePhase++;
			}
			else CS_axeHint();
		}
		touchEventON = false;
			
	}

	private void corruptedArea1Events() {

		if(eventCollision(gp.corrupted1, 27, 25, "down")) {
			CS_oldManEncounter();
			touchEventON = false;
		}
		else if(eventCollision(gp.corrupted1, 14, 29, "down", 0,0,gp.tileSize,gp.tileSize)) {
			CS_oldManExplain();
		}
			
	}

	private void dungeonF2Events() {

		if(eventCollision(gp.dungeonMap_F2, 12, 21, "left")) transition(gp.dungeonMap_F1, 13, 9, gp.dungeon);
		else if(eventCollision(gp.dungeonMap_F2, 27, 29, "any")) CS_skeletonLord();
		else if(eventCollision(gp.dungeonMap_F2, 12, 12, "left")) transition(gp.princessCage, 29, 16, gp.dungeon);
			
	}

	private void dungeonF1events() {

		if(eventCollision(gp.dungeonMap_F1, 12, 40, "left")) transition(gp.maze, 37, 9, gp.dungeon);
		else if(eventCollision(gp.dungeonMap_F1, 12, 9, "left")) transition(gp.dungeonMap_F2, 13, 21, gp.dungeon);
		
			
	}

	private void merchantHouseEvents() {

		if(eventCollision(gp.merchantHouse, 24, 24, "down")) transition(gp.forest, 32, 40, gp.outside);
		else if(eventCollision(gp.merchantHouse, 18, 20, "up")) {
			gp.npc[2][gp.worldMapA].startDialogue(gp.npc[2][gp.worldMapA], 0);
			touchEventON = false;
		}
		
			
	}

	private void worldMapAEvents() {

//		SAVING EVENT
		if(eventCollision(gp.worldMapA, 33, 29, "any")) {
			gp.player.startDialogue(gp.player, 2);
			
			if(gp.gameState == gp.dialogueState && !gp.keys.yesOn) {
				dialogue_type = dt_save;
					
				touchEventON = false;
			}
		}
		else if(eventCollision(gp.worldMapA, 14, 39, "up")) transition(gp.merchantHouse, 24, 23, gp.indoor);
		else if(eventCollision(gp.worldMapA, 35, 37, "right")) transition(gp.dungeonMap_F1, 13, 40, gp.dungeon);
			
	}

	private void finalStageEvent() {
		if(eventCollision(gp.finalStage, 36, 26, "any", 0, 0, gp.tileSize, gp.tileSize/2)) {
			CS_trenkLordBattle();
			touchEventON = false;
		}
	}
	
	public boolean eventCollision(int map, int eventCol, int eventRow, String reqDirection) {
		setEventRectangle((gp.tileSize/2)-5, (gp.tileSize/2)-5,5,5);
		
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
	private void CS_skeletonLord() {
		if(!gp.bossBattleOn && !GameProgress.defeatedSkeletonLord) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.bossSkeletonLord;
			gp.player.worldX = 27*gp.tileSize; gp.player.worldY = 29*gp.tileSize;
		}
	}
	private void CS_oldManEncounter() {
		if(!GameProgress.encounterOldMan) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.oldManEncounter;
			gp.player.worldX = 27*gp.tileSize; gp.player.worldY = 25*gp.tileSize;
		}
	}
	private void CS_oldManExplain() {
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
	private void CS_axeHint() {
		if(!gp.player.itemIsInsideInventory(OBJ_Iron_Axe.objName) && GameProgress.intro_done) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.axeHint;
		}
	}
	
	private void CS_witchEncounter() {
		if(!GameProgress.witchQuest1Complete) {
		}
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.witchEncounter;
	}
	
	private void CS_witchQuest1Complete() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.witchQuest1Complete;
	}
	
	private void CS_oldManQuest2() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.oldManQuest2;
	}
	
	private void CS_waterGolem() {
		if(!gp.bossBattleOn && !GameProgress.waterGolemDefeated) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.waterGolem;
		}
	}
	
	private void CS_witchGolemDefeated() {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.witchGolemDefeated;
	}
 
	private void CS_waterCrystal() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.waterCrystal;
	}
	
	private void CS_witchPrincessInfo() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.witchPrincessInfo;
	}
	
	private void CS_knightEncounter() {
		if(!GameProgress.knightEncountered) {
			gp.gameState = gp.cutSceneState;
			gp.csHandler.sceneNum = gp.csHandler.knightEncounter;
		}
	}
	
	private void CS_princessEncounter() {

		if(!GameProgress.princessEncountered) {
			Entity obj = null;
			for(int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[gp.currentMap][i] != null && gp.gameObjs[gp.currentMap][i].cs_id.equals("002A")) {
					obj = gp.gameObjs[gp.currentMap][i];
				}
			}
			
			if(obj == null) {
				gp.gameState = gp.cutSceneState;
				gp.csHandler.sceneNum = gp.csHandler.princessEncounter;
				System.out.print("called");
			}
		}
	
	}
	
	private void CS_reportWarning() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.reportWarning;
	}
	
	private void CS_craftWarning() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.craftWarning;
	}
	
	private void CS_witchReport() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.witchReport;
	}
	
	private void CS_princessReunited() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.princessReunited;
	}

	private void CS_trenkLordBattle() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.trenkLordBattle;
	}
}
