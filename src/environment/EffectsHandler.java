package environment;

import java.awt.Graphics2D;

import main.GamePanel;

public class EffectsHandler {
	GamePanel gp;
	public LigthingEffect lighting;
	
	public EffectsHandler(GamePanel gp) {
		this.gp = gp;
		
		lighting = new LigthingEffect(gp);
	}
	public void setUp() {
		
	}
	public void update() {
		lighting.update();
	}
	public void draw(Graphics2D g2) {
		lighting.draw(g2);
	}
}
