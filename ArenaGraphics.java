import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

public class ArenaGraphics extends JFrame {

	public class ArenaPanel extends JPanel {
		private int width;
		private int height;
		
		private ArrayList<Drawable> ds;
		
		public ArenaPanel(int width , int height) {
			this.width = width;
			this.height = height;
			ds = new ArrayList<>();
		}
		
		public void AddDrawable(Drawable dToAdd) {
			ds.add(dToAdd);
		}
		
		@Override
		public void paint(Graphics g) {
			Graphics2D ctx = (Graphics2D) g;
			ctx.setColor(Color.WHITE);
			ctx.fillRect(0, 0, width, height);
			
			ctx.setColor(Color.BLACK);
			ds.forEach(d -> d.draw(ctx));
		}
	}
	
	private ArenaPanel panel;
	
	public ArenaGraphics(int width , int height) {
		panel = new ArenaPanel(width, height);
		add(panel);
		
		setTitle("Orc");
		setSize(width, height);
		setVisible(true);
		setResizable(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void update() {
		panel.repaint();
	}
	
	public void addDrawable(Drawable dToAdd) {
		panel.AddDrawable(dToAdd);
	}
}

