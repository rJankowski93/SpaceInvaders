package lab08;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Gun extends PApplet {
    float x;
    float y;
    float w;
    float h;
    float angle;
    Box2DProcessing box2d;

    Body body;

    Gun(float x, float y, float w, float h, Box2DProcessing box2d) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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
        body = box2d.createBody(bd);

        // Attached the shape to the body using a Fixture
        body.createFixture(sd, 1);
    }

    void display(PApplet window) {
        window.pushMatrix();
        window.translate(window.width / 2 - 500, window.height);
        window.rotate(radians(angle));
        window.fill(0);
        window.rect(0, -100, 20, 100);
        window.popMatrix();
    }
}
