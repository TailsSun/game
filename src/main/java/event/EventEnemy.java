package event;

import enemy.Enemy;
import map.GameMap;
import utils.DekoderListMAPinX_Y;
import utils.Time;

import static map.GameMap.WALL;

/**
 * Created by DNS on 21.04.2017.
 */
public class EventEnemy {
    private static long lastTime = 0;
    private static final long TIME_FO_GENERATE_NEW_Enemy = Time.SEKOND * 4;//spawn 4 sek

    public static boolean eventSpawnEn(){
        if (Time.getTime() > lastTime + TIME_FO_GENERATE_NEW_Enemy && Enemy.getEnemieList().size() < 1){        //пришло время генерации врага
            int indexMap = (int) (Math.random() * GameMap.getMap().size());
            int getValue = GameMap.getMap().get(indexMap - 1);

            if( getValue != WALL){


                int [] listX_Y = DekoderListMAPinX_Y.decoder(indexMap);
                int x = listX_Y[0];
                int y = listX_Y[1];
                Enemy enemy = new Enemy(x,y);
                Enemy.getEnemieList().add(enemy);
                lastTime = Time.getTime();
                return true;
            }

        }
        return false;

    }
}
