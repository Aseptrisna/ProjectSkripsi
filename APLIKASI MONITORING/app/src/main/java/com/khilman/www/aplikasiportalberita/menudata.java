package com.khilman.www.aplikasiportalberita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menudata extends AppCompatActivity {
    Button lok,amp,volt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudata);

        lok=(Button)findViewById(R.id.lok);
        amp=(Button)findViewById(R.id.amp);
        volt=(Button)findViewById(R.id.volt);



        lok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudata.this,notifikasi .class);
                startActivity(intent);
            }
        });
        volt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudata.this,recordvot.class);
                startActivity(intent);
            }
        });
        amp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudata.this,recordamp .class);
                startActivity(intent);
            }
        });

    }
}
