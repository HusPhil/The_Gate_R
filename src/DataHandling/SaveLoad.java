package DataHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.GamePanel;

public class SaveLoad {
	GamePanel gp;
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public void saveData() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("temp0.dat")));
			
			DataStorage ds = new DataStorage();
			
		//set the values to the data storage
			
			//PLAYERS STATUS
			ds.level = gp.player.level;
			ds.life = gp.player.life;
			ds.maxLife = gp.player.maxLife;
			ds.mana = gp.player.mana;
			ds.maxMana = gp.player.maxMana;
			ds.strength = gp.player.str;
			ds.exp =  gp.player.exp;
			ds.nextLevelExp = gp.player.nextLvlExp;
			ds.coin = gp.player.coin;
			
			
			//PLAYER INVENTORY
			for(int i = 0; i < gp.player.inventory.size(); i++) {
				ds.itemNames.add(gp.player.inventory.get(i).name);
				ds.itemAmmount.add(gp.player.inventory.get(i).ammount);
			}
			
			//PLAYER'S EQUIPMET
			ds.currentWeaponSlot = gp.player.getCurrenntWeaponSlot();
			ds.currentShieldSlot = gp.player.getCurrenntShieldSlot();
			
			//OBJECTS ON THE MAP
			ds.objectNames = new String[gp.maxMap][gp.gameObjs[1].length];
			ds.objectLootNames = new String[gp.maxMap][gp.gameObjs[1].length];
			ds.objectWorldX = new int[gp.maxMap][gp.gameObjs[1].length];
			ds.objectWorldY = new int[gp.maxMap][gp.gameObjs[1].length];
			ds.objectOpened = new boolean[gp.maxMap][gp.gameObjs[1].length];
			
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for(int i = 0; i < gp.gameObjs[1].length; i++){
					if(gp.gameObjs[mapNum][i] == null) ds.objectNames[mapNum][i] = "NULL";
					else {
						ds.objectNames[mapNum][i] = gp.gameObjs[mapNum][i].name;
						ds.objectWorldX[mapNum][i] = gp.gameObjs[mapNum][i].worldX;
						ds.objectWorldY[mapNum][i] = gp.gameObjs[mapNum][i].worldY;
						if(gp.gameObjs[mapNum][i].loot != null) ds.objectLootNames[mapNum][i] = gp.gameObjs[mapNum][i].loot.name;
						ds.objectOpened[mapNum][i] = gp.gameObjs[mapNum][i].opened;
					}
				}
			}
			
			
			//save/write the data to save.dat
			oos.writeObject(ds);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//ditoo
		System.out.println(gp.player.ID);
		gp.DBMS.updatePlayerData("temp0.dat");
		
		
	} 
	public void loadData( ) {
		
		gp.DBMS.loadPlayerData();
		
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(new File("save_data.dat"))
					);
			
			try {
				DataStorage ds = (DataStorage)ois.readObject();
				//PLAYER STATUS
				gp.player.level = ds.level;
				gp.player.life = ds.life;
				gp.player.maxLife = ds.maxLife;
				gp.player.mana = ds.mana;
				gp.player.maxMana = ds.maxMana;
				gp.player.str = ds.strength;
				gp.player.exp = ds.exp;
				gp.player.nextLvlExp = ds.nextLevelExp;
				gp.player.coin = ds.coin;
				
				//PLAYER INVENTORY
				gp.player.inventory.clear();
				for(int i = 0; i < ds.itemNames.size(); i++) {
					gp.player.inventory.add(gp.objGen.getObjectFromName(ds.itemNames.get(i)));
					gp.player.inventory.get(i).ammount = ds.itemAmmount.get(i);
				}
				
				//PLAYER'S INBENTORY
				gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
				gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
				gp.player.getAtk();
				gp.player.getDef();
				gp.player.getPlayerAttackImage();
				
				//OBJECTS ON MAP
				for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
					for(int i = 0; i < gp.gameObjs[1].length; i++){
						if(ds.objectNames[mapNum][i].equals("NULL")) gp.gameObjs[mapNum][i] = null;
						
						else {
							gp.gameObjs[mapNum][i] = gp.objGen.getObjectFromName(ds.objectNames[mapNum][i]);
							gp.gameObjs[mapNum][i].worldX = ds.objectWorldX[mapNum][i];
							gp.gameObjs[mapNum][i].worldY = ds.objectWorldY[mapNum][i];
							if(ds.objectLootNames[mapNum][i] != null) gp.gameObjs[mapNum][i].setLoot(gp.objGen.getObjectFromName(ds.objectLootNames[mapNum][i]));
							gp.gameObjs[mapNum][i].opened = ds.objectOpened[mapNum][i];
							if(gp.gameObjs[mapNum][i].opened) gp.gameObjs[mapNum][i].down1 = gp.gameObjs[mapNum][i].image2;
						}
					}
				}
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
