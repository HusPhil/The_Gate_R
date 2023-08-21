package main;

import entity.Entity;
import entity.NPC_Hermit;
import interactive_tiles.IT_Tree;
import monster.MON_FireSlime;
import object.ITM_Coin;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Health_Potion;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Slime_Shield;
import object.OBJ_Wood_Axe;

public class AssetsHandler {
	GamePanel gp;
	public AssetsHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void makeObjects() {
		int i = 0;
		setEntity(i, "objA","Key", 55, 46);
		i++;
		setEntity(i, "objA","Key", 57, 57);
		i++;
		setEntity(i, "objB","ChestA", 58, 57);
		i++;
		setEntity(i, "objC","Door", 56, 43);
		i++;
		setEntity(i, "objC","Door 2", 49, 12);
		i++;
		setEntity(i, "objB", "ChestB", 55, 43);
		i++;
	}
	public void makeItems() {
		int i = 7;
		gp.items[i] = new ITM_Coin(gp);
		gp.items[i].worldX = 73*48;
		gp.items[i].worldY = 27*48;
		i++;
		
		gp.items[i] = new OBJ_Health_Potion(gp);
		gp.items[i].worldX = 73*48;
		gp.items[i].worldY = 28*48;
		i++;
		
		gp.items[i] = new OBJ_Slime_Shield(gp);
		gp.items[i].worldX = 57*48;
		gp.items[i].worldY = 43*48;
		i++;
		
		gp.items[i] = new OBJ_Wood_Axe(gp);
		gp.items[i].worldX = 57*48;
		gp.items[i].worldY = 45*48;
		i++;
		
		gp.items[i] = new OBJ_Heart(gp);
		gp.items[i].worldX = (57*48) + 12;
		gp.items[i].worldY = (46*48) + 12;
		i++;
		
		gp.items[i] = new OBJ_Heart(gp);
		gp.items[i].worldX = (57*48) + 12;
		gp.items[i].worldY = (47*48) + 12;
		i++;
		
		gp.items[i] = new OBJ_Heart(gp);
		gp.items[i].worldX = (57*48) + 12;
		gp.items[i].worldY = (48*48) + 12;
		i++;
		
		gp.items[i] = new OBJ_Heart(gp);
		gp.items[i].worldX = (57*48) + 12;
		gp.items[i].worldY = (49*48) + 12;
		i++;
	}
	public void makeNpc() {
		int i = 0;
		setEntity(i, "npcA", "Hermit 1", 54, 40);
		i++;
		setEntity(i, "npcA", "Hermit 2", 36, 29);
		i++;
	}
	
	public void makeMonster() {
		setEntity(0, "monA", "Fire Slime", 36, 30);
		setEntity(1, "monA", "Fire Slime", 37, 30);
		setEntity(2, "monA", "Fire Slime", 64, 49);
		gp.monsters[2].life =100;
		gp.monsters[2].speed = 0;
		gp.monsters[2].exp = 1000;
		setEntity(3, "monA", "F_Slime 4", 27, 26);
		int i = 3;
		
		setEntity(i, "monA", "Fire Slime", 60, 52);
		i++;
		setEntity(i, "monA", "Fire Slime", 61, 54);
		i++;
		setEntity(i, "monA", "Fire Slime", 64, 53);
		i++;
		setEntity(i, "monA", "Fire Slime", 66, 51);
		i++;
		setEntity(i, "monA", "Fire Slime", 66, 54);
		i++;
		setEntity(i, "monA", "Fire Slime", 64, 56);
		i++;
	}
	
	public void makeInteractiveTiles() {
		int i = 0;
		gp.IT_Manager[i] = new IT_Tree(gp, 18, 27);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 18, 28);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 17, 27);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 17, 28);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 17, 29);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 17, 30);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 18, 30);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 17, 31);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 18, 31);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 18, 29);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 52, 40);
		i++;
		gp.IT_Manager[i] = new IT_Tree(gp, 52, 41);
		i++;
	}
	
	public void setEntity(int i, String type, String name, int x, int y) {
		switch(type) {
		//SET NPCs
		case "npcA":
				gp.npc[i] = new NPC_Hermit(gp);
				gp.npc[i].worldX = x*48;
				gp.npc[i].worldY = y*48;
				gp.npc[i].name = name;
				break;
		case "npcB":
			break;
			
		//SET OBJECTS
		case "objA":
			gp.gameObjs[i] = new OBJ_Key(gp);
			gp.gameObjs[i].worldX = (x*48)+12;
			gp.gameObjs[i].worldY = (y*48)+12;
			gp.gameObjs[i].name = name;
			break;
		case "objB":
			gp.gameObjs[i] = new OBJ_Chest(gp);
			gp.gameObjs[i].worldX = x*48;
			gp.gameObjs[i].worldY = y*48;
			gp.gameObjs[i].name = name;
			break;
		case "objC":
			gp.gameObjs[i] = new OBJ_Door(gp);
			gp.gameObjs[i].worldX = x*48;
			gp.gameObjs[i].worldY = y*48;
			gp.gameObjs[i].name = name;
			break;
			
		//SET MONSTERS
		case "monA":
			gp.monsters[i] = new MON_FireSlime(gp);
			gp.monsters[i].worldX = x*48;
			gp.monsters[i].worldY = y*48;
			gp.monsters[i].name = name;
			break;
		default:
			System.out.println("Please type the variant");
		}
	}
	
}
