package bullet;

import utils.Target;

import java.awt.*;
import java.util.ArrayList;

import static game.Game.height;
import static game.Game.width;

/**
 * Created by DNS on 12.04.2017.
 */
public class  BuletGG{
    public static final byte directionLeft = 1;
    public static final byte directionRight = 2;
    public static final byte directionUp = 4;
    public static final byte directionDown = 3;


    private double x;
    private  double y;
    private int r;
    private Color color;
    public double speed = 2;
    private int nap;
    Target target;

    public BuletGG(double x, double y, int i){
        r = 2;
        color = Color.white;
        this.x = x;
        this.y = y;
        this.nap = i;

        if(i == directionLeft || i == directionUp) {
            speed = -speed; //left
        }


    }

    /*@Override*/
    public boolean removeOut(ArrayList<Integer> map) {


        if (target.target(x,y,r,map)){
            return true;
        }
        if(y <= 0 || x <= 0 || x >= width || y >= height){return true;}
        else return false;
    }



    public void update(){
        if (nap == directionLeft || nap == directionRight)x += speed;
        if (nap == directionDown || nap == directionUp)y += speed;

    }
    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    public void draw(Graphics2D graf){
        graf.setColor(color);
        graf.fillOval((int)x,(int) y, r * 2, r * 2);
    }
}




