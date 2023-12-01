package DataHandling;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
	
	//STATS
	int level;
	int life, maxLife;
	int mana, maxMana;
	int strength;
	int exp, nextLevelExp;
	int coin;
	
	boolean encounterOldMan;
	boolean oldManExplained;
	boolean intro_done;
	boolean witchQuest1Complete;
	boolean oldManQuest2Explained;
	boolean waterGolemDefeated;
	boolean waterCrystalActivated;
	boolean knightEncountered;
	boolean princessEncountered;
	boolean defeatedSkeletonLord;
	boolean witchReported;
	boolean princessReunited;
	boolean princessCrafted;
	boolean ending;
	
	//INVENTORY
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmmount = new ArrayList<>();
	
	//EQUIPMENTS
	int currentWeaponSlot;
	int currentShieldSlot;
	
	//OBJECTS on MAP
	String objectNames[][];
	String objectLootNames[][];
	int objectWorldX[][];
	int objectWorldY[][];
	boolean objectOpened[][];
	String objectCsId[][];
	
}
