package event;

import enemy.Enemy;
import utils.Time;

/**
 * Created by DNS on 21.04.2017.
 */
public class EventEnemy {
    private static long lastTime = 0;
    private static final long TIME_FO_GENERATE_NEW_Enemy = Time.SEKOND * 4;//spawn 4 sek

    public static boolean eventSpawnEn(){
        if (Time.getTime() > lastTime + TIME_FO_GENERATE_NEW_Enemy){
            Enemy.getEnemieList().add(new Enemy());
            return true;
        }
        return false;

    }
}
