import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TextZone implements Drawable {
	
	public int posX;
	public int posY;
	
	public String text;
	
	public TextZone(int posX, int posY, String text) {
		this.posX = posX;
		this.posY = posY;
		this.text = text;
	}
	
	@Override
	public void draw(Graphics2D ctx) {		
		ctx.drawString(text, posX, posY);
	}
}

