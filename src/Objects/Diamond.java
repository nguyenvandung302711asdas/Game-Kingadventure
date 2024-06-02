package Objects;
import Main.Game;

public class Diamond extends GameObject {

	private float hoverOffset;
	private int maxHoverOffset, hoverDir = 1;

	public Diamond(int x, int y, int objType) {
		super(x, y, objType);
		doAnimation = true;

		initHitbox(14, 10);

		xDrawOffset = (int) (4 * Game.SCALE);
		yDrawOffset = (int) (2 * Game.SCALE);

		maxHoverOffset = (int) (10 * Game.SCALE);

		hitbox.y += yDrawOffset + (int) (Game.SCALE * 7);
		hitbox.x += xDrawOffset - 10;
	}

	public void update() {
		updateAnimationTick();
		updateHover();
	}

	private void updateHover() {
		hoverOffset += (0.075f * Game.SCALE * hoverDir);

		if (hoverOffset >= maxHoverOffset)
			hoverDir = -1;
		else if (hoverOffset < 0)
			hoverDir = 1;

		hitbox.y = y + hoverOffset;
	}
}
