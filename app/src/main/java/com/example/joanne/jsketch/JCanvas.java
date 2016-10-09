package com.example.joanne.jsketch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class JCanvas extends View {
    Paint paint;
    Path path;

    public JCanvas(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);
        paint = new Paint();

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // paint settings
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);

        for (JShape shape : Model.shapes) {
            // fill shape
            paint.setColor(shape.getFillColor());
            paint.setStyle(Paint.Style.FILL);

            switch(shape.getId()) {
                case JRectangle.ID:
                    canvas.drawRect(
                            ((JRectangle) shape).getX0(),
                            ((JRectangle) shape).getY0(),
                            ((JRectangle) shape).getX1(),
                            ((JRectangle) shape).getY1(), paint);
                    break;
                case JCircle.ID:
                    canvas.drawCircle(
                            ((JCircle) shape).getCenterX(),
                            ((JCircle) shape).getCenterY(),
                            ((JCircle) shape).getRadius(), paint);
                    break;
            }

            // draw border
            paint.setStrokeWidth(shape.getThickness());
            if (shape.isSelected()) {
                paint.setColor(Model.SELECTED);
            } else {
                paint.setColor(shape.getLineColor());
            }
            paint.setStyle(Paint.Style.STROKE);

            switch(shape.getId()) {
                case JRectangle.ID:
                    canvas.drawRect(
                            ((JRectangle) shape).getX0(),
                            ((JRectangle) shape).getY0(),
                            ((JRectangle) shape).getX1(),
                            ((JRectangle) shape).getY1(), paint);
                    break;
                case JCircle.ID:
                    canvas.drawCircle(
                            ((JCircle) shape).getCenterX(),
                            ((JCircle) shape).getCenterY(),
                            ((JCircle) shape).getRadius(), paint);
                    break;
                case JLine.ID:
                    canvas.drawLine(
                            ((JLine) shape).getX0(),
                            ((JLine) shape).getY0(),
                            ((JLine) shape).getX1(),
                            ((JLine) shape).getY1(), paint);
                    break;
                case JImage.ID:

                    canvas.drawBitmap(((JImage) shape).bitmap, null,
                            new Rect((int) ((JImage) shape).getX0(),(int) ((JImage) shape).getY0(),(int) ((JImage) shape).getX1(),(int) ((JImage) shape).getY1()), paint);
                    break;
            }
        }
    }
}