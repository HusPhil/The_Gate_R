package interactive_tiles;


import entity.Entity;
import main.GamePanel;

public class IT_TempTree extends InteractiveTiles{
	GamePanel gp;
	
	public IT_TempTree(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		solidArea.x = 0;
		solidArea.y = 0;
		
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
		down1 = createImage("interactive_tiles", "temp_tree");
	}

	@Override
	public boolean checkReqItem(Entity item) {
		// TODO Auto-generated method stub
		return false;
	}
}











