package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	GamePanel gp;
	public Config(GamePanel gp) {
		this.gp = gp;
	}
	
	public void saveConfig() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			
			//Full Screen config
			if(gp.fullScreen) bw.write("on");
			if(!gp.fullScreen) bw.write("off");
			bw.newLine();
			
			//Volume
			bw.write(String.valueOf(gp.soundM.volumeScale)); bw.newLine();//MUSIC
			
			bw.write(String.valueOf(gp.soundSE.volumeScale)); bw.newLine();//SOUND EFFECTS
			
			//Close the buffered writer
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			String t = br.readLine();
			 
			//reading full screen config
			if(t.equals("on")) gp.fullScreen = true;
			if(t.equals("off")) gp.fullScreen = !true;
			
			
			//Reading vloume config
			t = br.readLine();//Music
			gp.soundM.volumeScale = Integer.parseInt(t);
			
			t = br.readLine();//Sound effects
			gp.soundSE.volumeScale = Integer.parseInt(t);
			
			//close the reader
			br.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
}
