package entity;

import main.GamePanel;
import object.OBJ_Wooden_Shield;
import object.OBJ_Wooden_Sword;

public class NPC_Narrator extends Entity{
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
	public static final int villager_comeout = 9;
	public static final int witchEncounter = 10;
	public static final int witchQuest1Complete = 11;
	public static final int witchQuest1Incomplete = 12;
	public static final int oldManQ2a = 13;
	public static final int oldManQ2b = 14;
	public static final int oldManQ2c = 15;
	public static final int oldManQ2d = 16;
	public static final int oldManQ2e = 17;
	public static final int waterGolem = 18;
	public static final int receiveWaterCrystal = 19;
	public static final int oldManWaterCrystalA = 20;
	public static final int oldManWaterCrystalB = 21;
	public static final int knightEncounterA = 22;
	public static final int knightEncounterB = 23;
	public static final int princessEncounterA = 24;
	public static final int princessEncounterB = 25;
	public static final int princessEncounterC = 26;
	public static final int princessEncounterD = 27;
	public static final int witchReportedA = 28;
	public static final int receiveFireAmulet = 29;
	
	public static final int playerRequestA = 30;
	public static final int playerRequestB = 31;
	public static final int playerRequestC = 32;
	
	
	public static final int receiveTerra = 33;
	
	public static final int finalBattleA = 34;
	public static final int finalBattleB = 35;
	public static final int finalBattleC = 36;
	public static final int finalBattleD = 37;
	public static final int finalBattleE = 38;
	public static final int finalBattleF = 39;
	public static final int finalBattleG = 40;
	public static final int finalBattleH = 41;
	public static final int finalBattleI = 42;
	public static final int finalBattleL = 43;
	public static final int finalBattleM = 44;
	public static final int finalBattleN = 45;
	public static final int finalBattleO = 46;
	
	public static final int craftWarning = 47;
	
	public static final int endingA = 48;
	

	public final static String NPC_Name ="Narrator";

