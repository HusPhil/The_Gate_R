package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Chest;
import object.OBJ_Heart;

public class GUI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_80B;
	public Entity npc;

	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	BufferedImage heart_blank, heart_half, heart_full;
	int counter = 0;
	
	public String currentDialogue = "";
	public int npcX, npcY;
	public int selectItem = 0;
	public int slotRow = 0;
	public int slotCol = 0;
	public int substate = 0;
	public int charIndex = 0;
	public String combinedText = "";
	
	public GUI(GamePanel gp) {
		this.gp = gp;
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		Entity parentObj = new OBJ_Heart(gp); 
		heart_blank = parentObj.image;
		heart_half = parentObj.image1; 
		heart_full = parentObj.image2;
		
	}
	public void characterScreen() {
		
		
		int frameX = gp.tileSize;
		int frameY = gp.tileSize-24;
		int frameW = (gp.screenWidth/2) - gp.tileSize*2;
		int frameH = gp.screenHeight - (gp.tileSize*6)-12;
		
		//WINDOW FOR STATS
		subWindow(frameX, frameY, frameW, frameH);
		
		//TEXT
		float fSize = 30;
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, fSize));
		
		int textX = frameX + 30 ;
		int textY = frameY + (gp.tileSize)-10;
		int lineHeight = 35;
		
		//LABEL
		g2.drawString("Level:", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Life:", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Speed:", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Strength:", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Dexterity:", textX, textY);
		textY += lineHeight;
		
		g2.drawString("Coin:", textX, textY);
		textY += lineHeight;
		
		g2.drawString("EXP:", textX, textY);
		textY += lineHeight;
		
		//VALUES	
		int tailX = (frameX + frameW) - 30;
		textY = frameY + (gp.tileSize)-12;
		lineHeight = 35;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = textAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = textAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.speed);
		textX = textAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.str);
		textX = textAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.dex);
		textX = textAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = textAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.exp + "/" + gp.player.nextLvlExp);
		textX = textAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		
		frameX = gp.screenWidth/2 -24;
		frameY = gp.tileSize-24;
		frameW = (gp.screenWidth/2) - 24;
		frameH = gp.screenHeight - (gp.tileSize*6)-12;
		
		//WINDOW FOR EQUIPMENTS
		subWindow(frameX, frameY, frameW, frameH);
		
		textX = frameX + 30 ;
		textY = frameY + (gp.tileSize)-12;
		lineHeight = 35;
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
		//DISPLAY EQUIPMENT IMAGES
		g2.drawString("CURRENT WEAPON", textX, textY);
		g2.drawImage(gp.player.currentWeapon.down1, textX, textY+12, null);
		textY+=gp.tileSize*2;
		
		g2.drawString("CURRENT SHIELD", textX, textY);
		g2.drawImage(gp.player.currentShield.down1, textX, textY+12, null);
		
		textY += gp.tileSize*2 +24 ;
		
		g2.drawString("Attack:", textX, textY);
		g2.drawString("Defense:", textX+gp.tileSize*3, textY);
		
		//DISPLAY EQUIPMENT Property
		tailX = (frameX + frameW) - 30;
		textY = frameY + (gp.tileSize);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		
		
		
		textY += gp.tileSize;
		g2.drawString("ATK+", (textX+gp.tileSize*2), textY);
		
		textY += gp.tileSize*2;
		g2.drawString("DEF+", (textX+gp.tileSize)+gp.tileSize, textY);
		
		textX = frameX + 30 ;
		textY = frameY + (gp.tileSize)*2;
		lineHeight = 35;
		
		value = String.valueOf(gp.player.currentWeapon.atkVal);
		textX = textAlignRight(value, tailX)-100;
		g2.drawString(value, textX, textY);
		
		textY+=gp.tileSize*2;
		value = String.valueOf(gp.player.currentShield.defVal);
		textX = textAlignRight(value, tailX)-100;
		g2.drawString(value, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
		
		textY+=gp.tileSize +12;
		value = String.valueOf(gp.player.getAtk());
		textX = gp.screenWidth-gp.tileSize*6;
		g2.drawString(value, textX, textY);
		
		value = String.valueOf(gp.player.getDef());
		textX += gp.tileSize*3 +24;//gp.screenWidth - (gp.tileSize*6)+24;
		g2.drawString(value, textX, textY);
		
	}
	public void showInventory() {
		//SET WINDOW FRAME
				int frameX = gp.tileSize;
				int frameY = gp.screenHeight - (gp.tileSize*5)-12;
				int frameW = gp.tileSize*8;
				int frameH = gp.tileSize*5;
				
				//WINDOW FOR ITEMS
				subWindow(frameX, frameY, frameW, frameH-12);
				
				final int slotStartX = frameX + 10;
				final int slotStartY = frameY + 10;
				
				int slotX = slotStartX;
				int slotY = slotStartY;
				int slotSize = gp.tileSize+5;
				
				//DRAW THE ITEMS IMAGEs
				for(int i = 0; i < gp.player.inventory.size(); i++) {
					
					//EQUIP CURSOR
					if(gp.player.inventory.get(i) == gp.player.currentWeapon || 
					   gp.player.inventory.get(i) == gp.player.currentShield ||
					   gp.player.inventory.get(i) == gp.player.currentLightItem) {
						g2.setColor(new Color(169, 239, 10));
						g2.setStroke(new BasicStroke(3));
						g2.drawRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
					}
					
					g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
					
					//DISPLAY AMOUNT OF STACKABLE ITEMS
					if(gp.player.inventory.get(i).ammount > 1) {
						//draw
						g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
						String amount = String.valueOf(gp.player.inventory.get(i).ammount);;
						int amountX = textAlignRight(amount, slotX);
						int amountY = slotY + gp.tileSize ;
						
						g2.setColor(Color.white);
						g2.drawString(amount, amountX + slotSize, amountY);
						
					}
					
					slotX+=slotSize;
					
					if(i == 6 || i == 13 || i == 20 || i== 27) {
						slotX = slotStartX;
						slotY += slotSize;
					}
				}
				
				
				 
				//CURSOR
				int cursorX = slotStartX + (slotSize*slotCol);
				int cursorY = slotStartY + (slotSize*slotRow);
				int cursorW = gp.tileSize;
				int cursorH = gp.tileSize;
				
				g2.setColor(Color.white);
				g2.setStroke(new BasicStroke(3));
				g2.drawRoundRect(cursorX, cursorY, cursorW, cursorH, 10, 10);
				
				//Descriptiion window
				int dFrameX = frameX + frameW +24;
				int dFrameY = frameY;
				int dFrameW = frameW-(gp.tileSize*2)-24;
				int dFrameH = frameH - (gp.tileSize)+24;
				
				
				//Description text
				int textY = dFrameY + gp.tileSize;
				int textX = dFrameX + 25;
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
				
				int itemIndex = getItemIndex();
				
				if(itemIndex < gp.player.inventory.size()) {
					subWindow(dFrameX, dFrameY, dFrameW, dFrameH+12);
					for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
						g2.drawString(line, textX, textY);
						textY+=30;
					}
				}
				
				
	}
	public int getItemIndex() {
		return slotCol + (slotRow*7);
	}
	public void showPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x+=gp.tileSize;
		}
		//RESET
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;			
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x+=gp.tileSize;
		}
	}
	public void showPlayerMana() {
		double oneScale = (double)gp.tileSize/gp.player.maxMana;
		double manaVal = oneScale*gp.player.mana;
		int maxRedHP = 6;
		
		int x  = gp.tileSize-24; int y = gp.tileSize*2;
		int manaBarW;
		
		//limit the expansion of mana bar width
		if((gp.player.maxLife/2)*gp.tileSize <= maxRedHP*gp.tileSize)
			manaBarW = (gp.player.maxLife/2)*gp.tileSize;
		else manaBarW = maxRedHP*gp.tileSize;
		
		g2.setColor(new Color(35, 35, 35));
		g2.fillRect(x-1, y-16, manaBarW, 20);
		
		
		int manaBarscale;
		manaBarscale = manaBarW/gp.tileSize;
		if(gp.player.mana > 0) {
			
		g2.setColor(Color.BLUE);
		g2.fillRect(x, y-15,(int)manaVal*manaBarscale-3, 18);
		
		}
		
		//Display players mana val
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F));
		g2.setColor(Color.white);
		String text = String.valueOf(gp.player.mana) + "/" + String.valueOf(gp.player.maxMana);
		int length;
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = (manaBarW/2 - length/2) + 22; y-=1;
		
		
		g2.drawString(text, x, y);
		
	}
	public void gameMenuScreen() {
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.drawImage(gp.player.down1, 0, 0, gp.screenWidth, gp.screenHeight, null);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
		String title = "2D Adventure Game";
		int x1 = screenCenterX(title);
		float y1 = gp.tileSize*2.5F;
		g2.setColor(new Color(255,255,255));
		g2.drawString(title, x1, y1);
		
		//Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		g2.setColor(Color.white);
		
		String menuItem = "NEW GAME";
		int x = screenCenterX(menuItem);
		int y = gp.screenHeight - gp.tileSize*4;
		g2.drawString(menuItem, x, y);
		if(selectItem == 0) g2.drawString(">", x-gp.tileSize, y);
		
		menuItem = "LOAD GAME";
		x = screenCenterX(menuItem);
		y+=gp.tileSize+12;
		g2.drawString(menuItem, x, y);
		if(selectItem == 1) g2.drawString(">", x-gp.tileSize, y);
		
		menuItem = "QUIT";
		x = screenCenterX(menuItem);
		y+=gp.tileSize+12;
		g2.drawString(menuItem, x, y);
		if(selectItem == 2) g2.drawString(">", x-gp.tileSize, y);
	}
	public void pauseScreen() {
		String text = "PAUSED";
		int x = screenCenterX(text);
		int y = screenCenterY();
		
		g2.drawString(text, x, y);
		
	}
	public void dialougeScreen() {
		int x = gp.tileSize*3;
		int y = gp.tileSize;
		
		int width = gp.screenWidth - (6*gp.tileSize);
		int height = gp.screenHeight - (gp.tileSize*10) ;
		
		subWindow(x, y, width, height);
		
		x += gp.tileSize; y += gp.tileSize;
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
		
		
		
		if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
			currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
			if(npc.name !=  OBJ_Chest.objName && npc.type != gp.player.type_player) {
				char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
				if(charIndex < characters.length) {
					String s = String.valueOf(characters[charIndex]);
					combinedText += s;
					currentDialogue = combinedText;
					charIndex++;
				}	
			}
			if(gp.keys.enterPressed && (gp.gameState == gp.dialogueState || gp.gameState == gp.cutSceneState)) {
				charIndex = 0; combinedText = "";
				npc.dialogueIndex++;
			} gp.keys.enterPressed = false;
		} else {
			
			npc.dialogueIndex = 0;

			if(npc.type == npc.type_merchant) {
				gp.gameState = gp.tradingState;
			}
			if(gp.gameState == gp.dialogueState) {
				gp.gameState = gp.playState;
			}
			if(gp.gameState == gp.cutSceneState) {
				gp.csHandler.scenePhase++;
			}
		}
		
		for(String line: currentDialogue.split("\n")) {			
			g2.drawString(line,x,y);
			y+=40;
		}
		
	}
	public String insertString(
	        String originalString,
	        String stringToBeInserted,
	        int index)
	    {
	  
	        // Create a new string
	        String newString = originalString.substring(0, index + 1)
	                           + stringToBeInserted
	                           + originalString.substring(index + 1);
	  
	        // return the modified String
	        return newString;
	    }
	public void addMessage(String text) {
		if(message.size() < 10) {
		message.add(text);
		messageCounter.add(0);
		}
	}
	public void drawMessage() {
	
		int messageX = gp.tileSize;
		int messageY = gp.screenHeight-gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		
		for(int i = 0; i < message.size(); i++) {
				if(message.get(i) != null) {
					g2.setColor(Color.yellow);
					g2.drawString(message.get(i), messageX, messageY);
					
					int counter = messageCounter.get(i)+1;
					messageCounter.set(i, counter);
					messageY -= gp.tileSize-12;
					
					if(messageCounter.get(i)  > 180) {
						message.remove(i);
						messageCounter.remove(i);
					}
				}
			}
	}
	public void subWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 30, 30);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x, y, width, height, 30,30);
		
	}
	public int screenCenterY() {
		int y;
		y =  gp.screenWidth/2;
		
		return y;
	}
	public void showOptions() {
		//Set color and font
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		//Set frame
		int frameW = gp.tileSize*12;
		int frameH = gp.screenHeight - (gp.tileSize);
		int frameX = (int)((gp.screenWidth/2) - gp.tileSize*6);
		int frameY = gp.tileSize-24;
		//draw window
		subWindow(frameX, frameY, frameW, frameH);
		
		switch(substate) {
		
			case 0: optionsP1(windowCenterX("OPTIONS", frameW)+frameX, frameY+gp.tileSize); break;
			case 1: optionsNotif(frameX, frameY);break;
			case 2: optionsControls(frameX, frameY); break;
			case 3: optionsTitleScreen(frameX, frameY); break;
		}
		gp.keys.enterPressed = false;
		//save the config
		gp.config.saveConfig();
	}
	public void showTransition() {
		counter++;
		//draw the a rectangle that covers the entire screen
		//its opacity is scaled by the counter which starts from 0 so it creates a fade iout effect
		g2.setColor(new Color(0,0,0,counter*5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		//when the counter hits a certain amoun, it stops and imlements the transition
		if(counter == 50) {
			
			gp.gameState = gp.playState;
			//teleport the player
			gp.currentMap = gp.eventHandler.tempMap;
			gp.player.worldX = gp.eventHandler.tempCol*gp.tileSize;
			gp.player.worldY = gp.eventHandler.tempRow*gp.tileSize;
			
			//save the prev event to make sure the player does not activate the transition effect immeiately after transitioning
			gp.eventHandler.prevEventX = gp.player.worldX;
			gp.eventHandler.prevEventY = gp.player.worldY;
			gp.changeArea();
			//ALWAYS RESET THE COUNTER
			counter = 0;
		}
	}
	public void showBossLife() {
		for(int i = 0; i < gp.monsters[1].length; i++) {
			Entity monster = gp.monsters[gp.currentMap][i];
			if(monster != null && monster.type_boss && monster.inCamera() && gp.bossBattleOn) {
				int length = gp.tileSize*15;
				
				double oneScale = (double)length/monster.maxLife;
				double hpBarVal = oneScale*monster.life;
				
				int x = (gp.screenWidth/2) - (length/2);
				int y = gp.tileSize*12;
				
				g2.setColor(new Color(124, 119, 119));
				g2.fillRect(x-1, y-16, (int)oneScale*monster.maxLife+2, 52);
				
				g2.setColor(new Color(124, 16, 14));
				g2.fillRect(x, y-15,(int)hpBarVal, 50);
				
				String text = "[ " +  monster.name.toUpperCase() + " ]"; 
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));
				g2.setColor(Color.WHITE);
				x = (gp.screenWidth/2) - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
				g2.drawString(text, x, y-28);
			}
		}
	}
	
	//TRADING STATE
	public void showTrade() {
		
		
		
		//CONTROLLING THE SUBSTATE
		switch(substate) {
		case 0: tradeOptions(); break;
		case 1: 
			trade_buy(gp.npc[2][0], gp.tileSize, gp.tileSize);
			showTradeInventoryWindow(gp.player, gp.screenWidth - (gp.tileSize*9), gp.tileSize, false);
			break;
		case 2: 
			trade_sell();
			
			break;
		case 3: break;
		}
		gp.keys.enterPressed = false;
		//TEXT FOR CHOICES
		
		
		
		

		
				
				
	
	}
	public void tradeOptions() {
		//DIALOGUE PART
		currentDialogue = "So, shall the trading begin? What do you want to do? \nI have some pretty powerful items, "
				+ "but they cost \nsome gold, kikiki..";
		int x = gp.tileSize*3;
		int y = gp.tileSize;
		
		int width = gp.screenWidth - (6*gp.tileSize);
		int height = gp.screenHeight - (gp.tileSize*10) ;
		
		subWindow(x, y, width, height);
		
		x += gp.tileSize; y += gp.tileSize;
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line,x,y);
			y+=40;
		}
		
		//RESPONSE WINDOW
		x = gp.screenWidth - (gp.tileSize*7);
		y = (gp.tileSize*5) + 24;
		width = (gp.tileSize*3) + 18;
		height = (gp.tileSize*4) - 12;
		
		subWindow(x, y, width, height);
				
		
		x = gp.screenWidth - (gp.tileSize*7);
		y = (gp.tileSize*5) + 24;
		
		x+=32+15; y+=gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		
		//BUY OPTION
		g2.drawString(" Buy", x, y);
		if(selectItem == 0) {
			g2.drawString(">", x-15, y);
			if(gp.keys.enterPressed) {
				substate = 1;
				selectItem = 0;
			};
		} y+=gp.tileSize;
		
		//SELL OPTION
		g2.drawString(" Sell", x, y);
		if(selectItem == 1) {
			g2.drawString(">", x-15, y);
			if(gp.keys.enterPressed) {
				substate = 2;
				selectItem = 0;
			};
		} y+=gp.tileSize;
	
		
		
		//LEAVE OPTION
		g2.drawString(" Leave", x, y); 
		if(selectItem == 2) {
			g2.drawString(">", x-15, y);
			if(gp.keys.enterPressed) {
				if(substate == 0) gp.gameState = gp.playState;
				selectItem = 0;
			};
		 } y+=gp.tileSize;
	}
	
	
	public void trade_buy(Entity ent, int x, int y) {
		
		
		//DRAW INVENTORY
		int frameX = x;
		int frameY = y;
		int frameW = gp.tileSize*8;
		int frameH = gp.tileSize*5;
		
		//WINDOW FOR ITEMS
		subWindow(frameX, frameY, frameW, frameH-12);
		
		final int slotStartX = frameX + 10;
		final int slotStartY = frameY + 10;
		
		int slotX = slotStartX;
		int slotY = slotStartY;
		int slotSize = gp.tileSize+5;
		
		//DRAW THE ITEMS IMAGEs
		for(int i = 0; i < ent.inventory.size(); i++) {
			
			//EQUIP CURSOR
			if(ent.inventory.get(i) == ent.currentWeapon || 
			   ent.inventory.get(i) == ent.currentShield ||
			   ent.inventory.get(i) == ent.currentLightItem) {
				g2.setColor(new Color(169, 239, 10));
				g2.setStroke(new BasicStroke(3));
				g2.drawRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(ent.inventory.get(i).down1, slotX, slotY, null);
			
			
			
			slotX+=slotSize;
			
			if(i == 6 || i == 13 || i == 20 || i== 27) {
				slotX = slotStartX;
				slotY += slotSize;
			}
		}
		
		
		 
		//CURSOR
		int cursorX = slotStartX + (slotSize*slotCol);
		int cursorY = slotStartY + (slotSize*slotRow);
		int cursorW = gp.tileSize;
		int cursorH = gp.tileSize;
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorW, cursorH, 10, 10);
		
		//last item in inventory
		int lCursorX = slotStartX + (slotSize*6);
		int lCursorY = slotStartY + (slotSize*3);
		
		if(cursorX == lCursorX && cursorY == lCursorY &&
		   gp.keys.enterPressed && substate == 1) {
			substate = 0; selectItem = 0;
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12F));
		g2.setColor(new Color(255,0,0));
	
		g2.drawString("BACK", lCursorX+8, lCursorY+30);

		//Descriptiion window
		int dFrameX = frameX;
		int dFrameY = gp.tileSize*8;
		int dFrameW = frameW-(gp.tileSize*2)-24;
		int dFrameH = frameH - (gp.tileSize)+24;
		
		
		//Description text
		int textY = dFrameY + gp.tileSize;
		int textX = dFrameX + 25;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		
		int itemIndex = getItemIndex();
		
		if(itemIndex < ent.inventory.size()) {
			subWindow(dFrameX, dFrameY, dFrameW, dFrameH+12);
			for(String line: ent.inventory.get(itemIndex).description.split("\n")){
				g2.drawString(line, textX, textY);
				textY+=30;
			}
		}
		
		//draw price
		String price = "PRICE: ";
		int priceX = gp.tileSize;
		int priceY = frameH + gp.tileSize;  
		int priceW = gp.tileSize*8;
		int priceH = gp.tileSize;
		textX = (priceX+priceW) - gp.tileSize;
		
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		
		if(itemIndex < ent.inventory.size()) {
			subWindow(priceX, priceY, priceW, priceH);
			price = String.valueOf(ent.inventory.get(itemIndex).coin);
			textX = (priceX+priceW) - (gp.tileSize/4 + (int)g2.getFontMetrics().getStringBounds(price, g2).getWidth());
				g2.drawString(price, textX, priceY+32);
				g2.drawString("PRICE: ", priceX+12, priceY+32);
		}
	}
	public void trade_sell() {
		showTradeInventoryWindow(gp.player, gp.screenWidth - (gp.tileSize*9), gp.tileSize, true);
		String price = "PRICE: ";
		int priceX = gp.screenWidth - gp.tileSize*9;
		int priceY = (gp.tileSize*7)+12;  
		int priceW = gp.tileSize*8;
		int priceH = gp.tileSize;
		int textX = (priceX+priceW) - gp.tileSize;
		int itemIndex = getItemIndex();
		
		
		
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		
		if(itemIndex < gp.player.inventory.size()) {
			int sellPrice = (gp.player.inventory.get(itemIndex).coin) - (gp.player.inventory.get(itemIndex).coin)/4;
			subWindow(priceX, priceY, priceW, priceH);
			price = String.valueOf(sellPrice);
			textX = (priceX+priceW) - (gp.tileSize/4 + (int)g2.getFontMetrics().getStringBounds(price, g2).getWidth());
				g2.drawString(price, textX, priceY+32);
				g2.drawString("SELL PRICE: ", priceX+12, priceY+32);
		}
		
		final int slotStartX = gp.screenWidth - (gp.tileSize*9) + 10;
		final int slotStartY = gp.tileSize + 10;
		int slotSize = gp.tileSize+5;
		
		int cursorX = slotStartX + (slotSize*slotCol);
		int cursorY = slotStartY + (slotSize*slotRow);
		
		int lCursorX = slotStartX + (slotSize*6);
		int lCursorY = slotStartY + (slotSize*3);
		
		if(cursorX == lCursorX && cursorY == lCursorY &&
		   gp.keys.enterPressed && substate == 1) {
			selectItem = 0; substate = 0; 
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12F));
		g2.setColor(new Color(255,0,0));
	
		g2.drawString("BACK", lCursorX+8, lCursorY+30);
		
	}
	public void showTradeInventoryWindow(Entity ent, int x, int y, boolean cursorON) {
		//DRAW INVENTORY
				int frameX = x;
				int frameY = y;
				int frameW = gp.tileSize*8;
				int frameH = gp.tileSize*5;
				
				//WINDOW FOR ITEMS
				subWindow(frameX, frameY, frameW, frameH-12);
				
				final int slotStartX = frameX + 10;
				final int slotStartY = frameY + 10;
				
				int slotX = slotStartX;
				int slotY = slotStartY;
				int slotSize = gp.tileSize+5;
				
				//DRAW THE ITEMS IMAGEs
				for(int i = 0; i < ent.inventory.size(); i++) {
					
					//EQUIP CURSOR
					if(ent.inventory.get(i) == ent.currentWeapon || 
					   ent.inventory.get(i) == ent.currentShield ||
					   ent.inventory.get(i) == ent.currentLightItem) {
						g2.setColor(new Color(169, 239, 10));
						g2.setStroke(new BasicStroke(3));
						g2.drawRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
					}
					
					g2.drawImage(ent.inventory.get(i).down1, slotX, slotY, null);
					
						//DISPLAY AMOUNT OF STACKABLE ITEMS
						if(gp.player.inventory.get(i).ammount > 1) {
							//draw
							g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
							String amount = String.valueOf(gp.player.inventory.get(i).ammount);;
							int amountX = textAlignRight(amount, slotX);
							int amountY = slotY + gp.tileSize ;
							
							g2.setColor(Color.white);
							g2.drawString(amount, amountX + slotSize, amountY);
							
						}
					
					slotX+=slotSize;
					
					if(i == 6 || i == 13 || i == 20 || i== 27) {
						slotX = slotStartX;
						slotY += slotSize;
					}
				}
			if(cursorON) {
				int cursorX = slotStartX + (slotSize*slotCol);
				int cursorY = slotStartY + (slotSize*slotRow);
				int cursorW = gp.tileSize;
				int cursorH = gp.tileSize;
				
				g2.setColor(Color.white);
				g2.setStroke(new BasicStroke(3));
				g2.drawRoundRect(cursorX, cursorY, cursorW, cursorH, 10, 10);
			}
			
			String price = "PRICE: ";
			int priceX = gp.screenWidth - gp.tileSize*9;
			int priceY = frameH + gp.tileSize;  
			int priceW = gp.tileSize*8;
			int priceH = gp.tileSize;
			int textX = (priceX+priceW) - gp.tileSize;
			
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
			
			
			subWindow(priceX, priceY, priceW, priceH);
			g2.setColor(Color.YELLOW);
			price = String.valueOf(ent.coin);
			textX = (priceX+priceW) - (gp.tileSize/4 + (int)g2.getFontMetrics().getStringBounds(price, g2).getWidth());
			
				g2.drawString(price, textX, priceY+32);
				g2.drawString("COIN: ", priceX+12, priceY+32);
			
	}
	
	//OPTIONS SUBSTATE
	public void optionsP1(int frameX, int frameY) {
		
		//DRAW THE TITLE
		String text = "OPTIONS";
		int frameH = gp.screenHeight - (gp.tileSize);
		int frameW = gp.tileSize*12;
		int lineheight = 50;
		g2.drawString(text, frameX, frameY);
		
		//DRAW THE LABELS
		
		
		//Full Screen
		frameY += gp.tileSize*2;
		frameX -= gp.tileSize*3;
		text = "Full Screen";
		g2.drawString(text, frameX, frameY);
		if(selectItem == 0) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) {
			if(!gp.fullScreen) {gp.fullScreen = true; substate = 1; selectItem = 0;}
			else if(gp.fullScreen) {gp.fullScreen = false; substate = 1; selectItem = 0;}
			}
		}
		
		//Volume
		text = "Volume";
		frameY += lineheight;
		g2.drawString(text, frameX, frameY);
		if(selectItem == 1) g2.drawString(">", frameX-35, frameY);
		
		//SoundEffects
		text = "Sound Effects";
		frameY += lineheight;
		g2.drawString(text, frameX, frameY);
		if(selectItem == 2) {
			g2.drawString(">", frameX-35, frameY);
			
		}
		
		//Controls
		text = "View Controls";
		frameY += lineheight;
		g2.drawString(text, frameX, frameY);
		if(selectItem == 3) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) {substate = 2; selectItem = 0;}
		}
		
		//Back to Menu
		text = "Title Screen";
		frameY += lineheight;
		g2.drawString(text, frameX, frameY);
		if(selectItem == 4) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) {substate = 3; selectItem = 0;}
		}
		
		
		text = "BACK";
		frameY = frameH-gp.tileSize;
		g2.drawString(text, frameX, frameY);
		if(selectItem == 5 ) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) {
				if(substate == 0) gp.gameState= gp.playState;
			};
		}
		
		frameY = (gp.tileSize-6) + gp.tileSize*2;
		frameX = (int)((gp.screenWidth/2) - gp.tileSize*6);
		frameX = windowRightX(frameX, frameW) - gp.tileSize*4;
		optionsP2(frameX, frameY);
	}
	public void optionsP2(int frameX, int frameY) {
		int lineheight = 50;
		
		//draw full screen check box
		if(gp.fullScreen) {
			g2.fillRect(frameX+4, frameY+4, 25, 25);
			g2.setStroke(new BasicStroke(1));
		}
		g2.drawRect(frameX, frameY, 32, 32);
		
		
		//draw rect music volume
		frameY+=lineheight;
		int volWidth = (125/5) * gp.soundM.volumeScale;
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(frameX, frameY, 125, 32);
		g2.fillRect(frameX, frameY, volWidth, 32);
		
		
		//draw rect sound effects volume
		frameY+=lineheight;
		volWidth = (125/5) * gp.soundSE.volumeScale;
		g2.drawRect(frameX, frameY, 125, 32);
		g2.fillRect(frameX, frameY, volWidth, 32);
	}

	
	public void optionsTitleScreen(int frameX, int frameY) {

		String text = "ARE YOU SURE";
		int frameH = gp.screenHeight - (gp.tileSize);
		int lineheight = 50;
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Are you sure you want to \ngo back to the title screen?";
		for(String line: currentDialogue.split("\n")){
			g2.drawString(line, textX, textY);
			textY += lineheight+10;
		}
			
		text = "BACK";
		frameY = frameH-gp.tileSize;
		frameX = gp.tileSize + (frameX+35);
		g2.drawString(text, frameX, frameY);
		if(selectItem == 0) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) {substate = 0; selectItem = 4;}
		}
		frameY -= lineheight;
		text = "YES";
		g2.drawString(text, frameX, frameY);
		if(selectItem == 1) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) {
				selectItem = 0; 
				gp.resetStatus(true);
				gp.gameState = gp.gameMenu; 
				substate = 0;}
		}
	}
	public void optionsControls(int frameX, int frameY) {
		String text = "CONTROLS";
		int frameH = gp.screenHeight - (gp.tileSize);
		int frameW = gp.tileSize*12;
		int lineheight = 50;
		int titleX = (int)((gp.screenWidth/2) - gp.tileSize*6.3);
		int titleY = gp.tileSize-24;
		
		g2.drawString(text, windowCenterX("OPTIONS", frameW)+titleX, titleY+gp.tileSize );
		
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		text = "Move";
		g2.drawString(text, textX, textY);
		text = "W/A/S/D";
		g2.drawString(text, windowRightX(textX, frameW) - gp.tileSize*3, textY);
		textY += lineheight;
		
		
		text = "Attack/Confirm";
		g2.drawString(text, textX, textY);
		text = "Enter";
		g2.drawString(text, windowRightX(textX, frameW) - gp.tileSize*3, textY);
		textY += lineheight;
		
		text = "Use Skill";
		g2.drawString(text, textX, textY);
		text = "F";
		g2.drawString(text, windowRightX(textX, frameW) - gp.tileSize*3, textY);
		textY += lineheight;
		
		text = "Character Screen";
		g2.drawString(text, textX, textY);
		text = "C";
		g2.drawString(text, windowRightX(textX, frameW) - gp.tileSize*3, textY);
		textY += lineheight;
		
		text = "Options";
		g2.drawString(text, textX, textY);
		text = "ESC";
		g2.drawString(text, windowRightX(textX, frameW) - gp.tileSize*3, textY);
		textY += lineheight;
		
		text = "BACK";
		frameY = frameH-gp.tileSize;
		frameX = gp.tileSize + (frameX+35);
		g2.drawString(text, frameX, frameY);
		if(selectItem == 0) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) {substate = 0; selectItem = 3;}
		}
	}
	public void optionsNotif(int frameX, int frameY) {
		String text = "OPTIONS";
		int frameH = gp.screenHeight - (gp.tileSize);
		int lineheight = 50;
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "In order to perform this action \nthe game needs to be restarted. \n"
				+ "Please restart the game if you \nwish to play in this mode.";
		for(String line: currentDialogue.split("\n")){
			g2.drawString(line, textX, textY);
			textY += lineheight+10;
		}
		
		
		text = "BACK";
		frameY = frameH-gp.tileSize;
		frameX = gp.tileSize + (frameX+35);
		g2.drawString(text, frameX, frameY);
		if(selectItem == 0) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) substate = 0;
		}
		
		text = "SAVE & EXIT";
		frameY -= gp.tileSize;
		g2.drawString(text, frameX, frameY);
		if(selectItem == 1) {
			g2.drawString(">", frameX-35, frameY);
			if(gp.keys.enterPressed) System.exit(0);
		}
	}
	
	//TOOLS
	public int screenCenterX(String text) {
		int x;
		int length;
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		return x;
	}
	public int textAlignRight(String text, int tailX){ 
		int x;
		int length;
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = tailX - length;
		return x;
	}
	public int windowCenterX(String text, int width) {
		int x;
		int length;
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = width/2 - length/2;
		return x;
	}
	public int windowRightX(int frameX, int width) {
		int realWidth = gp.screenWidth - (frameX + width);
		return realWidth + width;
	}
	
	
	//GAME OVER SCREEN
	public void showGameOverScreen() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x, y;
		String text;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		
		text = "GAME OVER";
		x = screenCenterX(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		g2.drawString(text, x-2, y-2);
		
		int timer =  3 - (gp.keys.delayCounter/60);
		//RETRY OPTION
		text = "RETRY";
		g2.setFont(g2.getFont().deriveFont(50f));
		x = screenCenterX(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(selectItem == 0 && timer <= 0) g2.drawString(">", x-40, y);
		
		
		text = "QUIT";
		x = screenCenterX(text);
		y += 55;
		g2.drawString(text, x, y);
		if(selectItem == 1 && timer <= 0) g2.drawString(">", x-40, y);
		
		text = String.valueOf(timer);
		g2.setFont(g2.getFont().deriveFont(35f));
		if (timer <= 0) {
			timer = 0;
			text = "Please select your option.";
		}
		x = screenCenterX(text);
		y += 55;
		g2.drawString(text, x, y);
		
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_80B);
		//g2.setColor(Color.BLACK);
		
		//DRAW GAME MENU
		if (gp.gameState == gp.gameMenu) {
			gameMenuScreen();
		}
		
		//DRAW PLAY STATE
		if(gp.gameState == gp.playState) {
			showPlayerLife();
			showBossLife();
			if(gp.player.magicOn) showPlayerMana();
			drawMessage();
		} 
		
		//DRAW PAUSE SCREEN
		if (gp.gameState == gp.pauseState) {
			pauseScreen();
		}
		
		//DRAW DIALOGUE SCREEN
		if (gp.gameState == gp.dialogueState) {
			dialougeScreen();
		}
		
		//DRAW VIEW CHAR SCREEN
		if(gp.gameState == gp.viewCharState) {
			characterScreen();
			showInventory();
		}
		
		//DRAW OPTIONS SCREEN
		if(gp.gameState == gp.optionsState) {
			showOptions();
		}
		//DRAW TRANSITION STATE
		if(gp.gameState == gp.transitionState) {
			showTransition();
		}
		
		//DRAW TRADING STATE
		if(gp.gameState == gp.tradingState) {
			showTrade();
		}
		
		//DRAW GAMEOVERSTATE
		if(gp.gameState == gp.gameOverState) {
			showGameOverScreen();
		}
	}
	
}
