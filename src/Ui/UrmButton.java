package Ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utilz.LoadSave;
import static Utilz.Constants.UI.UrmButton.*;

public class UrmButton extends PauseButton {
	
	private boolean mouseOver, mousePressed;
	private BufferedImage[] Imgs;
	int rowIndex, index;
	
	public UrmButton(int x, int y, int Width, int Height, int rowIndex) {
		super(x, y, Width, Height);
		this.rowIndex = rowIndex;
		loadUrmImgs();
	}
	
	private void loadUrmImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_Button);
		Imgs = new BufferedImage[3];
		for(int i = 0; i < Imgs.length; i++) {
			Imgs[i] = temp.getSubimage(i * URM_SIZE_DEFAULT, rowIndex * URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT); 
		}
	}

	public void update() {
		index = 0;
		if(mouseOver)
			index = 1;
		if(mousePressed)
			index = 2;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Imgs[index], x, y, Width, Height, null);
	}
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
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
	
}