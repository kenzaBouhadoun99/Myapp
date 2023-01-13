package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;


public class ContactsActivity extends AppCompatActivity {
    private ListView resultat;
    private String data1[], data2[];
    private ContentResolver CResolver;
    private Cursor Tel;
    private  ArrayAdapter<String> adapt;
    private ArrayList<String> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        array=new ArrayList<String>();
        adapt = new ArrayAdapter<String>(this,R.layout.activity_contact_bis,R.id.textView,array);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        voirContact();
    }

    /**
     *Fonction qui permet d'afficher les contacts (Dont le nom commence par A ) avec une Liste view
     */
    public void voirContact(){
        CResolver =this.getContentResolver();
        Tel = CResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,ContactsContract.CommonDataKinds.Phone.NUMBER},null,null);

        if(Tel == null){
            Log.d("recuperer les contacts","erreur");
        }else{
             resultat = (ListView)findViewById(R.id.edittext);
             while(Tel.moveToNext()){
                @SuppressLint("Range") String nom = Tel.getString(Tel.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                @SuppressLint("Range") String num = Tel.getString(Tel.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                if(nom.startsWith("A")){
                    array.add(nom+" : "+num);
                    resultat.setAdapter(adapt);
                }
             }
            Tel.close();
        }
    }
}