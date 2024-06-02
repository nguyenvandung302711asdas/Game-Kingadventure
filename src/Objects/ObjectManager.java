package Objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Player;
import GameStates.Playing;
import Levels.Level;
import Main.Game;
import Utilz.LoadSave;
import static Utilz.Constants.ObjectConstants.*;
import static Utilz.HelpMethods.CanCannonSeePlayer;
import static Utilz.Constants.Projectiles.*;
import static Utilz.HelpMethods.IsProjectileHittingLevel;

public class ObjectManager {

	private Playing playing;
	private BufferedImage[] diamonImgs, containerImgs;
	private BufferedImage[][] spikeImg ;	
	private BufferedImage[] cannonImg;
	private BufferedImage cannonBallImg;
	private ArrayList<Diamond> diamonds;
	private ArrayList<GameContainer> containers;
	private ArrayList<Spike> spikes;
	private ArrayList<Cannon> cannons;
	private ArrayList<Projectile> projectiles = new ArrayList<>();

	public ObjectManager(Playing playing) {
		this.playing = playing;
		loadImgs();
	}
	
	public void checkSpikesTouched(Player p) {
		for (Spike s : spikes)	
			if (s.getHitbox().intersects(p.getHitbox()))
				p.kill();
	}



	public void checkObjectTouched(Rectangle2D.Float hitbox) {
		for (Diamond p : diamonds)
			if (p.isActive()) {
				if (hitbox.intersects(p.getHitbox())) {
					p.setActive(false);
					Playing.score ++;
//					applyEffectToPlayer(p);
				}
			}
	}

//	public void applyEffectToPlayer(Diamond p) {
//		if (p.getObjType() == RED_POTION)
//			playing.getPlayer().changeHealth(DIAMON);
//		
//	}
	
	public void checkObjectHit(Rectangle2D.Float attackbox) {
		for (GameContainer gc : containers)
			if (gc.isActive()&& !gc.doAnimation) {
				if (gc.getHitbox().intersects(attackbox)) {
					gc.setAnimation(true);
					int type = 0;
					if (gc.getObjType() == BARREL)
						type = 1;
					diamonds.add(new Diamond((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y - gc.getHitbox().height / 2), type));
					return;
				}
			}
	}

	public void loadObjects(Level newLevel) {
		diamonds = newLevel.getDiamonds();
		containers = new ArrayList<>(newLevel.getContainers());
		spikes = newLevel.getSpikes();
		cannons = newLevel.getCannon();
		projectiles.clear();

	}

	private void loadImgs() {
		BufferedImage diamondSprite = LoadSave.GetSpriteAtlas(LoadSave.DIAMON);
		diamonImgs = new BufferedImage[10];

		for (int i = 0; i < diamonImgs.length; i++)
			diamonImgs[i] = diamondSprite.getSubimage(18 * i, 0, 18, 14);

		BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.BOX_ATLAS);
		containerImgs = new BufferedImage[3];
			for (int i = 0; i < containerImgs.length; i++)
				containerImgs[i] = containerSprite.getSubimage(22 * i, 0, 22, 20);
		
		BufferedImage spikeSprite = LoadSave.GetSpriteAtlas(LoadSave.TRAP_ATLAS);
		spikeImg = new BufferedImage[2][1];
		for (int j = 0; j <spikeImg.length; j++)
			for (int i = 0; i < spikeImg[j].length; i++)
				spikeImg[j][i] =spikeSprite.getSubimage(32 * i, 32 * j, 32, 32);
		
		BufferedImage cannonSprite = LoadSave.GetSpriteAtlas(LoadSave.CANNON);
		cannonImg = new BufferedImage[7];
		
		for (int i = 0; i < cannonImg.length; i++)
			cannonImg[i] = cannonSprite.getSubimage(i * 40, 0, 40, 26);
		
