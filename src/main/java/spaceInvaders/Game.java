package spaceInvaders;

import gab.opencv.OpenCV;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.Amplitude;
import processing.sound.AudioIn;
import processing.video.Capture;
import utils.CameraHandler;
import utils.DiffFrameUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



public class Game extends PApplet {


    public static int SIZE_X = 640;

    public static int SIZE_Y = 480;

    public static float SCALE = 1.0F;

    public OpenCV openCV;

    public List enemies = new ArrayList<Spacecraft>();

    public List bullets = new ArrayList<Bullet>();

    public Spacecraft spacecraftUp;

    public Spacecraft spacecraftDown;

    CameraHandler cameraHandler;

    public static boolean cameraMode = false;

     long timeStartMode=0;

    public static PImage imageSpacecraft;

    public static PImage imageMonster;

    AudioIn input;
    Amplitude analyzer;

    @Override
    public void settings() {
        super.settings();
        size((int) (SIZE_X * SCALE), (int) (SIZE_Y * SCALE));
        cameraHandler = new CameraHandler(new Capture(this, SIZE_X, SIZE_Y, 30));
    }


    @Override
    public void setup() {
        super.setup();
//        input = new AudioIn(this, 0);
//        input.start();
//        analyzer = new Amplitude(this);
//        analyzer.input(input);
        openCV = new OpenCV(this, cameraHandler.getPrevFrame());
        cameraHandler.setup();
        generateEnemies(20);
        spacecraftUp = new Spacecraft(SIZE_X / 4, 0, 0, 40, color(100, 100, 255));
        spacecraftDown = new Spacecraft((SIZE_X - SIZE_X / 4), SIZE_Y - 40, 0, 40, color(100, 100, 255));
        imageSpacecraft = loadImage("../resources/img/spaceInvaders/spacecraft.png");
        imageMonster = loadImage("../resources/img/spaceInvaders/monster.png");
    }

    public void draw() {
        if (cameraHandler.getVideo().available()) {
            background(0);
            if (cameraMode) {
                cameraHandler.getVideo().read();
                DiffFrameUtil.calculateDiffFrame(cameraHandler, openCV);
                DiffFrameUtil.drawImg(this, (int) (SIZE_X * SCALE), (int) (SIZE_Y * SCALE), cameraHandler, openCV, false);
                if((new Date().getTime() - timeStartMode)>1000){
                    cameraMode = false;
                }
            }
            mousePressed();
            Iterator<Bullet> iteratorBullet = bullets.iterator();
            while (iteratorBullet.hasNext()) {
                Bullet bullet = iteratorBullet.next();
                bullet.move(this);
                bullet.draw(this);
            }
            PImage orgDiff = openCV.getOutput().get();
            Iterator<Spacecraft> iterator = enemies.iterator();
            while (iterator.hasNext()) {
                Spacecraft enemy = iterator.next();
                enemy.move(this);
                enemy.drawEnemy(this);
                if (enemy.touch(this, orgDiff, bullets)) {
                    iterator.remove();
                }
            }
            spacecraftUp.drawSpacecraft(mouseX, this);
            spacecraftDown.drawSpacecraft(mouseX, this);

        }
        if (enemies.isEmpty()){
            generateEnemies(30);
        }
    }

    public void mousePressed() {
        if (mousePressed && mouseButton == LEFT) {
            bullets.add(new Bullet(mouseX, 0, 10, 10, 255));
        }
    }

    public void keyPressed() {
        if (key == ' ') {
            cameraMode = !cameraMode;
            timeStartMode = new Date().getTime();
        }
    }

    private void generateEnemies(int numberEnemies){
        for (int i = 0; i < numberEnemies; i++) {
            enemies.add(new Spacecraft());
        }
    }


}
