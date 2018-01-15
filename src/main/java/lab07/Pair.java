package lab07;

import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import processing.core.PApplet;
import shiffman.box2d.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

public class Pair extends PApplet {

    Particle p1;
    Particle p2;
    Box2DProcessing box2d;
    float len;
    // Chain constructor
    Pair(float x, float y, Box2DProcessing box2d) {
        len = 32;
        this.box2d=box2d;
        p1 = new Particle(x,y, box2d);
        p2 = new Particle(x+random(-1,1),y+random(-1,1), box2d);


        DistanceJointDef djd = new DistanceJointDef();
        // Connection between previous particle and this one
        djd.bodyA = p1.body;
        djd.bodyB = p2.body;
        // Equilibrium length
        djd.length = box2d.scalarPixelsToWorld(len);

        // These properties affect how springy the joint is
        djd.frequencyHz = 3;  // Try a value less than 5 (0 for no elasticity)
        djd.dampingRatio = 0.1F; // Ranges between 0 and 1

        // Make the joint.  Note we aren't storing a reference to the joint ourselves anywhere!
        // We might need to someday, but for now it's ok
        DistanceJoint dj = (DistanceJoint) box2d.world.createJoint(djd);
    }



    // Draw the bridge
    void display(PApplet window) {
        Vec2 pos1 = box2d.getBodyPixelCoord(p1.body);
        Vec2 pos2 = box2d.getBodyPixelCoord(p2.body);
        window.stroke(0);
        window.line(pos1.x,pos1.y,pos2.x,pos2.y);

        p1.display(window);
        p2.display(window);
    }
}
