package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_PlayerDummy extends Entity{


	public NPC_PlayerDummy(GamePanel gp) {
		super(gp);
		type = type_npc;
		direction = "up";
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(12, 18, 24, 30);
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
		speed = 0;
		getNpcImage();
	}
	private void getNpcImage() {
		up1 = createImage("player", "walking/up0");
		up2 = createImage("player", "walking/up1");
		up3 = createImage("player", "walking/up2");
		up4 = createImage("player", "walking/up3");
		
		down1 = createImage("player", "walking/down0");
		down2 = createImage("player", "walking/down1");
		down3 = createImage("player", "walking/down2");
		down4 = createImage("player", "walking/down3");
		
		right1 = createImage("player", "walking/right0");
		right2 = createImage("player", "walking/right1");
		right3 = createImage("player", "walking/right2");
		right4 = createImage("player", "walking/right3");
		
		left1 = createImage("player", "walking/left0");
		left2 = createImage("player", "walking/left1");
		left3 = createImage("player", "walking/left2");
		left4 = createImage("player", "walking/left3");
	}
	public void update() {}
}