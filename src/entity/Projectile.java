package entity;

import main.GamePanel;

public class Projectile extends Entity{

	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
	}
	public void set(int worldX, int worldY, boolean alive, String direction, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.alive = alive;
		this.direction = direction;
		this.user = user;
		this.life = maxLife;
	}
	public void update() {
		setDamage();
		setAnimation();
		int monIndex = gp.collCheck.checkEntity(this, gp.monsters);
		life--;
		if(life <= 0) {
			alive = false;
		}
		
	}
	public void setDamage() {
		if(user == gp.player) {
			int monIndex = gp.collCheck.checkEntity(this, gp.monsters);
			if(monIndex != 777) {
				gp.playSE(1);
				generateParticle(user.projectile, gp.monsters[monIndex]);
				gp.player.manageMonDmg(monIndex, atk);
				alive = false; 
			}
		}
		if(user != gp.player) {
			boolean playerIntersect = gp.collCheck.playerIntersect(this);
			if(playerIntersect && !gp.player.invincible) {
				gp.playSE(1);
				generateParticle(user.projectile, gp.player);
				damagePlayer(atk);
				alive = false;
 			}
		}
	}
	public void setAnimation() {
		switch(direction) {
		case "up": {worldY-=speed; break;}
		case "down":{ worldY+=speed; break;}
		case "left": {worldX-=speed; break;}
		case "right": {worldX+=speed; break;}
		}
		spriteCounter++;
		if (spriteCounter > 12) {
			if(spriteNum == 1) {spriteNum = 2;}
			else if(spriteNum == 2) {spriteNum = 3;}
			else if(spriteNum == 3) {spriteNum = 4;}
			else if(spriteNum == 4) {spriteNum = 1;} 
			spriteCounter = 0;
		}
	}
	public boolean sufficientResource(Entity user) {
		if(user.mana >= manaCost) {
			return true;
		} 
		return false;
	}
	public void useResource(Entity user) {
		user.mana -= manaCost;
		
	}
}
