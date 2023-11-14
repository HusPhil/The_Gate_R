package entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import interactive_tiles.IT_MetalPlate;
import interactive_tiles.InteractiveTiles;
import main.GamePanel;
import object.OBJ_IronDoor;
import object.SKL_MudBall;

public class NPC_BigRock extends Entity{
	public static final String NPC_Name = "Big Rock";

	public NPC_BigRock(GamePanel gp) {
		super(gp);
		
		debugOn= true;
		
		type = type_npc;
		name = NPC_Name;
		direction = "down";
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(2, 6, 44, 40);
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		pathAI = true;
		
		projectile = new SKL_MudBall(gp);
		speed = 4;
		getNpcImage();
		setDialouge();
	}

	private void getNpcImage() {
		up1 = createImage("npc", "bigrock");
		up2 = createImage("npc", "bigrock");
		up3 = createImage("npc", "bigrock");
		up4 = createImage("npc", "bigrock");
		
		down1 = createImage("npc", "bigrock");
		down2 = createImage("npc", "bigrock");
		down3 = createImage("npc", "bigrock");
		down4 = createImage("npc", "bigrock");
		
		right1 = createImage("npc", "bigrock");
		right2 = createImage("npc", "bigrock");
		right3 = createImage("npc", "bigrock");
		right4 = createImage("npc", "bigrock");
		
		left1 = createImage("npc", "bigrock");
		left2 = createImage("npc", "bigrock");
		left3 = createImage("npc", "bigrock");
		left4 = createImage("npc", "bigrock");
	}
	
	public void setAction() {}
	public void move(String direction) {
		this.direction = direction;
		
		checkCollision();
		if(!collisionOn) {
			switch(direction){
				case "up": worldY-=speed; break;
				case "down": worldY+=speed; break;
				case "left": worldX-=speed; break;
				case "right": worldX+=speed; break;
			}
		}
		detectPlate();
	}
	public void detectPlate() {
		ArrayList<InteractiveTiles> metal_plates = new ArrayList<>();
		ArrayList<Entity> big_rocks = new ArrayList<>();
		
		int linkCount = 0;
		
		for(int i = 0; i < gp.IT_Manager[1].length; i++) {
			if(
				gp.IT_Manager[gp.currentMap][i] != null && 
				gp.IT_Manager[gp.currentMap][i].name.equals(IT_MetalPlate.IT_Name) &&
				gp.IT_Manager[gp.currentMap][i].name != null
			) {
				metal_plates.add(gp.IT_Manager[gp.currentMap][i]);
			} 
		}
		
		for(int i = 0; i < gp.npc[1].length; i++) {
			if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.NPC_Name))
				big_rocks.add(gp.npc[gp.currentMap][i]);
		}
		
		
		for(int i = 0; i < metal_plates.size(); i++) {
			int xDistance = Math.abs(worldX - metal_plates.get(i).worldX); 
			int yDistance = Math.abs(worldY - metal_plates.get(i).worldY); 
			int rDistance = Math.max(xDistance, yDistance);
			
			if(rDistance < 8) {
				if(linkedEntity == null) {
					linkedEntity = metal_plates.get(i);	
					gp.playSE(7);
				}
			}
			else if(linkedEntity == metal_plates.get(i)) {
				linkedEntity = null;
			}
		}
		
		for(int i = 0; i < big_rocks.size(); i++) {
			if(big_rocks.get(i).linkedEntity != null) {
				linkCount++;
			}
		}
		
		if(linkCount == big_rocks.size()) {
			for(int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[gp.currentMap][i] != null && gp.gameObjs[gp.currentMap][i].name.equals(OBJ_IronDoor.objName)) {
					gp.gameObjs[gp.currentMap][i] = null;
					gp.playSE(6);
				}
			}
		}	
		
		
	}

	public void update() {}
	public void setDialouge() {
		dialogues[0][0] = "What a huge rock! It's very heavy!";
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	


}
