package Utilz;

import Main.Game;

public class HelpMethods {

	public static boolean CanMoveHere(float x, float y, float width, float height, int level) {
		if (!IsSolid(x, y, level))
			if (!IsSolid(x + width, y + height, level))
				if (!IsSolid(x + width, y, level))
					if (!IsSolid(x, y + height, level))
						return true;
		return false;
	}

	private static boolean IsSolid(float x, float y, int level) {
		if (x < Game.TILES_SIZE || x >= Game.GAME_WIDTH - Game.TILES_SIZE)
			return true;
		if (y < Game.TILES_SIZE|| y >= Game.GAME_HEIGHT - Game.TILES_SIZE)
			return true;
	// Map 1
		if(level == 1) { 
			if(x > Game.TILES_SIZE && x < Game.TILES_SIZE * 6 - 9)
				if(y > Game.TILES_SIZE * 10) 
					return true;
			if(x > Game.TILES_SIZE * 10 + 10 && x < Game.TILES_SIZE * 18 - 9)
				if(y > Game.TILES_SIZE * 12) 
					return true;		
			//
			if(x > Game.TILES_SIZE * 7 + 9 && x < Game.TILES_SIZE * 11 - 9)
				if(y > Game.TILES_SIZE * 7 && y < Game.TILES_SIZE * 8 - 33) 
					return true;
			if(x > Game.TILES_SIZE * 15 + 9 && x < Game.TILES_SIZE * 18 - 9)
				if(y > Game.TILES_SIZE * 9 && y < Game.TILES_SIZE * 9 + 15) 
					return true;
			if(x > Game.TILES_SIZE * 18 + 9 && x < Game.TILES_SIZE * 21 - 9)
				if(y > Game.TILES_SIZE * 5 && y < Game.TILES_SIZE * 5 + 15) 
					return true;
			if(x > Game.TILES_SIZE * 12 + 9 && x < Game.TILES_SIZE * 16 - 9)
				if(y > Game.TILES_SIZE * 4 && y < Game.TILES_SIZE * 4 + 15) 
					return true;
			if(x > Game.TILES_SIZE * 21 + 9 && x < Game.TILES_SIZE * 25 - 9)
				if(y > Game.TILES_SIZE * 8 && y < Game.TILES_SIZE * 8 + 5) 
					return true;
			if(x > Game.TILES_SIZE * 24 + 9 && x < Game.TILES_SIZE * 26 - 9)
				if(y > Game.TILES_SIZE * 7 && y < Game.TILES_SIZE * 8 + 5) 
					return true;
		}
		
	// Map 2
				if(level == 2) { 
					///continue
				}
	return false;	
	}		
}