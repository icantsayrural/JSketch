package com.example.joanne.jsketch;

import android.graphics.Bitmap;

public class JImage extends JShape {
    public final static int ID = 7;

    Bitmap bitmap;

    float x0;
    float y0;
    float x1;
    float y1;

    JImage(Bitmap bitmap) {
        super(Model.SELECTED, Model.TH1);

        this.x0 = 0;
        this.y0 = 0;

        this.x1 = 300;
        this.y1 = 300;

        this.bitmap = bitmap;
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

    @Override
    public boolean contains(float x, float y) {
        return x >= x0 && x <= x1 && y >= y0 && y <= y1;
    }
}
