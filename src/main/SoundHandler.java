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
	public static final int intro = 8;
	public static final int story = 9;
	public static final int boss = 10;
	public static final int death = 11;
	public static final int finale = 11;
	public static final int ending = 11;
	
	public SoundHandler() {
		soundURL[0] = getClass().getResource("/sounds/coin.wav"); 
		soundURL[1] = getClass().getResource("/sounds/hitmonster.wav"); 
		soundURL[2] = getClass().getResource("/sounds/burning.wav"); 
		soundURL[story] = getClass().getResource("/sounds/story.wav");
		soundURL[4] = getClass().getResource("/sounds/cuttree.wav");
		soundURL[5] = getClass().getResource("/sounds/cursor.wav");
		soundURL[6] = getClass().getResource("/sounds/chipwall.wav");
		soundURL[7] = getClass().getResource("/sounds/dooropen.wav");
		soundURL[intro] = getClass().getResource("/sounds/intro.wav");
		soundURL[boss] = getClass().getResource("/sounds/boss.wav");
		soundURL[death] = getClass().getResource("/sounds/death.wav");
		soundURL[finale] = getClass().getResource("/sounds/finalbattle.wav");
		soundURL[ending] = getClass().getResource("/sounds/ending.wav");
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
