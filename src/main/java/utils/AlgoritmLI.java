package utils;

import game.Game;
import map.GameMap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by DNS on 22.04.2017.
 */
public class AlgoritmLI {

    private int DLINNA_MASSIVA = Game.width / 4;
    private int SHIRINA_MASSIVA = Game.height / 4;
    private double znachenie = 0.1;
    ArrayList<Integer> gameMap = GameMap.getMap();
    double[][] massiv = new double [DLINNA_MASSIVA][SHIRINA_MASSIVA];
    boolean izmeneniya;
/*
    int i = -1;
*/



    private static void Init_massiv() { // todo

    }


    private boolean OsnovnoiMetod(int []vershina, double [][]massiv, int []end) {
        while (true) {
            for (int i = 0; i < SHIRINA_MASSIVA; i++) {
                for (int ii = 0; ii < DLINNA_MASSIVA; ii++) {
                    if (massiv[ii][i] == znachenie) {
                        vershina[1] = ii;
                        vershina[2] = i;
                        if (Metod(vershina, massiv, znachenie)) izmeneniya = true;
                    }
                }
            }
            if (!izmeneniya) return false;
            if (Arrays.equals(vershina, end)) {
                return true;
            }
            znachenie = znachenie +1;
        }


    }
        private boolean Metod(int []vershina, double [][]massiv, double znachenie) {
            int x = vershina[1];
            int y = vershina[2];
            if (y == 0 && x < DLINNA_MASSIVA &&  x > 0 ) {
                if(DOWN(vershina,znachenie,massiv) &&
                        DOWNLEFT(vershina,znachenie,massiv) &&
                        DOWNRIGHT(vershina,znachenie,massiv) &&
                        LEFT(vershina,znachenie,massiv) &&
                        RIGHT(vershina,znachenie,massiv)) return  true;

            } else if (x == 0 && y < SHIRINA_MASSIVA && y > 0) {
                if (RIGHT(vershina, znachenie, massiv) &&
                        UP(vershina, znachenie, massiv) &&
                        DOWN(vershina, znachenie, massiv) &&
                        UPRIGHT(vershina, znachenie, massiv) &&
                        DOWNRIGHT(vershina, znachenie, massiv)) return true;
            } else if (x == 0 && y == 0) {
                if (RIGHT(vershina, znachenie, massiv) &&
                        DOWNRIGHT(vershina, znachenie, massiv) &&
                        DOWN(vershina, znachenie, massiv)) return true;
            } else if (x == 0 && y == SHIRINA_MASSIVA){


            } else{
                if(zapoln(vershina, znachenie, massiv)) return true;
            }

        return false;
    }

    private boolean UP (int []vershina, double znachenie, double [][]massiv) {
        boolean TRUE = false;
        if (massiv[vershina[1]][vershina[2] - 1] == 0) {
            massiv[vershina[1]][vershina[2] - 1] = znachenie;       // клетка выше
            TRUE = true;
        }
        if (TRUE) return true;
        return false;
    }
    private boolean DOWN (int []vershina, double znachenie, double [][]massiv) {
        boolean TRUE = false;
        if (massiv[vershina[1]][vershina[2] + 1] == 0) {
            massiv[vershina[1]][vershina[2] + 1] = znachenie;       // клетка выше
            TRUE = true;
        }
        if (TRUE) return true;
        return false;
    }

    private boolean RIGHT (int []vershina, double znachenie, double [][]massiv) {
        boolean TRUE = false;
        if (massiv[vershina[1] + 1][vershina[2]] == 0) {
            massiv[vershina[1] + 1][vershina[2]] = znachenie;       // клетка правее
            TRUE = true;
        }
        if (TRUE) return true;
        return false;
    }
    private boolean LEFT (int []vershina, double znachenie, double [][]massiv) {
        boolean TRUE = false;
        if (massiv[vershina[1] - 1][vershina[2]] == 0) {
            massiv[vershina[1] - 1][vershina[2]] = znachenie;       // клетка левее
            TRUE = true;
        }
        if (TRUE) return true;
        return false;
    }

    private boolean UPLEFT (int []vershina, double znachenie, double [][]massiv) {
        boolean TRUE = false;
        if (massiv[vershina[1] - 1][vershina[2] - 1] == 0) {
            massiv[vershina[1] - 1][vershina[2] - 1] = znachenie;       // клетка выше
            TRUE = true;
        }
        if (TRUE) return true;
        return false;
    }
    private boolean UPRIGHT (int []vershina, double znachenie, double [][]massiv) {
        boolean TRUE = false;
        if (massiv[vershina[1] + 1][vershina[2] - 1] == 0) {
            massiv[vershina[1] + 1][vershina[2] - 1] = znachenie;       // клетка выше
            TRUE = true;
        }
        if (TRUE) return true;
        return false;
    }
    private boolean DOWNLEFT (int []vershina, double znachenie, double [][]massiv) {
        boolean TRUE = false;
        if (massiv[vershina[1] - 1][vershina[2] + 1] == 0) {
            massiv[vershina[1] - 1][vershina[2] + 1] = znachenie;       // клетка выше
            TRUE = true;
        }
        return TRUE;
    }
    private boolean DOWNRIGHT (int []vershina, double znachenie, double [][]massiv) {
        boolean TRUE = false;
        if (massiv[vershina[1] + 1][vershina[2] + 1] == 0) {
            massiv[vershina[1] + 1][vershina[2] + 1] = znachenie;       // клетка выше
            TRUE = true;
        }
        if (TRUE) return true;
        return false;
    }















    private boolean zapoln(int []vershina, double znachenie, double [][]massiv){ // todo
        return false;
    }

}

