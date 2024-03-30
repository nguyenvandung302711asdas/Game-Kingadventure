package Ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameStates.GameState;
import GameStates.Playing;
import Main.Game;
import Utilz.LoadSave;

import static Utilz.Constants.UI.PauseButtons.*;
import static Utilz.Constants.UI.UrmButton.*;


public class PauseOverlay {

	private Playing playing;
	
	private BufferedImage backgroundImg;
	
	private int bgX, bgY, bgW, bgH;
	private SoundButton musicButton, sfxButton;
	private UrmButton unpauseB, replayB, menuB;
	
	public PauseOverlay(Playing playing){
		this.playing = playing;
		loadBackground();
		createSoundButton();
		createUrmButton();
		
	}
	
	private void createUrmButton() {
		int urmY = (int)(200 * Game.SCALE);
		int unpauseX = (int)(203 * Game.SCALE);
		int replayX = (int)(243 * Game.SCALE);
		int menuX = (int)(282 * Game.SCALE);
		unpauseB = new UrmButton(unpauseX, urmY, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, 0);
		replayB = new UrmButton(replayX, urmY, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, 1);
		menuB = new UrmButton(menuX, urmY, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, 2);
	}

	private void createSoundButton() {
		int soundX = (int)(275 * Game.SCALE);
		int musicY = (int)(122 * Game.SCALE);
		int sfxY = (int)(145 * Game.SCALE);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);

	}
	
	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.Pause_Background);
		bgW = (int) (backgroundImg.getWidth() );
		bgH = (int) (backgroundImg.getHeight() );
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (Game.GAME_WIDTH /4 - bgW / 2);
	}
	
	public void update() {
		musicButton.update();
		sfxButton.update();
		
		unpauseB.update();
		replayB.update();
		menuB.update();
	}

	public void draw(Graphics g) {
		g.drawImage(backgroundImg, bgX, bgY, null);	

		musicButton.draw(g);
		sfxButton.draw(g);
		
		unpauseB.draw(g);
		replayB.draw(g);
		menuB.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {

	}
	
	public void mousePressed(MouseEvent e) {
		if (isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
		
		else if (isIn(e, menuB))
			menuB.setMousePressed(true);
		else if (isIn(e, replayB))
			replayB.setMousePressed(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, musicButton)) {
			if (musicButton.isMousePressed())
				musicButton.setMuted(!musicButton.isMuted());
		} else if (isIn(e, sfxButton)) {
			if (sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());
			
		} else if (isIn(e, menuB)) {
			if (menuB.isMousePressed())
				GameState.state = GameState.MENU;
		} else if (isIn(e, replayB)) {
			if (replayB.isMousePressed())
				System.out.println("replayy");
		} else if (isIn(e, unpauseB)) {
			if (unpauseB.isMousePressed())
				playing.unpauseGame();
		}
		
		musicButton.resetBools();
		sfxButton.resetBools();
		
		unpauseB.resetBools();
		replayB.resetBools();
		menuB.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		
		unpauseB.setMouseOver(false);
		replayB.setMouseOver(false);
		menuB.setMouseOver(false);
		//
		if (isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMouseOver(true);
		
		else if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, menuB))
			menuB.setMouseOver(true);
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
