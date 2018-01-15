package objects;

import processing.core.PApplet;
import processing.core.PImage;

public class DynamicObject extends PApplet {
    private int x;
    private int y;
    private int size;
    private int color;


    public  boolean isTouched(PApplet window, PImage orgDiff){
         int count = 0;
        for (int i = getX() - getSize() / 2; i < getX() + getSize() / 2; i++) {
            for (int j = getY() - getSize() / 2; j < getY() + getSize() / 2; j++) {
                int c = orgDiff.get(i, j);
                if (window.brightness(c) >= 155) {
                    count++;
                }
            }
        }
        if (count > 10) {
            return true;
        }
        return false;
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
