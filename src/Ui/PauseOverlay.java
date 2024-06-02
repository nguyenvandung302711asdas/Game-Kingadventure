package Ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameStates.GameState;
import GameStates.Playing;
import Main.Game;
import Utilz.LoadSave;

import static Utilz.Constants.UI.UrmButton.*;


public class PauseOverlay {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	private UrmButton unpauseB, replayB, menuB;
	private AudioOptions audioOptions;
	
	
	public PauseOverlay(Playing playing){
		this.playing = playing;
		loadBackground();
		audioOptions = playing.getGame().getAudioOptions();
		createUrmButton();	
		
	}
	
	private void createUrmButton() {
		int urmY = (int)(385 * Game.SCALE);
		int unpauseX = (int)(380 * Game.SCALE);
		int replayX = (int)(450 * Game.SCALE);
		int menuX = (int)(520 * Game.SCALE);
		unpauseB = new UrmButton(unpauseX, urmY, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, 0);
		replayB = new UrmButton(replayX, urmY, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, 1);
		menuB = new UrmButton(menuX, urmY, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, 2);
	}

	
	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.Pause_Background);
		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = Game.GAME_HEIGHT / 2 - bgH / 2;
	}
	
	public void update() {
		unpauseB.update();
		replayB.update();
		menuB.update();
		
		audioOptions.update();
	}

	public void draw(Graphics g) {
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
		
		unpauseB.draw(g);
		replayB.draw(g);
		menuB.draw(g);
		
		audioOptions.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		audioOptions.mouseDragged(e);
	}
	
	public void mousePressed(MouseEvent e) {
		if (isIn(e, menuB))
			menuB.setMousePressed(true);
		else if (isIn(e, replayB))
			replayB.setMousePressed(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
		else
			audioOptions.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		 if (isIn(e, menuB)) {
			if (menuB.isMousePressed()) {
				playing.setGamestate(GameState.MENU);
				playing.unpauseGame();
			}
		}  else if (isIn(e, replayB)) {
			if (replayB.isMousePressed()) {
				Playing.score = playing.getCurrentScore();
				playing.resetAll();
				playing.unpauseGame();
			}
		} else if (isIn(e, unpauseB)) {
			if (unpauseB.isMousePressed())
				playing.unpauseGame();
		} else
			audioOptions.mouseReleased(e);

		unpauseB.resetBools();
		replayB.resetBools();
		menuB.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		
		unpauseB.setMouseOver(false);
		replayB.setMouseOver(false);
		menuB.setMouseOver(false);
		//
		if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else
			audioOptions.mouseMoved(e);
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
