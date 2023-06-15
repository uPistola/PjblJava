import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); // Faz com que não precise mudar as 2 variaveis para mudar o tamanho
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    // metodos "new" para começar um novo jogo
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }

    public void newPaddles(){
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    public void draw(Graphics g){
        // Desenha tudo na tela
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){
        // Deixa a movimentaçaõ mais suave e lisa
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollission(){

        // Colissão da bola no teto
        if(ball.y <= 0){
            ball.setYdirection(-ball.Yvelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER){
            ball.setYdirection(-ball.Yvelocity);
        }
        
        // Colissão da bola com o player
        if(ball.intersects(paddle1)){
            ball.Xvelocity = Math.abs(ball.Xvelocity);
            ball.Xvelocity++; // Aumenta a velocidade da bola ao colidir com o player
            if(ball.Yvelocity > 0)
                ball.Yvelocity++;
            else
                ball.Yvelocity--;
            ball.setXdirection(ball.Xvelocity);
            ball.setYdirection(ball.Yvelocity);
        }

        if(ball.intersects(paddle2)){
            ball.Xvelocity = Math.abs(ball.Xvelocity);
            ball.Xvelocity++; // Aumenta a velocidade da bola ao colidir com o player
            if(ball.Yvelocity > 0)
                ball.Yvelocity++;
            else
                ball.Yvelocity--;
            ball.setXdirection(-ball.Xvelocity);
            ball.setYdirection(ball.Yvelocity);
        }



        // Para o player de sair da janela
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;

        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;

        // Dar ponto para o player e reseta o jogo
        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
        }

        if(ball.x >= GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
        }
    }

    public void run(){
        // Loop do jogo
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollission();
                repaint();
                delta--;
            }
        
        }

    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
