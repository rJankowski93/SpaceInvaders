package utils;

import gab.opencv.OpenCV;
import processing.core.PApplet;
import processing.core.PImage;

public class DiffFrameUtil {

    public static void calculateDiffFrame(CameraHandler cameraHandler, OpenCV openCV) {
        openCV.loadImage(cameraHandler.getPrevFrame());
        cameraHandler.setCurrFrame(cameraHandler.getVideo().get());
        openCV.diff(cameraHandler.getCurrFrame());
        cameraHandler.setDiffFrame(openCV.getSnapshot());
        openCV.loadImage(cameraHandler.getDiffFrame());
        openCV.threshold(80);
        cameraHandler.setPrevFrame(cameraHandler.getCurrFrame());
    }

    public static void drawImg(PApplet window,int sizeX, int sizeY, CameraHandler cameraHandler, OpenCV openCV, boolean isTransparent) {
        PImage result = new PImage(cameraHandler.getVideo().get().width, cameraHandler.getVideo().get().height);
        PImage orgVideo = cameraHandler.getVideo().get();
        PImage orgDiff = openCV.getOutput().get();
        for (int i = 0; i < orgDiff.width; i++) {
            for (int j = 0; j < orgDiff.height; j++) {
                int color = orgDiff.get(i, j);
                if (window.brightness(color) >= 255 || !isTransparent) {
                    result.set(orgDiff.width - i, j, color);
                } else {
                    result.set(orgVideo.width - i, j, orgVideo.get(i, j));
                }
            }
        }
        window.image(result, 0, 0, sizeX, sizeY);
    }

}
