package Ui;

import Main.Game;

import static Utilz.Constants.UI.PauseButtons.SOUND_SIZE_DEFAULT;
import static Utilz.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static Utilz.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class AudioOptions {

	private SoundButton musicButton, sfxButton;
	private VolumeButton volumeButton;
	private Game game;
	
	public AudioOptions(Game game) {
		this.game=game;
		createSoundButton();
		createVolumeButton();
	}

	private void createVolumeButton() {
		int vX = (int) (368 * Game.SCALE);
		int vY = (int) (345 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}

	
	private void createSoundButton() {
		int soundX = (int)(515 * Game.SCALE);
		int musicY = (int)(210 * Game.SCALE);
		int sfxY = (int)(255 * Game.SCALE);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);

	}
	
	public void update() {
		musicButton.update();
		sfxButton.update();
		
		volumeButton.update();
	}
	
	public void draw(Graphics g) {
		musicButton.draw(g);
		sfxButton.draw(g); 
		
		volumeButton.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (volumeButton.isMousePressed()) {
			float valueBefore = volumeButton.getFloatValue();
			volumeButton.changeX(e.getX());
			float valueAfter = volumeButton.getFloatValue();
			if(valueBefore != valueAfter)
				game.getAudioPlayer().setVolume(valueAfter);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if (isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, musicButton)) {
			if (musicButton.isMousePressed())
				musicButton.setMuted(!musicButton.isMuted());
			game.getAudioPlayer().toggleSongMute();
		} else if (isIn(e, sfxButton)) {
			if (sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());
			game.getAudioPlayer().toggleEffectMute();
		}
		musicButton.resetBools();
		sfxButton.resetBools();
		
		volumeButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		volumeButton.setMouseOver(false);

		if (isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMouseOver(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMouseOver(true);
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}