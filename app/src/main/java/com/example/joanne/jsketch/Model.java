package com.example.joanne.jsketch;

import android.graphics.Color;

import java.util.ArrayList;

public class Model {
    public final static int SELECT_ID = 100;
    public final static int ERASE_ID = 101;
    public final static int RECT_ID = 102;
    public final static int CIR_ID = 103;
    public final static int LINE_ID = 104;
    public final static int PAINT_ID = 105;

    public final static int ORANGE = Color.rgb(255, 204, 61);
    public final static int GREEN = Color.rgb(133, 246, 67);
    public final static int RED = Color.rgb(242, 20, 20);

    public final static int SELECTED = Color.BLACK;

    public final static float TH1 = 3f;
    public final static float TH2 = 6f;
    public final static float TH3 = 9f;

    // keeps track of all the shapes
    public static ArrayList<JShape> shapes = new ArrayList<JShape>();

    // tool id
    public static int toolId = LINE_ID;

    // color
    public static int color = ORANGE;

    // thickness
    public static float thickness = TH1;

    // focused shape
    public static JShape focusedShape = null;

    public static JShape selectShape(float x, float y) {
        JShape selectedShape = null;

        for (int i=shapes.size()-1; i>=0; i--) {
            if (shapes.get(i).contains(x, y)) {

                selectedShape = shapes.get(i);
                break;
            }
        }

        return selectedShape;
    }
}
