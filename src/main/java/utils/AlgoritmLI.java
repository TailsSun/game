package utils;

import game.Game;
import map.GameMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.Target.XXMap;

/**
 * Created by DNS on 22.04.2017.
 */
public class AlgoritmLI {
    public static int MAX_SPEED_EN = 40;
    private static int DLINNA_MASSIVA = Game.width / MAX_SPEED_EN;
    private static int SHIRINA_MASSIVA = Game.height / MAX_SPEED_EN;
    private double znachenie = 1;
    private static ArrayList<Integer> gameMap = GameMap.getMap();
    private double[][] MapFoKreatingRoute = new double [DLINNA_MASSIVA][SHIRINA_MASSIVA];
    private  boolean testTRUE;
    private static double WALL = 1_000_000;
    private int chen  = 0;
    private ArrayList<int[]> listRoute;
/*
    int i = -1;
*/

    public AlgoritmLI() {
        this.MapFoKreatingRoute = MapFoKreatingRoute;
    }

    public double[][] getMapFoKreatingRoute() {
        return MapFoKreatingRoute;
    }

    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        int q = XXMap - 1;

        AlgoritmLI li = new AlgoritmLI();
        li.Init_MapFoKreatingRoute(MAX_SPEED_EN);

        int mas[] = {0,0};
        int end [] = {19,14};

