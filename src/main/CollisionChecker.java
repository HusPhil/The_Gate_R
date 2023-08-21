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
		
		String currentDirection = ent.direction;
		if(ent.knockBackState) currentDirection = ent.knockDirection;
		
		switch(currentDirection) {
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
		String currentDirection = ent.direction;
		if(ent.knockBackState) currentDirection = ent.knockDirection;
		
			for (int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[gp.currentMap][i] != null) {
					
					ent.solidArea.x += ent.worldX;
					ent.solidArea.y += ent.worldY;
					
					gp.gameObjs[gp.currentMap][i].solidArea.x += gp.gameObjs[gp.currentMap][i].worldX;
					gp.gameObjs[gp.currentMap][i].solidArea.y += gp.gameObjs[gp.currentMap][i].worldY;
					
					switch(currentDirection) {
					case "up": ent.solidArea.y -= ent.speed; break;
					case "down": ent.solidArea.y += ent.speed; break;
					case "left": ent.solidArea.x -= ent.speed; break;
					case "right": ent.solidArea.x += ent.speed; break;
					}
					
					if(ent.solidArea.intersects(gp.gameObjs[gp.currentMap][i].solidArea)) {
						if(gp.gameObjs[gp.currentMap][i].collision) ent.collisionOn = true;
						if(player) index = i;
					}
					
					ent.solidArea.x = ent.defaultSolidAreaX;
					ent.solidArea.y = ent.defaultSolidAreaY;
					
					gp.gameObjs[gp.currentMap][i].solidArea.x = gp.gameObjs[gp.currentMap][i].defaultSolidAreaX;
					gp.gameObjs[gp.currentMap][i].solidArea.y = gp.gameObjs[gp.currentMap][i].defaultSolidAreaY;
				}
			}
		return index;
	}
	public int checkItem(Entity ent, boolean player) {
		int index = 777;
		String currentDirection = ent.direction;
		if(ent.knockBackState) currentDirection = ent.knockDirection;
		
			for (int i = 0; i < gp.gameObjs[1].length; i++) {
				if(gp.gameObjs[gp.currentMap][i] != null) {
					
					ent.solidArea.x += ent.worldX;
					ent.solidArea.y += ent.worldY;
					
					gp.gameObjs[gp.currentMap][i].solidArea.x += gp.gameObjs[gp.currentMap][i].worldX;
					gp.gameObjs[gp.currentMap][i].solidArea.y += gp.gameObjs[gp.currentMap][i].worldY;
					
					switch(currentDirection) {
					case "up": ent.solidArea.y -= ent.speed; break;
					case "down": ent.solidArea.y += ent.speed; break;
					case "left": ent.solidArea.x -= ent.speed; break;
					case "right": ent.solidArea.x += ent.speed; break;
					}
					
					if(ent.solidArea.intersects(gp.gameObjs[gp.currentMap][i].solidArea)) {
						if(gp.gameObjs[gp.currentMap][i].collision) ent.collisionOn = true;
						if(player) index = i;
					}
					
					ent.solidArea.x = ent.defaultSolidAreaX;
					ent.solidArea.y = ent.defaultSolidAreaY;
					
					gp.gameObjs[gp.currentMap][i].solidArea.x = gp.gameObjs[gp.currentMap][i].defaultSolidAreaX;
					gp.gameObjs[gp.currentMap][i].solidArea.y = gp.gameObjs[gp.currentMap][i].defaultSolidAreaY;
				}
			}
		return index;
	}
	public int checkEntity(Entity ent, Entity[][] entType) {
		int index = 777;
		String currentDirection = ent.direction;
		if(ent.knockBackState) currentDirection = ent.knockDirection;
		
		for (int i = 0; i < entType[1].length; i++) {
			if(entType[gp.currentMap][i] != null) {
				
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;
				
				entType[gp.currentMap][i].solidArea.x += entType[gp.currentMap][i].worldX;
				entType[gp.currentMap][i].solidArea.y += entType[gp.currentMap][i].worldY;
				
				switch(currentDirection) {
				case "up": ent.solidArea.y -= ent.speed; break;
				case "down": ent.solidArea.y += ent.speed; break;
				case "left": ent.solidArea.x -= ent.speed; break;
				case "right": ent.solidArea.x += ent.speed; break;
				}
				
				if(ent.solidArea.intersects(entType[gp.currentMap][i].solidArea)) {
					if (entType[gp.currentMap][i] != ent) {
						ent.collisionOn = true;
						index = i;
					}
				}
				
				ent.solidArea.x = ent.defaultSolidAreaX;
				ent.solidArea.y = ent.defaultSolidAreaY;
				
				entType[gp.currentMap][i].solidArea.x = entType[gp.currentMap][i].defaultSolidAreaX;
				entType[gp.currentMap][i].solidArea.y = entType[gp.currentMap][i].defaultSolidAreaY;
			}
		}
	return index;
	}
	public int checkEntityCol(Entity ent, Entity[][] entType) {

		int index = 777;
		String currentDirection = ent.direction;
		if(ent.knockBackState) currentDirection = ent.knockDirection;
		
		for (int i = 0; i < entType[1].length; i++) {
			if(entType[gp.currentMap][i] != null) {
				
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;
				
				entType[gp.currentMap][i].solidArea.x += entType[gp.currentMap][i].worldX;
				entType[gp.currentMap][i].solidArea.y += entType[gp.currentMap][i].worldY;
				
				switch(currentDirection) {
				case "up": ent.solidArea.y -= ent.speed+48; break;
				case "down": ent.solidArea.y += ent.speed+48; break;
				case "left": ent.solidArea.x -= ent.speed+48; break;
				case "right": ent.solidArea.x += ent.speed+48; break;
				}
				
				if(ent.solidArea.intersects(entType[gp.currentMap][i].solidArea)) {
					if (entType[gp.currentMap][i] != ent) {
						ent.collisionOn = true;
						index = i;
					}
				}
				
				ent.solidArea.x = ent.defaultSolidAreaX;
				ent.solidArea.y = ent.defaultSolidAreaY;
				
				entType[gp.currentMap][i].solidArea.x = entType[gp.currentMap][i].defaultSolidAreaX;
				entType[gp.currentMap][i].solidArea.y = entType[gp.currentMap][i].defaultSolidAreaY;
			}
		}
	return index;
	
	}
	
	
	public boolean playerIntersect(Entity ent) {
		String currentDirection = ent.direction;
		if(ent.knockBackState) currentDirection = ent.knockDirection;
		boolean result = false;
		
		ent.solidArea.x += ent.worldX;
		ent.solidArea.y += ent.worldY;
		
		gp.player.solidArea.x += gp.player.worldX;
		gp.player.solidArea.y += gp.player.worldY;
		
		switch(currentDirection) {
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