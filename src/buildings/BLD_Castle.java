package buildings;


import java.awt.Graphics2D;
import entity.Entity;
import main.GamePanel;

public class BLD_Castle extends Entity{
	GamePanel gp;
	public BLD_Castle(GamePanel gp, int col, int row) {
	super(gp);
	this.gp = gp;
	
	this.worldX = gp.tileSize*col;
	this.worldY = gp.tileSize*row;
	solidArea.width = 128*gp.scale;
	solidArea.height = 144*gp.scale;
	solidArea.x = 0;
	solidArea.y = 0;
	
	defaultSolidAreaX = solidArea.x;
	defaultSolidAreaY = solidArea.y;
	
	
	down1 = createImage("objects", "castle", 128*gp.scale,144*gp.scale);
	}
	public void update() {
	}
	public void draw(Graphics2D g2) {
		image = down1;
		
		// TODO Auto-generated method stub
		int screenX = worldX - gp.player.worldX + gp.player.screenX; 
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
			g2.drawImage(image, screenX, screenY, null);
		}
}