	public NPC_Narrator(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = NPC_Name;
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
		dialogues[player_agree][j] = "...."; j++;
		dialogues[player_agree][j] = "You decided to help the old man."; j++;
		
		j = 0;
		dialogues[player_acquiredWS][j] = "You acquired: " + OBJ_Wooden_Sword.objName; j++;
		dialogues[player_acquiredWS][j] = "You acquired: " + OBJ_Wooden_Shield.objName; j++; 
		
		j = 0;
		dialogues[defeated_all_enemy][j] = "You have defeated all the enemy!"; j++;
		dialogues[defeated_all_enemy][j] = "Talk to the old man"; j++;
		dialogues[defeated_all_enemy][j] = "(Press T near him)"; j++;
		
		
		j = 0;
		dialogues[axeHint_1][j] = "You noticed some dry trees!"; j++;
		dialogues[axeHint_1][j] = "You might need a tool to cut them!"; j++;
		dialogues[axeHint_1][j] = "Maybe.. you can find it in the village!"; j++;
	
		j = 0;
		dialogues[villager_comeout][j] = "The villagers came out of the house"; j++;
		dialogues[villager_comeout][j] = "They look weird.."; j++;
	
		j = 0;
		dialogues[witchEncounter][j] = "You told the her about your journey.."; j++;
		
		j = 0;
		dialogues[witchQuest1Complete][j] = "You received a Trenk Amulet!"; j++;
		dialogues[witchQuest1Complete][j] = "She told you how to activate it."; j++;
		j = 0;
		dialogues[witchQuest1Incomplete][j] = "You have yet to acquire all the materials!"; j++;
		
		j = 0;
		dialogues[oldManQ2a][j] = "You gave the amulet to Silvio."; j++;
		dialogues[oldManQ2a][j] = "You also told him how to activate it."; j++;
		
		j = 0;
		dialogues[oldManQ2b][j] = "You told him about what the witch said."; j++;

		j = 0;
		dialogues[oldManQ2c][j] = "Silvio activated the amulet!"; j++;
		
		j = 0;
		dialogues[oldManQ2d][j] = "...."; j++;
		dialogues[oldManQ2d][j] = "You agreed to help Silvio!"; j++;
		
		j = 0;
		dialogues[oldManQ2e][j] = "You received a lantern!"; j++;
		
		j = 0;
		dialogues[waterGolem][j] = "..."; j++;
		dialogues[waterGolem][j] = "You feel an eerie, chilly, aura..!"; j++;
		dialogues[waterGolem][j] = "..."; j++;
		dialogues[waterGolem][j] = "The Water Golem has awoken!"; j++;
		
		j = 0;
		dialogues[receiveWaterCrystal][j] = "You received a Water Crystal!"; j++;		
		
		j = 0;
		dialogues[oldManWaterCrystalA][j] = "You gave him the Water Crystal!"; j++;	
		j = 0;
		dialogues[oldManWaterCrystalB][j] = "Silvio activated the Water Crystal!"; j++;
		
		j = 0;
		dialogues[knightEncounterA][j] = "You told him about your story so far.."; j++;
		
		j = 0;
		dialogues[knightEncounterB][j] = "..."; j++;
		dialogues[knightEncounterB][j] = "You took on the challenge!"; j++;
		
		j = 0;
		dialogues[princessEncounterA][j] = "You were mesmerized by her beauty!"; j++;
		
		j = 0;
		dialogues[princessEncounterB][j] = "You felt embarassed.."; j++;
		
		j = 0;
		dialogues[princessEncounterC][j] = "You felt honored to escort her."; j++;
		
		j = 0;
		dialogues[princessEncounterD][j] = "Being too mesmerized by the Princess.."; j++;
		dialogues[princessEncounterD][j] = "..You forgot to tell her something!"; j++;
	
		j = 0;
		dialogues[witchReportedA][j] = "..."; j++;
		dialogues[witchReportedA][j] = "Maybe you should report back first.."; j++;
		
		j = 0;
		dialogues[receiveFireAmulet][j] = "You received a Fire Amulet!"; j++;
		dialogues[receiveFireAmulet][j] = "Equip it and now you can use magic!"; j++;
		dialogues[receiveFireAmulet][j] = "(Press F to use magic)"; j++;
		
		j = 0;
		dialogues[playerRequestA][j] = "You told them about the Water Crystal"; j++;
		j = 0;
		dialogues[playerRequestB][j] = "Silvio gave the Water Crystal to her"; j++;
		j = 0;
		dialogues[playerRequestC][j] = "She examined the Water Crystal"; j++;
		
		j = 0;
		dialogues[craftWarning][j] = "..."; j++;
		dialogues[craftWarning][j] = "The time is of the essence!"; j++;
		dialogues[craftWarning][j] = "Quickly do as the princess says!"; j++;
		
		j = 0;
		dialogues[receiveTerra][j] = "You received a Terra Blade!"; j++;
		dialogues[receiveTerra][j] = "You received a Vorpal Stone!"; j++;
		
		
		j = 0;
		dialogues[finalBattleA][j] = "The Trenk Lord is enchanting!"; j++;
		dialogues[finalBattleA][j] = "He summoned some monsters...!"; j++;
		dialogues[finalBattleA][j] = "A wave of Trenklins is coming!"; j++;
		
		j = 0;
		dialogues[finalBattleB][j] = "The Trenk Lord is enchanting!"; j++;
		dialogues[finalBattleB][j] = "Another wave of Trenklins coming!"; j++;
		
		j = 0;
		dialogues[finalBattleC][j] = "The Trenk Lord is enchanting!"; j++;
		dialogues[finalBattleC][j] = "A wave of Mummies is coming!"; j++;
		
		j = 0;
		dialogues[finalBattleD][j] = "The Trenk Lord is enchanting!"; j++;
		dialogues[finalBattleD][j] = "Another wave of Mummies is coming!"; j++;
		
		j = 0;
		dialogues[finalBattleE][j] = "The Trenk Lord is weary.."; j++;
		dialogues[finalBattleE][j] = "Find his heart and attack it!"; j++;
		
		j = 0;
		dialogues[endingA][j] = 
				"Congratulations, valiant adventurer! With the Trenk"
				+ "\nLord vanquished and the world's balance restored, "
				+ "\nharmony prevails once more. Yet, our victory today"
				+ "\nis merely a testament to the power of unity and"
				+ "\nresilience."; j++;
				
				
		dialogues[endingA][j] = 
				"As you stand here in this moment, it's crucial to "
				+ "\nunderstand the journey that brought us here. "
				+ "\nYour brave trek through time itself, confronting "
				+ "\nthe Trenk Lord in the future, has reshaped our reality.";j++;
				
				
		dialogues[endingA][j] = 
				"But this victory is not just a singular conquest; "
				+ "\nit's a lesson, a guiding beacon towards a "
				+ "\nsustainable future."; j++;
				
				
		dialogues[endingA][j] = 
				"Remember, the battle against corruption and chaos is "
				+ "\nongoing. It's not just about this triumph; it's "
				+ "\nabout safeguarding the future. The key lies in honoring "
				+ "\nthe Sustainable Development Goals (SDGs)."; j++; 
				
				
		dialogues[endingA][j] = 
				"Each fulfilled goal fortifies the world's defenses, "
				+ "\nshielding it from the looming threat of the Trenk "
				+ "\nLord's resurgence."; j++;
				
				
		dialogues[endingA][j] = 
				"Your actions today have altered fate's course. "
				+ "\nBut, the significance of sustaining these efforts "
				+ "\nextends beyond this moment."; j++;
				
				
		dialogues[endingA][j] = 
				"Only by embracing these goals can we fortify our "
				+ "\nworld against the darkness lurking in the shadows."; j++;
				
				
		dialogues[endingA][j] = 
				"Let this victory inspire a continuous journey "
				+ "\ntowards a future where the legacy of the Trenk Lord remains "
				+ "\nin the past, forever sealed by the collective dedication to the SDGs."; j++; 
				
				
		dialogues[endingA][j] = 
				"Then, until we met again! Hero..!"; j++;
		
		
	}
	

}
