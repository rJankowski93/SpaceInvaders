package lab08;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Boundary extends PApplet {
    // A boundary is a simple rectangle with x,y,width,and height
    float x;
    float y;
    float w;
    float h;
    Box2DProcessing box2d;

    // But we also have to make a body for box2d to know about it
    Body b;

    public Boundary(float x_, float y_, float w_, float h_, Box2DProcessing box2d) {
        x = x_;
        y = y_;
        w = w_;
        h = h_;
        this.box2d = box2d;

        // Define the polygon
        PolygonShape sd = new PolygonShape();
        // Figure out the box2d coordinates
        float box2dW = box2d.scalarPixelsToWorld(w / 2);
        float box2dH = box2d.scalarPixelsToWorld(h / 2);
        // We're just a box
        sd.setAsBox(box2dW, box2dH);


        // Create the body
        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position.set(box2d.coordPixelsToWorld(x, y));
        b = box2d.createBody(bd);

        // Attached the shape to the body using a Fixture
        b.createFixture(sd, 1);
    }

    // Draw the boundary, if it were at an angle we'd have to do something fancier
    void display(PApplet window) {

        Vec2 pos = box2d.getBodyPixelCoord(b);
        // Get its angle of rotation
        window.pushMatrix();
        window.translate(pos.x, pos.y);
        window.fill(window.color(0));
        window.stroke(0);
        window.strokeWeight(1);

        window.rect(-30, -h/2, w, h);
        window.popMatrix();
    }
}
