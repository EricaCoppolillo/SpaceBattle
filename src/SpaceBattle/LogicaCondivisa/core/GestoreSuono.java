package SpaceBattle.LogicaCondivisa.core;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GestoreSuono {

	private AudioInputStream sottofondo;
	private AudioInputStream risorse;
	private AudioInputStream sparo;
	private AudioInputStream esplosione;
	
	private Clip clipB;
	private Clip clipI;
	
	private boolean fermaSparo;
	private boolean fermaEsplosione;
    
	public GestoreSuono()
	{
		try {
			sottofondo = AudioSystem.getAudioInputStream(new File("filewav" + File.separator + "soundBackground.wav"));
			risorse =  AudioSystem.getAudioInputStream(new File("filewav" + File.separator + "soundIcon.wav"));
			sparo = AudioSystem.getAudioInputStream(new File("filewav" + File.separator + "soundLaser.wav"));

			clipB = AudioSystem.getClip();
			clipI = AudioSystem.getClip();
			
			clipB.open(sottofondo);
			clipI.open(risorse);

			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		fermaSparo = false;
		fermaEsplosione = false;
	}
	
	public void avviaSottofondo() {
		if(clipB != null) {
			clipB.loop(Clip.LOOP_CONTINUOUSLY);
			clipB.start();			
		}
	}
	
	public void fermaSottofondo() {
		if(clipB != null) {
			clipB.stop();			
		}
	}
	
	public void avviaRisorse() {
		if(clipI != null) {
			if(clipI.getFramePosition() == clipI.getFrameLength())
				clipI.setFramePosition(0);
			clipI.start();			
		}
	}
	
	public void avviaSparo() 
	{
		if(!fermaSparo) 
		{
			try {
				Clip clipS = AudioSystem.getClip();
				sparo = AudioSystem.getAudioInputStream(new File("filewav" + File.separator + "soundLaser.wav"));
				clipS.open(sparo);
				clipS.start(); 
			} catch (Exception e) {
	    	  e.printStackTrace();
			}
		}
    }

	
	public void fermaSparo()
	{
		fermaSparo = true;
	}
	
	public void avviaEsplosione()
	{
		if(!fermaEsplosione)
		{
			try {
				Clip clip = AudioSystem.getClip();
		        esplosione = AudioSystem.getAudioInputStream(new File("filewav" + File.separator + "soundExplosion.wav"));
		        clip.open(esplosione);
		        if(clip!=null)
		        {
		        	if(clip.getFramePosition() == clip.getFrameLength())
		        		clip.setFramePosition(0);
		        	clip.start();
		        }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fermaEsplosione()
	{
		fermaEsplosione = true;
	}
}
