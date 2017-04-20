package game;

import bullet.BuletGG;
import displey.Displey;
import grafika.TextureGG;
import keyboard.Keyboard;
import map.GameMap;
import utils.Target;
import utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *     public static void create(int width, int height, String title, int _clearColor, int numBuffers) {

 * Created by DNS on 10.04.2017.
 */
public class Game implements Runnable{

    //temp
    double speed = 1;
    double XX, YY;
    int i = 2;

    double x = 100;
    double y = 100;
    //temp

    public static final int width = 800;
    public static final int height = 600;
    public static final String title = "My first game";
    public static final int nullColor = 0xff000000;
    public static final int numBuffers = 4;

    public static final float updPhis = 60.0f;
    public static final float updInterval = Time.SEKOND / updPhis;
    public static final float updPhisika = 60.0f;

    public static LinkedList<BuletGG> buletsGG;
    public static final long speedShoot = Time.SEKOND;
    long lastShot = 0;
/*
    public static final String fileNameGG = "GG.png";
*/
    public static final String fileNameGG = "robo.jpg";
    public static final String buletGG = "bah1.jpg";
    public static  int widthGG = 22;
    public static  int heightGG = 22;
    GameMap gameMap;
    Target target;


    private boolean	running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Keyboard keyboard;
    private TextureGG GG;


    public Game(){
        running = false;
        Displey.create(width, height, title, nullColor, numBuffers);
        graphics = Displey.getGraphics();
        keyboard = new Keyboard();
        Displey.addKeyboard(keyboard);
        GG = new TextureGG(fileNameGG);
        gameMap = new GameMap();


    }
    public synchronized void start(){
        if(running)
            return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();

    }

    public synchronized void stop(){
        if (!running)
            return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cleen();

    }

    private void update(){
        boolean L, R, U, D;
        if (keyboard.getKey(KeyEvent.VK_UP) && keyboard.getKey(KeyEvent.VK_RIGHT)||
                keyboard.getKey(KeyEvent.VK_UP) && keyboard.getKey(KeyEvent.VK_LEFT)||
                keyboard.getKey(KeyEvent.VK_DOWN) && keyboard.getKey(KeyEvent.VK_RIGHT) ||
                keyboard.getKey(KeyEvent.VK_DOWN) && keyboard.getKey(KeyEvent.VK_LEFT)){
            if(!Target.target(x,y,widthGG,gameMap.getMap())){
                YY = Math.sin(45);
                XX = Math.cos(45);
            }
        }
        if (keyboard.getKey(KeyEvent.VK_UP)){
            if(!Target.target(x,y - 5,widthGG,gameMap.getMap())) {
                YY = -speed;
                i = 4;
            }
        }
        if (keyboard.getKey(KeyEvent.VK_RIGHT)) {
            if(!Target.target(x + 5,y,widthGG,gameMap.getMap())) {
                XX = speed;
                i = 2;
            }
        }

        if (keyboard.getKey(KeyEvent.VK_DOWN)) {
            if(!Target.target(x, y + 5,widthGG,gameMap.getMap())) {
                YY = speed;
                i = 3;
            }
        }

        if (keyboard.getKey(KeyEvent.VK_LEFT)) {
            if(!Target.target(x - 5,y,widthGG,gameMap.getMap())) {
                XX = -speed;
                i = 1;
            }
        }
        y += YY;
        x += XX;


        YY = 0;
        XX = 0;


        if (keyboard.getKey(KeyEvent.VK_SPACE)){

            long perSpeedShot = Time.getTime();
            if (perSpeedShot >= lastShot + speedShoot/2) {
                lastShot = Time.getTime();
                buletsGG.add(new BuletGG(x, y, i));
            }

        }
        for (int i = 0; i < buletsGG.size(); i ++ ){
            buletsGG.get(i).update();
            if(buletsGG.get(i).removeOut(gameMap.getMap())){
                buletsGG.remove(i);  // todo
                i--;
            }
        }


    }



    private void render(){
        Displey.clear();
        // mapa
        gameMap.draw(graphics);

        // gg
        BufferedImage h = GG.cut(18,21,44,44);
        /*h.getScaledInstance(20,20,2)*/

        graphics.drawImage(h.getScaledInstance(widthGG,widthGG,2),(int)x,(int)y,null);

        //patroshki
        BufferedImage imgBuletGG = GG.cut(50,50,80,80);
        for (BuletGG buletGG:buletsGG){
            buletGG.draw(graphics);
        }

        Displey.swapBuffers();
    }

    private void cleen(){
        Displey.destroy();
    }


    @Override
    public void run() {

        int fps = 0;
        int upd = 0;
        int updl = 0;
        long count = 0;
        float delta = 0;
        buletsGG = new LinkedList<>();

        long lastTime = Time.getTime(); //1
        while (running) {
            long now = Time.getTime(); //2 5
            long elapsedTime = now - lastTime; //1 4
            lastTime = now; //1 5

            count += elapsedTime;

            boolean render = false;
            delta += (elapsedTime / updInterval);
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= Time.SEKOND) {
                Displey.setTitle(title + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }

    }

}
