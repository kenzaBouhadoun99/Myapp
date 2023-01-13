package com.example.myapp;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.sql.Timestamp;

public class ChronometreActivity extends AppCompatActivity{


    int hoursdeb,mindeb,secdeb;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometre);
        ImageButton boutonstart= findViewById(R.id.str);
        ImageButton boutonstop = findViewById(R.id.stp);

        //un clic sur ce bouton permet de donner l'heure actuelle
        boutonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoursdeb = timestamp.getHours();
                mindeb = timestamp.getMinutes();
                secdeb = timestamp.getSeconds();
                String s = ""+hoursdeb+":"+mindeb+":"+secdeb;
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }
        });
        //un clic sur ce Bouton : permet de faire la différence entre la premiere heure est l'heure actuelle
        boutonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
                int newh = timestamp2.getHours();
                int newm=timestamp2.getMinutes();
                int news=timestamp2.getSeconds();

                if(newh >hoursdeb){  //h2>h1
                    if(newm>mindeb) {//m2>m1
                        Toast.makeText(getApplicationContext(),""+(newh-hoursdeb)+":"+(newm- mindeb)+":"+(news-secdeb),Toast.LENGTH_SHORT).show();
                    }else{  //m2<m1
                        if(news>secdeb){//s2>s1
                            Toast.makeText(getApplicationContext(),""+(newh-hoursdeb-1)+":"+(60+newm- mindeb)+":"+(news-secdeb),Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),""+(newh-hoursdeb-1)+":"+(60+newm- mindeb-1)+":"+(60+news-secdeb),Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (newh == hoursdeb ) {
                    if(news>secdeb){
                        Toast.makeText(getApplicationContext(),""+(newh-hoursdeb)+":"+(newm- mindeb)+":"+(news-secdeb),Toast.LENGTH_SHORT).show();
                    } else if (news<secdeb) {
                        Toast.makeText(getApplicationContext(),""+(newh-hoursdeb)+":"+(newm- mindeb-1)+":"+(60+news-secdeb),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}