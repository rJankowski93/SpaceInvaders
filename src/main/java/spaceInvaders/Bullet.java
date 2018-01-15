package spaceInvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet extends PApplet {
    private int x;
    private int y;
    private int speed;
    private int size;
    private int color;

    public Bullet() {
        size = Math.round(random(50, 70));
        y = Math.round(random(150, Game.SIZE_Y - 150));
        x = Math.round(random(size / 2, Game.SIZE_X - size / 2));
        speed = Math.round(random(1, 3));
        color = color(255, 255, 255);
    }

    public Bullet(int x, int y, int speed, int size, int color) {
        this.x = x;
        if (this.x > Game.SIZE_X / 2) { //UP
            this.speed = -speed;
            this.y = Game.SIZE_Y-15;
        } else {
            this.speed = speed;
            this.y = 5;
        }
        this.size = size;
        this.color = color;
    }

    public void draw(PApplet window) {
        window.fill(this.color);
        window.ellipse(this.x, this.y, this.size, this.size);
    }

    public void move(PApplet window) {
        y = y + speed;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(int color) {
        this.color = color;
    }
}