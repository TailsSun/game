package utils.algoritmLi;

import map.GameMap;

import java.util.ArrayList;

import static utils.Target.XXMap;
import static utils.algoritmLi.AlgoritmLI.DLINNA_MASSIVA;
import static utils.algoritmLi.AlgoritmLI.SHIRINA_MASSIVA;

/**
 * Created by DNS on 25.04.2017.
 */
public class RebildingMap {
    public double[][] MapFoKreatingRoute;
    public static final double WALL = 1_000;

    public double[][] getMapFoKreatingRoute() {
        return MapFoKreatingRoute;
    }

    public   void Init_MapFoKreatingRoute(int speed) { // todo

        ArrayList<Integer> gameMap = new ArrayList<>( GameMap.getMap());
        MapFoKreatingRoute = new double[DLINNA_MASSIVA][SHIRINA_MASSIVA];

        /*for (int i = 0; i < SHIRINA_MASSIVA; i ++){
            for ( int h = 0; h < DLINNA_MASSIVA; h ++){
                MapFoKreatingRoute[h][i] = 1000;
            }
        }*/

        for ( int i = 0; i < gameMap.size(); i ++ ){
            if (gameMap.get(i) == GameMap.WALL){
                int mapScale = GameMap.lengthKub / speed;
                for (int y = 0; y < mapScale; y++) {
                    for (int x = 0; x < mapScale; x ++){
                        int XX, YY;
                        if (i > XXMap - 1) {            //если И больше дленны мапы по У
                            XX = i % XXMap;

                        } else XX = i;

                        if (i > XXMap - 1) {
                            YY = i / (XXMap);
                        } else YY = 0;

                        MapFoKreatingRoute[(XX * mapScale)+ x][(YY * mapScale) + y] = WALL;
                    }
                }
            }
        }
    }
}
