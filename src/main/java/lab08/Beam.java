package lab08;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

public class Beam {
    Body body;
    float x;
    float y;
    float w,h;
    Box2DProcessing box2d;
    int col=0;
    public Beam(float x, float y, Box2DProcessing box2d) {
        this.x = x;
        this.y = y;
        this.box2d = box2d;
        w=20;
        h=300;


        PolygonShape polygonShape= new PolygonShape();
        float box2dW = box2d.scalarPixelsToWorld(w/2);
        float box2dH = box2d.scalarPixelsToWorld(h/2);
        // We're just a box
        polygonShape.setAsBox(box2dW, box2dH);

        FixtureDef fd = new FixtureDef();
        fd.shape = polygonShape;
//        fd.density = 10;
//        fd.restitution=10;

        // Define a body
        BodyDef bd = new BodyDef();
        // Set its position
        bd.position = box2d.coordPixelsToWorld(x, y);
        bd.type = BodyType.STATIC;

        body = box2d.world.createBody(bd);

        body.createFixture(fd);
        body.setUserData(this);

    }

    void display(PApplet window) {
        // We look at each body and get its screen position
        Vec2 pos = box2d.getBodyPixelCoord(body);
        // Get its angle of rotation
        window.pushMatrix();
        window.translate(pos.x, pos.y);
        window.fill(window.color(col));
        window.stroke(0);
        window.strokeWeight(1);

        window.rect(-30, -h/2, w, h);
        window.popMatrix();
    }

    public void change(PApplet window) {
        col = window.color(255, 0, 0);
    }
}
