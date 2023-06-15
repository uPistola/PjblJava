package Pong;

import java.awt.Color;
import javax.swing.*;

public class GameFrame extends JFrame{

    GamePanel panel;

    public GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game"); // Nome da janela
        this.setResizable(false);
        this.setBackground(Color.BLACK); // Cor de fundo: Preto
        this.pack(); // Ajusta o tamanho da janela
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
