package lab08;

import lab07.Box;
import lab07.Pair;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PApplet;
import shiffman.box2d.Box2DProcessing;

import java.util.ArrayList;
import java.util.List;

public class GunGame extends PApplet implements ContactListener {

    Box2DProcessing box2d;

    Gun gun;
    List<Beam> beams;
    List<Bullet> bullets;
    long startPressedTime=0;
    long diffTime;
    Boundary boundary;

    @Override
    public void settings() {
        super.settings();
        size(1024, 512);
    }

    @Override
    public void setup() {
        smooth();

        // Initialize box2d physics and create the world
        box2d = new Box2DProcessing(this);
        box2d.createWorld();
        box2d.setGravity(0, -10);
        box2d.listenForCollisions();
        // Create objects
        gun = new Gun(0, height - 200, 20, 200, box2d);
        bullets = new ArrayList<>();
        beams = new ArrayList<>();
        beams.add(new Beam(Math.round(random(300F, 500F)), 400, box2d));
        beams.add(new Beam(Math.round(random(550F, 750F)), 400, box2d));
        beams.add(new Beam(Math.round(random(800F, 1000F)), 400, box2d));
        boundary = new Boundary(0L,height,width*2,1, box2d);
    }


    public void draw() {
        background(255);
        box2d.step();
        diffTime = System.currentTimeMillis() - startPressedTime;

boundary.display(this);
        //display objects
        gun.display(this);
        for (Bullet bullet : bullets) {
            bullet.display(this);
        }
        for(Beam beam : beams){
            beam.display(this);
        }
    }

    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == LEFT) {
                gun.angle = gun.angle - 1F;
            } else if (keyCode == RIGHT) {
                gun.angle = gun.angle + 1F;
            }
            if (keyCode == UP) {
                if (startPressedTime == 0) {
                    startPressedTime = System.currentTimeMillis();
                }
            }
        }
    }

    @Override
    public void keyReleased() {
        super.keyReleased();
        if (keyCode == UP) {
            diffTime = System.currentTimeMillis() - startPressedTime+1;
            bullets.add(new Bullet(30, height - 5, gun.angle, 90 - gun.angle, box2d, diffTime / 50));
            startPressedTime = 0;
            diffTime = 0;
        }
    }


    @Override
    public void beginContact(Contact contact) {
        Fixture f1 = contact.getFixtureA();
        Fixture f2 = contact.getFixtureB();
        // Get both bodies
        Body b1 = f1.getBody();
        Body b2 = f2.getBody();

        // Get our objects that reference these bodies
        Object o1 = b1.getUserData();
        Object o2 = b2.getUserData();

        if (o1 instanceof Beam) {
            Beam p1 = (Beam) o1;
            p1.change(this);
        }
        if (o2 instanceof Beam) {
            Beam p1 = (Beam) o2;
            p1.change(this);
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
