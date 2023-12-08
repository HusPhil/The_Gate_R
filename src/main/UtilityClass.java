package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityClass {
	
	public BufferedImage scaleImage(int width, int height, BufferedImage image) {
		BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		
		g2.drawImage(image, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
}
