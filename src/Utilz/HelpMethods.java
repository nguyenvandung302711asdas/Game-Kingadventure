package Utilz;

import static Utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Piggy;
import Main.Game;
import Objects.Cannon;
import Objects.Diamond;
import Objects.GameContainer;
import Objects.Projectile;
import Objects.Spike;

public class HelpMethods {

	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!IsSolid(x, y, lvlData))
			if (!IsSolid(x + width, y + height, lvlData))
				if (!IsSolid(x + width, y, lvlData))
					if (!IsSolid(x, y + height, lvlData))
						return true;
		return false;
	}

	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		if (x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGHT)
			return true;
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;

		return IsTileSolid((int) xIndex, (int) yIndex, lvlData);
	}
	
	public static boolean IsProjectileHittingLevel(Projectile p, int[][] lvlData) {
		return IsSolid(p.getHitbox().x + p.getHitbox().width / 2, p.getHitbox().y + p.getHitbox().height / 2, lvlData);

	}

	public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
		int value = lvlData[yTile][xTile];

	if (value >= 136 || value < 0 || value ==22||value==124 || value==123 || value ==106 || value==107||value==125 || value==126 || value ==108 || value==109 )
			return false;
		return true;
	}

	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
		if (xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset - 1;
		} else
			// Left
			return currentTile * Game.TILES_SIZE;
	}
	public static boolean CanCannonSeePlayer(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
		int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
		int secondXTile = (int) (secondHitbox.x / Game.TILES_SIZE);

		if (firstXTile > secondXTile)
			return IsAllTilesClear(secondXTile, firstXTile, yTile, lvlData);
		else
			return IsAllTilesClear(firstXTile, secondXTile, yTile, lvlData);
	}
	public static boolean IsAllTilesClear(int xStart, int xEnd, int y, int[][] lvlData) {
		for (int i = 0; i < xEnd - xStart; i++)
			if (IsTileSolid(xStart + i, y, lvlData))
				return false;
		return true;
	}
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			// Falling - touching floor
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		} else
			// Jumping
			return currentTile * Game.TILES_SIZE;

	}

	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
				return false;

		return true;

	}

	public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
		if (xSpeed > 0)
			return IsSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
		else
			return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
	}

	public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
		for (int i = 0; i < xEnd - xStart; i++) {
			if (IsTileSolid(xStart + i, y, lvlData))
				return false;
			if (!IsTileSolid(xStart + i, y + 1, lvlData))
				return false;
		}

		return true;
	}

	public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox,
			int yTile) {
		int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
		int secondXTile = (int) (secondHitbox.x / Game.TILES_SIZE);

		if (firstXTile > secondXTile)
			return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
		else
			return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);
	}

	public static boolean IsSightClear2(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox,
			int yTile) {
		int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
		int secondXTile = (int) ((secondHitbox.x + secondHitbox.width) / Game.TILES_SIZE);

		if (firstXTile > secondXTile)
			return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
		else
			return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);
	}

	public static int[][] GetLevelData(BufferedImage img) {
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 136)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;
	}

	public static ArrayList<Piggy> GetPigs(int lvlIndex) {
		ArrayList<Piggy> list = new ArrayList<>();
		switch (lvlIndex) {
		case 0:
			
			list.add(new Piggy(23 * Game.TILES_SIZE, 4 * Game.TILES_SIZE));
			list.add(new Piggy(12 * Game.TILES_SIZE, 10 * Game.TILES_SIZE));
			list.add(new Piggy(9 * Game.TILES_SIZE, 10 * Game.TILES_SIZE));
			break;
		case 1:

			list.add(new Piggy(8 * Game.TILES_SIZE, 8 * Game.TILES_SIZE));
			list.add(new Piggy(13 * Game.TILES_SIZE, 8 * Game.TILES_SIZE));
			list.add(new Piggy(20 * Game.TILES_SIZE, 4 * Game.TILES_SIZE));
			list.add(new Piggy(22 * Game.TILES_SIZE, 12 * Game.TILES_SIZE));
			list.add(new Piggy(21 * Game.TILES_SIZE, 9 * Game.TILES_SIZE));
			list.add(new Piggy(10 * Game.TILES_SIZE, 14 * Game.TILES_SIZE));
			list.add(new Piggy(2 * Game.TILES_SIZE, 5 * Game.TILES_SIZE));
			break;
		case 2:
			
			list.add(new Piggy(8 * Game.TILES_SIZE, 8 * Game.TILES_SIZE));
			list.add(new Piggy(13 * Game.TILES_SIZE, 8 * Game.TILES_SIZE));
			list.add(new Piggy(20 * Game.TILES_SIZE, 4 * Game.TILES_SIZE));
			list.add(new Piggy(22 * Game.TILES_SIZE, 12 * Game.TILES_SIZE));
			list.add(new Piggy(24 * Game.TILES_SIZE, 12 * Game.TILES_SIZE));
			list.add(new Piggy(7 * Game.TILES_SIZE, 14 * Game.TILES_SIZE));
			list.add(new Piggy(12 * Game.TILES_SIZE, 14 * Game.TILES_SIZE));
			break;
		case 3:

			list.add(new Piggy(9 * Game.TILES_SIZE, 6 * Game.TILES_SIZE));
			list.add(new Piggy(8 * Game.TILES_SIZE, 8 * Game.TILES_SIZE));
			list.add(new Piggy(3 * Game.TILES_SIZE, 8 * Game.TILES_SIZE));
			list.add(new Piggy(12 * Game.TILES_SIZE, 9 * Game.TILES_SIZE));
			list.add(new Piggy(24 * Game.TILES_SIZE, 14 * Game.TILES_SIZE));
			list.add(new Piggy(26 * Game.TILES_SIZE, 14 * Game.TILES_SIZE));
			list.add(new Piggy (18 * Game.TILES_SIZE, 4 * Game.TILES_SIZE));
			list.add(new Piggy (20 * Game.TILES_SIZE, 4 * Game.TILES_SIZE));
			break;
		case 4:
			list.add(new Piggy(15 * Game.TILES_SIZE, 6 * Game.TILES_SIZE));
			list.add(new Piggy(22 * Game.TILES_SIZE, 6 * Game.TILES_SIZE));
			list.add(new Piggy(17 * Game.TILES_SIZE, 3 * Game.TILES_SIZE));
			list.add(new Piggy(24 * Game.TILES_SIZE, 3 * Game.TILES_SIZE));
			list.add(new Piggy(12 * Game.TILES_SIZE, 12 * Game.TILES_SIZE));

			list.add(new Piggy(7 * Game.TILES_SIZE, 7 * Game.TILES_SIZE));
			list.add(new Piggy(26 * Game.TILES_SIZE, 10 * Game.TILES_SIZE));
			list.add(new Piggy(2 * Game.TILES_SIZE, 12 * Game.TILES_SIZE));
			list.add(new Piggy(15 * Game.TILES_SIZE, 9 * Game.TILES_SIZE));
			break;
			
		case 5:
			list.add(new Piggy(7 * Game.TILES_SIZE, 6 * Game.TILES_SIZE));
			list.add(new Piggy(22 * Game.TILES_SIZE, 9 * Game.TILES_SIZE));
			list.add(new Piggy(7 * Game.TILES_SIZE, 11 * Game.TILES_SIZE));
			list.add(new Piggy(12 * Game.TILES_SIZE, 12 * Game.TILES_SIZE));
			list.add(new Piggy(17 * Game.TILES_SIZE, 12 * Game.TILES_SIZE));
			list.add(new Piggy(23 * Game.TILES_SIZE, 14 * Game.TILES_SIZE));
			list.add(new Piggy(27 * Game.TILES_SIZE, 14 * Game.TILES_SIZE));			
			list.add(new Piggy(9 * Game.TILES_SIZE, 15 * Game.TILES_SIZE));

			break;
}
		return list;
	}


	public static Point GetPlayerSpawn(int lvlIndex) {
		switch (lvlIndex) {
		case 0:
			return new Point(19 * Game.TILES_SIZE, 4 * Game.TILES_SIZE);
		case 1:
			return new Point(22 * Game.TILES_SIZE, 4 * Game.TILES_SIZE);
		case 2:
			return new Point(2 * Game.TILES_SIZE, 4 * Game.TILES_SIZE);
		case 3:
			return new Point(22 * Game.TILES_SIZE, 4 * Game.TILES_SIZE);
		case 4:	
			return new Point(2 * Game.TILES_SIZE, 8 * Game.TILES_SIZE);
		case 5:	
			return new Point(22 * Game.TILES_SIZE, 13 * Game.TILES_SIZE);
		}
		return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
	}
	public static ArrayList<Diamond> GetDiamonds(int lvlIndex) {
		ArrayList<Diamond> list = new ArrayList<>();
		return list;
	}

	public static ArrayList<GameContainer> GetContainers(int lvlIndex) {

		ArrayList<GameContainer> list = new ArrayList<>();
		switch (lvlIndex) {
		case 0:
			list.add(new GameContainer(18 * Game.TILES_SIZE, 12 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(9 * Game.TILES_SIZE, 11 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(12 * Game.TILES_SIZE, 11 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(12 * Game.TILES_SIZE, 6 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(9 * Game.TILES_SIZE, 6 * Game.TILES_SIZE, BOX));
			break;
		case 1:
		
			list.add(new GameContainer(9 * Game.TILES_SIZE, 6 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(9 * Game.TILES_SIZE, 10 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(18 * Game.TILES_SIZE, 12 * Game.TILES_SIZE, BOX));

			list.add(new GameContainer(18 * Game.TILES_SIZE, 8 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(21 * Game.TILES_SIZE, 9 * Game.TILES_SIZE, BOX));
			break;
		case 2:
			
			list.add(new GameContainer(9 * Game.TILES_SIZE, 6 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(2 * Game.TILES_SIZE, 5 * Game.TILES_SIZE, BOX));

			list.add(new GameContainer(12 * Game.TILES_SIZE, 6 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(18 * Game.TILES_SIZE, 8 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(21 * Game.TILES_SIZE, 8 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(19 * Game.TILES_SIZE, 12 * Game.TILES_SIZE, BOX));
			break;
			
		case 3:

			list.add(new GameContainer(9 * Game.TILES_SIZE, 6 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(18 * Game.TILES_SIZE, 8 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(2 * Game.TILES_SIZE, 8 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(27 * Game.TILES_SIZE, 14 * Game.TILES_SIZE, BOX));
			break;
		case 4:
			
			list.add(new GameContainer(9 * Game.TILES_SIZE, 7 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(14 * Game.TILES_SIZE, 9 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(2 * Game.TILES_SIZE, 9 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(27 * Game.TILES_SIZE, 14 * Game.TILES_SIZE, BOX));
			break;
		case 5:
	
			list.add(new GameContainer(21 * Game.TILES_SIZE,9 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(27 * Game.TILES_SIZE,11 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(2 * Game.TILES_SIZE,8 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(2 * Game.TILES_SIZE,13 * Game.TILES_SIZE, BOX));
			list.add(new GameContainer(11 * Game.TILES_SIZE,15 * Game.TILES_SIZE, BOX));
			break;
		}
		return list;
	}

	public static ArrayList<Spike> GetSpikes(int lvlIndex) {
		ArrayList<Spike> list = new ArrayList<>();
		switch (lvlIndex) {
		case 0:
			
			for (int i = 5; i < 7; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}

			for (int i = 15; i < 17; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			break;
			
		case 1:
			
			for (int i = 5; i < 7; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			for (int i = 15; i < 17; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			for (int i = 15; i < 18; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 1 * Game.TILES_SIZE, SPIKE2));
			}
			list.add(new Spike(12 * Game.TILES_SIZE, 8 * Game.TILES_SIZE, SPIKE2));
			break;
			 
		case 2:
			for (int i = 5; i < 7; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			for (int i = 15; i < 17; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			for (int i = 17; i < 24; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 10 * Game.TILES_SIZE, SPIKE2));
			}
			for (int i = 12; i < 18; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 3 * Game.TILES_SIZE, SPIKE2));
			}
			break;
			
		case 3:
			for (int i = 5; i < 17; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			for (int i = 12; i < 15; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 4 * Game.TILES_SIZE, SPIKE2));
			}
			
				list.add(new Spike(9 * Game.TILES_SIZE, 8 * Game.TILES_SIZE, SPIKE2));
			for(int i=13; i<15;i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 8 * Game.TILES_SIZE, SPIKE2));
				}
			for(int i=2; i<5;i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 4 * Game.TILES_SIZE, SPIKE2));
				}
			for(int i=26; i<28;i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 4 * Game.TILES_SIZE, SPIKE2));
				}
			break;
			
		case 4:

			for (int i = 5; i < 17; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			for (int i = 21; i < 23; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 14 * Game.TILES_SIZE, SPIKE1));
			}
			
				list.add(new Spike(13 * Game.TILES_SIZE, 11 * Game.TILES_SIZE, SPIKE2));
			
			
			for (int i =2 ; i < 9; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 3 * Game.TILES_SIZE, SPIKE2));
			}
			break;
			
		case 5:
			
			for (int i =17 ; i < 19; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 10 * Game.TILES_SIZE, SPIKE2));
			}
			for (int i =5 ; i < 9; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			for (int i =13 ; i < 17; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 16 * Game.TILES_SIZE, SPIKE1));
			}
			for (int i =7 ; i <15; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 4 * Game.TILES_SIZE, SPIKE2));
			}
			for (int i =7 ; i <15; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 8 * Game.TILES_SIZE, SPIKE2));
			}
			for (int i =2 ; i < 5; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 10 * Game.TILES_SIZE, SPIKE2));
			}
			for (int i = 26; i <28; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 9 * Game.TILES_SIZE, SPIKE2));
			}
			for (int i = 21; i < 24; i++) {
				list.add(new Spike(i * Game.TILES_SIZE, 7 * Game.TILES_SIZE, SPIKE2));
			}
			break;
		}
		return list;
	}


	public static ArrayList<Cannon> GetCannon(int lvlIndex) {
		ArrayList<Cannon> list = new ArrayList<>();
		switch (lvlIndex) {
		case 0:
			
			list.add(new Cannon(2 * Game.TILES_SIZE, 14 * Game.TILES_SIZE, CANNON_RIGHT));
			break;
		case 1:
		
			list.add(new Cannon(18 * Game.TILES_SIZE, 5 * Game.TILES_SIZE, CANNON_LEFT));
			list.add(new Cannon(2 * Game.TILES_SIZE, 13 * Game.TILES_SIZE, CANNON_RIGHT));
			break;
		case 2:
		
			list.add(new Cannon(18 * Game.TILES_SIZE, 5 * Game.TILES_SIZE, CANNON_LEFT));
			list.add(new Cannon(2 * Game.TILES_SIZE, 13 * Game.TILES_SIZE, CANNON_RIGHT));
			list.add(new Cannon(2 * Game.TILES_SIZE, 8 * Game.TILES_SIZE, CANNON_RIGHT));
			break;
		case 3:
			list.add(new Cannon(3 * Game.TILES_SIZE, 11 * Game.TILES_SIZE, CANNON_RIGHT));
			list.add(new Cannon(21 * Game.TILES_SIZE, 9 * Game.TILES_SIZE, CANNON_RIGHT));
			break;
		case 4:
			
			list.add(new Cannon(7 * Game.TILES_SIZE, 11 * Game.TILES_SIZE, CANNON_RIGHT));
			list.add(new Cannon(4 * Game.TILES_SIZE, 5 * Game.TILES_SIZE, CANNON_RIGHT));
			break;
		case 5:
			
			list.add(new Cannon(3 * Game.TILES_SIZE, 5 * Game.TILES_SIZE, CANNON_RIGHT));
			break;
			
		}
		return list;
	}




//		ArrayList<GameContainer> list = new ArrayList<>();
//		for (int j = 0; j < img.getHeight(); j++)
//			for (int i = 0; i < img.getWidth(); i++) {
//				Color color = new Color(img.getRGB(i, j));
//				int value = color.getBlue();
//				if (value == BOX || value == BARREL)
//					list.add(new GameContainer(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
//			}
//
//		return list;
//	}

}