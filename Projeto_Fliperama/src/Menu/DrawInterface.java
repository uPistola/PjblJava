package Menu;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class DrawInterface extends JLabel{
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Game.nextBlock.getColor());
		for (int i = 0; i < Game.nextBlock.getBounds()[Game.nextBlock.getRotation()].length; i++) {
			for (int j = 0; j < Game.nextBlock.getBounds()[Game.nextBlock.getRotation()][i].length; j++) {
				if (Game.nextBlock.getBounds()[Game.nextBlock.getRotation()][i][j] == 1) {
					
					g.fillRect(Conversion.cellToCoord(1 + i), 
							Conversion.cellToCoord(j), 32, 32);
				}
			}
		}
		
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				g.drawRect(i*32 + 32, j*32, 32, 32);
			}
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(32, 0, 128, 128);
		
		g.drawString("SCORE:" + Game.score, 32, 200);
		
		repaint();
	}
}
