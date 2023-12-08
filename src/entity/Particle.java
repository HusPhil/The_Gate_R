package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Particle extends Entity{

	Color color;
	Entity generator;	
	int size;
	int yd;
	int xd;
	
	public Particle(GamePanel gp, Color color, Entity generator, int size, int speed, int maxLife, int xd, int yd ) {
		super(gp);
		// TODO Auto-generated constructor stub
		
		this.color = color;
		this.size = size;
		this.yd = yd;
		this.xd = xd;
		this.speed = speed;
		this.maxLife = maxLife;
		
 		life = maxLife;
		worldX = generator.worldX;
		worldY = generator.worldY;
	}
	public void update() {
		life--;
		if(life == 0) {
			alive = false;
		}
		
		if(life < maxLife/3) yd++;
		
		worldX += xd*speed;
		worldY += yd*speed;
	}
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		g2.setColor(color);
		g2.fillRect(screenX+24, screenY+24, size, size);
	}
	
	
	
	
	
	
	
}
