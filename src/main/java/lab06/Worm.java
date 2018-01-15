package lab06;

import processing.core.PApplet;
import objects.DynamicObject;
import processing.core.PImage;

public class Worm extends DynamicObject {
    private int x;
    private int y;
    private int size;
    private int color;
    private int timeTouched;
    private int speedX;
    private int speedY;
    private boolean touched;

    public Worm() {
        generateWorm();
    }

    public void touch(PApplet window, PImage orgDiff) {
//        if (timeTouched == 0) {
        if (isTouched(window, orgDiff)) {
            color = color(255, 0, 0);
            touched = true;
            speedX = 0;
            speedY = 10;
// timeTouched = millis();
        }
//        } else {
//            if (millis() - timeTouched > 3000) {
//                generateWorm();
//            }
//        }
    }

    private void generateWorm() {
        size = Math.round(random(50, 70));
        x = Math.round(random(size / 2, Worms.SIZE_X - size / 2));
        y = Math.round(random(size / 2, Worms.SIZE_Y - size / 2));
        speedX = Math.round(random(5, 10));
        speedY = Math.round(random(5, 10));
        color = color(100, 100, 100);
        timeTouched = 0;
        touched = false;
    }

    public void draw(PApplet window) {
        window.fill(this.getColor());
        window.ellipse(this.getX(), this.getY(), this.getSize(), this.getSize());
    }

    public void move(PApplet window) {
        x = x + speedX;
        y = y + speedY;
        if (x > (window.width - size / 2) || x < size / 2) {
            speedX = -speedX;
        }
        if (y > (window.height - size / 2) || y < size / 2) {
            if (touched) {
                generateWorm();
                return;
            }
            speedY = -speedY;
        }
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