        if(li.InitializationWave(mas,li.getMapFoKreatingRoute(),end)){
            ArrayList<int[]> peremennaya = new ArrayList<int[]>(li.PostroenieMarshruta(mas,li.getMapFoKreatingRoute(),end));

            for (int[] res: peremennaya){
                for (int result:res){
                    System.out.print(result+ "  ");
                }
                System.out.println();
            }
        }
    }

    private static void print(double [][] massiv){
        for (int i = 0; i < SHIRINA_MASSIVA; i++) {
            for (int ii = 0; ii < DLINNA_MASSIVA; ii++) {
                System.out.print(massiv[ii][i] + "  ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }

    private  void Init_MapFoKreatingRoute(int speed) { // todo

        for ( int i = 0; i < gameMap.size(); i ++ ){
            if (gameMap.get(i) == GameMap.WALL){
                int mapScale = GameMap.lengthKub / speed;
                for (int y = 0; y < mapScale; y++) {
                    for (int x = 0; x < mapScale; x ++){
                        int XXtemp, YYtemp;
                        if (i > XXMap - 1) {            //если И больше дленны мапы по У
                            XXtemp = i % XXMap;

                        } else XXtemp = i;

                        if (i > XXMap - 1) {
                            YYtemp = i / (XXMap);
                        } else YYtemp = 0;

                         MapFoKreatingRoute[XXtemp * mapScale+ x][YYtemp * mapScale + y] = WALL;
                    }
                }
            }
        }
    }

    private boolean InitializationWave(int []startPoint, double [][]massiv, int []endPoint) {
        int []tempStartPoint = new int[2];
        tempStartPoint[0] = startPoint[0];
        tempStartPoint[1] = startPoint[1];
        massiv[0][0] = 1;
        while (true) {
            for (int i = 0; i < SHIRINA_MASSIVA; i++) {
                for (int ii = 0; ii < DLINNA_MASSIVA; ii++) {
                    if (massiv[ii][i] == znachenie) {
                        tempStartPoint[0] = ii;
                        tempStartPoint[1] = i;

                        if (addWave(tempStartPoint, massiv, znachenie)){
                            testTRUE = true;
                            chen = 0;
                        }
                        else testTRUE = false;
                    }
                }
            }
            if (!testTRUE){
                chen++;
                if (chen > 11){
                    System.out.println("Изменений нет !!!!");
                    return false;
                }
            }

            if (Arrays.equals(tempStartPoint, endPoint)) {
                System.out.println("Eaaa");
                print(massiv);
                return true;
            }
            znachenie += 0.2;
            BigDecimal bd = new BigDecimal(znachenie);
            znachenie =bd.setScale(2,5).doubleValue();
        }
    }


    private boolean addWave(int []startPoint, double [][]initMap, double znachenie) {
            int x = startPoint[0];
            int y = startPoint[1];
            if (y == 0 && x < DLINNA_MASSIVA -1 &&  x > 0 ) {
                if(DOWN(startPoint,znachenie,initMap) |
                        DOWNLEFT(startPoint,znachenie,initMap) |
                        DOWNRIGHT(startPoint,znachenie,initMap) |
                        LEFT(startPoint,znachenie,initMap) |
                        RIGHT(startPoint,znachenie,initMap)) return  true;

            } else if (x == 0 && y < SHIRINA_MASSIVA -1 && y > 0) {
                if (RIGHT(startPoint, znachenie, initMap) |
                        UP(startPoint, znachenie, initMap) |
                        DOWN(startPoint, znachenie, initMap) |
                        UPRIGHT(startPoint, znachenie, initMap) |
                        DOWNRIGHT(startPoint, znachenie, initMap)) return true;
            } else if (x == 0 && y == 0) {
                if (RIGHT(startPoint, znachenie, initMap) |
                        DOWNRIGHT(startPoint, znachenie, initMap) |
                        DOWN(startPoint, znachenie, initMap)) return true;
            } else if (x == 0 && y == SHIRINA_MASSIVA -1) {
                if (UP(startPoint, znachenie, initMap) |
                        UPRIGHT(startPoint, znachenie, initMap) |
                        RIGHT(startPoint, znachenie, initMap)) return true;
            } else if (x > 0 && x < DLINNA_MASSIVA -1  && y == SHIRINA_MASSIVA -1) {
                if (UP(startPoint, znachenie, initMap) |
                        UPRIGHT(startPoint, znachenie, initMap) |
                        RIGHT(startPoint, znachenie, initMap) |
                        LEFT(startPoint, znachenie, initMap) |
                        UPLEFT(startPoint, znachenie, initMap)) return true;
            } else if(x == DLINNA_MASSIVA -1 && y == 0) {
                if (LEFT(startPoint, znachenie, initMap) |
                        DOWNLEFT(startPoint, znachenie, initMap) |
                        DOWN(startPoint, znachenie, initMap)) return true;
            } else if(x == DLINNA_MASSIVA -1 && y != 0 && y != SHIRINA_MASSIVA -1) {
                if (LEFT(startPoint, znachenie, initMap) |
                        DOWNLEFT(startPoint, znachenie, initMap) |
                        UP(startPoint, znachenie, initMap) |
                        UPLEFT(startPoint, znachenie, initMap) |
                        DOWN(startPoint, znachenie, initMap)) return true;
            } else if (x == DLINNA_MASSIVA -1 && y == SHIRINA_MASSIVA -1){
                if (UP(startPoint, znachenie, initMap) |
                        UPLEFT(startPoint, znachenie, initMap) |
                        LEFT(startPoint, znachenie, initMap)) return true;

            } else{
                if(RIGHT(startPoint, znachenie, initMap) |
                        LEFT(startPoint, znachenie, initMap) |
                        UP(startPoint, znachenie, initMap) |
                        DOWN(startPoint, znachenie, initMap) |
                        UPRIGHT(startPoint, znachenie, initMap) |
                        UPLEFT(startPoint, znachenie, initMap) |
                        DOWNRIGHT(startPoint, znachenie, initMap) |
                        DOWNLEFT(startPoint, znachenie, initMap)) return true;
            }

        return false;
    }

    private ArrayList<int[]> PostroenieMarshruta(int []vershina, double [][]massiv, int []end){
        listRoute = new ArrayList<int[]>();
        listRoute.add(end.clone()); // todo
        while (!Arrays.equals(vershina, end)) {


            int x = end[0];
            int y = end[1];

            int leftt[] = {x - 1, y};
            int rightt[] = {x + 1, y};
            int upp[] = {x, y - 1};
            int downn[] = {x, y + 1};
            int downRightt[] = {x + 1, y + 1};
            int downLeftt[] = {x - 1, y + 1};
            int upRightt[] = {x + 1, y - 1};
            int upLeftt[] = {x - 1, y - 1};

            if (y == 0 && x < DLINNA_MASSIVA - 1 && x > 0) {
                if (left(x, y) < downLeft(x, y) && left(x, y) < down(x, y) && left(x, y) < downRight(x, y) && left(x, y) < right(x, y)) { // left
                    listRoute.add( leftt);
                }
                if (right(x, y) < left(x, y) && right(x, y) < downLeft(x, y) && right(x, y) < downRight(x, y) && right(x, y) < down(x, y)) { // right
                    listRoute.add( rightt);
                }
                if (down(x, y) < left(x, y) && down(x, y) < downLeft(x, y) && down(x, y) < downRight(x, y) && down(x, y) < right(x, y)) { // down
                    listRoute.add(downn);
                }
                if (downLeft(x, y) < left(x, y) && downLeft(x, y) < down(x, y) && downLeft(x, y) < downRight(x, y) && downLeft(x, y) < right(x, y)) { // downLeft
                    listRoute.add( downLeftt);
                }
                if (downRight(x, y) < left(x, y) && downRight(x, y) < downLeft(x, y) && downRight(x, y) < down(x, y) && downRight(x, y) < right(x, y)) { // downRight
                    listRoute.add( downRightt);
                }


            } else if (x == 0 && y < SHIRINA_MASSIVA - 1 && y > 0) {
                if (right(x, y) < up(x, y) && right(x, y) < upRight(x, y) && right(x, y) < downRight(x, y) && right(x, y) < down(x, y)) { // right
                    listRoute.add( rightt);
                }
                if (down(x, y) < up(x, y) && down(x, y) < upRight(x, y) && down(x, y) < right(x, y) && down(x, y) < downRight(x, y)) { // down
                    listRoute.add( downn);
                }
                if (upRight(x, y) < up(x, y) && upRight(x, y) < right(x, y) && upRight(x, y) < downRight(x, y) && upRight(x, y) < down(x, y)) { // upRight
                    listRoute.add( upRightt);
                }
                if (downRight(x, y) < up(x, y) && downRight(x, y) < upRight(x, y) && downRight(x, y) < right(x, y) && downRight(x, y) < down(x, y)) { // downRight
                    listRoute.add(downRightt);
                }
            } else if (x == 0 && y == 0) {
                if (right(x, y) < downRight(x, y) && right(x, y) < down(x, y)) { // right
                    listRoute.add( rightt);
                }
                if (down(x, y) < right(x, y) && down(x, y) < downRight(x, y)) { // down
                    listRoute.add( downn);
                }
                if (downRight(x, y) < right(x, y) && downRight(x, y) < down(x, y)) { // downRight
                    listRoute.add( downRightt);
                }
            } else if (x == 0 && y == SHIRINA_MASSIVA - 1) {
                if (right(x, y) < upRight(x, y) && left(x, y) < up(x, y)) { // right
                    listRoute.add( rightt);
                }
                if (up(x, y) < upRight(x, y) && up(x, y) < right(x, y)) { // up
                    listRoute.add( upp);
                }
                if (upRight(x, y) < up(x, y) && upRight(x, y) < right(x, y)) { // upRight
                    listRoute.add( upRightt);
                }
            } else if (x > 0 && x < DLINNA_MASSIVA - 1 && y == SHIRINA_MASSIVA - 1) {
                if (left(x, y) < upLeft(x, y) && left(x, y) < up(x, y) && left(x, y) < upRight(x, y) && left(x, y) < right(x, y)) { // left
                    listRoute.add( leftt);
                }
                if (right(x, y) < left(x, y) && right(x, y) < upRight(x, y) && right(x, y) < up(x, y) && right(x, y) < upRight(x, y)) { // right
                    listRoute.add( rightt);
                }
                if (upLeft(x, y) < left(x, y) && upLeft(x, y) < up(x, y) && upLeft(x, y) < upRight(x, y) && upLeft(x, y) < right(x, y)) { // upLeft
                    listRoute.add( upLeftt);
                }
                if (upRight(x, y) < left(x, y) && upRight(x, y) < up(x, y) && upRight(x, y) < upLeft(x, y) && upRight(x, y) < right(x, y)) { // upRight
                    listRoute.add( upRightt);
                }
                if (up(x, y) < left(x, y) && up(x, y) < upLeft(x, y) && up(x, y) < upRight(x, y) && up(x, y) < right(x, y)) { // up
                    listRoute.add( upRightt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y == 0) {
                if (left(x, y) < downLeft(x, y) && left(x, y) < down(x, y)) { // left
                    listRoute.add( leftt);
                }
                if (down(x, y) < left(x, y) && down(x, y) < downLeft(x, y)) { // down
                    listRoute.add( downn);
                }
                if (downLeft(x, y) < left(x, y) && downLeft(x, y) < down(x, y)) { // downLeft
                    listRoute.add( downLeftt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y != 0 && y != SHIRINA_MASSIVA - 1) {
                if (left(x, y) < downLeft(x, y) && left(x, y) < down(x, y) && left(x, y) < up(x, y) && left(x, y) < upLeft(x, y)) { // left
                    listRoute.add( leftt);
                }
                if (downLeft(x, y) < left(x, y) && downLeft(x, y) < down(x, y) && downLeft(x, y) < up(x, y) && downLeft(x, y) < upLeft(x, y)) { // downLeft
                    listRoute.add( downLeftt);
                }
                if (down(x, y) < left(x, y) && down(x, y) < downLeft(x, y) && down(x, y) < up(x, y) && down(x, y) < upLeft(x, y)) { // down
                    listRoute.add( downn);
                }
                if (up(x, y) < upLeft(x, y) && up(x, y) < left(x, y) && up(x, y) < downLeft(x, y) && up(x, y) < down(x, y)) { // up
                    listRoute.add( upp);
                }
                if (upLeft(x, y) < up(x, y) && upLeft(x, y) < left(x, y) && upLeft(x, y) < downLeft(x, y) && upLeft(x, y) < down(x, y)) { // upLeft
                    listRoute.add( upLeftt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y == SHIRINA_MASSIVA - 1) {
                if (up(x, y) < upLeft(x, y) && up(x, y) < left(x, y)) { // up
                    listRoute.add( upp);
                }
                if (left(x, y) < upLeft(x, y) && left(x, y) < up(x, y)) { // left
                    listRoute.add( leftt);
                }
                if (upLeft(x, y) < left(x, y) && upLeft(x, y) < up(x, y)) { // upLeft
                    listRoute.add( upLeftt);
                }
            } else {
                if (left(x, y) < downLeft(x, y) && left(x, y) < down(x, y) && left(x, y) < downRight(x, y) && left(x, y) < right(x, y) && left(x, y) < upRight(x, y) && left(x, y) < up(x, y) && left(x, y) < upLeft(x, y)) { // left
                    listRoute.add( leftt);
                }
                else if (downLeft(x, y) < left(x, y) && downLeft(x, y) < down(x, y) && downLeft(x, y) < downRight(x, y) && downLeft(x, y) < right(x, y) && downLeft(x, y) < upRight(x, y) && downLeft(x, y) < up(x, y) && downLeft(x, y) < upLeft(x, y)) { // downLeft
                    listRoute.add( downLeftt);
                }
                else if (down(x, y) < left(x, y) && down(x, y) < downLeft(x, y) && down(x, y) < downRight(x, y) && down(x, y) < right(x, y) && down(x, y) < upRight(x, y) && down(x, y) < up(x, y) && down(x, y) < upLeft(x, y)) { // down
                    listRoute.add( downn);
                }
                else if (downRight(x, y) < left(x, y) && downRight(x, y) < downLeft(x, y) && downRight(x, y) < down(x, y) && downRight(x, y) < right(x, y) && downRight(x, y) < upRight(x, y) && downRight(x, y) < up(x, y) && downRight(x, y) < upLeft(x, y)) { // downRight
                    listRoute.add( downRightt);
                }
                else if (right(x, y) < left(x, y) && right(x, y) < downLeft(x, y) && right(x, y) < down(x, y) && right(x, y) < downRight(x, y) && right(x, y) < upRight(x, y) && right(x, y) < up(x, y) && right(x, y) < upLeft(x, y)) { // right
                    listRoute.add( rightt);
                }
                else if (upRight(x, y) < left(x, y) && upRight(x, y) < downLeft(x, y) && upRight(x, y) < down(x, y) && upRight(x, y) < downRight(x, y) && upRight(x, y) < right(x, y) && upRight(x, y) < up(x, y) && upRight(x, y) < upLeft(x, y)) { // upRight
                    listRoute.add( upRightt);
                }
                else if (up(x, y) < left(x, y) && up(x, y) < downLeft(x, y) && up(x, y) < down(x, y) && up(x, y) < downRight(x, y) && up(x, y) < right(x, y) && up(x, y) < upRight(x, y) && up(x, y) < upLeft(x, y)) { // up
                    listRoute.add( upp);
                }
                else if (upLeft(x, y) < left(x, y) && upLeft(x, y) < downLeft(x, y) && upLeft(x, y) < down(x, y) && upLeft(x, y) < downRight(x, y) && upLeft(x, y) < right(x, y) && upLeft(x, y) < upRight(x, y) && upLeft(x, y) < up(x, y)) { // upLeft
                    listRoute.add( upLeftt);
                }


            }
            end[0] = listRoute.get(listRoute.size() -1 )[0];
            end[1] = listRoute.get(listRoute.size() -1 )[1];
          /*  for (int s[]: listRoute){
                for (int q: s){
                    System.out.print(q + "   ");
                }
                System.out.println(); // todo
            }
            System.out.println();
*/
        }

return listRoute;
    }

    private double left(int x, int y) {
        return MapFoKreatingRoute[x - 1][y];
    }
    private double right (int x, int y) {
        return MapFoKreatingRoute[x + 1][y];
    }
    private double up(int x, int y) {
        return MapFoKreatingRoute[x][y - 1];
    }
    private double down(int x, int y) {
        return MapFoKreatingRoute[x][y + 1];
    }
    private double downRight(int x, int y) {
        return MapFoKreatingRoute[x + 1][y + 1];
    }

    private double downLeft(int x, int y) {
        return  MapFoKreatingRoute[x - 1][y + 1];
    }

    private double upRight (int x, int y) {
        return MapFoKreatingRoute[x + 1][y - 1];
    }
    private double upLeft(int x, int y) {
        return MapFoKreatingRoute[x - 1][y - 1];
    }

    private boolean UP (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0]][vershina[1] - 1] == 0) {
            massiv[vershina[0]][vershina[1] - 1] = znachenie + 1;       // клетка выше
            return true;
        }
        return false;
    }
    private boolean DOWN (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0]][vershina[1] + 1] == 0) {
            massiv[vershina[0]][vershina[1] + 1] = znachenie + 1;
            return true;
        }
        return false;
    }

    private boolean RIGHT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] + 1][vershina[1]] == 0) {
            massiv[vershina[0] + 1][vershina[1]] = znachenie + 1;       // клетка правее
            return true;
        }
        return false;
    }
    private boolean LEFT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] - 1][vershina[1]] == 0) {
            massiv[vershina[0] - 1][vershina[1]] = znachenie + 1;       // клетка левее
            return true;
        }
        return false;
    }

    private boolean UPLEFT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] - 1][vershina[1]] == WALL || massiv[vershina[0]][vershina[1] -1] == WALL)return false;
        if (massiv[vershina[0] - 1][vershina[1] - 1] == 0) {
            massiv[vershina[0] - 1][vershina[1] - 1] = new BigDecimal(znachenie + 1.2).setScale(2,5).doubleValue();
            return true;
        }
        return false;
    }

    private boolean UPRIGHT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] + 1][vershina[1]] == WALL || massiv[vershina[0] ][vershina[1] -1] == WALL ) return false;
        if (massiv[vershina[0] + 1][vershina[1] - 1] == 0) {
            massiv[vershina[0] + 1][vershina[1] - 1] = new BigDecimal(znachenie + 1.2).setScale(2,5).doubleValue();
            return true;
        }
        return false;
    }

    private boolean DOWNLEFT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] - 1][vershina[1]] == WALL || massiv[vershina[0]][vershina[1] + 1] == WALL) return false;
        if (massiv[vershina[0] - 1][vershina[1] + 1] == 0) {
            massiv[vershina[0] - 1][vershina[1] + 1] =new BigDecimal(znachenie + 1.2).setScale(2,5).doubleValue();
            return true;
        }
        return false;
    }

    private boolean DOWNRIGHT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] + 1][vershina[1] ] == WALL || massiv[vershina[0] ][vershina[1] + 1] == WALL) return false;
        if (massiv[vershina[0] + 1][vershina[1] + 1] == 0) {
            massiv[vershina[0] + 1][vershina[1] + 1] = new BigDecimal(znachenie + 1.2).setScale(2,5).doubleValue();
            return true;
        }
        return false;
    }

    /*private static  int[] MAX(int x, int y, double a1, double a2, double a3){
        if (x == DLINNA_MASSIVA && y == SHIRINA_MASSIVA){

        }
    }*/


}

