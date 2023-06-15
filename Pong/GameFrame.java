import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

    GamePanel panel;

    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game"); // Nome da janela
        this.setResizable(false);
        this.setBackground(Color.BLACK); // Cor de fundo: Preto
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // Ajusta o tamanho da janela
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
