package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class LigthingEffect {
	GamePanel gp; 
	BufferedImage darknessFilter;
	
	int timeCounter = 0;
	float filterAlpha = 0f;
	
	final int day = 0;
	final int dusk = 1;
	final int night = 2;
	final int dawn = 3;
	
	int timeState = day;
	
	public LigthingEffect(GamePanel gp) {
		this.gp = gp;
		setLightSource();
		
	}
	public void setLightSource() {
		//create a buffered i,age where we will draw the filter
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();
		
		if(gp.player.currentLightItem == null) {
			g2.setColor( new Color( 0.08f,0,0.13f, 0.90f));
		}
		else {
			//Get the centerX and the center Y of the light circle
			int centerX = gp.player.screenX + (gp.tileSize/2);
			int centerY = gp.player.screenY + (gp.tileSize/2);
			
			//Create a gradient effect
			Color c[] = new Color[12];
			float fraction[] = new float[12];
			
			int i = 0; int j = 0;
			
			c[i] = new Color( 0.08f,0,0.13f,0.1f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.42f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.52f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.61f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.69f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.76f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.82f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.87f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.91f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.92f); i++;
			c[i] = new Color( 0.08f,0,0.13f,0.93f); i++; 
			c[i] = new Color( 0.08f,0,0.13f,0.93f); i++;
		
			fraction[j] = 0f; j++;
			fraction[j] = 0.4f; j++;
			fraction[j] = 0.5f; j++;
			fraction[j] = 0.6f; j++;
			fraction[j] = 0.65f; j++;
			fraction[j] = 0.7f; j++;
			fraction[j] = 0.75f; j++;
			fraction[j] = 0.8f; j++;
			fraction[j] = 0.85f; j++;
			fraction[j] = 0.9f; j++;
			fraction[j] = 0.95f; j++; 
			fraction[j] = 1f; j++;
			
			//Create gradient paint settings
			RadialGradientPaint gradient = new RadialGradientPaint(centerX, centerY, gp.player.currentLightItem.lightRadius, fraction, c);
			//set the gradient data on g2
			g2.setPaint(gradient);
		}
		
		g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
		g2.dispose();
	}
	public void resetDay() {
		timeState = day;
		filterAlpha = 0f;
	}
	
	public void update() {
		if(gp.player.lightUpdated) {
			setLightSource();
			gp.player.lightUpdated = false;
		}
		//Changing the state of time
		if(timeState == day) {
			timeCounter++;
			if(timeCounter > 3000) {
				timeState = dusk;
				timeCounter = 0;
			}
		}
		if(timeState == dusk) {
			filterAlpha += 0.001f;
			if(filterAlpha > 1f) {
				filterAlpha = 1f;
				timeState = night;
			}
		}
		if(timeState == night) {
			timeCounter++;
			if(timeCounter > 1200) {
				timeState = dawn;
				timeCounter = 0;
			}
		}
		if(timeState == dawn) {
			filterAlpha -= 0.001f;
			if(filterAlpha < 0f) {
				filterAlpha = 0f;
				timeState = day;
			}
		}
	} 
	public void draw(Graphics2D g2) {
		if(gp.currentArea == gp.outside) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha)); 
		}
		if(gp.currentArea == gp.outside || gp.currentArea == gp.dungeon) {
			g2.drawImage(darknessFilter, 0, 0, null);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); 
	}
}
