package Menu;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gui {
	public static int width = 320, height = 576;
		
	JFrame jf;
	
	public void create() {
		jf = new JFrame("Tetrix");
		jf.setSize(width + 17 + 200, height + 41);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setLayout(null);
		jf.addKeyListener(new KeyHandler());
		jf.requestFocus();
		
		DrawMenu dm = new DrawMenu();
		setupDraw(dm,0,0,width+200,height);
		
		Draw dg = new Draw();
		setupDraw(dg, 0, 0, width + 1, height + 1);

		DrawInterface di = new DrawInterface();
		setupDraw(di, width+1, 1, width, height);
		
		jf.setVisible(true);
	}
	
	private void setupDraw(JLabel draw, int x, int y, int width, int height) {
		draw.setBounds(x,y,width,height);
		draw.setVisible(true);
		jf.add(draw);
	}
	
}
