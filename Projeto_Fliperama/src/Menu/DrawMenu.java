package Menu;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class DrawMenu extends JLabel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (Game.gamestate == GameState.start) {
			g.setColor(new Color(24, 70, 145));
			g.fillRect(0, Gui.height / 2 - 50, Gui.width+200, 100);
			g.setColor(Color.WHITE);
			g.drawString("PRESS ENTER TO START", Gui.width/2, Gui.height / 2 +10);
		}else if(Game.gamestate == GameState.pause) {
			g.setColor(new Color(100, 100, 100));
			g.fillRect(0, Gui.height / 2 - 50, Gui.width+200, 100);
			g.setColor(Color.WHITE);
			g.drawString("PRESS ESC TO CONTINUE", Gui.width/2 -10, Gui.height / 2 +10);
		}else if(Game.gamestate == GameState.gameover){
			g.setColor(new Color(175, 28, 28));
			g.fillRect(0, Gui.height / 2 - 50, Gui.width+200, 100);
			g.setColor(Color.WHITE);
			g.drawString("VOCE PERDEU, APERTE ENTER PARA REJOGAR", 75, Gui.height / 2 +10);
		}

		repaint();
		
	}
}