package Entities;

import static Utilz.Constants.Directions.LEFT;
import static Utilz.Constants.EnemyConstants.*;
import static Utilz.HelpMethods.CanMoveHere;
import static Utilz.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static Utilz.HelpMethods.IsEntityOnFloor;
import static Utilz.HelpMethods.IsFloor;

import java.util.ArrayList;

import Main.Game;

public class Piggy extends Enemy{

	public Piggy(float x, float y) {
		super(x, y, 38, 28, PIG);
		initHitbox(x, y,(int)(38 * Game.SCALE),(int)(28 * Game.SCALE) - 2);
	}
	
	public static ArrayList<Piggy> GetPigs(){
		ArrayList<Piggy> list = new ArrayList<>();
		list.add(new Piggy(24 * Game.TILES_SIZE, 4* Game.TILES_SIZE));
		return list;
	}
	
	public void update(int[][] level, Player player) {
		updateMove(level, player);
		updateAnimationTick();
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
				if (isPlayerCloseForAttack(player) && canSeePlayer(level, player))
					newState(ATTACK);

				move(level);
				break;
		}
	}
}
