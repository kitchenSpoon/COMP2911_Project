import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundPlayer {
	public static synchronized void playSound(String filepath) {
		try {
			Clip clip = AudioSystem.getClip();
			File soundFile = new File(filepath);
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			clip.open(inputStream);
			clip.start(); 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static synchronized void playCoinSound() {
		playSound("./sound/smb_coin.wav");
	}
	
	public static synchronized void playWinSound() {
		playSound("./sound/smb_stage_clear.wav");
	}
	
	public static synchronized void playBGSound() {
		playSound("./sound/wild_pokemon_battle.mid");
	}
}
