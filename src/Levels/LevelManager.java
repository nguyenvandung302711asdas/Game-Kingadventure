package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameStates.GameState;
import Main.Game;
import Utilz.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;
	private ArrayList<Level> levels;
	public static int lvlIndex = 0;
	
	
	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();
		levels = new ArrayList<>();
		buildAllLevels();
	}

	private void buildAllLevels() {
		BufferedImage[] allLevels = LoadSave.getAllLevels();
		for(BufferedImage img : allLevels)
			levels.add(new Level(img));
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[102];
		for (int j = 0; j < 6; j++)
			for (int i = 0; i < 17; i++) {
				int index = j * 17 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < levels.get(lvlIndex).getLevelData()[0].length; i++) {
				int index = levels.get(lvlIndex).getSpriteIndex(i, j);
			g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
	}

	public void update() {
		
	}	

	public Level getCurrentLevel() {
		return levels.get(lvlIndex);
	}
	
	public int getAmountOfLevels(){
		return levels.size();
	}

	public void loadNextLevel() {
		lvlIndex++;
		if(lvlIndex >= levels.size()) {
			lvlIndex = 0;
			System.out.println("Win cmnr");
			GameState.state = GameState.MENU;
		}
		Level newLevel = levels.get(lvlIndex);
		newLevel.createEnemies(lvlIndex);
		newLevel.calcPlayerSpawn(lvlIndex);
		game.getPlaying().getEnemyManager().loadEnemies(newLevel);
		game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
	}
}