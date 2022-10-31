import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Orc implements Drawable {
	
	public int id;
	
	public int posX;
	public int posY;
	public int maxX;
	public int maxY;
	
	public int size;
	
	public double maxHealth;
	public double health;
	
	public double damage;
	private int damagedFramesCountdown;

	public boolean followTarget;
	public int targetX;
	public int targetY;
	
	public Orc(int id, int posX, int posY, int size, int maxX, int maxY) {
		this.id = id;
		
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		
		this.maxX = maxX;
		this.maxY = maxY;
		
		this.followTarget = false;
		this.targetX = 0;
		this.targetY = 0;
		
		this.maxHealth = 1.0;
		this.health = this.maxHealth;
		
		this.damage = 0.07;
		this.damagedFramesCountdown = 0;
	}
	
	@Override
	public void draw(Graphics2D ctx) {
		if (damagedFramesCountdown > 0) {
			ctx.setColor(Color.red);
		} else if (isAlive()) {
			ctx.setColor(Color.black);
		} else {
			ctx.setColor(Color.lightGray);
		}
		ctx.drawOval(posX - size, posY - size, 2*size, 2*size);
		ctx.drawString(""+id, posX - size, posY + 3*size);
		if (isAlive()) {
			ctx.setColor(Color.red);
			ctx.fillRect((int)(posX - maxHealth*25), posY - size - 15, (int)(maxHealth*50), 10);
			ctx.setColor(Color.green);
			ctx.fillRect((int)(posX - maxHealth*25), posY - size - 15, (int)(maxHealth*50 * (health/maxHealth)), 10);
		}
	}
	
	public void update() {
		if (isAlive()) {
			if (health < maxHealth)
				health += damage * 0.01;
			if (followTarget) {
				moveTowardTarget();
			}
		}
		if (damagedFramesCountdown > 0) {
			damagedFramesCountdown --;
		}
	}
	
	
	
	public void move(int x, int y) {
		int nextX = x+posX;
		int nextY = y+posY;
		if (nextX < size)
			nextX = size;
		if (nextX > maxX-size)
			nextX = maxX-size;
		if (nextY < size)
			nextY = size;
		if (nextY > maxY-size)
			nextY = maxY-size;
	
		posX = nextX;
		posY = nextY;
	}
	
	public void target(int x, int y) {
		if (x < size*2)
			x = size*2;
		if (x > maxX-size*2)
			x = maxX-size*2;
		if (y < size*2)
			y = size*2;
		if (y > maxY-size*2)
			y = maxY-size*2;
		targetX = x;
		targetY = y;
		/*
		if (x > 5 && x < maxX-5 && y > 5 && y < maxY-5) {
			targetX = x;
			targetY = y;
		}
		*/
	}
	
	public void setFollowTarget(boolean val) {
		followTarget = val;
	}
	
	public void hit(Orc other) {
		other.takeDamage(damage);
	}
	
	public void takeDamage(double damage) {
		this.health -= damage;
		damagedFramesCountdown = 10;
	}
	
	
	
	
	public boolean isAlive() {
		return this.health > 0;
	}
	
	
	
	private void moveTowardTarget() {
		double dx = targetX - posX;
		double dy = targetY - posY;
		double dist = dx*dx+dy*dy;
		dist = 1.0 / Math.sqrt(dist);
		dx = dx * dist;
		dy = dy * dist;
		
		double angle = 3.141592635 * 0.25;
		int x = 0;
		int y = 0;
		if (dx > Math.cos(angle * 3)) {
			x = 1;
		} else if (dx < Math.cos(angle * 5)) {
			x = -1;
		}
		
		if (dy > Math.sin(angle)) {
			y = 1;
		} else if (dy < Math.sin(-angle)) {
			y = -1;
		}
		
		move(x, y);
	}
}

