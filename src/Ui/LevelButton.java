package Ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameStates.GameState;
import Utilz.LoadSave;
import static Utilz.Constants.UI.LevelButtons.*;

public class LevelButton {
	private int xPos, yPos, rowIndex, index;
	public GameState state;
	private int level;
	private BufferedImage img;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;

	public LevelButton(int xPos, int yPos, int rowIndex, int level) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = GameState.PLAYING;
		this.level = level;
		loadImgs();
		initBounds();
	}

	private void initBounds() {
		bounds = new Rectangle(xPos, yPos, LB_WIDTH / 2, LB_HEIGHT / 2);	
		
	}

	private void loadImgs() {
		img = LoadSave.GetSpriteAtlas(LoadSave.Level_Button);
	}

	public void draw(Graphics g) {
		g.drawImage(img, xPos, yPos, LB_WIDTH /2 , LB_HEIGHT / 2, null);
	}

	public void update() {
		index = 0;
//		if (mouseOver)
//			index = 1;
//		if (mousePressed)
//			index = 2;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void applyGamestate() {
		GameState.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	public int getLevel() {
		return level;
	}

}