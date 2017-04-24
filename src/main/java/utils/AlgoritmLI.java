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
    private double[][] massiv = new double [DLINNA_MASSIVA][SHIRINA_MASSIVA];
    private  boolean izmeneniya;
    private static double WALL = 1_000_000;
    int chen  = 0;
    private ArrayList<int[]> marshrut;
/*
    int i = -1;
*/

    public AlgoritmLI() {
        this.massiv = massiv;
    }

    public double[][] getMassiv() {
        return massiv;
    }

    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        int q = XXMap - 1;

        AlgoritmLI li = new AlgoritmLI();
        li.Init_massiv(MAX_SPEED_EN);


        int mas[] = {0,0};
        int end [] = {19,14};

        if(li.OsnovnoiMetod(mas,li.getMassiv(),end)){
            ArrayList<int[]> peremennaya = new ArrayList<int[]>(li.PostroenieMarshruta(mas,li.getMassiv(),end));

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

    private  void Init_massiv(int speed) { // todo

        for ( int i = 0; i < gameMap.size(); i ++ ){
            if (gameMap.get(i) == GameMap.WALL){
                int raznica = GameMap.lengthKub / speed;
                for (int y = 0; y < raznica; y++) {
                    for (int x = 0; x < raznica; x ++){
                        int XX, YY;
                        if (i > XXMap - 1) {            //если И больше дленны мапы по У
                            XX = i % XXMap;

                        } else XX = i;

                        if (i > XXMap - 1) {
                            YY = i / (XXMap);
                        } else YY = 0;

                         massiv[XX * raznica+ x][YY * raznica + y] = WALL;
                    }
                }
            }

        }
    }


    private boolean OsnovnoiMetod(int []vers, double [][]massiv, int []end) {
        int []vershina = new int[2];
        vershina[0] = vers[0];
        vershina[1] = vers[1];
        massiv[0][0] = 1;
        while (true) {
            for (int i = 0; i < SHIRINA_MASSIVA; i++) {
                for (int ii = 0; ii < DLINNA_MASSIVA; ii++) {
                    if (massiv[ii][i] == znachenie) {
                        vershina[0] = ii;
                        vershina[1] = i;

                        if (Metod(vershina, massiv, znachenie)){
                            izmeneniya = true;
                            chen = 0;
                        }
                        else izmeneniya = false;
                    }
                }
            }
            if (!izmeneniya){
                chen++;
                if (chen > 11){
                    System.out.println("Изменений нет !!!!");
                    return false;
                }

            }

            if (Arrays.equals(vershina, end)) {
                System.out.println("Eaaa");
                print(massiv);
                return true;
            }
            znachenie += 0.2;
            BigDecimal bd = new BigDecimal(znachenie);
            znachenie =bd.setScale(2,5).doubleValue();
        }
    }


    private boolean Metod(int []vershina, double [][]massiv, double znachenie) {
            int x = vershina[0];
            int y = vershina[1];
            if (y == 0 && x < DLINNA_MASSIVA -1 &&  x > 0 ) {
                if(DOWN(vershina,znachenie,massiv) |
                        DOWNLEFT(vershina,znachenie,massiv) |
                        DOWNRIGHT(vershina,znachenie,massiv) |
                        LEFT(vershina,znachenie,massiv) |
                        RIGHT(vershina,znachenie,massiv)) return  true;

            } else if (x == 0 && y < SHIRINA_MASSIVA -1 && y > 0) {
                if (RIGHT(vershina, znachenie, massiv) |
                        UP(vershina, znachenie, massiv) |
                        DOWN(vershina, znachenie, massiv) |
                        UPRIGHT(vershina, znachenie, massiv) |
                        DOWNRIGHT(vershina, znachenie, massiv)) return true;
            } else if (x == 0 && y == 0) {
                if (RIGHT(vershina, znachenie, massiv) |
                        DOWNRIGHT(vershina, znachenie, massiv) |
                        DOWN(vershina, znachenie, massiv)) return true;
            } else if (x == 0 && y == SHIRINA_MASSIVA -1) {
                if (UP(vershina, znachenie, massiv) |
                        UPRIGHT(vershina, znachenie, massiv) |
                        RIGHT(vershina, znachenie, massiv)) return true;
            } else if (x > 0 && x < DLINNA_MASSIVA -1  && y == SHIRINA_MASSIVA -1) {
                if (UP(vershina, znachenie, massiv) |
                        UPRIGHT(vershina, znachenie, massiv) |
                        RIGHT(vershina, znachenie, massiv) |
                        LEFT(vershina, znachenie, massiv) |
                        UPLEFT(vershina, znachenie, massiv)) return true;
            } else if(x == DLINNA_MASSIVA -1 && y == 0) {
                if (LEFT(vershina, znachenie, massiv) |
                        DOWNLEFT(vershina, znachenie, massiv) |
                        DOWN(vershina, znachenie, massiv)) return true;
            } else if(x == DLINNA_MASSIVA -1 && y != 0 && y != SHIRINA_MASSIVA -1) {
                if (LEFT(vershina, znachenie, massiv) |
                        DOWNLEFT(vershina, znachenie, massiv) |
                        UP(vershina, znachenie, massiv) |
                        UPLEFT(vershina, znachenie, massiv) |
                        DOWN(vershina, znachenie, massiv)) return true;
            } else if (x == DLINNA_MASSIVA -1 && y == SHIRINA_MASSIVA -1){
                if (UP(vershina, znachenie, massiv) |
                        UPLEFT(vershina, znachenie, massiv) |
                        LEFT(vershina, znachenie, massiv)) return true;

            } else{
                if(RIGHT(vershina, znachenie, massiv) |
                        LEFT(vershina, znachenie, massiv) |
                        UP(vershina, znachenie, massiv) |
                        DOWN(vershina, znachenie, massiv) |
                        UPRIGHT(vershina, znachenie, massiv) |
                        UPLEFT(vershina, znachenie, massiv) |
                        DOWNRIGHT(vershina, znachenie, massiv) |
                        DOWNLEFT(vershina, znachenie, massiv)) return true;
            }

        return false;
    }

    private ArrayList<int[]> PostroenieMarshruta(int []vershina, double [][]massiv, int []end){
        marshrut = new ArrayList<int[]>();
        marshrut.add(end.clone()); // todo
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
                    marshrut.add( leftt);
                }
                if (right(x, y) < left(x, y) && right(x, y) < downLeft(x, y) && right(x, y) < downRight(x, y) && right(x, y) < down(x, y)) { // right
                    marshrut.add( rightt);
                }
                if (down(x, y) < left(x, y) && down(x, y) < downLeft(x, y) && down(x, y) < downRight(x, y) && down(x, y) < right(x, y)) { // down
                    marshrut.add(downn);
                }
                if (downLeft(x, y) < left(x, y) && downLeft(x, y) < down(x, y) && downLeft(x, y) < downRight(x, y) && downLeft(x, y) < right(x, y)) { // downLeft
                    marshrut.add( downLeftt);
                }
                if (downRight(x, y) < left(x, y) && downRight(x, y) < downLeft(x, y) && downRight(x, y) < down(x, y) && downRight(x, y) < right(x, y)) { // downRight
                    marshrut.add( downRightt);
                }


            } else if (x == 0 && y < SHIRINA_MASSIVA - 1 && y > 0) {
                if (right(x, y) < up(x, y) && right(x, y) < upRight(x, y) && right(x, y) < downRight(x, y) && right(x, y) < down(x, y)) { // right
                    marshrut.add( rightt);
                }
                if (down(x, y) < up(x, y) && down(x, y) < upRight(x, y) && down(x, y) < right(x, y) && down(x, y) < downRight(x, y)) { // down
                    marshrut.add( downn);
                }
                if (upRight(x, y) < up(x, y) && upRight(x, y) < right(x, y) && upRight(x, y) < downRight(x, y) && upRight(x, y) < down(x, y)) { // upRight
                    marshrut.add( upRightt);
                }
                if (downRight(x, y) < up(x, y) && downRight(x, y) < upRight(x, y) && downRight(x, y) < right(x, y) && downRight(x, y) < down(x, y)) { // downRight
                    marshrut.add(downRightt);
                }
            } else if (x == 0 && y == 0) {
                if (right(x, y) < downRight(x, y) && right(x, y) < down(x, y)) { // right
                    marshrut.add( rightt);
                }
                if (down(x, y) < right(x, y) && down(x, y) < downRight(x, y)) { // down
                    marshrut.add( downn);
                }
                if (downRight(x, y) < right(x, y) && downRight(x, y) < down(x, y)) { // downRight
                    marshrut.add( downRightt);
                }
            } else if (x == 0 && y == SHIRINA_MASSIVA - 1) {
                if (right(x, y) < upRight(x, y) && left(x, y) < up(x, y)) { // right
                    marshrut.add( rightt);
                }
                if (up(x, y) < upRight(x, y) && up(x, y) < right(x, y)) { // up
                    marshrut.add( upp);
                }
                if (upRight(x, y) < up(x, y) && upRight(x, y) < right(x, y)) { // upRight
                    marshrut.add( upRightt);
                }
            } else if (x > 0 && x < DLINNA_MASSIVA - 1 && y == SHIRINA_MASSIVA - 1) {
                if (left(x, y) < upLeft(x, y) && left(x, y) < up(x, y) && left(x, y) < upRight(x, y) && left(x, y) < right(x, y)) { // left
                    marshrut.add( leftt);
                }
                if (right(x, y) < left(x, y) && right(x, y) < upRight(x, y) && right(x, y) < up(x, y) && right(x, y) < upRight(x, y)) { // right
                    marshrut.add( rightt);
                }
                if (upLeft(x, y) < left(x, y) && upLeft(x, y) < up(x, y) && upLeft(x, y) < upRight(x, y) && upLeft(x, y) < right(x, y)) { // upLeft
                    marshrut.add( upLeftt);
                }
                if (upRight(x, y) < left(x, y) && upRight(x, y) < up(x, y) && upRight(x, y) < upLeft(x, y) && upRight(x, y) < right(x, y)) { // upRight
                    marshrut.add( upRightt);
                }
                if (up(x, y) < left(x, y) && up(x, y) < upLeft(x, y) && up(x, y) < upRight(x, y) && up(x, y) < right(x, y)) { // up
                    marshrut.add( upRightt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y == 0) {
                if (left(x, y) < downLeft(x, y) && left(x, y) < down(x, y)) { // left
                    marshrut.add( leftt);
                }
                if (down(x, y) < left(x, y) && down(x, y) < downLeft(x, y)) { // down
                    marshrut.add( downn);
                }
                if (downLeft(x, y) < left(x, y) && downLeft(x, y) < down(x, y)) { // downLeft
                    marshrut.add( downLeftt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y != 0 && y != SHIRINA_MASSIVA - 1) {
                if (left(x, y) < downLeft(x, y) && left(x, y) < down(x, y) && left(x, y) < up(x, y) && left(x, y) < upLeft(x, y)) { // left
                    marshrut.add( leftt);
                }
                if (downLeft(x, y) < left(x, y) && downLeft(x, y) < down(x, y) && downLeft(x, y) < up(x, y) && downLeft(x, y) < upLeft(x, y)) { // downLeft
                    marshrut.add( downLeftt);
                }
                if (down(x, y) < left(x, y) && down(x, y) < downLeft(x, y) && down(x, y) < up(x, y) && down(x, y) < upLeft(x, y)) { // down
                    marshrut.add( downn);
                }
                if (up(x, y) < upLeft(x, y) && up(x, y) < left(x, y) && up(x, y) < downLeft(x, y) && up(x, y) < down(x, y)) { // up
                    marshrut.add( upp);
                }
                if (upLeft(x, y) < up(x, y) && upLeft(x, y) < left(x, y) && upLeft(x, y) < downLeft(x, y) && upLeft(x, y) < down(x, y)) { // upLeft
                    marshrut.add( upLeftt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y == SHIRINA_MASSIVA - 1) {
                if (up(x, y) < upLeft(x, y) && up(x, y) < left(x, y)) { // up
                    marshrut.add( upp);
                }
                if (left(x, y) < upLeft(x, y) && left(x, y) < up(x, y)) { // left
                    marshrut.add( leftt);
                }
                if (upLeft(x, y) < left(x, y) && upLeft(x, y) < up(x, y)) { // upLeft
                    marshrut.add( upLeftt);
                }
            } else {
                if (left(x, y) < downLeft(x, y) && left(x, y) < down(x, y) && left(x, y) < downRight(x, y) && left(x, y) < right(x, y) && left(x, y) < upRight(x, y) && left(x, y) < up(x, y) && left(x, y) < upLeft(x, y)) { // left
                    marshrut.add( leftt);
                }
                else if (downLeft(x, y) < left(x, y) && downLeft(x, y) < down(x, y) && downLeft(x, y) < downRight(x, y) && downLeft(x, y) < right(x, y) && downLeft(x, y) < upRight(x, y) && downLeft(x, y) < up(x, y) && downLeft(x, y) < upLeft(x, y)) { // downLeft
                    marshrut.add( downLeftt);
                }
                else if (down(x, y) < left(x, y) && down(x, y) < downLeft(x, y) && down(x, y) < downRight(x, y) && down(x, y) < right(x, y) && down(x, y) < upRight(x, y) && down(x, y) < up(x, y) && down(x, y) < upLeft(x, y)) { // down
                    marshrut.add( downn);
                }
                else if (downRight(x, y) < left(x, y) && downRight(x, y) < downLeft(x, y) && downRight(x, y) < down(x, y) && downRight(x, y) < right(x, y) && downRight(x, y) < upRight(x, y) && downRight(x, y) < up(x, y) && downRight(x, y) < upLeft(x, y)) { // downRight
                    marshrut.add( downRightt);
                }
                else if (right(x, y) < left(x, y) && right(x, y) < downLeft(x, y) && right(x, y) < down(x, y) && right(x, y) < downRight(x, y) && right(x, y) < upRight(x, y) && right(x, y) < up(x, y) && right(x, y) < upLeft(x, y)) { // right
                    marshrut.add( rightt);
                }
                else if (upRight(x, y) < left(x, y) && upRight(x, y) < downLeft(x, y) && upRight(x, y) < down(x, y) && upRight(x, y) < downRight(x, y) && upRight(x, y) < right(x, y) && upRight(x, y) < up(x, y) && upRight(x, y) < upLeft(x, y)) { // upRight
                    marshrut.add( upRightt);
                }
                else if (up(x, y) < left(x, y) && up(x, y) < downLeft(x, y) && up(x, y) < down(x, y) && up(x, y) < downRight(x, y) && up(x, y) < right(x, y) && up(x, y) < upRight(x, y) && up(x, y) < upLeft(x, y)) { // up
                    marshrut.add( upp);
                }
                else if (upLeft(x, y) < left(x, y) && upLeft(x, y) < downLeft(x, y) && upLeft(x, y) < down(x, y) && upLeft(x, y) < downRight(x, y) && upLeft(x, y) < right(x, y) && upLeft(x, y) < upRight(x, y) && upLeft(x, y) < up(x, y)) { // upLeft
                    marshrut.add( upLeftt);
                }


            }
            end[0] = marshrut.get(marshrut.size() -1 )[0];
            end[1] = marshrut.get(marshrut.size() -1 )[1];
          /*  for (int s[]: marshrut){
                for (int q: s){
                    System.out.print(q + "   ");
                }
                System.out.println(); // todo
            }
            System.out.println();
*/
        }

return marshrut;
    }

    private double left(int x, int y) {
        return massiv[x - 1][y];
    }
    private double right (int x, int y) {
        return massiv[x + 1][y];
    }
    private double up(int x, int y) {
        return massiv[x][y - 1];
    }
    private double down(int x, int y) {
        return massiv[x][y + 1];
    }
    private double downRight(int x, int y) {
        return massiv[x + 1][y + 1];
    }

    private double downLeft(int x, int y) {
        return  massiv[x - 1][y + 1];
    }

    private double upRight (int x, int y) {
        return massiv[x + 1][y - 1];
    }
    private double upLeft(int x, int y) {
        return massiv[x - 1][y - 1];
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
            massiv[vershina[0] - 1][vershina[1] - 1] = new BigDecimal(znachenie + 1.2).setScale(2,5).doubleValue();;
            return true;
        }
        return false;
    }

    private boolean UPRIGHT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] + 1][vershina[1]] == WALL || massiv[vershina[0] ][vershina[1] -1] == WALL ) return false;
        if (massiv[vershina[0] + 1][vershina[1] - 1] == 0) {
            massiv[vershina[0] + 1][vershina[1] - 1] = new BigDecimal(znachenie + 1.2).setScale(2,5).doubleValue();;
            return true;
        }
        return false;
    }

    private boolean DOWNLEFT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] - 1][vershina[1]] == WALL || massiv[vershina[0]][vershina[1] + 1] == WALL) return false;
        if (massiv[vershina[0] - 1][vershina[1] + 1] == 0) {
            massiv[vershina[0] - 1][vershina[1] + 1] =new BigDecimal(znachenie + 1.2).setScale(2,5).doubleValue();;
            return true;
        }
        return false;
    }

    private boolean DOWNRIGHT (int []vershina, double znachenie, double [][]massiv) {
        if (massiv[vershina[0] + 1][vershina[1] ] == WALL || massiv[vershina[0] ][vershina[1] + 1] == WALL) return false;
        if (massiv[vershina[0] + 1][vershina[1] + 1] == 0) {
            massiv[vershina[0] + 1][vershina[1] + 1] = new BigDecimal(znachenie + 1.2).setScale(2,5).doubleValue();;
            return true;
        }
        return false;
    }

    /*private static  int[] MAX(int x, int y, double a1, double a2, double a3){
        if (x == DLINNA_MASSIVA && y == SHIRINA_MASSIVA){

        }
    }*/


}

