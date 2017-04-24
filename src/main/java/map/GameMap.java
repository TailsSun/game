package map;

import java.awt.*;
import java.util.ArrayList;

import static game.Game.height;
import static game.Game.width;

/**
 * Created by DNS on 14.04.2017.
 * Карта, объектов и ланшафта
 */
public class GameMap {

    public static final int lengthKub = 40;
    private static final int PercentageImpassableObjects = 15;
    public static final int WALL = 2;
    public static final int NOT_WALL = 1;

    private static ArrayList<Integer> map = new ArrayList<>();
    private static int XXMap = width/lengthKub;      //длинна мапы по Х
    private static int YYMap  = height/lengthKub;   //Длинна мапы по У
    private int S = XXMap * YYMap;


    public static ArrayList<Integer> getMap() {
        return map;
    }

    public static int getXXMap() {
        return XXMap;
    }

    public static int getYYMap() {
        return YYMap;
    }
    /*public void proverka(ArrayList map){

    }*/

    public GameMap(){

        for (int x = 0; x < S; x++){
            final double rnd = Math.random()*100;
            if (rnd < PercentageImpassableObjects){
                map.add(WALL);
            }
            else map.add(NOT_WALL);
           /* if (x ==20){
                map.add(2);
            }
            else map.add(1);*/

        }

    }
    public void draw(Graphics2D graf){

        for(int i = 0; i < map.size(); i++) {
            graf.setColor(Color.MAGENTA);
            if (map.get(i) == WALL){
                int XX,YY;

                if (i > XXMap - 1) {            //если И больше дленны мапы по У
                    XX = i % XXMap;

                } else XX = i;

                if (i > XXMap - 1) {
                    YY = i / (XXMap);
                } else YY = 0;
                XX = XX * lengthKub;
                YY = YY * lengthKub;



                graf.drawRect(XX, YY , lengthKub, lengthKub);

            }
        }
    }

}
