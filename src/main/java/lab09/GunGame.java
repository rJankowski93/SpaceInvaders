package lab09;

import processing.core.PApplet;

import shiffman.box2d.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

public class GunGame extends PApplet{

    Box2DProcessing box2d;

    @Override
    public void settings() {
        super.settings();
        size(640,360);
    }



}
