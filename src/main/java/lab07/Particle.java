package lab07;

import processing.core.PApplet;
import shiffman.box2d.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

public class Particle extends PApplet {
    // We need to keep track of a Body and a radius
    Body body;
    float r;

    int col;
    Box2DProcessing box2d;
    Particle(float x, float y, Box2DProcessing box2d) {
        r = 8;
        this.box2d=box2d;
        // Define a body
        BodyDef bd = new BodyDef();
        // Set its position
        bd.position = box2d.coordPixelsToWorld(x,y);
        bd.type = BodyType.DYNAMIC;
        body = box2d.world.createBody(bd);

        // Make the body's shape a circle
        CircleShape cs = new CircleShape();
        cs.m_radius = box2d.scalarPixelsToWorld(r);

        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        // Parameters that affect physics
        fd.density = 1;
        fd.friction = 0.01F;
        fd.restitution = 0.3F;

        // Attach fixture to body
        body.createFixture(fd);
        body.setLinearVelocity(new Vec2(random(-5, 5), random(2, 5)));

        col = color(175);
    }

    // This function removes the particle from the box2d world
    void killBody() {
        box2d.destroyBody(body);
    }

    // Is the particle ready for deletion?
    boolean done() {
        // Let's find the screen position of the particle
        Vec2 pos = box2d.getBodyPixelCoord(body);
        // Is it off the bottom of the screen?
        if (pos.y > height+r*2) {
            killBody();
            return true;
        }
        return false;
    }

    void display(PApplet window) {
        // We look at each body and get its screen position
        Vec2 pos = box2d.getBodyPixelCoord(body);
        // Get its angle of rotation
        float a = body.getAngle();
        window.pushMatrix();
        window.translate(pos.x,pos.y);
        window.rotate(a);
        window.fill(col);
        window.stroke(0);
        window.strokeWeight(1);
        window.ellipse(0,0,r*2,r*2);
        // Let's add a line so we can see the rotation
        window.line(0,0,r,0);
        window.popMatrix();
    }

}
