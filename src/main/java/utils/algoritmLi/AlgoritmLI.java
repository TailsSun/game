package utils.algoritmLi;

import game.Game;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.algoritmLi.RebildingMap.WALL;

/**
 * Created by DNS on 22.04.2017.
 */
public class AlgoritmLI {
    public static int MAX_SPEED_EN = 1;
    public static int DLINNA_MASSIVA = Game.width / MAX_SPEED_EN;
    public static int SHIRINA_MASSIVA = Game.height / MAX_SPEED_EN;
    double[][] MapFoKreatingRoute;
    private double znachenie = 1;
    private boolean testTRUE;
    private int chen = 0;
    private ArrayList<int[]> listRoute;
    ArrayList<int[]> temp;

    public AlgoritmLI() {

    }

//    private void print(double[][] massiv) {
//        for (int i = 0; i < SHIRINA_MASSIVA; i++) {
//            for (int ii = 0; ii < DLINNA_MASSIVA; ii++) {
//                System.out.print(massiv[ii][i] + "  ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//    }

    public boolean InitializationWave(int x, int y, int[] endPoint) {

        temp = new ArrayList<>();

        ArrayList<int[]> _localTmp = new ArrayList<>();
        _localTmp.add(new int[]{x,y});
        RebildingMap rebildingMap = new RebildingMap();
        rebildingMap.Init_MapFoKreatingRoute(MAX_SPEED_EN);
        MapFoKreatingRoute = rebildingMap.getMapFoKreatingRoute().clone();

        MapFoKreatingRoute[x][y] = 1;
        while (true) {
            for (int i = 0; i < _localTmp.size(); i++) {
                        int[] q = Arrays.copyOf(_localTmp.get(i),2);


                        if (q[0] == endPoint[0] && q[1] == endPoint[1]) {
                            System.out.println("Eaaa");
                            return true;  //путь найден
                        }

                        if (addWave(q, MapFoKreatingRoute, znachenie)) {
                            testTRUE = true;
                            chen = 0;

                        } else testTRUE = false;


            }
            if (!testTRUE) {
                chen++;
                if (chen > 2) {
                    System.out.println("Изменений нет !!!!");

                    return false;


                }


            }
            _localTmp = new ArrayList<>(temp);
            temp.clear();
            znachenie ++;
            BigDecimal bd = new BigDecimal(znachenie);
            znachenie = bd.setScale(2, 5).doubleValue();
        }
    }


