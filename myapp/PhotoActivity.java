package com.example.myapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ImageView image_saved = findViewById(R.id.imgview);
        Bitmap bm ;
        FileInputStream  fis = null ;
        try {
          fis = openFileInput("image.data");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        bm = BitmapFactory.decodeStream(fis);
        image_saved.setImageBitmap(bm);
        // image sauvegarder est null
        if (image_saved != null){
            Log.i("activity_main" , "image");
        }

        //image bitmap null
        if ( bm != null){
            Log.i("activity_main" , " bm image");
        }
    }
}