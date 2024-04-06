package Utilz;

import Main.Game;

public class Constants {
	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 280;
			public static final int B_HEIGHT_DEFAULT = 112;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);	
		}
		
		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);	
		}
		
		public static class UrmButton {
			public static final int URM_SIZE_DEFAULT = 56;
			public static final int URM_SIZE = (int) (URM_SIZE_DEFAULT * Game.SCALE);	
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
			default:
				return 1;
			}
		}
	}
	
	public static class EnemyConstants {
		public static final int PIG = 0;
		
		public static final int IDLE = 5;
		public static final int RUNNING = 7;
		public static final int ATTACK = 0;
		public static final int HIT = 4;
		public static final int DEAD = 1;

		public static final int PIG_WIDTH_DEFAULT = 32;
		public static final int PIG_HEIGHT_DEFAULT = 32;

		public static final int PIG_WIDTH = (int) (PIG_WIDTH_DEFAULT * Game.SCALE);
		public static final int PIG_HEIGHT = (int) (PIG_HEIGHT_DEFAULT * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {
			switch(enemy_type) {
			case PIG: 
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
					return 4;
				}
				return 0;
			}
			return 0;
		}

	}

}
