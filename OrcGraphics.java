import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class OrcGraphics implements Drawable {
	
	public int posX;
	public int posY;
	
	public int size;

	public boolean followTarget;
	public int targetX;
	public int targetY;
	
	public OrcGraphics(int posX , int posY, int size) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		
		this.followTarget = false;
		this.targetX = 0;
		this.targetY = 0;
	}
	
	@Override
	public void draw(Graphics2D ctx) {		
		ctx.drawOval(posX - size, posY - size, 2*size, 2*size);
	}
	
	public void update() {
		if (followTarget) {
			moveTowardTarget();
		}
	}
	
	public int getFollowTarget() {
		return followTarget ? 1 : 0;
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
		dist = 1.0 / (dist * dist);
		dx = dx * dist;
		dy = dy * dist;
		
		double angle = 3.141592635 * 0.25;
		int x = 0;
		int y = 0;
		if (dx > Math.cos(angle * 3)) {
			x = 1;
		} else if (dx < Math.cos(angle * 5)) {
			x = -1;
		} else {
			x = 0;
		}
		
		if (dy > Math.sin(angle)) {
			y = 1;
		} else if (dy < Math.sin(-angle)) {
			x = -1;
		} else {
			x = 0;
		}
		
		move(x, y);
	}
}

