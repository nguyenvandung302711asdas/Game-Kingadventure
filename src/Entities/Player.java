package Entities;

import static Utilz.Constants.Directions.*;
import static Utilz.Constants.PlayerConstants.*;
import static Utilz.HelpMethods.*;
import Utilz.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;

public class Player extends Entity{
	
	private BufferedImage[][] animation;
	private int aniTick,aniIndex,aniSpeed = 25;
	private int playerAction = IDLE ;
	private boolean left, up,right,down,jump;
	private boolean moving = false
			,attacking;
	private float playerSpeed = 1.5f;
	private int[][] lvlData;
	private float xDrawOffset = 26 * Game.SCALE;
	private float yDrawOffset = 19 *Game.SCALE;

	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -4.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	public Player(float x, float y, int width ,int height) {
		super(x, y, width, height);
		loadAnimation();
		initHitbox(x, y, (int)35, (int) 38);
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
		if (aniTick >= aniSpeed) { 
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				if(playerAction == ATTACK && aniIndex == 3)
					attacking = false;
				aniIndex = 0;
				
			}
		}
	}
	
	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = RUN;
		else
			playerAction = IDLE;

		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALL;
		}

		if (attacking)
			playerAction = ATTACK;

		if (startAni != playerAction)
			resetAniTick();
	}
	

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	private void updatePos() {
		moving = false;
		
	    if (jump && !inAir) {
	        jump();
	    }

	    float xSpeed = 0;

	    if (left) {
	        xSpeed -= playerSpeed;
	        moving = true;
	    }
	    if (right) {
	        xSpeed += playerSpeed;
	        moving = true;
	    }

	    if (!inAir && !IsEntityOnFloor(hitbox, lvlData)) {
	        inAir = true;
	    }

	    if (inAir) {
	        if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
	            hitbox.y += airSpeed;
	            y += airSpeed;
	            airSpeed += gravity;
	            updateXPos(xSpeed);
	        } else {
	            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
	            if (airSpeed > 0) {
	                resetInAir();
	            } else {
	                airSpeed = fallSpeedAfterCollision;
	            }
	            updateXPos(xSpeed);
	        }
	    } else {
	        updateXPos(xSpeed);
	        
	    }

	    hitbox.x = x + xDrawOffset;
	    hitbox.y = y + yDrawOffset;
	    
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
		
	}
	
	private void updateXPos(float xSpeed) {
	    if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
	        hitbox.x += xSpeed;
	        x += xSpeed;
	    } else {
	        hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
	    }
	}


	private void loadAnimation() {
		
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Player);
		animation = new BufferedImage[10][11];
		for(int j=0;j<animation.length;j++) 
			for(int i=0;i<animation[j].length;i++) 
				animation[j][i] = img.getSubimage(i*78,j*58, 78, 58);
	}
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		down = false;
		up = false;
	}
	
	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;

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
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
}