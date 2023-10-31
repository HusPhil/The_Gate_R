package entity;

import main.GamePanel;

public class Narrator extends Entity{
	public String player_name;

	public Narrator(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		setDialogue();
	}
	
	public void setDialogue() {
		int i = 0;
		int j = 0;
		dialogues[i][j] = "In the annals of time, fate weaves its intricate tapestry, "
				+ "\nguided by the hands of heroes known and unknown."; j++;
		dialogues[i][j] = "Now, " + player_name + ", you stand at the precipice of a "
				+ "\nfractured world, a testament to humanity's forgotten "
				+ "\nvows."; j++;
		dialogues[i][j] = "As the tendrils of an ancient relic thrust you into the "
				+ "\nheart of a desolate future Earth, the echoes of an "
				+ "\nage-old plea resonate within your being."; j++;
		dialogues[i][j] = "Your quest is clear: resurrect the fading embers of the "
				+ "\nSustainable Development Goals, rekindle hope, and "
				+ "\ncarve a new legacy upon the barren canvas of time."; j++;
		dialogues[i][j] = "Heed the call, " + player_name + ", and weave a new "
				+ "chapter \nin the timeless saga of Earth's redemption."; j++;
		
		
		i++; j = 0;
		dialogues[i][j] = "You are heavily burdened, you cannot \ncarry anymore."; j++;
		
		i++; j = 0;
		dialogues[i][j] = "Press 'Y' to save the game. Esc to cancel"; j++;
		
	}

}
