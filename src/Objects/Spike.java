package Objects;
public class Spike extends GameObject {

	public Spike(int x, int y, int objType) {
		super(x, y, objType);
		
		initHitbox(32, 32);
		xDrawOffset = 0;
		yDrawOffset = 3;
		hitbox.y += yDrawOffset;	
	}

}
