package object;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class DropChanceSystem {
	GamePanel gp;
	public Entity defaultItem = new Entity(gp);
	public Entity possibleDrops[] = new Entity[20];
	
	public DropChanceSystem() {
	}
	
	public Entity pickDrop() {
		int rand = new Random().nextInt(500)+1;
		System.out.println(rand);
		for(int i = 0; i < possibleDrops.length; i++) {
			if(possibleDrops[i] != null) {
				if(rand <= possibleDrops[i].dropChance) {
					return possibleDrops[i];
					
				}
			} else return defaultItem;
		}
		return null; 
		
	}
	
}
