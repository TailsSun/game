package bullet;

import utils.Target;

import java.awt.*;

/**
 * Created by DNS on 12.04.2017.
 */
public interface Bullet {
 double x = 0;
 double y = 0;
 int r = 0;
 Color color = Color.pink;
 double speed = 0;
 Target TARGET = new Target();


public boolean removeOut();
public void update();
public void draw(Graphics2D graf);
}
