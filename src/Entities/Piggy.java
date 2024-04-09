package Entities;

import static Utilz.Constants.EnemyConstants.*;
import static Utilz.Constants.Directions.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Main.Game;

public class Piggy extends Enemy{

	//Attack Box
	private Rectangle2D.Float attackBox;
	
	public Piggy(float x, float y) {
		super(x, y, PIGGY_WIDTH, PIGGY_HEIGHT, PIGGY);
		initHitbox(x, y ,(int)(28 * Game.SCALE),(int)(27 * Game.SCALE));
		initAttackBox();
	}
	
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (12 * Game.SCALE), (int) (28 * Game.SCALE));
	}

	public static ArrayList<Piggy> GetPigs(){
		ArrayList<Piggy> list = new ArrayList<>();
		list.add(new Piggy(24 * Game.TILES_SIZE, 4* Game.TILES_SIZE));
		list.add(new Piggy(9 * Game.TILES_SIZE, 5* Game.TILES_SIZE));
		list.add(new Piggy(7 * Game.TILES_SIZE, 12* Game.TILES_SIZE));

		return list;
	}
	
	public void update(int[][] level, Player player) {
		updateMove(level, player);
		updateAnimationTick();
		updateAttackBox();
	}
	
	private void updateAttackBox() {
		if (walkDir == RIGHT)
			attackBox.x = hitbox.x + hitbox.width;
		else 
			attackBox.x = hitbox.x - attackBox.width;
		attackBox.y = hitbox.y;
	}
	
	public void drawAttackBox(Graphics g) {
		g.setColor(Color.red);
		g.drawRect((int) (attackBox.x), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
	}
	
	private void updateMove(int[][] level, Player player) {
		firstUpdateCheck(level);
		
		if (inAir)
			updateInAir(level); 
		else 
			switch (enemyState) {
			case IDLE:
				newState(RUNNING);
				break;
			case RUNNING:
				if (canSeePlayer(level, player)) 
					turnTowardsPlayer(player);
				if (isPlayerCloseForAttack(player))
					if(canSeePlayer(level, player))
						newState(ATTACK);

				move(level);
				break;
			case ATTACK:
				if (aniIndex == 0)
					attackChecked = false;
				if (aniIndex == 3 && !attackChecked)
					checkPlayerHit(attackBox, player);

				break;
		}
	}
	
	public int flipX() {
		if (walkDir == RIGHT)
			return (int)(width * 1.3);
		else
			return - 5;
	}

	public int flipW() {
		if (walkDir == RIGHT)
			return -1;
		else
			return 1;

	}
}