package main;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import DataHandling.DatabaseManagement;
import DataHandling.GameProgress;
import entity.NPC_Narrator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class KeyHandler implements KeyListener {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, dashPressed, enterPressed, talkOn, yesOn, fireAway;
	public boolean debugPressed;
	public int pressTime = 0;
	public boolean keyFreeze = false;
	public int testInt = 0;
	public int delayTimer = 0;
	GamePanel gp;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
		
	}
	
	@Override
	
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		//KEYS DURING GAME MENU
		if(gp.gameState == gp.gameMenu) {
			gameMenuState(code);
		}
		
		//KEYS DURING PAUSE
		else if(gp.gameState == gp.pauseState) {
			pauseStateKeys(code);
		}
		
		//KEYS DURING DIALOUGE
		else if(gp.gameState == gp.dialogueState || 
				gp.gameState == gp.cutSceneState ||
				gp.gameState == gp.loadingDialogueState) {
			dialogueStateKeys(code);
		}
		
		//KEYS DURING VIEW CHAR STATE
		else if (gp.gameState == gp.viewCharState) {
			viewCharState(code);
		}
		
		//KEYS DURING PLAYSTATE
		else if(gp.gameState == gp.playState) {
			if(!keyFreeze) playStateKeys(code);
		}
		
		//KEYS DURING OPTIONS
		else if(gp.gameState == gp.optionsState) {
			optionsStateKeys(code);
		}
		
		//KEYS DURING TRADING
		else if(gp.gameState == gp.tradingState) {
			tradingStateKeys(code);
		}
		
		//KEYS DURING MAP VIEW 
		else if(gp.gameState == gp.viewMapState) {
			mapStateKeys(code);
		}
		
		//KEYS DURING GAME OVER STATE
		else if(gp.gameState == gp.gameOverState) {
			gameOverStateKeys(code);
		}

		//KEYS DURING GAME OVER STATE
		else if(gp.gameState == gp.ending) {
			endingKeys(code);
		}
		

	}
	
	private void endingKeys(int code) {
		gp.player.attacking = false;
		
		System.out.println(delayTimer);
		gp.player.attacking = false;
		if(delayTimer > 60*3) {
			gp.gui.selectItem = 0;
			switch(code) {
			case KeyEvent.VK_ENTER:
					System.exit(0);
				
				break;
			}
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
	
		switch (code) {
		case KeyEvent.VK_W:
			upPressed = false;
			break;
		case KeyEvent.VK_S:
			downPressed = false;
			break;
		case KeyEvent.VK_A:
			leftPressed = false;
			break;
		case KeyEvent.VK_D:
			rightPressed = false;
			break;
		case KeyEvent.VK_SPACE:
			dashPressed = false;
			break;
		case KeyEvent.VK_T:
			talkOn = false;
			break;
		case KeyEvent.VK_ENTER:
			enterPressed = false;
			
			break;
		case KeyEvent.VK_Y:
			gp.player.offsetRand++;
			yesOn = false;
			break;
		case KeyEvent.VK_F:
			gp.player.offsetRand--;
			 fireAway = false;
			break;
		case KeyEvent.VK_L: 
			debugPressed = false;
			break;
		}		
	}
	
	public void gameOverStateKeys(int code) {
		
		
		
		if(delayTimer > 60*3) {
			switch(code) {
			case KeyEvent.VK_W:
				gp.gui.selectItem--;
				if(gp.gui.selectItem < 0) gp.gui.selectItem = 1;
				gp.playSE(5);
				break;
			case KeyEvent.VK_S:
				gp.gui.selectItem++;
				if(gp.gui.selectItem > 1) gp.gui.selectItem = 0;
				gp.playSE(5);
				break;
			case KeyEvent.VK_ENTER:
				delayTimer++;
					if(gp.gui.selectItem == 0) {
						gp.playSE(0);
						gp.gameState = gp.playState;
						gp.resetStatus(false);
					} 
					else {
						gp.playSE(0);
						gp.gameState = gp.gameMenu;
						gp.resetStatus(true);
					}
					delayTimer = 0;
				
				break;
			}
		}
	}
	
	public void mapStateKeys(int code) {
		switch(code) {
		case KeyEvent.VK_M: gp.gameState = gp.playState; break;
		}
		
	}

	public void gameMenuState(int code) {
		switch(code) {
		case KeyEvent.VK_W:
			gp.gui.selectItem--;
			if(gp.gui.selectItem < 0) gp.gui.selectItem = 2;
			break;
		case KeyEvent.VK_S:
			gp.gui.selectItem++;
			if(gp.gui.selectItem > 2) gp.gui.selectItem = 0;
			break;
		case KeyEvent.VK_ENTER:
			gp.gui.messages.clear();
			if(gp.gui.selectItem == 0) {
			String input = JOptionPane.showInputDialog(null, " Tell me, brave one, what name echoes through the annals of your valor?", "WHO ARE YOU?", JOptionPane.PLAIN_MESSAGE);
			if(input != null) {
				
				input = input.trim();
			}
			if (input == null) {
				JOptionPane.showMessageDialog(null, "Hmm, so you decided not to partake in this journey, what a shame.", "What a shame..", JOptionPane.PLAIN_MESSAGE);
			}
			else if (!DatabaseManagement.checkUserExist("player_name", input)) {
//				gp.gui.showTransition(true);
				gp.narrator.player_name = input;
				gp.narrator.setDialogue();
				gp.player.name = input;
//				gp.gameState = gp.playState; 
				gp.gui.addMessage("The player's name is: " + input);
				gp.player.ID = DatabaseManagement.generatePlayerID();
				gp.DBMS.createPlayerData();
				
				gp.gameState = gp.cutSceneState;
				gp.csHandler.sceneNum = gp.csHandler.introduction;
			}
			else if(input.isEmpty()) JOptionPane.showMessageDialog(null, "You name cannot be blank, try again.", "There seems to be a problem..", JOptionPane.WARNING_MESSAGE);
			else JOptionPane.showMessageDialog(null, "This name is already taken, try another one.", "There seems to be a problem..", JOptionPane.WARNING_MESSAGE);
			}
			else if(gp.gui.selectItem == 1) {
				String input = JOptionPane.showInputDialog(null, "Enter your player ID", "Verification", JOptionPane.PLAIN_MESSAGE);
				
				if (DatabaseManagement.checkUserExist("player_id", input)) {
					gp.player.ID = input;
					gp.saverLoader.loadData(); 
					gp.DBMS.loadGameProgress();
					
					gp.gameState = gp.fadeIN;
				} 
				else JOptionPane.showMessageDialog(null, "The ID you entered was not found in the database. Try another one.", "There seems to be a problem..", JOptionPane.WARNING_MESSAGE);
				
				}
			else if(gp.gui.selectItem == 2) System.exit(0);
			break;
		}
	}
	
	public void playStateKeys(int code) {
		int x = gp.player.getPlayerWordlX();
		int y = gp.player.getPlayerWordlY();
		
		switch (code) {
		case KeyEvent.VK_W: upPressed = true; break;
		case KeyEvent.VK_S: downPressed = true; break;
		case KeyEvent.VK_A: leftPressed = true; break;
		case KeyEvent.VK_D: rightPressed = true; break;
		case KeyEvent.VK_SPACE:
			//System.out.println("X:" + x + " Y: " + y); 
			if(gp.player.life <= 0) gp.player.life = gp.player.maxLife;
			pressTime+=5;
			dashPressed = true;
			break;
		case KeyEvent.VK_M: 
//			gp.DBMS.storeFileContentToDatabase("save.dat");
			x = gp.player.worldX;	
			
			System.out.println("X:" + x + " Y: " + y); 
			gp.tManager.reloadMaps();
//			gp.DBMS.createPlayerData();
//			gp.DBMS.loadPlayerData();

			
//			gp.DBMS.retrieveAndSaveToFile();
//			gp.gui.addMessage(DatabaseManagement.generatePlayerID());
			//gp.gameState = gp.viewMapState;
			break;
		case KeyEvent.VK_P: 
			gp.gameState = gp.pauseState; 
			
//			gp.createAssets.makeMonster();
//			gp.tManager.loadMap("/maps/worldMapA.txt", gp.worldMapA);
//		    gp.tManager.loadMap("/maps/dungeonMap_F1.txt", gp.dungeonMap_F1);
//		    gp.tManager.loadMap("/maps/merchantHouse.txt", gp.merchantHouse);
//		    gp.tManager.loadMap("/maps/dungeonMap_F2.txt", gp.dungeonMap_F2);
//		    gp.tManager.loadMap("/maps/corrupted_area1.txt", gp.corrupted1);
//		    gp.tManager.loadMap("/maps/silvioVillage.txt", gp.silvioVillage);
//		    gp.tManager.loadMap("/maps/silvioHouse.txt", gp.silvioHouse);
//		    gp.tManager.loadMap("/maps/forest.txt", gp.forest);
			
			break;
		case KeyEvent.VK_T: talkOn = true; break;
		case KeyEvent.VK_ENTER: 
			if(!keyFreeze) {
				enterPressed = true; 
				if(gp.player.attacking == false) gp.player.spriteNum = 1; 
			}
			break;
		case KeyEvent.VK_Y: yesOn = true; break;
		case KeyEvent.VK_C: 
//			if(GameProgress.oldManExplained)
				gp.gameState = gp.viewCharState; 
			break;
		case KeyEvent.VK_F: fireAway = true; break;
		case KeyEvent.VK_ESCAPE: gp.gameState = gp.optionsState; break;
		case KeyEvent.VK_L: 
			if(debugPressed) debugPressed = false;
			else if(!debugPressed) debugPressed = true; 
			break;
		case KeyEvent.VK_X: 
//			gp.gameState = gp.tradingState;
//			gp.currentMap = 0;
//			switch(gp.currentMap) {
//			case 0: gp.tManager.loadMap("/maps/worldMapA.txt", 0); break;
//			case 1: gp.tManager.loadMap("/maps/dungeonMap.txt", 1); break;
//			}
//			if(!gp.map.minimapON) gp.map.minimapON = true;
//			else if(gp.map.minimapON) gp.map.minimapON = false;
			break;
		}
	}
	
	public void pauseStateKeys(int code) {
		switch(code) {
		case KeyEvent.VK_P:
			gp.gameState = gp.playState;	
			break;
		}
	}
	
	public void dialogueStateKeys(int code) {
		switch(code) {
		case KeyEvent.VK_T:
			talkOn = false;
//			gp.gameState = gp.playState;
//			System.out.println(enterPressed);
			
			break;
		case KeyEvent.VK_ENTER:
			enterPressed = true;
			//gp.gameState = gp.playState;
			break;
		case KeyEvent.VK_ESCAPE:
			if(gp.gameState != gp.cutSceneState)
				gp.gameState = gp.playState;
			break;
		case KeyEvent.VK_Y:
			if(gp.eventHandler.dialogue_type == gp.eventHandler.dt_save) {
				gp.saverLoader.saveData();
				gp.gameState = gp.playState;
				gp.gui.addMessage("The game was successfully saved!");
				gp.eventHandler.dialogue_type = 0;
			}
			break;
		}
	}
	
	public void viewCharState(int code) {
		switch(code) {			 
		case KeyEvent.VK_C: gp.gameState = gp.playState; break;
		case KeyEvent.VK_ENTER:
			gp.player.selectItem();
			break;
		}
		inventoryKeys(code);
	}
	
	public void tradingStateKeys(int code) {
		int maxSelectNum = 0;
		if(gp.gui.substate == 0) maxSelectNum = 2;
		else if(gp.gui.substate == 1) maxSelectNum = 1;
		else if(gp.gui.substate == 3) maxSelectNum = 1;
		
		switch(code) {
		case KeyEvent.VK_ESCAPE: gp.gameState = gp.playState; gp.gui.substate = 0; talkOn = false; break;
		case KeyEvent.VK_ENTER:
			if(gp.gui.substate == 1) {
				gp.npc[2][0].sellItem();
			}
			else if(gp.gui.substate == 2) {
				gp.npc[2][0].buyItem();
			} 
			enterPressed = true;	
			break;
		case KeyEvent.VK_W:
			gp.gui.selectItem--;
			gp.playSE(5);
			if(gp.gui.selectItem < 0) gp.gui.selectItem = maxSelectNum;
			break;
		case KeyEvent.VK_S:
			gp.gui.selectItem++;
			gp.playSE(5);
			if(gp.gui.selectItem > maxSelectNum) gp.gui.selectItem = 0;
			break;
		}
		inventoryKeys(code);
	}
	
	public void inventoryKeys(int code) {
		switch(code) {
		case KeyEvent.VK_W:
			gp.gui.slotRow--;
			if(gp.gui.slotRow < 0 ) {
				gp.gui.slotRow = 3;
			}
			break;
		case KeyEvent.VK_A:
			gp.gui.slotCol--;
			if(gp.gui.slotCol < 0 ) {
				gp.gui.slotCol = 6;
			}
			break;
		case KeyEvent.VK_S:
			gp.gui.slotRow++;
			if(gp.gui.slotRow > 3 ) {
				gp.gui.slotRow = 0;
			}
			break;
		case KeyEvent.VK_D:
			gp.gui.slotCol++;
			if(gp.gui.slotCol > 6 ) {
				gp.gui.slotCol = 0;
			}
			break;
		}
	}
	
	

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void optionsStateKeys(int code) {
		int maxSelectNum = 0;
		if(gp.gui.substate == 0) maxSelectNum = 5;
		if(gp.gui.substate == 1) maxSelectNum = 1;
		if(gp.gui.substate == 3) maxSelectNum = 1;
		
		
		switch(code) {
		case KeyEvent.VK_ESCAPE: gp.gameState = gp.playState; break;
		case KeyEvent.VK_ENTER: enterPressed = true; gp.playSE(5); break;
		case KeyEvent.VK_W:
			gp.gui.selectItem--;
			gp.playSE(5);
			if(gp.gui.selectItem < 0) gp.gui.selectItem = maxSelectNum;
			break;
		case KeyEvent.VK_S:
			gp.gui.selectItem++;
			gp.playSE(5);
			if(gp.gui.selectItem > maxSelectNum) gp.gui.selectItem = 0;
			break;
		case KeyEvent.VK_A:
			if(gp.gui.substate == 0) {
				System.out.println(gp.soundM.volumeScale);
				if(gp.gui.selectItem == 1 && gp.soundM.volumeScale > 0) {gp.soundM.volumeScale--; gp.soundM.controlVolume(); gp.playSE(5);}
				if(gp.gui.selectItem == 2 && gp.soundSE.volumeScale > 0) {gp.soundSE.volumeScale--; gp.playSE(5);}
			}
			break;
		case KeyEvent.VK_D:
			if(gp.gui.substate == 0) {
				System.out.println(gp.soundM.volumeScale);
				if(gp.gui.selectItem == 1 && gp.soundM.volumeScale < 5) {gp.soundM.volumeScale++; gp.soundM.controlVolume(); gp.playSE(5);}
				if(gp.gui.selectItem == 2 && gp.soundSE.volumeScale < 5) {gp.soundSE.volumeScale++; gp.playSE(5);}
			}
			break;
			
			
			
			
		}
	}

	
}
