import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundPlayer {
	private static boolean hasSound;
	public static synchronized void playSound(String filepath) {
		if(!hasSound) return;
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
	
	public boolean getHasSound(){
		return hasSound;
	}
	
	public static void setHasSound(boolean _hasSound){
		hasSound = _hasSound;
	}
	
	public static void toggleSound(){
		if(hasSound){
			hasSound = false;
		} else {
			hasSound = true;
		}
	}
}
