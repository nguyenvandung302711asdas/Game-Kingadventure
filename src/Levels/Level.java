package Levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Piggy;

import static Utilz.HelpMethods.GetLevelData;
import static Utilz.HelpMethods.GetPigs;
import static Utilz.HelpMethods.GetPlayerSpawn;


public class Level {

	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Piggy> pigs;
	private Point playerSpawn;

	public Level(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies(LevelManager.lvlIndex);;
		calcPlayerSpawn(LevelManager.lvlIndex);
	}
	
	public void calcPlayerSpawn(int lvlIndex) {
		playerSpawn = GetPlayerSpawn(lvlIndex);
	}
	
	public void createEnemies(int lvlIndex) {
		pigs = GetPigs(lvlIndex);
	}
	
	public ArrayList<Piggy> getPigs() {
		return pigs;
	}

	private void createLevelData() {
		lvlData = GetLevelData(img);
	}

	public Level(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}

	public int[][] getLevelData() {
		return lvlData;
	}

	public Point getPlayerSpawn() {
		
		return playerSpawn;
	}
 
}