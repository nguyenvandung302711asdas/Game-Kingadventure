package Objects;

import static Utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import Main.Game;

public class GameObject {

	private static final int ANI_SPEED = 25;
	protected int x, y, objType;
	protected Rectangle2D.Float hitbox;
	protected boolean doAnimation, active = true;
	protected int aniTick, aniIndex;
	protected int xDrawOffset, yDrawOffset;

	public GameObject(int x, int y, int objType) {
		this.x = x;
		this.y = y;
		this.objType = objType;
	}


	protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= ANI_SPEED) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(objType)) {
				aniIndex = 0;
				if (objType == BOX) {
					doAnimation = false;
					active = false;
				} else if (objType == CANNON_LEFT || objType == CANNON_RIGHT)
					doAnimation = false;
			}
		}
	}

	public void reset() {
		aniIndex = 0;
		aniTick = 0;
		active = true;

		if (objType == BOX || objType == CANNON_LEFT || objType == CANNON_RIGHT)
			doAnimation = false;
		else {
			doAnimation = false;
			active = false;
		}
	}

	protected void initHitbox(int width, int height) {
		hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
	}

	public void drawHitbox(Graphics g, int xLvlOffset) {
		g.setColor(Color.PINK);
		g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}

	public int getObjType() {
		return objType;
	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAnimation(boolean doAnimation) {
		this.doAnimation = doAnimation;
	}

	public int getXDrawOffset() {
		return xDrawOffset;
	}

	public int getYDrawOffset() {
		return yDrawOffset;
	}

	public int getAniIndex() {	
		return aniIndex;
	}
	public int getAniTick() {
		return aniTick;
	}

}
