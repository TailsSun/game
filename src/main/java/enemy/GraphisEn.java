package enemy;

import game.Game;
import grafika.CutTexture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by DNS on 21.04.2017.
 */
public class GraphisEn {

    // temp

    //temp

    private static final int widthEn = 22;



    public static void draw(Graphics2D graphics) {
        CutTexture cutTexture = new CutTexture(Game.enemyPic);
        if(Enemy.getEnemieList() != null && !Enemy.getEnemieList().isEmpty()) {
            for (int i = 0; i < Enemy.getEnemieList().size(); i++) {
                double x = Enemy.getEnemieList().get(i).getX();
                double y = Enemy.getEnemieList().get(i).getY();

                BufferedImage h = cutTexture.cut(6, 9, 38, 40);

                graphics.drawImage(h.getScaledInstance(widthEn, widthEn, 2), (int) x, (int) y, null);
            }
        }
    }

}
