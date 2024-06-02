package Utilz;

import Main.Game;

public class Constants {
	
	public static final float GRAVITY = 0.04f * Game.SCALE;
	public static final int ANI_SPEED = 25;
	
	public static class Projectiles{
		public static final int CANNON_BALL_DEFAULT_WIDTH = 15;//chieu rong
		public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;//chieu cao
		
		public static final int CANNON_BALL_WIDTH = (int)(Game.SCALE * CANNON_BALL_DEFAULT_WIDTH);
		public static final int CANNON_BALL_HEIGHT = (int)(Game.SCALE * CANNON_BALL_DEFAULT_HEIGHT);
		public static final float SPEED = 0.75f * Game.SCALE;
	}
	
	
	public static class ObjectConstants {

		public static final int RED_POTION = 0;
		public static final int BARREL = 2;
		public static final int BOX = 3;
		public static final int SPIKE1 = 4;
		public static final int SPIKE2 = 5;
		public static final int CANNON_LEFT = 6;
		public static final int CANNON_RIGHT = 7;

		
		
		
		public static final int DIAMON = 1;
		
		public static final int CONTAINER_WIDTH_DEFAULT = 30;
		public static final int CONTAINER_HEIGHT_DEFAULT = 25;
		public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

		public static final int POTION_WIDTH_DEFAULT = 18;
		public static final int POTION_HEIGHT_DEFAULT = 14;
		public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
		public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);
		
		public static final int SPIKE_WIDTH_DEFAULT = 32;
		public static final int SPIKE_HEIGHT_DEFAULT = 32;
		public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
		public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);
		
		public static final int CANNON_WIDTH_DEFAULT = 40;
		public static final int CANNON_HEIGHT_DEFAULT = 26;
		public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
		public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);

		public static int GetSpriteAmount(int object_type) {
			switch (object_type) {
			case DIAMON :
				return 7;
			case BOX:
				return 3;	
			case SPIKE1, SPIKE2:
				return 1;
			case CANNON_LEFT, CANNON_RIGHT:
				return 7;
			}
			
			return 1;
		}
	}
	
	public static class UI {
		public static class MenuButtons {
			public static final int MB_WIDTH_DEFAULT = 280;
			public static final int MB_HEIGHT_DEFAULT = 112;
			public static final int MB_WIDTH = (int) (MB_WIDTH_DEFAULT * Game.SCALE);
			public static final int MB_HEIGHT = (int) (MB_HEIGHT_DEFAULT * Game.SCALE);	
		}
		public static class LevelButtons {
			public static final int LB_WIDTH_DEFAULT = 32;
			public static final int LB_HEIGHT_DEFAULT = 32;
			public static final int LB_WIDTH = (int) (LB_WIDTH_DEFAULT * Game.SCALE * 3);
			public static final int LB_HEIGHT = (int) (LB_HEIGHT_DEFAULT * Game.SCALE * 3);	
		}
		
		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);	
		}
		
		public static class UrmButton {
			public static final int URM_SIZE_DEFAULT = 56;
			public static final int URM_SIZE = (int) (URM_SIZE_DEFAULT * Game.SCALE);	
		}
		
		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
		
	}
	
	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int ATTACK = 0;
		public static final int DEAD = 1;
		public static final int DOOR_IN = 2;
		public static final int DOOR_OUT = 3;
		public static final int FALL = 4;
		public static final int GROUND = 5;
		public static final int HIT = 6;
		public static final int IDLE = 7;
		public static final int JUMP = 8;
		public static final int RUN = 9;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case RUN:
				return 8;
			case IDLE:
				return 10;
			case JUMP:
				return 1;
			case ATTACK:
				return 3;
			case GROUND: 
				return 1;
			case DEAD:
				return 3;
			default:
				return 1;
			}
		}
	}
	
	public static class EnemyConstants {
		public static final int PIGGY = 0;
		
		public static final int IDLE = 5;
		public static final int RUNNING = 7;
		public static final int ATTACK = 0;
		public static final int HIT = 4;
		public static final int DEAD = 1;

		public static final int PIGGY_WIDTH_DEFAULT = 34;
		public static final int PIGGY_HEIGHT_DEFAULT = 28;

		public static final int PIGGY_WIDTH = (int) (PIGGY_WIDTH_DEFAULT * Game.SCALE * Game.SCALE);
		public static final int PIGGY_HEIGHT = (int) (PIGGY_HEIGHT_DEFAULT * Game.SCALE * Game.SCALE);
		
		public static final int PIGGY_DRAWOFFSET_X = (int) (10 * Game.SCALE);
		public static final int PIGGY_DRAWOFFSET_Y = (int) (12 * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {
			switch(enemy_type) {
			case PIGGY: 
				switch (enemy_state) {
				case IDLE:
					return 11;
				case RUNNING:
					return 6;
				case ATTACK:
					return 5;
				case HIT:
					return 2;
				case DEAD:
					return 3;
				}
				return 0;
			}
			return 0;
		}
		
		public static int GetMaxHealth(int enemy_type) {
			switch(enemy_type) {
			case PIGGY:
				return 2;
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case PIGGY:
				return 1;
			default:
				return 0;
			}

		}

	}

}
