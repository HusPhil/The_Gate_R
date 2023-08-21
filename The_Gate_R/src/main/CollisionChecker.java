package main;

import entity.Entity;

public class CollisionChecker{
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity ent) {
		int leftX = ent.worldX + ent.solidArea.x;
		int rightX = ent.worldX + ent.solidArea.x + ent.solidArea.width;
		int topY = ent.worldY + ent.solidArea.y;
		int bottomY = ent.worldY + ent.solidArea.y + ent.solidArea.height;
		
		int leftTilePos = leftX/gp.tileSize;
		int rightTilePos = rightX/gp.tileSize;
		int topTilePos = topY/gp.tileSize;
		int bottomTilePos = bottomY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(ent.direction) {
		case "up":
			topTilePos = (topY - ent.speed)/gp.tileSize;
			tileNum1 = gp.tManager.mapTileNum[gp.currentMap][leftTilePos][topTilePos];
			tileNum2 = gp.tManager.mapTileNum[gp.currentMap][rightTilePos][topTilePos];
			if(gp.tManager.tile[tileNum1].collisionOn || gp.tManager.tile[tileNum2].collisionOn )
				ent.collisionOn = true;
			break;
		case "down":
			bottomTilePos = (bottomY + ent.speed)/gp.tileSize;
			tileNum1 = gp.tManager.mapTileNum[gp.currentMap][leftTilePos][bottomTilePos];
			tileNum2 = gp.tManager.mapTileNum[gp.currentMap][rightTilePos][bottomTilePos];
			if(gp.tManager.tile[tileNum1].collisionOn || gp.tManager.tile[tileNum2].collisionOn )
				ent.collisionOn = true;
			break;
		case "left":
			leftTilePos = (leftX - ent.speed)/gp.tileSize;
			tileNum1 = gp.tManager.mapTileNum[gp.currentMap][leftTilePos][topTilePos];
			tileNum2 = gp.tManager.mapTileNum[gp.currentMap][leftTilePos][bottomTilePos];
			if(gp.tManager.tile[tileNum1].collisionOn || gp.tManager.tile[tileNum2].collisionOn )
				ent.collisionOn = true;
			break;
		case "right":
			rightTilePos = (rightX + ent.speed)/gp.tileSize;
			tileNum1 = gp.tManager.mapTileNum[gp.currentMap][rightTilePos][topTilePos];
			tileNum2 = gp.tManager.mapTileNum[gp.currentMap][rightTilePos][bottomTilePos];
			if(gp.tManager.tile[tileNum1].collisionOn || gp.tManager.tile[tileNum2].collisionOn )
				ent.collisionOn = true;
			break;
		}
		
	}
	
	public int checkObj(Entity ent, boolean player) {
		int index = 777;
		
			for (int i = 0; i < gp.gameObjs.length; i++) {
				if(gp.gameObjs[i] != null) {
					
					ent.solidArea.x += ent.worldX;
					ent.solidArea.y += ent.worldY;
					
					gp.gameObjs[i].solidArea.x += gp.gameObjs[i].worldX;
					gp.gameObjs[i].solidArea.y += gp.gameObjs[i].worldY;
					
					switch(ent.direction) {
					case "up": ent.solidArea.y -= ent.speed; break;
					case "down": ent.solidArea.y += ent.speed; break;
					case "left": ent.solidArea.x -= ent.speed; break;
					case "right": ent.solidArea.x += ent.speed; break;
					}
					
					if(ent.solidArea.intersects(gp.gameObjs[i].solidArea)) {
						if(gp.gameObjs[i].collision) ent.collisionOn = true;
						if(player) index = i;
					}
					
					ent.solidArea.x = ent.defaultSolidAreaX;
					ent.solidArea.y = ent.defaultSolidAreaY;
					
					gp.gameObjs[i].solidArea.x = gp.gameObjs[i].defaultSolidAreaX;
					gp.gameObjs[i].solidArea.y = gp.gameObjs[i].defaultSolidAreaY;
				}
			}
		return index;
	}
	public int checkItem(Entity ent, boolean player) {
		int index = 777;
		
			for (int i = 0; i < gp.items.length; i++) {
				if(gp.items[i] != null) {
					
					ent.solidArea.x += ent.worldX;
					ent.solidArea.y += ent.worldY;
					
					gp.items[i].solidArea.x += gp.items[i].worldX;
					gp.items[i].solidArea.y += gp.items[i].worldY;
					
					switch(ent.direction) {
					case "up": ent.solidArea.y -= ent.speed; break;
					case "down": ent.solidArea.y += ent.speed; break;
					case "left": ent.solidArea.x -= ent.speed; break;
					case "right": ent.solidArea.x += ent.speed; break;
					}
					
					if(ent.solidArea.intersects(gp.items[i].solidArea)) {
						if(gp.items[i].collision) ent.collisionOn = true;
						if(player) index = i;
					}
					
					ent.solidArea.x = ent.defaultSolidAreaX;
					ent.solidArea.y = ent.defaultSolidAreaY;
					
					gp.items[i].solidArea.x = gp.items[i].defaultSolidAreaX;
					gp.items[i].solidArea.y = gp.items[i].defaultSolidAreaY;
				}
			}
		return index;
	}
	
	public int checkEntity(Entity ent, Entity[] entType) {
		int index = 777;
		
		for (int i = 0; i < entType.length; i++) {
			if(entType[i] != null) {
				
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;
				
				entType[i].solidArea.x += entType[i].worldX;
				entType[i].solidArea.y += entType[i].worldY;
				
				switch(ent.direction) {
				case "up": ent.solidArea.y -= ent.speed; break;
				case "down": ent.solidArea.y += ent.speed; break;
				case "left": ent.solidArea.x -= ent.speed; break;
				case "right": ent.solidArea.x += ent.speed; break;
				}
				
				if(ent.solidArea.intersects(entType[i].solidArea)) {
					if (entType[i] != ent) {
						ent.collisionOn = true;
						index = i;
					}
				}
				
				ent.solidArea.x = ent.defaultSolidAreaX;
				ent.solidArea.y = ent.defaultSolidAreaY;
				
				entType[i].solidArea.x = entType[i].defaultSolidAreaX;
				entType[i].solidArea.y = entType[i].defaultSolidAreaY;
			}
		}
	return index;
	}
	
	public boolean playerIntersect(Entity ent) {
		boolean result = false;
		
		ent.solidArea.x += ent.worldX;
		ent.solidArea.y += ent.worldY;
		
		gp.player.solidArea.x += gp.player.worldX;
		gp.player.solidArea.y += gp.player.worldY;
		
		switch(ent.direction) {
		case "up": ent.solidArea.y -= ent.speed; break;
		case "down": ent.solidArea.y += ent.speed; break;
		case "left": ent.solidArea.x -= ent.speed; break;
		case "right": ent.solidArea.x += ent.speed; break;
		
		}
		
		if(ent.solidArea.intersects(gp.player.solidArea)) {
			ent.collisionOn = true;
			result = true;
		}
		
		ent.solidArea.x = ent.defaultSolidAreaX;
		ent.solidArea.y = ent.defaultSolidAreaY;
		
		gp.player.solidArea.x = gp.player.defaultSolidAreaX;
		gp.player.solidArea.y = gp.player.defaultSolidAreaY;
		
		return result;
	}
	
}