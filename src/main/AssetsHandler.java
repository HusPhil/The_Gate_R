package main;



import entity.NPC_BigRock;
import entity.NPC_Cursed_Villager;
import entity.NPC_Hermit;
import entity.NPC_Merchant;
import entity.NPC_Witch;
import interactive_tiles.IT_DryTree;
import interactive_tiles.IT_DryTree_Corrupted;
import interactive_tiles.IT_FeebleWall;
import interactive_tiles.IT_MetalPlate;
import monster.BOSS_SkeletonLord;
import monster.MON_Bat;
import monster.MON_FireSlime;
import monster.MON_Slime;
import monster.MON_Trenklin;
import monster.MON_Zombie;
import object.ITM_Coin;
import object.ITM_Key;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Health_Potion;
import object.OBJ_Heart;
import object.OBJ_IronDoor;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Pickaxe;
import object.OBJ_Iron_Shield;
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
		
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.gameObjs[mapNum][i].worldX = 27*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 18*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F1;
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.gameObjs[mapNum][i].worldX = 38*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 36*gp.tileSize; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_IronDoor(gp);
		gp.gameObjs[mapNum][i].worldX = 24*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 14*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F2;
		gp.gameObjs[mapNum][i] = new OBJ_IronDoor(gp);
		gp.gameObjs[mapNum][i].worldX = 16*gp.tileSize;
		gp.gameObjs[mapNum][i].worldY = 41*gp.tileSize; i++;
		
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
		
		
	
		
	}
	public void makeItems() {
		int i = 0;
		int mapNum = 0;
		
	}
	public void makeNpc() {
		int i; int mapNum;
		
//		i = 0;
//		mapNum = gp.worldMapA; // OVERWORLD
//		setEntity(mapNum, i, "npcA", "", 15, 30); i++;
		
		setDungeonRocks();
		
//		i = 0;
//		mapNum = gp.merchantHouse; //STORE/ MERCHANT
//		setEntity(mapNum, i, "npcB", "", 18, 18); i++;
//		gp.npc[2][0].speed = 0;
		
		i = 0;
		mapNum = gp.corrupted1; // OVERWORLD
		gp.npc[mapNum][i] = new NPC_Hermit(gp);
		gp.npc[mapNum][i].name = "Silvio";
		gp.npc[mapNum][i].speed = 0;
		gp.npc[mapNum][i].worldX = 12*gp.tileSize;
		gp.npc[mapNum][i].worldY = 30*gp.tileSize; i++;
		
		i = 0;
		mapNum = gp.silvioHouse; // OVERWORLD
		gp.npc[mapNum][i] = new NPC_Hermit(gp);
		gp.npc[mapNum][i].name = "Silvio";
		gp.npc[mapNum][i].dialogueSet = NPC_Hermit.intro_end_3;
		gp.npc[mapNum][i].worldX = 18*gp.tileSize;
		gp.npc[mapNum][i].worldY = 21*gp.tileSize; i++;
		
		gp.npc[mapNum][i] = new NPC_Witch(gp);
//		gp.npc[mapNum][i].dialogueSet = NPC_Witch.quest1a;
		gp.npc[mapNum][i].speed = 0;
		gp.npc[mapNum][i].worldX = 18*gp.tileSize;
		gp.npc[mapNum][i].worldY = 35*gp.tileSize; i++;
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
		mapNum = gp.dungeonMap_F2;
		gp.monsters[mapNum][i] = new BOSS_SkeletonLord(gp);
		gp.monsters[mapNum][i].worldX = 25*gp.tileSize;
		gp.monsters[mapNum][i].worldY = 35*gp.tileSize;
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
//		i++;
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
		
	}
	
	
	public void setDungeonRocks() {
//		int i = 0;
//		int mapNum = gp.dungeonMap_F1; // DUNGEON
//		setEntity(mapNum, i, "npcC", "", 16, 33); i++;
//		setEntity(mapNum, i, "npcC", "", 34, 32); i++;
//		setEntity(mapNum, i, "npcC", "", 34, 15); i++;
	}
	
}
