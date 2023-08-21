package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundHandler {
	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
	public int volumeScale = 3;
	float volume = 0;
	
	public SoundHandler() {
		soundURL[0] = getClass().getResource("/sounds/coin.wav"); 
		soundURL[1] = getClass().getResource("/sounds/hitmonster.wav"); 
		soundURL[2] = getClass().getResource("/sounds/burning.wav"); 
		soundURL[3] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
		soundURL[4] = getClass().getResource("/sounds/cuttree.wav");
		soundURL[5] = getClass().getResource("/sounds/cursor.wav");
	}
	public void setAudioFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			controlVolume();
		} catch (Exception e) {}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop(); 
	}
	public void controlVolume() {
		
		switch(volumeScale){
			case 0: volume = -80f; break;
			case 1: volume = -25f; break;
			case 2: volume = -12f; break;
			case 3: volume = -5f; break;
			case 4: volume = 1f; break;
			case 5: volume = 6f; break;
		}
		fc.setValue(volume);
	}
}
