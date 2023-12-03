package main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	
	public static JFrame window;
	public static void main(String args[]) {
		GamePanel gamePanel = new GamePanel();
		window = new JFrame();
		
		ImageIcon icon = new ImageIcon("objects/items/map.png"); 

        // Set the icon image for the JFrame
        window.setIconImage(icon.getImage());
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Harmonial Principles"); 
		new Main().setIcon();
		window.add(gamePanel);		
		gamePanel.config.loadConfig();
		if(gamePanel.fullScreen) window.setUndecorated(true);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true); 
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
	public void setIcon(){

	    ImageIcon icon =  new ImageIcon(getClass().getClassLoader().getResource("objects/items/map.png"));
	    window.setIconImage(icon.getImage());

	}
    
}


