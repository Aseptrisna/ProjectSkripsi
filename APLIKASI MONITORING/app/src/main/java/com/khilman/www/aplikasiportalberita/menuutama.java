package com.khilman.www.aplikasiportalberita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class menuutama extends AppCompatActivity {
    private String mStringUrl ="https://monitoringaki.000webhostapp.com/sim900/led.php?light=";
            //"http://192.168.43.71/LED1/led.php?light=";
    //
    //"//192.168.43.71/LED1/led.php?light.json=";
    Button on, off,maps,volt,amp,user,kamera,data;
    private OkHttpClient client;
    private Request request;
    private String TAG = "menuutama";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama);
        client = new OkHttpClient();

        //inisialisasi
        on=(Button)findViewById(R.id.btnunlock);
        off=(Button)findViewById(R.id.btnlock);
        volt = (Button) findViewById(R.id.btnvolt);
        amp = (Button) findViewById(R.id.btnarus);
        maps = (Button) findViewById(R.id.btnlocation);
        user = (Button) findViewById(R.id.btnuser);
        kamera = (Button) findViewById(R.id.btnfoto);


        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nyala("on");
                Toast.makeText(getApplicationContext(), "Unlock Aktif", Toast.LENGTH_SHORT).show();
            }
        });


        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nyala("off");
                Toast.makeText(getApplicationContext(), "Lock Aktif", Toast.LENGTH_SHORT).show();
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuutama.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        volt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuutama.this, recordvot.class);
                startActivity(intent);
            }
        });
        amp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuutama.this, recordamp.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuutama.this, user.class);
                startActivity(intent);
            }
        });
        kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuutama.this, gantifoto.class);
                startActivity(intent);
            }
        });

    }

    private void nyala(String id){
        request = new Request.Builder().url(mStringUrl+id).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.i(TAG, "onResponse: succes ");
            }
        });
    }
}
