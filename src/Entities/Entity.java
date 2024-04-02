package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import Main.Game;

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

	protected void drawHitbox(Graphics g) {

		g.setColor(Color.PINK);
		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
//		g.drawRect((int) Game.TILES_SIZE * 7 + 9, (int) Game.TILES_SIZE * 7, (int) 172, (int) 15);

	}

	protected void initHitbox(float x, float y, float f, float g) {
		hitbox = new Rectangle2D.Float(x, y, f, g);
	}

//	protected void updateHitbox() {
//		hitbox.x = (int) x;
//		hitbox.y = (int) y;
//	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

}