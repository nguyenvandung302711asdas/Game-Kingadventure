package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	//vẽ hitbox len man hình
	protected void drawHitbox(Graphics g) {

		g.setColor(Color.PINK);
		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}
	
	//tao hit bắt va chạm
	protected void initHitbox(float x, float y, float f, float g) {
		hitbox = new Rectangle2D.Float(x, y, f, g);
	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

}