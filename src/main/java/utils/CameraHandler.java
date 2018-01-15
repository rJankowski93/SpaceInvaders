package utils;

import processing.core.PImage;
import processing.video.Capture;

public class CameraHandler {

    private Capture video;

    private PImage prevFrame, currFrame, diffFrame;

    public CameraHandler(Capture video) {
        this.video = video;
        prevFrame = getVideo().get();

    }

    public void setup(){
        video.start();
    }

    public Capture getVideo() {
        return video;
    }

    public void setVideo(Capture video) {
        this.video = video;
    }

    public PImage getPrevFrame() {
        return prevFrame;
    }

    public void setPrevFrame(PImage prevFrame) {
        this.prevFrame = prevFrame;
    }

    public PImage getCurrFrame() {
        return currFrame;
    }

    public void setCurrFrame(PImage currFrame) {
        this.currFrame = currFrame;
    }

    public PImage getDiffFrame() {
        return diffFrame;
    }

    public void setDiffFrame(PImage diffFrame) {
        this.diffFrame = diffFrame;
    }
}
