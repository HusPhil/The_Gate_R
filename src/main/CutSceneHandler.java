package main;

import java.awt.Graphics2D;

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
		
		draw(g2);
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
	}
}
