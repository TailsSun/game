package utils;

import map.GameMap;

/**
 * Created by DNS on 24.04.2017.
 */
public class DekoderListMAPinX_Y {

    private static int XXMap = GameMap.getXXMap();

    public static int[] decoder(int index) {
        index = index - 1;
        int XX, YY;
        if (index > XXMap - 1) {            //если И больше дленны мапы по У
            XX = index % XXMap;

        } else XX = index;

        if (index > XXMap - 1) {
            YY = index / (XXMap);
        } else YY = 0;
//        XX = XX * lengthKub;
//        YY = YY * lengthKub;

        int[] result = {XX, YY};
        return result;
    }
}
