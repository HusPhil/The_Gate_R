package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, dashPressed, enterPressed, talkOn, yesOn, fireAway;
	public int pressTime = 0;
	
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
		else if(gp.gameState == gp.dialougeState) {
			dialogueStateKeys(code);
		}
		
		//KEYS DURING VIEW CHAR STATE
		else if (gp.gameState == gp.viewCharState) {
			viewCharState(code);
		}
		
		//KEYS DURING PLAYSTATE
		else if(gp.gameState == gp.playState) {
			playStateKeys(code);
		}
		
		//KEYS DURING OPTIONS
		else if(gp.gameState == gp.optionsState) {
			optionsStateKeys(code);
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
			if(gp.gui.selectItem == 0) {gp.gameState = gp.playState;}
			else if(gp.gui.selectItem == 1) {}
			else if(gp.gui.selectItem == 2) System.exit(0);
			break;
		}
	}
	
	public void playStateKeys(int code) {
		int x = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
		int y = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
		
		switch (code) {
		case KeyEvent.VK_W: upPressed = true; break;
		case KeyEvent.VK_S: downPressed = true; break;
		case KeyEvent.VK_A: leftPressed = true; break;
		case KeyEvent.VK_D: rightPressed = true; break;
		case KeyEvent.VK_SPACE:
			if(gp.player.life <= 0) gp.player.life = gp.player.maxLife;
			pressTime+=5;
			dashPressed = true;
			break;
		case KeyEvent.VK_M: System.out.println("X:" + x + " Y: " + y); break;
		case KeyEvent.VK_P: 
			gp.gameState = gp.pauseState; 
			switch(gp.currentMap) {
			case 0: gp.tManager.loadMap("/maps/WorldMap.txt", 0); break;
			case 1: gp.tManager.loadMap("/maps/Cave.txt", 1); break;
			}
			
			
			break;
		case KeyEvent.VK_T: talkOn = true; break;
		case KeyEvent.VK_ENTER: enterPressed = true; break;
		case KeyEvent.VK_Y: yesOn = true; break;
		case KeyEvent.VK_C: gp.gameState = gp.viewCharState; break;
		case KeyEvent.VK_F: fireAway = true; break;
		case KeyEvent.VK_ESCAPE: gp.gameState = gp.optionsState; break;
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
			gp.gameState = gp.playState;
			System.out.println(enterPressed);
			break;
		case KeyEvent.VK_ENTER:
			gp.gameState = gp.playState;
			break;
		case KeyEvent.VK_Y:
			yesOn = true;
			break;
		}
	}
	
	public void viewCharState(int code) {
		switch(code) {			 
		case KeyEvent.VK_C: gp.gameState = gp.playState; break;
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
		case KeyEvent.VK_ENTER:
			gp.player.selectItem();
			break;
			
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
			yesOn = false;
			break;
		case KeyEvent.VK_F:
			 fireAway = false;
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
