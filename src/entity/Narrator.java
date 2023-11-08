package entity;

import main.GamePanel;
import object.OBJ_Wooden_Shield;
import object.OBJ_Wooden_Sword;

public class Narrator extends Entity{
	public String player_name;
	
	public static final int intro_story = 0;
	public static final int full_inventory = 1;
	public static final int save_dialouge = 2;
	public static final int village_monster = 3;
	public static final int player_agree = 4;
	public static final int player_acquiredWS = 5;
	public static final int defeated_all_enemy = 6;
	public static final int axeHint_1 = 7;
	public static final int axeHint_2 = 8;

	public Narrator(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Narrator";
		setDialogue();
	}
	
	public void setDialogue() {
		int j = 0;
		
		dialogues[intro_story][j] = "In the annals of time, fate weaves its intricate tapestry, "
				+ "\nguided by the hands of heroes known and unknown."; j++;
		dialogues[intro_story][j] = "Now, " + player_name + ", you stand at the precipice of a "
				+ "\nfractured world, a testament to humanity's forgotten "
				+ "\nvows."; j++;
		dialogues[intro_story][j] = "As the tendrils of an ancient relic thrust you into the "
				+ "\nheart of a desolate future Earth, the echoes of an "
				+ "\nage-old plea resonate within your being."; j++;
		dialogues[intro_story][j] = "Your quest is clear: resurrect the fading embers of the "
				+ "\nSustainable Development Goals, rekindle hope, and "
				+ "\ncarve a new legacy upon the barren canvas of time."; j++;
		dialogues[intro_story][j] = "Heed the call, " + player_name + ", and weave a new "
				+ "chapter \nin the timeless saga of Earth's redemption."; j++;
		
		
		j = 0;
		dialogues[full_inventory][j] = "You are heavily burdened, you cannot \ncarry anymore."; j++;
		
		j = 0;
		dialogues[save_dialouge][j] = "Press 'Y' to save the game. Esc to cancel"; j++;
		
		j = 0;
		dialogues[village_monster][j] = "The monsters are currently attacking!"; j++;
		
		j = 0;
		dialogues[player_agree][j] = "..."; j++;
		dialogues[player_agree][j] = "...."; j++;
		dialogues[player_agree][j] = "You decided to help the old man."; j++;
		
		j = 0;
		dialogues[player_acquiredWS][j] = "You acquired: " + OBJ_Wooden_Sword.objName; j++;
		dialogues[player_acquiredWS][j] = "You acquired: " + OBJ_Wooden_Shield.objName; j++; 
		
		j = 0;
		dialogues[defeated_all_enemy][j] = "You have defeated all the enemy!"; j++;
		dialogues[defeated_all_enemy][j] = "Talk to the old man (Press T near him)"; j++;
		
		
		j = 0;
		dialogues[axeHint_1][j] = "You noticed some dry trees!"; j++;
		dialogues[axeHint_1][j] = "You mights need a tool to cut them!"; j++;
		dialogues[axeHint_1][j] = "Maybe.. you can find it in the village!"; j++;
	
	}

}
