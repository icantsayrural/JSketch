package com.example.joanne.jsketch;


public abstract class JShape {
    protected int lineColor;
    protected int fillColor;
    protected float thickness;
    protected boolean selected;

    JShape(int lineColor, float thickness) {
        this.lineColor = lineColor;
        this.thickness = thickness;

        this.selected = false;
    }

    abstract public boolean contains(float x, float y);
    abstract public int getId();

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}