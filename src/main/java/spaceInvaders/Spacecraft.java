package spaceInvaders;

import lab06.Worms;
import objects.DynamicObject;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

import static java.lang.Math.random;

public class Spacecraft extends DynamicObject {
    private int x;
    private int y;
    private int speed;
    private int size;
    private int color;

    public Spacecraft() {
        size = Math.round(random(50, 70));
        y = Math.round(random(150, Game.SIZE_Y - 150));
        x = Math.round(random(size / 2, Game.SIZE_X - size / 2));
        speed = Math.round(random(1, 3));
        color = color(255, 100, 100);
    }

    public Spacecraft(int x, int y, int speed, int size, int color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.color = color;
    }

    public void drawEnemy(PApplet window) {
        window.fill(this.color);
//        window.ellipse(this.x, this.y, this.size, this.size);
        window.image(Game.imageMonster,this.x- this.size / 2, this.y, this.size, this.size);

    }

    public void drawSpacecraft(int x, PApplet window) {
        window.fill(this.color);
        if (y < Game.SIZE_Y / 2 && x < (Game.SIZE_X / 2)) { // up spacecraft
            this.x = x;
        }
        if ((y > Game.SIZE_Y / 2 && x > (Game.SIZE_X / 2) && x < Game.SIZE_X - size)) { // down spacecraft
            this.x = x;
        }

//        window.rect(this.x - this.size / 2, this.y, this.size, this.size);
        window.image(Game.imageSpacecraft,this.x - this.size / 2, this.y, this.size, this.size);

    }

    public void move(PApplet window) {
        x = x + speed;
        if (x > (window.width - size / 2) || x < size / 2) {
            speed = -speed;
        }
    }

    public boolean touch(PApplet window, PImage orgDiff, List<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            int size = this.size - 30;
            if (x - size <= bullet.getX() && x + size >= bullet.getX()) {
                if (y - size <= bullet.getY() && y + size >= bullet.getY()) {
                    bullet.setX(1000);
                    return true;
                }
            }
        }
        if (isTouched(window, orgDiff) && Game.cameraMode) {
            return true;
        }
        return false;

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getColor() {
        return color;
    }
}
