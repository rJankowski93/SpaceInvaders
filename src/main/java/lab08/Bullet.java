package lab08;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

import java.util.ArrayList;
import java.util.List;

public class Bullet extends PApplet{

    Body body;
    float x;
    float y;

    Box2DProcessing box2d;

    int begin =800;

    List<Point> xx = new ArrayList<>();


    public Bullet(float x, float y,float vecX, float vecY, Box2DProcessing box2d,float diffTime) {
        this.x = x;
        this.y = y;
        this.box2d=box2d;
        // Define a body
        BodyDef bd = new BodyDef();
        // Set its position
        bd.position = box2d.coordPixelsToWorld(x,y);
        bd.type = BodyType.DYNAMIC;
        body = box2d.world.createBody(bd);

        // Make the body's shape a circle
        CircleShape cs = new CircleShape();
        cs.m_radius = box2d.scalarPixelsToWorld(30);


        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        // Parameters that affect physics
        fd.density = 1;
        fd.friction = 0.01F;
        fd.restitution = 0.3F;

        // Attach fixture to body000
        body.createFixture(fd);
        body.setLinearVelocity(new Vec2(vecX/2 + diffTime,vecY/2+ diffTime));
        body.setUserData(this);
    }

    void display(PApplet window) {
        if(begin < 0){
            return;
        }
        begin--;

        // We look at each body and get its screen position
        Vec2 pos = box2d.getBodyPixelCoord(body);
        xx.add(new Point(pos.x, pos.y,100));
        // Get its angle of rotation
        window.pushMatrix();
        window.translate(pos.x,pos.y);
        window.fill(color(0));
        window.stroke(0);
        window.strokeWeight(1);
        window.ellipse(0,0,10,10);
        window.popMatrix();

        for(Point  temp : xx){
            pos = box2d.getBodyPixelCoord(body);
            // Get its angle of rotation
            window.pushMatrix();
            window.translate(temp.x,temp.y);
            window.fill(color(temp.color));
            window.stroke(temp.color);
            temp.color = temp.color >= 255 ? 255 : temp.color+1;

            window.strokeWeight(1);
            window.ellipse(0,0,10,10);
            window.popMatrix();

        }
    }
}
