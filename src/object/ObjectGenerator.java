package object;

import entity.Entity;
import main.GamePanel;

public class ObjectGenerator {
	GamePanel gp;
	public ObjectGenerator(GamePanel gp) {
		// TODO Auto-generated constructor stub
		this.gp = gp;
		
		
	}
	public Entity getObjectFromName(String name) {
		Entity obj = null;
		
		switch(name) {
		case ITM_Coin.objName:
			obj = new ITM_Coin(gp);
			break;
		case OBJ_Heart.objName:
			obj = new OBJ_Heart(gp);
			break;
		case OBJ_Wooden_Axe.objName:
			obj = new OBJ_Wooden_Axe(gp);
			break;
		case ITM_Key.objName:
			obj = new ITM_Key(gp);
			break;
		case OBJ_Lantern.objName:
			obj = new OBJ_Lantern(gp);
			break;
		case OBJ_Health_Potion.objName:
			obj = new OBJ_Health_Potion(gp);
			break;
		case OBJ_Wooden_Shield.objName:
			obj = new OBJ_Wooden_Shield(gp);
			break;
		case OBJ_Slime_Shield.objName:
			obj = new OBJ_Slime_Shield(gp);
			break;
		case OBJ_Wooden_Sword.objName:
			obj = new OBJ_Wooden_Sword(gp);
			break;
		case OBJ_Door.objName:
			obj = new OBJ_Door(gp);
			break;
		case OBJ_IronDoor.objName:
			obj = new OBJ_IronDoor(gp);
			break;
		case OBJ_Chest.objName:
			obj = new OBJ_Chest(gp);
			break;
		case OBJ_Pickaxe.objName:
			obj = new OBJ_Pickaxe(gp);
			break;
		}
		
		return obj;
	}
	
	

}
