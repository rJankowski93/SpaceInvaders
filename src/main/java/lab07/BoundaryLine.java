package lab07;

import processing.core.PApplet;

import shiffman.box2d.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

import java.util.ArrayList;

public class BoundaryLine extends PApplet {
    // A reference to our box2d world
    Box2DProcessing box2d;

    // A list we'll use to track fixed objects
    ArrayList<Boundary> boundaries;
    // A list for all of our rectangles
    ArrayList<Box> boxes;
    ArrayList<Pair> pairs;

    @Override
    public void settings() {
        super.settings();
        size(640,360);
    }

    @Override
    public void setup() {
        smooth();

        // Initialize box2d physics and create the world
        box2d = new Box2DProcessing(this);
        box2d.createWorld();
        box2d.setGravity(0, -10);

        // Create ArrayLists
        boxes = new ArrayList<>();
        boundaries = new ArrayList<>();
        pairs = new ArrayList<>();
        // Add a bunch of fixed boundaries
        boundaries.add(new Boundary(0L,height/2,width*2,1, box2d));
//        boundaries.add(new Boundary(3*width/4,height-5,width/2-50,10, box2d));
//        boundaries.add(new Boundary(width-5,height/2,10,height, box2d));
//        boundaries.add(new Boundary(5,height/2,10,height, box2d));
    }

    public void draw() {
        background(255);

        // We must always step through time!
        box2d.step();

        // When the mouse is clicked, add a new Box object
//        if (random(1) < 0.1) {
//            Box p = new Box(random(width),10,box2d);
//            boxes.add(p);
//        }
//
//        if (mousePressed) {
//            for (Box b: boxes) {
//                b.attract(mouseX,mouseY);
//            }
//        }

        if (mousePressed && mouseButton==LEFT) {
            Box p = new Box(mouseX,mouseY,box2d);
            boxes.add(p);
        }
        if(mousePressed && mouseButton==RIGHT){
            Pair p = new Pair(mouseX,mouseY,box2d);
            pairs.add(p);
        }

        // Display all the boundaries
        for (Boundary wall: boundaries) {
            wall.display(this);
        }

        for (Pair p: pairs) {
            p.display(this);
        }


        // Display all the boxes
        for (Box b: boxes) {
            b.display(this);
        }

        // Boxes that leave the screen, we delete them
        // (note they have to be deleted from both the box2d world and our list
        for (int i = boxes.size()-1; i >= 0; i--) {
            Box b = boxes.get(i);
            if (b.done()) {
                boxes.remove(i);
            }
        }

        fill(0);
        text("Click mouse to attract boxes",20,20);
    }
}
