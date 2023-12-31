package interactive_tiles;


import entity.Entity;
import main.GamePanel;
import object.ITM_Coin;

public class IT_TrunkBroke extends InteractiveTiles{
	GamePanel gp;
	
	public IT_TrunkBroke(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		life = 3;
		reqItem = type_axe;
		down1 = createImage("interactive_tiles", "trunk");
		destroyOn = true;
		
		solidArea.width = 0;
		solidArea.height = 0;
		solidArea.x = 12;
		solidArea.y = 24;
		
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
	}
	public boolean checkReqItem(Entity item) {
		boolean _item = false;
			if(item.type == type_sword) {
				_item = true;
			}
		return _item;
	}
	public InteractiveTiles destroyedForm() {
		InteractiveTiles tile = null;
		dropItem(new ITM_Coin(gp));
		return tile;
	}
}
