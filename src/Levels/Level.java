package Levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Piggy;
import Objects.Cannon;
import Objects.Diamond;
import Objects.GameContainer;
import Objects.Spike;
import Utilz.HelpMethods;

import static Utilz.HelpMethods.GetLevelData;
import static Utilz.HelpMethods.GetPigs;
import static Utilz.HelpMethods.GetPlayerSpawn;


public class Level {

	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Piggy> pigs;
	private Point playerSpawn;
	private ArrayList<Diamond> diamonds;
	private ArrayList<GameContainer> containers;
	private ArrayList<Spike> spikes;
	private ArrayList<Cannon> cannons;

	public Level(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies(LevelManager.lvlIndex);;
		calcPlayerSpawn(LevelManager.lvlIndex);
		createDiamonds(LevelManager.lvlIndex);
		createContainers(LevelManager.lvlIndex);
		createSpikes(LevelManager.lvlIndex);
		createCannons(LevelManager.lvlIndex);

	}
	public void createCannons(int lvlIndex) {
		cannons = HelpMethods.GetCannon(lvlIndex);
	}
	public void createSpikes(int lvlIndex) {
		spikes = HelpMethods.GetSpikes(lvlIndex);
	}
	public void createContainers( int lvIndex) {
		containers = HelpMethods.GetContainers(lvIndex);
	}
	public void createDiamonds(int lvlIndex  ) {
		diamonds = HelpMethods.GetDiamonds(lvlIndex);
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

	public void createLevelData() {
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
	
	public ArrayList<Diamond> getDiamonds() {
		return diamonds;
	}

	public ArrayList<GameContainer> getContainers() {
		return containers;
	}
	
	public ArrayList<Spike> getSpikes() {
		return spikes;
	}
	
	public ArrayList<Cannon> getCannon() {
		return cannons;
	}
}  