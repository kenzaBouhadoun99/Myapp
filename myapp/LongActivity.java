package com.example.myapp;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.AsyncTask;
public  class LongActivity extends AppCompatActivity {

    protected Button button;

    protected ProgressBar progB,progB2;
    int i = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long);
        progB =findViewById(R.id.progressbar);
        progB2 =findViewById(R.id.progressBar2);
        button = findViewById(R.id.tacheL);
        Button test = (Button) findViewById(R.id.bbbb);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                progB.setVisibility(View.VISIBLE);
                progB2.setVisibility(View.VISIBLE);
                Timer tim =new Timer();
                TimerTask Ttask = new TimerTask() {
                    @Override
                    public void run() {
                        //Conteur qui permet au ProgressBar de progresser au fil du temps
                        i++;
                        progB.setProgress(i);
                        progB2.setProgress(i);
                        if(i ==100){
                            tim.cancel();
                            progB2.setVisibility(View.INVISIBLE);
                            progB.setVisibility(View.INVISIBLE);
                        }
                    }
                };
                tim.schedule(Ttask,90,90);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                ExecutorService service = Executors.newSingleThreadExecutor();
                service.execute(runnable);
                runOnUiThread(runnable);
                Toast.makeText(LongActivity.this, "La tache longue est finie", Toast.LENGTH_SHORT).show();

            }
        });


        /**
         * Ici j'ai essayer d'afficher ProgressBar sur un autre boutuon vide est comme vous pouvez voir cela fonctionne bien par contre si je fais la meme chose
         sur le bouton de Long activity le ProgressBar ne s'affiche qu'à la fin du sleep
         */
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progB.setVisibility(View.VISIBLE);
                progB2.setVisibility(View.VISIBLE);
                Timer tim =new Timer();
                TimerTask Ttask = new TimerTask() {
                    @Override
                    public void run() {
                        i++;
                        progB.setProgress(i);
                        progB2.setProgress(i);
                        if(i ==100){
                            tim.cancel();
                            progB2.setVisibility(View.INVISIBLE);
                            progB.setVisibility(View.INVISIBLE);
                        }
                    }
                };
                tim.schedule(Ttask,90,90);
            }
        });
    }
}