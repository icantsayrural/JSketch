package com.example.joanne.jsketch;

public class JRectangle extends JShape {
    public final static int ID = 0;

    private float x0;
    private float y0;
    private float x1;
    private float y1;

    JRectangle(float x0, float y0, float x1, float y1, int lineColor, float thickness) {
        super(lineColor, thickness);

        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public float getX0() {
        return x0;
    }

    public void setX0(float x0) {
        this.x0 = x0;
    }

    public float getY0() {
        return y0;
    }

    public void setY0(float y0) {
        this.y0 = y0;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public int getId() {
        return ID;
    }

    public boolean contains(float x, float y) {
        return x >= x0 && x <= x1 && y >= y0 && y <= y1;
    }
}
