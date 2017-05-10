package enemy;

import map.GameMap;
import utils.DekoderListMAPinX_Y;
import utils.algoritmLi.AlgoritmLI;

import java.awt.*;
import java.util.ArrayList;

import static map.GameMap.WALL;

/**
 * 6/9    38/40
 * Created by DNS on 21.04.2017.
 */
public class Enemy {

    private static ArrayList<Enemy> enemieList = new ArrayList<>();
    boolean ifDetectedGG;
    ArrayList<int[]> kreatingRoute = null;
    private int x;
    private int y;
    private double speed = 1;
    private int course;
    private boolean nalichieMarshruta = false;

    public Enemy(int x, int y) {
        this.ifDetectedGG = ifDetectedGG;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.course = course;
    }

    public static ArrayList<Enemy> getEnemieList() {
        return enemieList;
    }

    public double getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void remuve(ArrayList<Enemy> list) {
        if (list != null && !list.isEmpty()) {
            for (int x = 0; x > list.size(); x++) {
            }
        }
    }

    public void update() {

        if (!nalichieMarshruta) {
            int indexMap = (int) (Math.random() * GameMap.getMap().size());
            int getValue = GameMap.getMap().get(indexMap);

            if (getValue != WALL) {
                AlgoritmLI li = new AlgoritmLI();
                int end[] = {DekoderListMAPinX_Y.decoder(indexMap)[0], DekoderListMAPinX_Y.decoder(indexMap)[1]};
                int mas[] = {x, y};
                if (li.InitializationWave(mas[0], mas[1], end)) {
                    kreatingRoute = new ArrayList<>(li.PostroenieMarshruta(mas, end));

                    nalichieMarshruta = true;
                } else nalichieMarshruta = false;

            }
        }
        if (nalichieMarshruta) {
            if (kreatingRoute.size() == 1) nalichieMarshruta = false;
            setX(kreatingRoute.get(kreatingRoute.size()-1)[0]);
            setY(kreatingRoute.get(kreatingRoute.size()-1)[1]);
            kreatingRoute.remove(kreatingRoute.size()-1);
        }

    }

    public void draw(Graphics2D graf) {
        GraphisEn.draw(graf);
    }
}
