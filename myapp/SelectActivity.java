package com.example.myapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SelectActivity extends AppCompatActivity {
    protected ImageView img;
    protected SurfaceView surfaceView ;
    protected RectangleSelect rs;
    protected RelativeLayout relativelayout;
    protected Canvas can;
    protected Paint pain;
    double a,b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

             img = findViewById(R.id.im);
             surfaceView = findViewById(R.id.surV);
            surfaceView=findViewById(R.id.surV);
            relativelayout=findViewById(R.id.relativeL);

            //Mettre surface view en transparent
            surfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
            surfaceView.setZOrderOnTop(true);

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               //Le cas ou le nombres de clics sur l'image est égale a deux
                if(motionEvent.getPointerCount() ==2) {
                    if(motionEvent.getActionIndex() == MotionEvent.ACTION_DOWN) {
                        a = motionEvent.getX(0);
                        b = motionEvent.getY(0);
                    }else
                        c = motionEvent.getX(1);
                        d = motionEvent.getY(1);
                }
                Log.i("SelectActivity.java","X : "+ a +"Y : "+ b +"V : = "+ c +"Z : "+d);

                //Pour dessiner le rectangle entre deux point
                pain = new Paint();
                can = new Canvas();
                rs = new RectangleSelect(SelectActivity.this, pain,can,(int) a,(int)b,(int)c,(int)d);
                relativelayout.addView(rs);
                return true;

              /*Log.v("MP", "testcode" + motionEvent.getPointerCount());
               if  (motionEvent.getPointerCount() == 2) {
                    Log.v("La valeur de X", "testx" + motionEvent.getX(0));
                    Log.v("La valeur de Y", "testy" + motionEvent.getY(1));
                }
                return SelectActivity.super.onTouchEvent(motionEvent);*/


              /*  if(motionEvent.getPointerCount() == 2 ) {
                    switch (eventtype) {
                        case MotionEvent.ACTION_DOWN:
                            Log.i("Touchevents", "Action down");
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            Log.i("Touchevents", "Action Pointeur down" + motionEvent.getPointerCount());
                            Log.v("La valeur de X", "testx" + motionEvent.getX(0));
                            Log.v("La valeur de Y", "testy" + motionEvent.getY(1));
                            float x= motionEvent.getX(0);
                            float y =motionEvent.getY( 1);
                            rectangle =new Rect(2,1,1,1);
                            rectangle.sort();
                            Log.v("rectangle","voici le rectangle"+rectangle);
                            Log.i("Touchevents", "Action Pointeur down" + motionEvent.getPointerCount());
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.i("Touchevents", "Action up");
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            Log.i("Touchevents", "Action Pointeur up" + motionEvent.getPointerCount());
                            Log.v("La valeur de X ", "testx " + motionEvent.getX(0));
                            Log.v("La valeur de Y ", "testy " + motionEvent.getY(1));
                            rectangle =new Rect(2,1,1,1);
                            rectangle.sort();
                            Log.v("rectangledeux","voici le rectangleDeux"+rectangle);
                            break;
                    }
                }*/
            }
        });
    }
}