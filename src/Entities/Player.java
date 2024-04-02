package Entities;

import static Utilz.Constants.Directions.*;
import static Utilz.Constants.PlayerConstants.*;
import static Utilz.HelpMethods.CanMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;
import Utilz.LoadSave;

public class Player extends Entity{
	
	private BufferedImage[][] animation;
	private int aniTick,aniIndex,aniSpeed = 15;
	private int playerAction = IDLE ;
	private boolean left, up,right,down;
	private boolean moving = false,attacking;
	private float playerSpeed = 1.0f;
	 

	public Player(float x, float y, int width ,int height) {
		super(x, y, width, height);
		loadAnimation();
		initHitbox(x + 28, y + 32, (int)48, (int)39);
	}
	
	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}
	
	public void render(Graphics g) {
		g.drawImage(animation[playerAction][aniIndex], (int) x, (int) y, 128, 80, null);
		drawHitbox(g);
	}
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
		        if(aniIndex >= GetSpriteAmount(playerAction)) { 
			       aniIndex = 0;
			       attacking = false;
		        }
		}
	}
	
	private void setAnimation() {
		
		int startAni = playerAction;
		if(moving) 
			playerAction = RUNNING;
		else 
			playerAction = IDLE;
		
		if(attacking)
			playerAction = ATTACK;
		
		if(startAni != playerAction)
			resetAniTick();
	}
	

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
		
	}

	private void updatePos() {
	    moving = false;

	    if (!left && !right && !up && !down)
	        return;

	    float xSpeed = 0, ySpeed = 0;

	    if (left && !right) {
	        xSpeed = -playerSpeed;
	    } else if (right && !left) {
	        xSpeed = playerSpeed;
	    }

	    if (up && !down) {
	        ySpeed = -playerSpeed;
	    } else if (down && !up) {
	        ySpeed = playerSpeed;
	    }

	    float newX = x + xSpeed;
	    float newY = y + ySpeed;

	    
	    if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, 1) &&
	        CanMoveHere(hitbox.x + xSpeed, hitbox.y + hitbox.height / 2, hitbox.width, hitbox.height / 2, 1)) {
	        hitbox.x += xSpeed;
	        x = newX;
	    }

	    if (CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, 1) &&
	        CanMoveHere(hitbox.x + hitbox.width / 2, hitbox.y + ySpeed, hitbox.width / 2, hitbox.height, 1)) {
	        hitbox.y += ySpeed;
	        y = newY;
	    }
	}

	private void loadAnimation() {
		
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Player);
		animation = new BufferedImage[9][10];
		for(int j=0;j<animation.length;j++) 
			for(int i=0;i<animation[j].length;i++) 
				animation[j][i] = img.getSubimage(i*78,j*57, 78, 57);
	}
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		down = false;
		up = false;
	}
	
	

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
}