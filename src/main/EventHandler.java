package main;

public class EventHandler {
	
	GamePanel gp;
	EventRectangle eventRect[][];
	public boolean telOn = false;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRectangle[gp.maxWorldCol][gp.maxWorldRow];	
		int row = 0; int col = 0;
		//LOOP THE 2D ARRAY OF EVENT RECT
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[col][row]= new EventRectangle();
			eventRect[col][row].x = 23; 
			eventRect[col][row].y = 23;
			eventRect[col][row].defaultX = eventRect[col][row].x;
			eventRect[col][row].defaultY = eventRect[col][row].y;
			eventRect[col][row].width = 5;
			eventRect[col][row].height = 5;
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	public void checkEvent() {
		boolean event0 = eventCollision(58, 44, "any");
		if(event0) {
			damagePit();
			event0 = false; 
		}
		boolean event1 = eventCollision(55, 44, "any");
		if(event1) {
			teleport(59,50);
			event1 = true;
		}
	}
	public boolean eventCollision(int eventCol, int eventRow, String reqDirection) {
		boolean collide = false;
		
		//GET PLAYER'S SOLID AREA
		gp.player.solidArea.x += gp.player.worldX;
		gp.player.solidArea.y += gp.player.worldY;
		
		//GET EVENT'S SOLID AREA
		eventRect[eventCol][eventRow].x += eventCol*gp.tileSize;
		eventRect[eventCol][eventRow].y += eventRow*gp.tileSize;
		 
		//System.out.println(eventRect[eventCol][eventRow].y/48  + " m  " + eventRect[eventCol][eventRow].x/48);
		 
		if(gp.player.solidArea.intersects(eventRect[eventCol][eventRow])) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				collide = true;
			}
		}
		
		//RESET PLAYER'S SOLID AREA
		gp.player.solidArea.x = gp.player.defaultSolidAreaX;
		gp.player.solidArea.y = gp.player.defaultSolidAreaY;
		
		//RESET EVENT'S SOLID AREA
		eventRect[eventCol][eventRow].x = eventRect[eventCol][eventRow].defaultX;
		eventRect[eventCol][eventRow].y = eventRect[eventCol][eventRow].defaultY;
		
		return collide;
		
	}
	
	public void damagePit() {
			gp.player.life--;
			if(gp.player.life <= 0) gp.player.life = 0;
			System.out.println("you hit it");
		
	}
	public void teleport(int x, int y) {
		gp.player.attackCanceled = true;
		System.out.println(gp.player.attackCanceled);
		if(gp.keys.enterPressed) {
			gp.player.worldX = x*48;
			gp.player.worldY = y*48; 
			gp.createAssets.makeMonster();
			gp.createAssets.makeItems();
		}
		
		
	}
	
}
