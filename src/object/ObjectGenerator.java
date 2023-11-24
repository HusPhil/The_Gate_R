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
		case OBJ_Iron_Axe.objName:
			obj = new OBJ_Iron_Axe(gp); 
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
		case OBJ_Iron_Shield.objName:
			obj = new OBJ_Iron_Shield(gp);
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
		case OBJ_Iron_Sword.objName:
			obj = new OBJ_Iron_Sword(gp);
			break;
		case ITM_SlimeGel.objName:
			obj = new ITM_SlimeGel(gp);
			break;
		case ITM_TrenkMeat.objName:
			obj = new ITM_TrenkMeat(gp);
			break;
		case ITM_TrenkAmulet.objName:
			obj = new ITM_TrenkAmulet(gp);
			break;
		case ITM_Bandage.objName:
			obj = new ITM_Bandage(gp);
			break;
		case ITM_WaterEssence.objName:
			obj = new ITM_WaterEssence(gp);
			break;
		case ITM_WaterCrystal.objName:
			obj = new ITM_WaterCrystal(gp);
			break;
		case OBJ_HeartCrystal.objName:
			obj = new OBJ_HeartCrystal(gp);
			break;
			
			
		}
		
		return obj;
	}
	
	

}
