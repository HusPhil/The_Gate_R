package interactive_tiles;

import entity.Entity;
import main.GamePanel;

public class IT_MetalPlate extends InteractiveTiles{
	GamePanel gp;
	public static final String IT_Name = "Metal Plate";
	public IT_MetalPlate(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		down1 = createImage("interactive_tiles", "metalplate");
		
		
		name = IT_Name;
		solidArea.width = 0;
		solidArea.height = 0;
		solidArea.x = 12;
		solidArea.y = 24;
		
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
	}
	@Override
	public boolean checkReqItem(Entity item) {
		// TODO Auto-generated method stub
		return false;
	}
}
