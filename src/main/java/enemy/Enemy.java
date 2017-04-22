package enemy;

import event.EventEnemy;

import java.util.ArrayList;

/**
 * 6/9    38/40
 * Created by DNS on 21.04.2017.
 */
public class Enemy {

    boolean ifDetectedGG;
    private double x;
    private  double y;
    public double speed = 2;
    private int nap;

    private static ArrayList<Enemy> enemieList;



    public Enemy() {
        this.ifDetectedGG = ifDetectedGG;
        this.x = 0; //todo
        this.y = 0;
        this.speed = speed;
        this.nap = nap;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static ArrayList<Enemy> getEnemieList() {
        return enemieList;
    }
    public void update(){
        EventEnemy.eventSpawnEn();
        if (!ifDetectedGG){

        }

    }

    public void remuve(ArrayList<Enemy> list){
        if(list != null && !list.isEmpty()) {
            for (int x = 0; x > list.size(); x++) {

            }
        }

    }

    public static void updata() {

    }
}
