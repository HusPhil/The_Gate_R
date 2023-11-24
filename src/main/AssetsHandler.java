package main;



import DataHandling.GameProgress;
import entity.NPC_Knight;
import entity.NPC_Princess;
import entity.NPC_BigRock;
import entity.NPC_Hermit;
import entity.NPC_Witch;
import interactive_tiles.IT_DryTree;
import interactive_tiles.IT_DryTree_Corrupted;
import interactive_tiles.IT_FeebleWall;
import interactive_tiles.IT_MetalPlate;
import monster.BOSS_SkeletonLord;
import monster.BOSS_WaterGolem;
import monster.MON_Bat;
import monster.MON_FireSlime;
import monster.MON_Mummy;
import monster.MON_Slime;
import monster.MON_Trenklin;
import monster.MON_Zombie;
import object.ITM_Coin;
import object.ITM_Key;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Health_Potion;
import object.OBJ_HeartCrystal;
import object.OBJ_IronDoor;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Pickaxe;
import object.OBJ_Iron_Shield;
import object.OBJ_Iron_Sword;
import object.OBJ_Iron_Axe;

public class AssetsHandler {
	GamePanel gp;
	public AssetsHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void makeObjects() {
		

		
		int i = 0;
		int mapNum = gp.worldMapA;
		
		gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
		gp.gameObjs[mapNum][i].worldX = 15*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 29*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
		gp.gameObjs[mapNum][i].worldX = 25*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 33*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F1;
		gp.gameObjs[mapNum][i] = new OBJ_IronDoor(gp);
		gp.gameObjs[mapNum][i].worldX = 24*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 14*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F2;
		gp.gameObjs[mapNum][i] = new OBJ_IronDoor(gp);
		gp.gameObjs[mapNum][i].worldX = 16*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 41*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
		gp.gameObjs[mapNum][i].worldX = 15*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 9*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Iron_Shield(gp));
		gp.gameObjs[mapNum][i].worldX = 15*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 43*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.gameObjs[mapNum][i].worldX = 16*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 43*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Lantern(gp));
		gp.gameObjs[mapNum][i].worldX = 17*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 43*gp.tileSize; i++;
		
		
		i = 0;
		mapNum = gp.corrupted1;
		gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
		gp.gameObjs[mapNum][i].cs_id = "001";
		gp.gameObjs[mapNum][i].worldX = 14*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 29*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.silvioHouse;
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Iron_Axe(gp));
		gp.gameObjs[mapNum][i].worldX = 17*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 16*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new ITM_Coin(gp));
		gp.gameObjs[mapNum][i].worldX = 18*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 16*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Health_Potion(gp));
		gp.gameObjs[mapNum][i].worldX = 19*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 16*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
		gp.gameObjs[mapNum][i].worldX = 30*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 18*gp.tileSize; i++;
		
		
		i = 0;
		mapNum = gp.silvioVillage;
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new ITM_Key(gp));
		gp.gameObjs[mapNum][i].worldX = 38*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 29*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.sacredRiver;
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_HeartCrystal(gp));
		gp.gameObjs[mapNum][i].worldX = 14*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 24*gp.tileSize; i++;

		
		i = 0;
		mapNum = gp.maze;
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.gameObjs[mapNum][i].worldX = 36*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 21*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_HeartCrystal(gp));
		gp.gameObjs[mapNum][i].worldX = 13*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 15*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Health_Potion(gp));
		gp.gameObjs[mapNum][i].worldX = 14*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 15*gp.tileSize; i++;
	
		i = 0;
		mapNum = gp.princessCage;
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Iron_Sword(gp));
		gp.gameObjs[mapNum][i].worldX = 22*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 18*gp.tileSize; i++;

		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new ITM_Key(gp));
		gp.gameObjs[mapNum][i].worldX = 24*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 18*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
		gp.gameObjs[mapNum][i].cs_id = "002";
		gp.gameObjs[mapNum][i].worldX = 30*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 22*gp.tileSize; i++;
		

	}
	public void makeNpc() {
		int i; int mapNum;
		
		setDungeonRocks();
		
		i = 0;
		mapNum = gp.corrupted1; // OVERWORLD
		gp.npc[mapNum][i] = new NPC_Hermit(gp);
		gp.npc[mapNum][i].name = "Silvio";
		gp.npc[mapNum][i].speed = 0;
		gp.npc[mapNum][i].worldX = 12*gp.tileSize;
		gp.npc[mapNum][i].worldY = 30*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.silvioHouse; 
		gp.npc[mapNum][i] = new NPC_Hermit(gp);
		gp.npc[mapNum][i].name = "Silvio";
		gp.npc[mapNum][i].dialogueSet = NPC_Hermit.oldManGoodluck;
		gp.npc[mapNum][i].worldX = 17*gp.tileSize;
		gp.npc[mapNum][i].worldY = 22*gp.tileSize; 
		gp.npc[mapNum][i].speed = 0; i++;
		
		gp.npc[mapNum][i] = new NPC_Witch(gp);
		gp.npc[mapNum][i].speed = 0;
		gp.npc[mapNum][i].worldX = 18*gp.tileSize;
		gp.npc[mapNum][i].worldY = 35*gp.tileSize; i++;
		
//		i = 0;
//		mapNum = gp.sacredRiver;
//		gp.npc[mapNum][i] = new NPC_Princess(gp);
//		gp.npc[mapNum][i].speed = 0;
//		gp.npc[mapNum][i].worldX = 32*gp.tileSize;
//		gp.npc[mapNum][i].worldY = 23*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.forest;
		gp.npc[mapNum][i] = new NPC_Knight(gp);
		gp.npc[mapNum][i].speed = 0;
		gp.npc[mapNum][i].worldX = 33*gp.tileSize;
		gp.npc[mapNum][i].worldY = 15*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.princessCage;
		gp.npc[mapNum][i] = new NPC_Princess(gp);
		gp.npc[mapNum][i].worldX = 30*gp.tileSize;
		gp.npc[mapNum][i].worldY = 18*gp.tileSize; i++;
		
	}
	public void makeMonster() {
		int i = 0;
		int mapNum = gp.worldMapA;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp); 
		gp.monsters[mapNum][i].worldY = 26*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 14*gp.tileSize; 
		gp.monsters[mapNum][i].name = "slime"; i++;
		
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 26*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 15*gp.tileSize;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 26*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 16*gp.tileSize;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 27*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 14*gp.tileSize;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 27*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 15*gp.tileSize;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 27*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 16*gp.tileSize;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 28*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 14*gp.tileSize;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_Zombie(gp);
		gp.monsters[mapNum][i].worldX = 18*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 21*gp.tileSize;
		i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F1;
		gp.monsters[mapNum][i] = new MON_Bat(gp);
		gp.monsters[mapNum][i].worldY = 38*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 24*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Bat(gp);
		gp.monsters[mapNum][i].worldY = 38*gp.tileSize;
		gp.monsters[mapNum][i].worldX = 25*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Bat(gp);
		gp.monsters[mapNum][i].worldX = 28*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 39*gp.tileSize;
		i++;
		
		
		i = 0;
		mapNum = gp.silvioHouse;
		gp.monsters[mapNum][i] = new MON_Bat(gp);
		gp.monsters[mapNum][i].worldX = 26*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 5*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Bat(gp);
		gp.monsters[mapNum][i].worldX = 20*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 7*gp.tileSize;
		i++;
		
		
		i = 0;
		mapNum = gp.forest;
		gp.monsters[mapNum][i] = new MON_Slime(gp);
		gp.monsters[mapNum][i].worldX = 21*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 10*gp.tileSize;
		i++;
		gp.monsters[mapNum][i] = new MON_Slime(gp);
		gp.monsters[mapNum][i].worldX = 20*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 12*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Slime(gp);
		gp.monsters[mapNum][i].worldX = 12*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 16*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Slime(gp);
		gp.monsters[mapNum][i].worldX = 17*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 16*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Slime(gp);
		gp.monsters[mapNum][i].worldX = 21*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 14*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Trenklin(gp);
		gp.monsters[mapNum][i].worldX = 13*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 23*gp.tileSize;
		i++;
		gp.monsters[mapNum][i] = new MON_Trenklin(gp);
		gp.monsters[mapNum][i].worldX = 15*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 30*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Trenklin(gp);
		gp.monsters[mapNum][i].worldX = 12*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 27*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Trenklin(gp);
		gp.monsters[mapNum][i].worldX = 20*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 26*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Trenklin(gp);
		gp.monsters[mapNum][i].worldX = 22*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 24*gp.tileSize;
		i++;
		
		
		i = 0;
		mapNum = gp.sacredRiver;
		gp.monsters[mapNum][i] = new MON_Mummy(gp);
		gp.monsters[mapNum][i].worldX = 24*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 12*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Mummy(gp);
		gp.monsters[mapNum][i].worldX = 27*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 15*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Mummy(gp);
		gp.monsters[mapNum][i].worldX = 34*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 11*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Mummy(gp);
		gp.monsters[mapNum][i].worldX = 13*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 16*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Mummy(gp);
		gp.monsters[mapNum][i].worldX = 23*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 20*gp.tileSize;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Mummy(gp);
		gp.monsters[mapNum][i].worldX = 12*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 27*gp.tileSize;
		i++;
		
		
		//bossss
		
		i = 0;
		mapNum = gp.sacredRiver;
		if(!GameProgress.waterGolemDefeated) {
			gp.monsters[mapNum][i] = new BOSS_WaterGolem(gp);
			gp.monsters[mapNum][i].worldX = 20*gp.tileSize;
			gp.monsters[mapNum][i].worldY = 36*gp.tileSize;
			i++;
		}

		i = 0;
		mapNum = gp.dungeonMap_F2;
		if(!GameProgress.defeatedSkeletonLord) {
			gp.monsters[mapNum][i] = new BOSS_SkeletonLord(gp);
			gp.monsters[mapNum][i].worldX = 25*gp.tileSize;
			gp.monsters[mapNum][i].worldY = 35*gp.tileSize;
			i++;
		}
	}
	public void makeInteractiveTiles() {
		int i = 0; 
		int mapNum = gp.worldMapA;
		gp.IT_Manager[mapNum][i] = new IT_DryTree(gp, 18, 33); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree(gp, 19, 33); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree(gp, 20, 33); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree(gp, 18, 34); i++;
		
		i = 0;
		mapNum = gp.silvioVillage;
		gp.IT_Manager[mapNum][i] = new IT_DryTree_Corrupted(gp, 22, 23); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree_Corrupted(gp, 21, 23); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree_Corrupted(gp, 21, 24); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree_Corrupted(gp, 21, 25); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree_Corrupted(gp, 20, 25); i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F1;
		gp.IT_Manager[mapNum][i] = new IT_FeebleWall(gp, 18, 35); i++;
		gp.IT_Manager[mapNum][i] = new IT_FeebleWall(gp, 18, 36); i++;
		gp.IT_Manager[mapNum][i] = new IT_FeebleWall(gp, 19, 36); i++;
		
		gp.IT_Manager[mapNum][i] = new IT_MetalPlate(gp, 14, 23); i++;
		gp.IT_Manager[mapNum][i] = new IT_MetalPlate(gp, 38, 25); i++;
		gp.IT_Manager[mapNum][i] = new IT_MetalPlate(gp, 38, 7); i++;
		
		i = 0;
		mapNum = gp.forest;
//		gp.IT_Manager[mapNum][i] = new IT_TempTree(gp, 24, 29); 
//		gp.IT_Manager[mapNum][i].name = "cs_sect1"; i++;
//		gp.IT_Manager[mapNum][i] = new IT_TempTree(gp, 25, 29);
//		gp.IT_Manager[mapNum][i].name = "cs_sect1"; i++; 
//		gp.IT_Manager[mapNum][i] = new IT_TempTree(gp, 25, 30);
//		gp.IT_Manager[mapNum][i].name = "cs_sect1"; i++;
//		gp.IT_Manager[mapNum][i] = new IT_TempTree(gp, 26, 30);
//		gp.IT_Manager[mapNum][i].name = "cs_sect1"; i++;
		
//		gp.IT_Manager[mapNum][i] = new IT_TempTree(gp, 33, 23); 
//		gp.IT_Manager[mapNum][i].name = "cs_sect2"; i++;
//		gp.IT_Manager[mapNum][i] = new IT_TempTree(gp, 33, 24); 
//		gp.IT_Manager[mapNum][i].name = "cs_sect2"; i++;
//		gp.IT_Manager[mapNum][i] = new IT_TempTree(gp, 34, 23); 
//		gp.IT_Manager[mapNum][i].name = "cs_sect2"; i++;
	}
	
	
	public void setDungeonRocks() {
		int i = 0;
		int mapNum = gp.dungeonMap_F1; // DUNGEON
		
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = 16*gp.tileSize;
		gp.npc[mapNum][i].worldY = 33*gp.tileSize; i++;
		
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = 34*gp.tileSize;
		gp.npc[mapNum][i].worldY = 32*gp.tileSize; i++;
		
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = 34*gp.tileSize;
		gp.npc[mapNum][i].worldY = 15*gp.tileSize; i++;
	}
	
}
