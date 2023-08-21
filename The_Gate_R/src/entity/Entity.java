package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityClass;

public class Entity {
	
	GamePanel gp;
	Graphics2D g2;
	
	//Entity Images 
	public BufferedImage 
	up1, up2, up3, up4, 
	down1, down2, down3, down4,
	left1, left2, left3, left4, 
	right1, right2, right3, right4,
	upDash1, upDash2;
	public BufferedImage image, image1, image2;
	public BufferedImage attackUp1, attackUp2,attackDown1,attackDown2,
	attackLeft1, attackLeft2, attackRight1, attackRight2;
	
	//Entity's Solid Area
	public Rectangle solidArea = new Rectangle(0, 16,  48, 32);
	public Rectangle attackAreaX = new Rectangle(0, 0, 0, 0);
	public Rectangle attackAreaY = new Rectangle(0, 0, 0, 0);
	public int defaultSolidAreaX, defaultSolidAreaY;
	
	//Entity's dialogue
	String dialouges[] = new String[20];
	int dialougeIndex = 0;
	
	//Entity's animation
	public int spriteNum = 1;
	public int spriteCounter = 0;
	public int actionDelay = 0;
	public int invincibleTime = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;
	public int shotCounter = 0;
	public Random rN = new Random();
		
	//Entity State
	public boolean collisionOn = false;
	public boolean collision = false;
	public boolean attacking = false;
	public boolean invincible;
	public boolean alive = true;
	public boolean dying;
	public boolean hpBarOn;
		
	//Entity's Attributes
	public int maxLife, life;
	public int worldX;
	public int worldY; 
	public int speed;
	public int level;
	public int str;
	public int dex;
	public int atk;
	public int def;
	public int exp;
	public int nextLvlExp;
	public int coin;
	public int dropChance;
	public int reqItem;
	public Entity currentWeapon;
	public String description = "";
	public Entity currentShield;
	
	//Item Stats
	public int atkVal;
	public int defVal;
	public int plus;
	
	//Entity Status
	public String name;
	public String direction ="down"; 
	public int type;
	public int mana, maxMana, manaCost;
	
