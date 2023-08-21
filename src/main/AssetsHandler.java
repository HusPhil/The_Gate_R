package main;



import entity.NPC_BigRock;
import entity.NPC_Hermit;
import entity.NPC_Merchant;
import interactive_tiles.IT_DryTree;
import interactive_tiles.IT_MetalPlate;
import monster.BOSS_SkeletonLord;
import monster.MON_Bat;
import monster.MON_FireSlime;
import monster.MON_Zombie;
import object.ITM_Coin;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Health_Potion;
import object.OBJ_Heart;
import object.OBJ_IronDoor;
import object.OBJ_Key;
import object.OBJ_Pickaxe;
import object.OBJ_Slime_Shield;
import object.OBJ_Wooden_Axe;

public class AssetsHandler {
	GamePanel gp;
	public AssetsHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void makeObjects() {
		
		int i = 0;
		int mapNum = gp.worldMapA;
		
		gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
		gp.gameObjs[mapNum][i].worldX = 15*48;
		gp.gameObjs[mapNum][i].worldY = 29*48; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
		gp.gameObjs[mapNum][i].worldX = 25*48;
		gp.gameObjs[mapNum][i].worldY = 33*48; i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F1;
		gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
		gp.gameObjs[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.gameObjs[mapNum][i].worldX = 38*48;
		gp.gameObjs[mapNum][i].worldY = 36*48; i++;
		
		gp.gameObjs[mapNum][i] = new OBJ_IronDoor(gp);
		gp.gameObjs[mapNum][i].worldX = 24*48;
		gp.gameObjs[mapNum][i].worldY = 14*48; i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F2;
		gp.gameObjs[mapNum][i] = new OBJ_IronDoor(gp);
		gp.gameObjs[mapNum][i].worldX = 16*48;
		gp.gameObjs[mapNum][i].worldY = 41*48; i++;
		
	}
	public void makeItems() {
		int i = 0;
		int mapNum = 0;
		
	}
	public void makeNpc() {
		int i; int mapNum;
		
		i = 0;
		mapNum = gp.worldMapA; // OVERWORLD
		setEntity(mapNum, i, "npcA", "", 15, 30); i++;
		
		setDungeonRocks();
		
		i = 0;
		mapNum = gp.merchantHouse; //STORE/ MERCHANT
		setEntity(mapNum, i, "npcB", "", 18, 18); i++;
		gp.npc[2][0].speed = 0;
	}
	public void makeMonster() {
		int i = 0;
		int mapNum = gp.worldMapA;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp); 
		gp.monsters[mapNum][i].worldY = 26*48;
		gp.monsters[mapNum][i].worldX = 14*48; 
		gp.monsters[mapNum][i].name = "slime"; i++;
		
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 26*48;
		gp.monsters[mapNum][i].worldX = 15*48;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 26*48;
		gp.monsters[mapNum][i].worldX = 16*48;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 27*48;
		gp.monsters[mapNum][i].worldX = 14*48;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 27*48;
		gp.monsters[mapNum][i].worldX = 15*48;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 27*48;
		gp.monsters[mapNum][i].worldX = 16*48;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_FireSlime(gp);
		gp.monsters[mapNum][i].worldY = 28*48;
		gp.monsters[mapNum][i].worldX = 14*48;
		gp.monsters[mapNum][i].name = "slime";i++;
		
		gp.monsters[mapNum][i] = new MON_Zombie(gp);
		gp.monsters[mapNum][i].worldX = 18*48;
		gp.monsters[mapNum][i].worldY = 21*48;
		i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F1;
		gp.monsters[mapNum][i] = new MON_Bat(gp);
		gp.monsters[mapNum][i].worldY = 38*48;
		gp.monsters[mapNum][i].worldX = 24*48;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Bat(gp);
		gp.monsters[mapNum][i].worldY = 38*48;
		gp.monsters[mapNum][i].worldX = 25*48;
		i++;
		
		gp.monsters[mapNum][i] = new MON_Bat(gp);
		gp.monsters[mapNum][i].worldX = 28*48;
		gp.monsters[mapNum][i].worldY = 39*48;
		i++;
		
		i = 0;
		mapNum = gp.dungeonMap_F2;
		gp.monsters[mapNum][i] = new BOSS_SkeletonLord(gp);
		gp.monsters[mapNum][i].worldX = 25*48;
		gp.monsters[mapNum][i].worldY = 35*48;
		i++;
	}
	public void makeInteractiveTiles() {
		int i = 0; 
		int mapNum = 0;
		gp.IT_Manager[mapNum][i] = new IT_DryTree(gp, 18, 33); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree(gp, 19, 33); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree(gp, 20, 33); i++;
		gp.IT_Manager[mapNum][i] = new IT_DryTree(gp, 18, 34); i++;
		
		i = 0;
		mapNum = 1;
//		gp.IT_Manager[mapNum][i] = new IT_FeebleWall(gp, 18, 35); i++;
//		gp.IT_Manager[mapNum][i] = new IT_FeebleWall(gp, 18, 36); i++;
//		gp.IT_Manager[mapNum][i] = new IT_FeebleWall(gp, 19, 36); i++;
		
		gp.IT_Manager[mapNum][i] = new IT_MetalPlate(gp, 14, 23); i++;
		gp.IT_Manager[mapNum][i] = new IT_MetalPlate(gp, 38, 25); i++;
		gp.IT_Manager[mapNum][i] = new IT_MetalPlate(gp, 38, 7); i++;
		
	}
	
	
	public void setDungeonRocks() {
		int i = 0;
		int mapNum = gp.dungeonMap_F1; // DUNGEON
		setEntity(mapNum, i, "npcC", "", 16, 33); i++;
		setEntity(mapNum, i, "npcC", "", 34, 32); i++;
		setEntity(mapNum, i, "npcC", "", 34, 15); i++;
	}
	public void setEntity(int mapNum, int i, String type, String name, int x, int y) {
		switch(type) {
		//SET NPCs
		case "npcA":
			gp.npc[mapNum][i] = new NPC_Hermit(gp);
			gp.npc[mapNum][i].worldX = x*48;
			gp.npc[mapNum][i].worldY = y*48;
			break;
		case "npcB":
			gp.npc[mapNum][i] = new NPC_Merchant(gp);
			gp.npc[mapNum][i].worldX = x*48;
			gp.npc[mapNum][i].worldY = y*48;
			break;
		case "npcC":
			gp.npc[mapNum][i] = new NPC_BigRock(gp);
			gp.npc[mapNum][i].worldX = x*48;
			gp.npc[mapNum][i].worldY = y*48;
			break;
			
		//SET OBJECTS
		case "objA":
			gp.gameObjs[mapNum][i] = new OBJ_Key(gp);
			gp.gameObjs[mapNum][i].worldX = (x*48)+12;
			gp.gameObjs[mapNum][i].worldY = (y*48)+12;
			gp.gameObjs[mapNum][i].name = name;
			break;
		case "objB":
			gp.gameObjs[mapNum][i] = new OBJ_Chest(gp);
			gp.gameObjs[mapNum][i].worldX = x*48;
			gp.gameObjs[mapNum][i].worldY = y*48;
			gp.gameObjs[mapNum][i].name = name;
			break;
		case "objC":
			gp.gameObjs[mapNum][i] = new OBJ_Door(gp);
			gp.gameObjs[mapNum][i].worldX = x*48;
			gp.gameObjs[mapNum][i].worldY = y*48;
			gp.gameObjs[mapNum][i].name = name;
			break;
		
		//SET ITEMS
		case "itemA":
			gp.items[mapNum][i] = new ITM_Coin(gp);
			gp.items[mapNum][i].worldX = x*48;
			gp.items[mapNum][i].worldY = y*48;
			break;
		case "itemB":
			gp.items[mapNum][i] = new OBJ_Health_Potion(gp);
			gp.items[mapNum][i].worldX = x*48;
			gp.items[mapNum][i].worldY = y*48;
			break;
		case "itemC":
			gp.items[mapNum][i] = new OBJ_Slime_Shield(gp);
			gp.items[mapNum][i].worldX = x*48;
			gp.items[mapNum][i].worldY = y*48;
			break;
		case "itemD":
			gp.items[mapNum][i] = new OBJ_Wooden_Axe(gp);
			gp.items[mapNum][i].worldX = x*48;
			gp.items[mapNum][i].worldY = y*48;
			break;
		case "itemE":
			gp.items[mapNum][i] = new OBJ_Heart(gp);
			gp.items[mapNum][i].worldX = x*48;
			gp.items[mapNum][i].worldY = y*48;
			break;
			
		//SET MONSTERS
		case "monA":
			gp.monsters[mapNum][i] = new MON_FireSlime(gp);
			gp.monsters[mapNum][i].worldX = x*48;
			gp.monsters[mapNum][i].worldY = y*48;
			gp.monsters[mapNum][i].name = name;
			break;
		case "monB":
			gp.monsters[mapNum][i] = new MON_Zombie(gp);
			gp.monsters[mapNum][i].worldX = x*48;
			gp.monsters[mapNum][i].worldY = y*48;
			gp.monsters[mapNum][i].name = name;
			break;
		default:
			System.out.println("Please type the variant");
		}
	}
	
}
