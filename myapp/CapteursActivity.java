package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.hardware.*;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class CapteursActivity extends AppCompatActivity implements SensorEventListener {

    protected static final String TAG = "CapteursActivity";
    protected SensorManager sensorManager;
    protected Sensor accelerometre,magno ;
    protected TextView xvaleur,yvaleur,zvaleur,xmagno,ymagno,zmagno;
    protected Switch monswitch;
    protected DessinerVecteur dessinerVecteur;
    protected RelativeLayout RL;
    protected ImageView imageV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capteurs);

        //Cela permet de bloquer la rotation dans l'activité CapteurActivity
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        xvaleur = findViewById(R.id.xval);
        yvaleur = findViewById(R.id.yval);
        zvaleur = findViewById(R.id.zval);
        xmagno = findViewById(R.id.xmag);
        ymagno = findViewById(R.id.ymag);
        zmagno = findViewById(R.id.zmag);
        monswitch =findViewById(R.id.swit);
        RL = findViewById(R.id.relt);
        imageV =findViewById(R.id.imge);

        monswitch.setChecked(false);
        dessinerVecteur =new DessinerVecteur(this);
        dessinerVecteur.setVisibility(View.INVISIBLE);

        monswitch.setOnClickListener(new View.OnClickListener() {

            @SuppressLint({"ServiceCast", "WrongConstant"})
            @Override
            public void onClick(View view) {

                Log.d(TAG,"On create : inisialize senor services ");
                sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
                //Le type accelerometre
                accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                //le type magnetometre
                magno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

                //si le switch est activé et accelerometre n'est pas null
                if(monswitch.isChecked() && accelerometre != null){
                        monswitch.setText("ACTIVER");
                        sensorManager.registerListener(CapteursActivity.this,accelerometre,SensorManager.SENSOR_DELAY_NORMAL);
                        sensorManager.registerListener(CapteursActivity.this,magno,SensorManager.SENSOR_DELAY_NORMAL);
                        //Je n'ai pas reussis a afficher le vecteur
                       //RL.addView(dessinerVecteur);
                       //imageV.setVisibility(View.VISIBLE);
                       dessinerVecteur.setVisibility(View.VISIBLE);
                }else{
                    //si le switch est desactivé
                    dessinerVecteur.setVisibility(View.VISIBLE);
                    monswitch.setText("DESACTIVER");
                        xvaleur.setText("");
                        yvaleur.setText("");
                        zvaleur.setText("");
                        sensorManager.unregisterListener(CapteursActivity.this,accelerometre);
                        xmagno.setText("");
                        ymagno.setText("");
                        zmagno.setText("");
                        sensorManager.unregisterListener(CapteursActivity.this,magno);
                }
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG,"onSensor changer  x : "+ sensorEvent.values[0]+"onSensor changer  y :"+ sensorEvent.values[1]+"onSensor changer  z  :"+ sensorEvent.values[2]);
            xvaleur.setText("Accelerometer(X): " + sensorEvent.values[0]);
            yvaleur.setText("Accelerometer(Y): " + sensorEvent.values[1]);
            zvaleur.setText("Accelerometer(Z): " + sensorEvent.values[2]);
            dessinerVecteur.setVisibility(View.INVISIBLE);
        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            xmagno.setText("Magnetic(X): " + sensorEvent.values[0]);
            ymagno.setText("Magnetic(Y): " + sensorEvent.values[1]);
            zmagno.setText("Magnetic(Z): " + sensorEvent.values[2]);
        }
    }
}