	//ENtity types
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_shield = 4;
	public final int type_axe = 5;
	public final int type_consumables = 6;
	public final int non_inventory = 777;
	public int slowcounter = 0;
	public Projectile projectile;
	
	
	public void update() {
		setAction();
		//projectileAction();
		collisionOn = false;
		gp.collCheck.checkTile(this);
		gp.collCheck.checkObj(this, true);
		gp.collCheck.checkEntity(this, gp.npc);
		gp.collCheck.checkEntity(this, gp.monsters);
		gp.collCheck.checkEntity(this, gp.IT_Manager);
		gp.collCheck.playerIntersect(this);
		
		if (gp.collCheck.playerIntersect(this) && this.type == type_monster) {
			damagePlayer(atk);
		}
		
		if(!collisionOn) {
			switch(direction) {
			case "up": {worldY-=speed; break;}
			case "down":{ worldY+=speed; break;}
			case "left": {worldX-=speed; break;}
			case "right": {worldX+=speed; break;}
			}
		}
			
			spriteCounter++;
		
		if (spriteCounter > 12) {
			if(spriteNum == 1) {spriteNum = 2;}
			else if(spriteNum == 2) {spriteNum = 3;}
			else if(spriteNum == 3) {spriteNum = 4;}
			else if(spriteNum == 4) {spriteNum = 1;} 
			spriteCounter = 0;
		}
		if(invincible) {
			invincibleTime++;
			if(invincibleTime == 40) {
				invincible = false;
				invincibleTime = 0; 					
			}
		}
		if(shotCounter < 30) {shotCounter++;}
	}
 	public Entity(GamePanel gp) {
		this.gp = gp;
		this.name = "";
	}
	public BufferedImage createImage(String type, String fileName) {
		UtilityClass Utils = new UtilityClass();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read((getClass().getResourceAsStream("/" + type + "/" + fileName + ".png")));
			image = Utils.scaleImage(gp.tileSize, gp.tileSize, image); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	public BufferedImage createImage(String type, String fileName, int width, int height) {
		UtilityClass Utils = new UtilityClass();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read((getClass().getResourceAsStream("/" + type + "/" + fileName + ".png")));
			image = Utils.scaleImage(width, height, image);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	public void playerIntersect(Entity ent) {
		
			if(ent != null) {
				
				gp.player.solidArea.x += gp.player.worldX;
				gp.player.solidArea.y += gp.player.worldY;
				
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;
				
				switch(gp.player.direction) {
				case "up":
					gp.player.solidArea.y -= gp.player.speed;
					if(gp.player.solidArea.intersects(ent.solidArea)) {
						gp.player.collisionOn = true;
						
					}
					break;
				case "down":
					gp.player.solidArea.y += gp.player.speed;
					if(gp.player.solidArea.intersects(ent.solidArea)) {
						gp.player.collisionOn = true;
						
					}
					break;
				case "left":
					gp.player.solidArea.x -= gp.player.speed;
					if(gp.player.solidArea.intersects(ent.solidArea)) {
						gp.player.collisionOn = true;
						
					}
					break;
				case "right":
					gp.player.solidArea.x += gp.player.speed;
					if(gp.player.solidArea.intersects(ent.solidArea)) {
						gp.player.collisionOn = true;
						
					}
					break;
				
				}
				gp.player.solidArea.x = gp.player.defaultSolidAreaX;
				gp.player.solidArea.y = gp.player.defaultSolidAreaY;
				
				ent.solidArea.x = ent.defaultSolidAreaX;
				ent.solidArea.y = ent.defaultSolidAreaY;
			
		}
	}
	public void damagePlayer(int atk) {
		if(!gp.player.invincible) {
			int dmg = atk - gp.player.def;
			if(dmg < 0) dmg = 0;
			gp.player.life-=dmg;
			gp.player.invincible = true;
	}
	}
	public void damageReaction() {}
	public void setAction() {}
	public void speak() {
		if(dialouges[dialougeIndex] != null) {
			gp.gui.cD = dialouges[dialougeIndex];
			dialougeIndex++;
		} else gp.gui.cD = dialouges[19];
		
		switch(gp.player.direction) {
		case "up": direction = "down"; break;
		case "down": direction = "up"; break;
		case "left": direction = "left"; break;
		case "right": direction = "right"; break;
		}
	}
	public void use(Entity ent) {}
	public int getScreenX() {
		int x = 0;
		x = worldX - gp.player.worldX + gp.player.screenX; 
		return x;
	}
	public int getScreenY() {
		int y = 0;
		y = worldY - gp.player.worldY + gp.player.screenY;
		return y;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		// TODO Auto-generated method stub
		int screenX = worldX - gp.player.worldX + gp.player.screenX; 
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			BufferedImage image = null;
			
			switch(direction) {
			case "up":
				
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				if(spriteNum == 4) {image = up4;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				if(spriteNum == 4) {image = down4;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
				break;
			}
			
			//MONSTERS HEALTH BAR
			if(type == type_monster && hpBarOn) {
				
				double oneScale = (double)gp.tileSize/maxLife;
				double hpBarVal = oneScale*life;
				
				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
				
				g2.setColor(new Color(255, 0,30));
				g2.fillRect(screenX, screenY-15,(int)hpBarVal, 10);
				
				hpBarCounter++;
				if(hpBarCounter == 1) {
				}
				if(hpBarCounter == 60*5) {
					hpBarOn = false;
					hpBarCounter = 0;
				}
				
			}
			
			if(invincible) {
				hpBarOn = true;
				hpBarCounter = 0;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			}
			
			if(dying) dyingAnim();
			
			g2.drawImage(image, screenX, screenY, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
	public void dyingAnim() {
		dyingCounter++;
		int i = 5;
		
		if(dyingCounter < i) changeOpacity(0f);
		if(dyingCounter > i && dyingCounter <= i*2) changeOpacity(1f); 
		if(dyingCounter > i*2 && dyingCounter <= i*3) changeOpacity(0f);
		if(dyingCounter > i*3 && dyingCounter <= i*4) changeOpacity(1f);
		if(dyingCounter > i*4 && dyingCounter <= i*5) changeOpacity(0f);
		if(dyingCounter > i*5 && dyingCounter <= i*6) changeOpacity(1f);
		if(dyingCounter > i*6 && dyingCounter <= i*7) changeOpacity(0f);
		if(dyingCounter > i*7 && dyingCounter <= i*8) changeOpacity(1f);
		if(dyingCounter > i*8) {
			alive = false;
		}
		
		
	}
	public void changeOpacity(float value) {
		Graphics2D g2 = this.g2;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, value));
	}
	public void checkDrop() {
		
	}
	public void dropItem(Entity item) {
		int j = new Random().nextInt(2);
		int k = new Random().nextInt(2);
		int m = new Random().nextInt(2);
		
		for(int i = 0; i < gp.items.length; i++) {
			if(gp.items[i] == null) {
				gp.items[i] = item;
				if (k == 0) gp.items[i].worldX = worldX + j*(gp.tileSize/2);
				if (k == 1) gp.items[i].worldX = worldX - j*(gp.tileSize/2);
				
				if (m == 0) gp.items[i].worldY = worldY + k*(gp.tileSize/2);
				if (m == 1) gp.items[i].worldY = worldY - k*(gp.tileSize/2);
				break;
			}
		}
	}
	public void projectileAction() {
		if(gp.keys.fireAway && !projectile.alive && 
				   shotCounter == 30 && projectile.sufficientResource(this)) {
					projectile.set(worldX, worldY, true, direction, this);
					gp.projectileList.add(projectile);
					projectile.useResource(this);
					shotCounter = 0;
				} 
	}
	public Color getParticleColor() {
		Color c = null;
		return c;
	}
	public int getParticleSize() {
		int size = 0;
		return size;
	}
	public int getParticleSpeed(){
		int pSpeed = 0;
		return pSpeed;
	}
	public int getParticleLife() {
		int maxLife = 0;
		return maxLife;
	}
	public void generateParticle(Entity user, Entity target) {
			System.out.println("hakdog");
			
			Color c = user.getParticleColor();
			int size = user.getParticleSize();
			int speed = user.getParticleSpeed();
			int maxLife = user.getParticleLife();

			Particle particleA = new Particle(gp, c, user, size, speed, maxLife, -1,  -1);
			Particle particleB = new Particle(gp, c, user, size, speed, maxLife, -1,  1);
			Particle particleC = new Particle(gp, c, user, size, speed, maxLife, 1,  -1);
			Particle particleD = new Particle(gp, c, user, size, speed, maxLife, 1,  1);
			
			gp.particleList.add(particleA);		
			gp.particleList.add(particleB);		
			gp.particleList.add(particleC);		
			gp.particleList.add(particleD);		
	}
	
	
	
	
}
