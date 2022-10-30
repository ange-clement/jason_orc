import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Orc implements Drawable {
	
	public int id;
	
	public int posX;
	public int posY;
	
	public int size;
	
	public double maxHealth;
	public double health;

	public boolean followTarget;
	public int targetX;
	public int targetY;
	
	public Orc(int id, int posX, int posY, int size) {
		this.id = id;
		
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		
		this.followTarget = false;
		this.targetX = 0;
		this.targetY = 0;
		
		this.maxHealth = 1.0;
		this.health = this.maxHealth;
	}
	
	@Override
	public void draw(Graphics2D ctx) {
		ctx.setColor(Color.black);
		ctx.drawOval(posX - size, posY - size, 2*size, 2*size);
		ctx.drawString(""+id, posX - size, posY + 3*size);
		ctx.setColor(Color.red);
		ctx.fillRect((int)(posX - maxHealth*25), posY - size - 15, (int)(maxHealth*50), 10);
		ctx.setColor(Color.green);
		ctx.fillRect((int)(posX - maxHealth*25), posY - size - 15, (int)(maxHealth*50 * (health/maxHealth)), 10);
	}
	
	public void update() {
		if (followTarget) {
			moveTowardTarget();
		}
		this.health -= 0.0001;
	}
	
	
	
	public void move(int x, int y) {
		posX += x;
		posY += y;
	}
	
	public void target(int x, int y) {
		targetX = x;
		targetY = y;
	}
	
	public void setFollowTarget(boolean val) {
		followTarget = val;
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

