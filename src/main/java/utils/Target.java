package utils;

import map.GameMap;

import java.awt.*;
import java.util.ArrayList;

import static game.Game.width;
import static map.GameMap.lengthKub;


/**
 * Created by DNS on 15.04.2017.
 */
public  class Target {
     static int XXMap = width / lengthKub;
/*
     int YYMap = height / lengthKub;
*/


    public static boolean target(double x, double y, int r, ArrayList<Integer> map) {


        for (int i = 0; i < map.size(); i++) {

             if (map.get(i) == GameMap.WALL) {   //проверяем только стены ==2

                int XX, YY;
                 if (i > XXMap - 1) {            //если И больше дленны мапы по У
                     XX = i % XXMap;

                 } else XX = i;

                 if (i > XXMap - 1) {
                     YY = i / (XXMap);
                 } else YY = 0;


                 XX = XX * lengthKub;
                 YY = YY * lengthKub;

                 Rectangle rectMap = new Rectangle(XX,YY,lengthKub,lengthKub);  // квадрат карты
                 Rectangle rectBul = new Rectangle((int)x, (int)y,r,r);         // квадрат пули
                 if (rectMap.intersects(rectBul)) return true;                  //если квадрат карты соприскомается с пулей то тру

            }
        }
        return false;
    }




}
