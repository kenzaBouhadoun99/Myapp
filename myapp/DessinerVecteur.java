package com.example.myapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CancellationSignal;
import android.view.View;

public class DessinerVecteur extends View {
    protected Paint paint =new Paint();

    /**
     * Constructeur de DessinVecteur
     * @param c
     */
    public DessinerVecteur(Context c){
        super(c);
    }
    @Override
    protected void onDraw(Canvas can){
        super.onDraw(can);
        can.drawLine(600,900,600,600,paint);
    }

}
