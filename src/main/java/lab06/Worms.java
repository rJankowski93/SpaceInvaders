package lab06;

import gab.opencv.OpenCV;
import lab05.Ball;
import processing.core.PImage;
import utils.CameraHandler;
import processing.core.PApplet;
import processing.video.Capture;
import utils.DiffFrameUtil;

import java.util.ArrayList;
import java.util.Iterator;

public class Worms extends PApplet {

    public static int SIZE_X = 640;

    public static int SIZE_Y = 480;

    public static float SCALE = 1.2F;

    CameraHandler cameraHandler;

    OpenCV openCV;

    ArrayList<Worm> worms = new ArrayList();

    boolean isTransparent = true;


    @Override
    public void settings() {
        super.settings();
        size((int)(SIZE_X*SCALE),(int)(SIZE_Y*SCALE));
        cameraHandler = new CameraHandler(new Capture(this, SIZE_X, SIZE_Y, 30));
    }

    @Override
    public void setup() {
        super.setup();
        openCV = new OpenCV(this, cameraHandler.getPrevFrame());
        cameraHandler.setup();
        for (int i = 0; i < 50; i++) {
            worms.add(new Worm());
        }
    }

    public void draw() {
        if (cameraHandler.getVideo().available()) {
            cameraHandler.getVideo().read();
            background(100);
            DiffFrameUtil.calculateDiffFrame(cameraHandler, openCV);
            DiffFrameUtil.drawImg(this, (int)(SIZE_X*SCALE), (int)(SIZE_Y*SCALE), cameraHandler, openCV, isTransparent);
            PImage orgDiff = openCV.getOutput().get();

            Iterator<Worm> iterator = worms.iterator();
            while (iterator.hasNext()) {
                Worm worm = iterator.next();
                worm.move(this);
                worm.touch(this, orgDiff);
//                if (worm.isTouched(this, orgDiff)) {
//                    iterator.remove();
//                } else {
                    worm.draw(this);
//                }
            }
        }
    }


    public void keyPressed() {
        if (key == ' ') {
            isTransparent = !isTransparent;
        }
    }
}
