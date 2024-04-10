package Ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameStates.GameState;
import GameStates.Playing;
import Main.Game;
import Utilz.LoadSave;

import static Utilz.Constants.UI.UrmButton.*;

public class LevelCompletedOverlay {

	private Playing playing;
	 private UrmButton menu, next;
	 private BufferedImage img;
	 private int bgX, bgY, bgW, bgH;
	
	public LevelCompletedOverlay(Playing playing) {
		this.playing = playing;
		initImg();
		initButton();
		
	}

	private void initButton() {
		int menuX = (int)( Game.GAME_WIDTH / 2 - 100);
		int nextX = (int)( Game.GAME_WIDTH / 2 + 20);
		int y = (int)(380 * Game.SCALE);
		menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
		next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
		
	}

	private void initImg() {
		img = LoadSave.GetSpriteAtlas(LoadSave.COMPLETED);
		bgW = (int)(400 * Game.SCALE);
		bgH = (int)(600 * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 8*5;
		bgY = (int)(0 * Game.SCALE);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
		g.drawImage(img, bgX, bgY, bgW, bgH, null);	

		menu.draw(g);
		next.draw(g);
	}
	
	public void update() {
		next.update();
		menu.update();
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		if (isIn(e, menu))
			menu.setMousePressed(true);
		else if (isIn(e, next))
			next.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, menu)) {
			if (menu.isMousePressed());
				GameState.state = GameState.MENU;

		} else if (isIn(e, next)) 
			if (next.isMousePressed()) 
				playing.loadNextLevel();
		
		menu.resetBools();
		next.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		menu.setMouseOver(false);
		next.setMouseOver(false);
		
		if (isIn(e, menu))
			menu.setMouseOver(true);
		else if (isIn(e, next))
			next.setMouseOver(true);
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}

