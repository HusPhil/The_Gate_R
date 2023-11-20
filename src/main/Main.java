package main;
import javax.swing.JFrame;

public class Main {
	
	public static JFrame window;
	public static void main(String args[]) {
		GamePanel gamePanel = new GamePanel();
		window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("SDG Game Eme"); 
		 
		window.add(gamePanel);		
		gamePanel.config.loadConfig();
		if(gamePanel.fullScreen) window.setUndecorated(true);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true); 
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
	
}
