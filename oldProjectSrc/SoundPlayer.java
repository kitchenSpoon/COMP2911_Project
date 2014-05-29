import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound player used for sound playing.
 * @author User_Lian
 *
 */
public class SoundPlayer {
	private static boolean hasSound = true;
	
	/**
	 * Generic sound playing function
	 * @param filepath File to be played
	 */
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
	
	/**
	 * Play Coin sound
	 */
	public static synchronized void playCoinSound() {
		playSound("./sound/smb_coin.wav");
	}
	
	/**
	 * Play win music
	 */
	public static synchronized void playWinSound() {
		playSound("./sound/smb_stage_clear.wav");
	}
	
	/**
	 * Play Background music
	 */
	public static synchronized void playBGSound() {
		playSound("./sound/wild_pokemon_battle.mid");
	}
	
	/**
	 * Return if we have disable sound
	 * @return
	 */
	public static boolean getHasSound(){
		return hasSound;
	}
	
	/**
	 * Disable/enable sound
	 */
	public static void setHasSound(boolean _hasSound){
		hasSound = _hasSound;
	}
	
	/**
	 * Switch sound on/off
	 */
	public static void toggleSound(){
		if(hasSound){
			hasSound = false;
		} else {
			hasSound = true;
		}
	}
}
