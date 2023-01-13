package com.example.myapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RectangleSelect extends View {
    protected Canvas canvas;
    protected Paint dess;
    protected int x,y,X,Y;

    public RectangleSelect(Context context, Paint paint, Canvas canvas, int x, int y, int X, int Y) {
        super(context);
        this.dess = paint;
        this.canvas = canvas;
        this.x = x;
        this.y = y;
        this.X = X;
        this.Y = Y;
    }

    /**
     * Fonction qui permet de dessiner le rectangle
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas){
        dess.setColor(Color.RED);
        dess.setAlpha(50);
        dess.setStrokeWidth(10);
        canvas.drawRect(x,y,X,Y, dess);
    }
    }
