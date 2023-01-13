package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;


import java.io.*;

public class MainActivity extends AppCompatActivity {

    public  int PHOTO =15;


    public MainActivity() throws FileNotFoundException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", "une info");
        Gson f =new Gson();
        Button bnj = (Button) findViewById(R.id.bnj);
        Button photo = (Button) findViewById(R.id.ph);
        Button chrono = (Button) findViewById(R.id.chrn);
        Button tachelong =(Button) findViewById(R.id.nvAc);
        Button meteo=(Button) findViewById(R.id.met);
        Button dernierimg =(Button) findViewById(R.id.bmp);
        Button contact =(Button) findViewById(R.id.selectionner);
        Button capteur =(Button) findViewById(R.id.capt);
        Button select =(Button) findViewById(R.id.sel);

        /**
        Affichage de l'activité bonjour
         */
        bnj.setOnClickListener(view->{ Toast.makeText(this,"Bonjour",Toast.LENGTH_SHORT).show();});
        /**
        Affichage de l'activité chronometre
         */
        chrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChronometreActivity.class);
                startActivity(intent);
            }
        });

        ActivityResultLauncher<Intent> lancher1 =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result) {
                    Toast.makeText(MainActivity.this,"la prise de photo",Toast.LENGTH_SHORT).show();
                }
            }
         );
         /**
        Affichage de l'activité photo
         */
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent2,PHOTO);
            }
        });
        /*
        Affichage de l'activité tache longue
         */
        tachelong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(getApplicationContext(),LongActivity.class);
                startActivity(in);
            }
        });
         /**
        Affichage de l'activité meteo
         */
        meteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 =new Intent(getApplicationContext(), MeteoActivity.class);
                startActivity(in2);
            }
        });

        /**
         * Affichage de l'activité derniere image
         */
        dernierimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 =new Intent(getApplicationContext(), PhotoActivity.class);
                startActivity(in2);
            }
        });

        /**
         * Affichage de l'activité contact
         */
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent in3 = new Intent(getApplicationContext(), ContactsActivity.class);
            startActivity(in3);
            }

        });

        /**
         * Affichage de l'activité capteurs
         */
         capteur.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Intent in4 = new Intent(getApplicationContext(),CapteursActivity.class);
                startActivity(in4);
             }
         });
        /**
         *  Affichage de l'activité select
         */
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
    }

            @Override
            protected void onActivityResult(int requestCode , int resultCode,Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == PHOTO && resultCode == Activity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap image = (Bitmap) extras.get("data");
                    FileOutputStream foss = null;

                    Log.i("activity_main" , "copie");

                    try {
                        foss = openFileOutput("image.data", MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    image.compress(Bitmap.CompressFormat.PNG, 100, foss);
                    try {
                        foss.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                Log.i("activity_main" , "sauvgarde");
            }

            /**
             *Fonction qui permet de crée le menu
             * @param menu
             * @return
             */
            public boolean onCreateOptionsMenu(Menu menu) {
                        getMenuInflater().inflate(R.menu.menu_main, menu);
                        return true;
                    }

            /**
             *Fonction qui permet de quitter l'application
             * @param q
             */
            public void quitter(MenuItem q){
                        System.exit(0);
                    }

            /**
             *Fonction qui permet de suspendre l'application
             * @param sus
             */

            public void suspendre(MenuItem sus){
                finish();
            }

            /**
             *Fonction qui permet de lancer le chrono
             * @param lchrono
             */
            public void lancechrono(MenuItem lchrono){
                Intent intent = new Intent(getApplicationContext(), ChronometreActivity.class);
                startActivity(intent);
            }
    }
