package Ui;

import java.awt.Rectangle;

public class PauseButton {

	protected int x, y, Width, Height;
	protected Rectangle Bounds;
	
	public PauseButton(int x, int y, int Width, int Height) {
		this.x = x;
		this.y = y;
		this.Width = Width;
		this.Height = Width;
		createBounds();
	}

	private void createBounds() {
		Bounds = new Rectangle(x, y, Width, Height);
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public Rectangle getBounds() {
		return Bounds;
	}

	public void setBounds(Rectangle bounds) {
		Bounds = bounds;
	}
	
}