    private boolean addWave(int[] startPoint, double[][] MapFoKreatingRoute, double znachenie) {

        int x = startPoint[0];
        int y = startPoint[1];
        if (y == 0 && x < DLINNA_MASSIVA - 1 && x > 0) {
            if (DOWN(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWNLEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWNRIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    LEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    RIGHT(startPoint, znachenie, MapFoKreatingRoute)) return true;

        } else if (x == 0 && y < SHIRINA_MASSIVA - 1 && y > 0) {
            if (RIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    UP(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWN(startPoint, znachenie, MapFoKreatingRoute) |
                    UPRIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWNRIGHT(startPoint, znachenie, MapFoKreatingRoute)) return true;
        } else if (x == 0 && y == 0) {
            if (RIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWNRIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWN(startPoint, znachenie, MapFoKreatingRoute)) return true;
        } else if (x == 0 && y == SHIRINA_MASSIVA - 1) {
            if (UP(startPoint, znachenie, MapFoKreatingRoute) |
                    UPRIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    RIGHT(startPoint, znachenie, MapFoKreatingRoute)) return true;
        } else if (x > 0 && x < DLINNA_MASSIVA - 1 && y == SHIRINA_MASSIVA - 1) {
            if (UP(startPoint, znachenie, MapFoKreatingRoute) |
                    UPRIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    RIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    LEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    UPLEFT(startPoint, znachenie, MapFoKreatingRoute)) return true;
        } else if (x == DLINNA_MASSIVA - 1 && y == 0) {
            if (LEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWNLEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWN(startPoint, znachenie, MapFoKreatingRoute)) return true;
        } else if (x == DLINNA_MASSIVA - 1 && y != 0 && y != SHIRINA_MASSIVA - 1) {
            if (LEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWNLEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    UP(startPoint, znachenie, MapFoKreatingRoute) |
                    UPLEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWN(startPoint, znachenie, MapFoKreatingRoute)) return true;
        } else if (x == DLINNA_MASSIVA - 1 && y == SHIRINA_MASSIVA - 1) {
            if (UP(startPoint, znachenie, MapFoKreatingRoute) |
                    UPLEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    LEFT(startPoint, znachenie, MapFoKreatingRoute)) return true;

        } else {
            if (RIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    LEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    UP(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWN(startPoint, znachenie, MapFoKreatingRoute) |
                    UPRIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    UPLEFT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWNRIGHT(startPoint, znachenie, MapFoKreatingRoute) |
                    DOWNLEFT(startPoint, znachenie, MapFoKreatingRoute)) {
                return true;
            }
            return false;
        }

        return false;
    }

    public ArrayList<int[]> PostroenieMarshruta(int[] vershina, int[] end) {
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
                if (left(x, y) <= downLeft(x, y) && left(x, y) <= down(x, y) && left(x, y) <= downRight(x, y) && left(x, y) <= right(x, y)) { // left
                    listRoute.add(leftt);
                }
                if (right(x, y) <= left(x, y) && right(x, y) <= downLeft(x, y) && right(x, y) <= downRight(x, y) && right(x, y) <= down(x, y)) { // right
                    listRoute.add(rightt);
                }
                if (down(x, y) <= left(x, y) && down(x, y) <= downLeft(x, y) && down(x, y) <= downRight(x, y) && down(x, y) <= right(x, y)) { // down
                    listRoute.add(downn);
                }
                if (downLeft(x, y) <= left(x, y) && downLeft(x, y) <= down(x, y) && downLeft(x, y) <= downRight(x, y) && downLeft(x, y) <= right(x, y)) { // downLeft
                    listRoute.add(downLeftt);
                }
                if (downRight(x, y) <= left(x, y) && downRight(x, y) <= downLeft(x, y) && downRight(x, y) <= down(x, y) && downRight(x, y) <= right(x, y)) { // downRight
                    listRoute.add(downRightt);
                }


            } else if (x == 0 && y < SHIRINA_MASSIVA - 1 && y > 0) {
                if (right(x, y) <= up(x, y) && right(x, y) <= upRight(x, y) && right(x, y) <= downRight(x, y) && right(x, y) <= down(x, y)) { // right
                    listRoute.add(rightt);
                }
                if (down(x, y) <= up(x, y) && down(x, y) <= upRight(x, y) && down(x, y) <= right(x, y) && down(x, y) <= downRight(x, y)) { // down
                    listRoute.add(downn);
                }
                if (upRight(x, y) <= up(x, y) && upRight(x, y) <= right(x, y) && upRight(x, y) <= downRight(x, y) && upRight(x, y) <= down(x, y)) { // upRight
                    listRoute.add(upRightt);
                }
                if (downRight(x, y) <= up(x, y) && downRight(x, y) <= upRight(x, y) && downRight(x, y) <= right(x, y) && downRight(x, y) <= down(x, y)) { // downRight
                    listRoute.add(downRightt);
                }
            } else if (x == 0 && y == 0) {
                if (right(x, y) <= downRight(x, y) && right(x, y) <= down(x, y)) { // right
                    listRoute.add(rightt);
                }
                if (down(x, y) <= right(x, y) && down(x, y) <= downRight(x, y)) { // down
                    listRoute.add(downn);
                }
                if (downRight(x, y) <= right(x, y) && downRight(x, y) <= down(x, y)) { // downRight
                    listRoute.add(downRightt);
                }
            } else if (x == 0 && y == SHIRINA_MASSIVA - 1) {
                if (right(x, y) <= upRight(x, y) && left(x, y) <= up(x, y)) { // right
                    listRoute.add(rightt);
                }
                if (up(x, y) <= upRight(x, y) && up(x, y) <= right(x, y)) { // up
                    listRoute.add(upp);
                }
                if (upRight(x, y) <= up(x, y) && upRight(x, y) <= right(x, y)) { // upRight
                    listRoute.add(upRightt);
                }
            } else if (x > 0 && x < DLINNA_MASSIVA - 1 && y == SHIRINA_MASSIVA - 1) {
                if (left(x, y) <= upLeft(x, y) && left(x, y) <= up(x, y) && left(x, y) <= upRight(x, y) && left(x, y) <= right(x, y)) { // left
                    listRoute.add(leftt);
                }
                if (right(x, y) <= left(x, y) && right(x, y) <= upRight(x, y) && right(x, y) <= up(x, y) && right(x, y) <= upRight(x, y)) { // right
                    listRoute.add(rightt);
                }
                if (upLeft(x, y) <= left(x, y) && upLeft(x, y) <= up(x, y) && upLeft(x, y) <= upRight(x, y) && upLeft(x, y) <= right(x, y)) { // upLeft
                    listRoute.add(upLeftt);
                }
                if (upRight(x, y) <= left(x, y) && upRight(x, y) <= up(x, y) && upRight(x, y) <= upLeft(x, y) && upRight(x, y) <= right(x, y)) { // upRight
                    listRoute.add(upRightt);
                }
                if (up(x, y) <= left(x, y) && up(x, y) <= upLeft(x, y) && up(x, y) <= upRight(x, y) && up(x, y) <= right(x, y)) { // up
                    listRoute.add(upRightt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y == 0) {
                if (left(x, y) <= downLeft(x, y) && left(x, y) <= down(x, y)) { // left
                    listRoute.add(leftt);
                }
                if (down(x, y) <= left(x, y) && down(x, y) <= downLeft(x, y)) { // down
                    listRoute.add(downn);
                }
                if (downLeft(x, y) <= left(x, y) && downLeft(x, y) <= down(x, y)) { // downLeft
                    listRoute.add(downLeftt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y != 0 && y != SHIRINA_MASSIVA - 1) {
                if (left(x, y) <= downLeft(x, y) && left(x, y) <= down(x, y) && left(x, y) <= up(x, y) && left(x, y) <= upLeft(x, y)) { // left
                    listRoute.add(leftt);
                }
                if (downLeft(x, y) <= left(x, y) && downLeft(x, y) <= down(x, y) && downLeft(x, y) <= up(x, y) && downLeft(x, y) <= upLeft(x, y)) { // downLeft
                    listRoute.add(downLeftt);
                }
                if (down(x, y) <= left(x, y) && down(x, y) <= downLeft(x, y) && down(x, y) <= up(x, y) && down(x, y) <= upLeft(x, y)) { // down
                    listRoute.add(downn);
                }
                if (up(x, y) <= upLeft(x, y) && up(x, y) <= left(x, y) && up(x, y) <= downLeft(x, y) && up(x, y) <= down(x, y)) { // up
                    listRoute.add(upp);
                }
                if (upLeft(x, y) <= up(x, y) && upLeft(x, y) <= left(x, y) && upLeft(x, y) <= downLeft(x, y) && upLeft(x, y) <= down(x, y)) { // upLeft
                    listRoute.add(upLeftt);
                }
            } else if (x == DLINNA_MASSIVA - 1 && y == SHIRINA_MASSIVA - 1) {
                if (up(x, y) <= upLeft(x, y) && up(x, y) <= left(x, y)) { // up
                    listRoute.add(upp);
                }
                if (left(x, y) <= upLeft(x, y) && left(x, y) <= up(x, y)) { // left
                    listRoute.add(leftt);
                }
                if (upLeft(x, y) <= left(x, y) && upLeft(x, y) <= up(x, y)) { // upLeft
                    listRoute.add(upLeftt);
                }
            } else {
                if (left(x, y) <= downLeft(x, y) && left(x, y) <= down(x, y) && left(x, y) <= downRight(x, y) && left(x, y) <= right(x, y) && left(x, y) <= upRight(x, y) && left(x, y) <= up(x, y) && left(x, y) <= upLeft(x, y)) { // left
                    listRoute.add(leftt);
                } else if (downLeft(x, y) <= left(x, y) && downLeft(x, y) <= down(x, y) && downLeft(x, y) <= downRight(x, y) && downLeft(x, y) <= right(x, y) && downLeft(x, y) <= upRight(x, y) && downLeft(x, y) <= up(x, y) && downLeft(x, y) <= upLeft(x, y)) { // downLeft
                    listRoute.add(downLeftt);
                } else if (down(x, y) <= left(x, y) && down(x, y) <= downLeft(x, y) && down(x, y) <= downRight(x, y) && down(x, y) <= right(x, y) && down(x, y) <= upRight(x, y) && down(x, y) <= up(x, y) && down(x, y) <= upLeft(x, y)) { // down
                    listRoute.add(downn);
                } else if (downRight(x, y) <= left(x, y) && downRight(x, y) <= downLeft(x, y) && downRight(x, y) <= down(x, y) && downRight(x, y) <= right(x, y) && downRight(x, y) <= upRight(x, y) && downRight(x, y) <= up(x, y) && downRight(x, y) <= upLeft(x, y)) { // downRight
                    listRoute.add(downRightt);
                } else if (right(x, y) <= left(x, y) && right(x, y) <= downLeft(x, y) && right(x, y) <= down(x, y) && right(x, y) <= downRight(x, y) && right(x, y) <= upRight(x, y) && right(x, y) <= up(x, y) && right(x, y) <= upLeft(x, y)) { // right
                    listRoute.add(rightt);
                } else if (upRight(x, y) <= left(x, y) && upRight(x, y) <= downLeft(x, y) && upRight(x, y) <= down(x, y) && upRight(x, y) <= downRight(x, y) && upRight(x, y) <= right(x, y) && upRight(x, y) <= up(x, y) && upRight(x, y) <= upLeft(x, y)) { // upRight
                    listRoute.add(upRightt);
                } else if (up(x, y) <= left(x, y) && up(x, y) <= downLeft(x, y) && up(x, y) <= down(x, y) && up(x, y) <= downRight(x, y) && up(x, y) <= right(x, y) && up(x, y) <= upRight(x, y) && up(x, y) <= upLeft(x, y)) { // up
                    listRoute.add(upp);


                } else if (upLeft(x, y) <= left(x, y) && upLeft(x, y) <= downLeft(x, y) && upLeft(x, y) <= down(x, y) && upLeft(x, y) <= downRight(x, y) && upLeft(x, y) <= right(x, y) && upLeft(x, y) <= upRight(x, y) && upLeft(x, y) <= up(x, y)) { // upLeft

                    listRoute.add(upLeftt);
                }


            }

            end[0] = listRoute.get(listRoute.size() - 1)[0];
            end[1] = listRoute.get(listRoute.size() - 1)[1];
        }

        return listRoute;
    }

    private double left(int x, int y) {
        return MapFoKreatingRoute[x - 1][y] > 0 ? MapFoKreatingRoute[x - 1][y] : Integer.MAX_VALUE;
    }

    private double right(int x, int y) {
        return MapFoKreatingRoute[x + 1][y] > 0 ? MapFoKreatingRoute[x + 1][y] : Integer.MAX_VALUE;
    }

    private double up(int x, int y) {
        return MapFoKreatingRoute[x][y - 1] > 0 ? MapFoKreatingRoute[x][y - 1] : Integer.MAX_VALUE;
    }

    private double down(int x, int y) {
        return MapFoKreatingRoute[x][y + 1] > 0 ? MapFoKreatingRoute[x][y + 1] : Integer.MAX_VALUE;
    }

    private double downRight(int x, int y) {
        return MapFoKreatingRoute[x + 1][y + 1] > 0 ? MapFoKreatingRoute[x + 1][y + 1] : Integer.MAX_VALUE;
    }

    private double downLeft(int x, int y) {
        return MapFoKreatingRoute[x - 1][y + 1] > 0 ? MapFoKreatingRoute[x - 1][y + 1] : Integer.MAX_VALUE;
    }

    private double upRight(int x, int y) {
        return MapFoKreatingRoute[x + 1][y - 1] > 0 ? MapFoKreatingRoute[x + 1][y - 1] : Integer.MAX_VALUE;
    }

    private double upLeft(int x, int y) {
        return MapFoKreatingRoute[x - 1][y - 1] > 0 ? MapFoKreatingRoute[x - 1][y - 1] : Integer.MAX_VALUE;
    }

    private boolean UP(int[] vershina, double znachenie, double[][] massiv) {
        if (massiv[vershina[0]][vershina[1] - 1] == 0) {
            massiv[vershina[0]][vershina[1] - 1] = znachenie + 1;// клетка выше
            int[] q ={vershina[0],vershina[1] - 1};
            temp.add(q);
            return true;
        }
        return false;
    }

    private boolean DOWN(int[] vershina, double znachenie, double[][] massiv) {
        if (massiv[vershina[0]][vershina[1] + 1] == 0) {
            massiv[vershina[0]][vershina[1] + 1] = znachenie + 1;
            int[] q ={vershina[0],vershina[1] + 1};
            temp.add(q);
            return true;
        }
        return false;
    }

    private boolean RIGHT(int[] vershina, double znachenie, double[][] massiv) {
        if (massiv[vershina[0] + 1][vershina[1]] == 0) {
            massiv[vershina[0] + 1][vershina[1]] = znachenie + 1;       // клетка правее
            int[] q ={vershina[0] + 1,vershina[1]};
            temp.add(q);
            return true;
        }
        return false;
    }

    private boolean LEFT(int[] vershina, double znachenie, double[][] massiv) {
        if (massiv[vershina[0] - 1][vershina[1]] == 0) {
            massiv[vershina[0] - 1][vershina[1]] = znachenie + 1;       // клетка левее
            int[] q ={vershina[0] - 1,vershina[1]};
            temp.add(q);
            return true;
        }
        return false;
    }

    private boolean UPLEFT(int[] vershina, double znachenie, double[][] massiv) {
        if (massiv[vershina[0] - 1][vershina[1]] == WALL || massiv[vershina[0]][vershina[1] - 1] == WALL) return false;
        if (massiv[vershina[0] - 1][vershina[1] - 1] == 0) {
            massiv[vershina[0] - 1][vershina[1] - 1] = new BigDecimal(znachenie + 1.2).setScale(2, 5).doubleValue();
            int[] q ={vershina[0] - 1,vershina[1] - 1};
            temp.add(q);
            return true;
        }
        return false;
    }

    private boolean UPRIGHT(int[] vershina, double znachenie, double[][] massiv) {
        if (massiv[vershina[0] + 1][vershina[1]] == WALL || massiv[vershina[0]][vershina[1] - 1] == WALL) return false;
        if (massiv[vershina[0] + 1][vershina[1] - 1] == 0) {
            massiv[vershina[0] + 1][vershina[1] - 1] = new BigDecimal(znachenie + 1.2).setScale(2, 5).doubleValue();
            int[] q ={vershina[0] + 1,vershina[1] - 1};
            temp.add(q);
            return true;
        }
        return false;
    }

    private boolean DOWNLEFT(int[] vershina, double znachenie, double[][] massiv) {
        if (massiv[vershina[0] - 1][vershina[1]] == WALL || massiv[vershina[0]][vershina[1] + 1] == WALL) return false;
        if (massiv[vershina[0] - 1][vershina[1] + 1] == 0) {
            massiv[vershina[0] - 1][vershina[1] + 1] = new BigDecimal(znachenie + 1.2).setScale(2, 5).doubleValue();
            int[] q ={vershina[0] - 1,vershina[1] + 1};
            temp.add(q);
            return true;
        }
        return false;
    }

    private boolean DOWNRIGHT(int[] vershina, double znachenie, double[][] massiv) {
        if (massiv[vershina[0] + 1][vershina[1]] == WALL || massiv[vershina[0]][vershina[1] + 1] == WALL) return false;
        if (massiv[vershina[0] + 1][vershina[1] + 1] == 0) {
            massiv[vershina[0] + 1][vershina[1] + 1] = new BigDecimal(znachenie + 1.2).setScale(2, 5).doubleValue();
            int[] q ={vershina[0] + 1,vershina[1] + 1};
            temp.add(q);
            return true;
        }
        return false;
    }

    /*private static  int[] MAX(int x, int y, double a1, double a2, double a3){
        if (x == DLINNA_MASSIVA && y == SHIRINA_MASSIVA){

        }
    }*/


}

