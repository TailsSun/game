package utils.algoritmLi;

import map.GameMap;

import java.util.ArrayList;

import static map.GameMap.lengthKub;
import static utils.Target.XXMap;
import static utils.algoritmLi.AlgoritmLI.DLINNA_MASSIVA;
import static utils.algoritmLi.AlgoritmLI.SHIRINA_MASSIVA;

/**
 * Created by DNS on 25.04.2017.
 */
public class RebildingMap {
    public static final double WALL = 1_000;
    public double[][] MapFoKreatingRoute;

    public double[][] getMapFoKreatingRoute() {
        return MapFoKreatingRoute;
    }

    public void Init_MapFoKreatingRoute(int speed) { // todo

        ArrayList<Integer> gameMap = new ArrayList<>(GameMap.getMap());
        MapFoKreatingRoute = new double[DLINNA_MASSIVA][SHIRINA_MASSIVA];


        for (int i = 0; i < gameMap.size(); i++) {
            if (gameMap.get(i) == 2) {
                int XX, YY;
                if (i > XXMap - 1) {            //если И больше дленны мапы по У
                    XX = i % XXMap;

                } else XX = i;

                if (i > XXMap - 1) {
                    YY = i / (XXMap);
                } else YY = 0;
                XX = XX * lengthKub;
                YY = YY * lengthKub;
                for (int y = 0; y < lengthKub; y++) {
                    for (int x = 0; x < lengthKub; x++) {
                        MapFoKreatingRoute[XX + x][YY + y] = WALL;

                    }
                }

            }
        }
    }
}