		cannonBallImg = LoadSave.GetSpriteAtlas(LoadSave.CANNON_BALL);
	}

	public void update(int[][] lvlData, Player player) {
		for (Diamond p : diamonds)
			if (p.isActive())
				p.update();

		for (GameContainer gc : containers)
			if (gc.isActive())
				gc.update();
		updateCannons(lvlData, player);
		updateProjectiles(lvlData, player);
		
	}
	
	private void updateProjectiles(int[][] lvlData, Player player) {
		for (Projectile p : projectiles)
			if (p.isActive()) {
				p.updatePos();
				if (p.getHitbox().intersects(player.getHitbox())) {
					player.changeHealth(-1);
					p.setActive(false);
				} else if (IsProjectileHittingLevel(p, lvlData))
					p.setActive(false);
			}
	}

	private void updateCannons(int[][] lvlData, Player player) {
		for (Cannon c : cannons) {
			if (!c.doAnimation)
				if (c.getTileY() == player.getTileY())
					if (isPlayerInRange(c, player))
						if (isPlayerInfrontOfCannon(c, player))
							if (CanCannonSeePlayer(lvlData, player.getHitbox(), c.getHitbox(), c.getTileY())) {
								c.setAnimation(true);
							}
		
			c.update();
			if (c.getAniIndex() == 4 && c.getAniTick() == 0)
				shootCannon(c);
		}
	}


//dan
	private void shootCannon(Cannon c) {
	
		int dir = 1;
		if (c.getObjType() == CANNON_LEFT)
			dir = -1;

		projectiles.add(new Projectile((int) c.getHitbox().x, (int) c.getHitbox().y, dir));

	}

	private boolean isPlayerInRange(Cannon c, Player player) {
		int absValue = (int) Math.abs(player.getHitbox().x - c.getHitbox().x);
		return absValue <= Game.TILES_SIZE * 20;
	}

	private boolean isPlayerInfrontOfCannon(Cannon c, Player player) {
		if (c.getObjType() == CANNON_LEFT) {
			if (c.getHitbox().x > player.getHitbox().x)
				return true;

		} else if (c.getHitbox().x < player.getHitbox().x)
			return true;
		return false;
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawDiamons(g, xLvlOffset);
		drawContainers(g, xLvlOffset);
		drawTraps(g, xLvlOffset);
		drawCannons(g, xLvlOffset);
		drawProjectiles(g,xLvlOffset);
	}
	
	private void drawCannons(Graphics g, int xLvlOffset) {
		for (Cannon c : cannons) {
			int x = (int) (c.getHitbox().x - xLvlOffset);
			int width = CANNON_WIDTH;
			if (c.getObjType() == CANNON_RIGHT) {
				x += width;
				width *= -1;
			}
			g.drawImage(cannonImg[c.getAniIndex()], x, (int) (c.getHitbox().y), width, CANNON_HEIGHT, null);	
			//c.drawHitbox(g, xLvlOffset);
		}
		

	}
	private void drawTraps(Graphics g, int xLvlOffset) {
		for (Spike gc : spikes)
			if (gc.isActive()) {
				int type = 0;
				if (gc.getObjType() == SPIKE1)
					type = 1;
				g.drawImage(spikeImg[type][gc.getAniIndex()], (int) (gc.getHitbox().x - gc.getXDrawOffset() - xLvlOffset), (int) (gc.getHitbox().y - gc.getYDrawOffset()), SPIKE_WIDTH,
						SPIKE_HEIGHT, null);
				//gc.drawHitbox(g, xLvlOffset);
			}
	}
	
	private void drawProjectiles(Graphics g, int xLvlOffset) {
		for (Projectile p : projectiles)
			if (p.isActive())
				g.drawImage(cannonBallImg, (int) (p.getHitbox().x - xLvlOffset), (int) (p.getHitbox().y), CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT, null);

	}
	

	private void drawContainers(Graphics g, int xLvlOffset) {
		for (GameContainer gc : containers)
			if (gc.isActive()) {
				g.drawImage(containerImgs[gc.getAniIndex()], (int) (gc.getHitbox().x - xLvlOffset), (int) (gc.getHitbox().y - gc.getYDrawOffset()), CONTAINER_WIDTH, CONTAINER_HEIGHT, null);
			//	gc.drawHitbox(g, xLvlOffset);
			}
	}

	private void drawDiamons(Graphics g, int xLvlOffset) {
		for (Diamond p : diamonds) {
			if (p.isActive()) {
				g.drawImage(diamonImgs[p.getAniIndex()], (int) (p.getHitbox().x - p.getXDrawOffset() - xLvlOffset ), (int) (p.getHitbox().y - p.getYDrawOffset()), POTION_WIDTH, POTION_HEIGHT, null);
				//p.drawHitbox(g, xLvlOffset);	
			}
		}
	}

	public void resetAllObjects() {
		
		
		loadObjects(playing.getLevelManager().getCurrentLevel());
		for (Diamond p : diamonds)
			p.reset();

		for (GameContainer gc : containers)
			gc.reset();
		
		
		for (Cannon gc : cannons)
			gc.reset();
		
	}

}
