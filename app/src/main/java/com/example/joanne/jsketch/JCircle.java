package com.example.joanne.jsketch;

public class JCircle extends JShape {
    public final static int ID = 1;

    private float centerX;
    private float centerY;
    private float radius;

    JCircle(float centerX, float centerY, float radius, int lineColor, float thickness) {
        super(lineColor, thickness);

        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getId() {
        return ID;
    }

    public boolean contains(float x, float y) {
        return (Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) < Math.pow(radius, 2);
    }
}