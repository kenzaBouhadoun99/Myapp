package com.example.myapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
//7745643ce65cda7306283810745d0368
public class MeteoActivity extends AppCompatActivity implements LocationListener {


    TextView resMeteo;
    DecimalFormat decimalformat;
    Button geo;
   double latitude , longitude ;
    LocationManager locmanager;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=7745643ce65cda7306283810745d0368", latitude, longitude);

                InputStream in = null;
                String affichage="";
                try {
                    in = new java.net.URL(url).openStream();
                    JSONObject res = readStream(in);
                    // Afficher le resultat
                    decimalformat =new DecimalFormat("#.##");
                    JSONArray j = res.getJSONArray("weather");
                    JSONObject jsonObjectWeather =j.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    String icon =jsonObjectWeather.getString("icon");
                    JSONObject jsonObjectWind = res.getJSONObject("wind");
                    String vent = jsonObjectWind.getString("speed");
                    JSONObject jsonObjectMain = res.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp")-273.15;
                    int humidity = jsonObjectMain.getInt("humidity");
                    affichage += " \n DESCRIPTION :  "+description
                              +  " \n TEMPERAPTURE :  "+decimalformat.format(temp) + " °C "
                              +  " \n HUMIDITE : "+humidity +" %"
                              +  " \n ICON :  "+icon
                              +  " \n VENT  :  "+vent + " m/s (Metre par second)";
                    resMeteo.setText(affichage);
                    Log.i("MeteoActivity", "resultat " + res.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        geo = (Button) findViewById(R.id.geo);
        resMeteo =findViewById(R.id.recopie2);



        // Ajout du  runtime permission
        if (ContextCompat.checkSelfPermission(MeteoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MeteoActivity.this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, 100);
        }

        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfficherLocalisation();
            }
        });
    }

    private JSONObject readStream(InputStream is) throws IOException , JSONException{
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for(String line =r.readLine();line != null;line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return new JSONObject(sb.toString());
    }

    @SuppressLint("MissingPermission")
    private void AfficherLocalisation() {
        try{
            locmanager =(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
          /*gps-provider*/  locmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,5,MeteoActivity.this);
        }catch(Exception e){
           e.printStackTrace();
        }
     }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        thread.start();

        Toast.makeText(this, "latitude :"+latitude+" ,longitude : "+longitude, Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder =new Geocoder(MeteoActivity.this, Locale.getDefault());
            List<Address> adresses =geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String add=adresses.get(0).getAddressLine(0);
            Toast.makeText(this, ""+add, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}