package object;

import entity.Entity;
import main.GamePanel;

public class ITM_Coin extends Entity{
	GamePanel gp;
	public static final String objName = "Coin";
	public ITM_Coin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = non_inventory;
		name = objName;
		stackable = true;
		down1 = createImage("objects", "coin22");
		plus = 1;	 
		dropChance = 100;
	}
	public void use(Entity user) {
		user.coin += plus;
		gp.gui.addMessage("Coin+ " + plus);
	}
}
