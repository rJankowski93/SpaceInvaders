package lab05;

import gab.opencv.OpenCV;
import processing.core.PApplet;
import processing.core.PImage;
import processing.video.Capture;
import utils.CameraHandler;
import utils.DiffFrameUtil;

import java.util.ArrayList;
import java.util.Iterator;

public class SoapBubble extends PApplet {

    public static int SIZE_X = 640;

    public static int SIZE_Y = 480;

    CameraHandler cameraHandler;

    OpenCV openCV;

    int floor = 480;
    int counter = 10;
    int freqCreatingBall = 1;
    ArrayList<Ball> balls = new ArrayList();
    boolean isTransparent = true;

    @Override
    public void settings() {
        super.settings();
        size(SIZE_X,SIZE_Y);
    }

    @Override
    public void setup() {
        super.setup();
        cameraHandler = new CameraHandler(new Capture(this, SIZE_X, SIZE_Y, 30));
        openCV = new OpenCV(this, cameraHandler.getPrevFrame());
        cameraHandler.setup();
        balls.add(new Ball());
    }

    public void draw() {
        if (cameraHandler.getVideo().available()) {
            cameraHandler.getVideo().read();
            background(100);
            DiffFrameUtil.calculateDiffFrame(cameraHandler, openCV);
            DiffFrameUtil.drawImg(this,SIZE_X, SIZE_Y, cameraHandler, openCV, isTransparent);
            PImage orgDiff = openCV.getOutput().get();
            Iterator<Ball> iterator = balls.iterator();
            while (iterator.hasNext()) {
                Ball ball = iterator.next();
                if (ball.isTouched(this, orgDiff)) {
                    ball.setLife(ball.getLife() - 1);
                }
                if (ball.getY() > floor || ball.getLife() < 0) {
                    iterator.remove();
                } else {
                    ball.draw(this);
                }
            }
            if (counter < 0) {
                balls.add(new Ball());
                counter = 10;
            }
            counter = counter - freqCreatingBall;
        }
    }

    void increaseSpeed() {
        for (Ball ball : balls) {
            ball.setSpeed(ball.getSpeed() + 5);
        }
    }

    void decreaseSpeed() {
        for (Ball ball : balls) {
            if (ball.getSpeed() > 5) {
                ball.setSpeed(ball.getSpeed() - 5);
            }
        }
    }

    void increaseFreqCreating() {
        freqCreatingBall = freqCreatingBall + 1;
    }

    void decreaseFreqCreating() {
        freqCreatingBall = freqCreatingBall - 1;
    }

    public void keyPressed() {
        if (keyCode == UP) {
            increaseSpeed();
        } else if (keyCode == DOWN) {
            decreaseSpeed();
        } else if (keyCode == LEFT) {
            increaseFreqCreating();
        } else if (keyCode == RIGHT) {
            decreaseFreqCreating();
        } else if (key == ' ') {
            isTransparent = !isTransparent;
        }
    }

}
