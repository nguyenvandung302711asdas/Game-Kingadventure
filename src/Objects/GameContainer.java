package Objects;

import static Utilz.Constants.ObjectConstants.*;

import Main.Game;

public class GameContainer extends GameObject {

	public GameContainer(int x, int y, int objType) {
		super(x, y, objType);
		createHitbox();
	}

	private void createHitbox() {
		if (objType == BOX) {
			initHitbox(29, 19);

			xDrawOffset = (int) (9 * Game.SCALE);
			yDrawOffset = (int) (4 * Game.SCALE);

		} else {
			initHitbox(27, 29);
			xDrawOffset = (int) (8 * Game.SCALE);
			yDrawOffset = (int) (5 * Game.SCALE);
		}

		hitbox.y += yDrawOffset + (int) ( Game.SCALE * 10);
		hitbox.x += xDrawOffset / 2;
	}

	public void update() {
		if (doAnimation)
			updateAnimationTick();
	}

}
