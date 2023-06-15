import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

    Random random;
    int Xvelocity;
    int Yvelocity;
    int initialSpeed = 3;


    Ball(int x,int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXdirection = random.nextInt(2);
        if(randomXdirection == 0)
            randomXdirection--;
        setXdirection(randomXdirection*initialSpeed);

        int randomYdirection = random.nextInt(2);
        if(randomYdirection == 0)
            randomYdirection--;
        setYdirection(randomYdirection*initialSpeed);

    }

    public void setXdirection(int randomXdirection){
        Xvelocity = randomXdirection;
    }

    public void setYdirection(int randomYdirection){
        Yvelocity = randomYdirection;
    }

    public void move(){
        x += Xvelocity;
        y += Yvelocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, height, width);
    }
}
