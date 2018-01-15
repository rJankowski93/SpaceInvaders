package lab05;

import processing.core.PApplet;
import objects.DynamicObject;

public class Ball extends DynamicObject {
    private int x;
    private int y;
    private int size;
    private int speed;
    private int color;
    private int life;

    public Ball() {
        x = Math.round(random(0, 640));
        y = 20;
        speed = 10;
        size = Math.round(random(50, 70));
        color = color(Math.round(random(0, 255)), Math.round(random(0, 255)), Math.round(random(0, 255)));
        life=3;
    }

    public void draw(PApplet window) {
        window.fill(this.getColor());
        window.ellipse(this.getX(), this.getY(), this.getSize(), this.getSize());
        window.fill(color(0, 0, 0));
        window.text(this.getLife(), this.getX(), this.getY());
        this.setY(this.getY() + this.getSpeed());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
