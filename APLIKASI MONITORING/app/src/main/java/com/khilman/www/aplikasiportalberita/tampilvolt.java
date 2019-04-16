package com.khilman.www.aplikasiportalberita;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
public class tampilvolt extends AppCompatActivity {

    Button record;
    private ListView listview;
    private String JSON_STRING;
    public static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilvolt);
        getJOSN();
        listview = (ListView)findViewById(R.id.listview);





        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tampilvolt.this, data.class);
                startActivity(intent);
            }
        });

    }

    private void getJOSN(){
        class GetJSON extends AsyncTask<Void, Void, String> {

            //memanggil data JSON
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(config.URL_tampil);
                return s;
            }

            ProgressDialog loading;
            //meminta data dari server 000webhost
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilvolt.this,"Mengambil Data...","Mohon Tunggu",false,false);
            }

            //menampung data dari server 000webhost
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMahasiswa();
                //Toast.makeText(getApplication(),"Data Server"+JSON_STRING,Toast.LENGTH_LONG).show();
            }

            private void showMahasiswa(){
                JSONObject jsonObject = null;
                ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray result = jsonObject.getJSONArray("result");

                    for (int i = 0; i<result.length(); i++)  {
                        JSONObject jo = result.getJSONObject(i);
                        String id = jo.getString(config.KEY_ID);
                        String volt = jo.getString(config.KEY_VOLT);

                        //parameter
                        HashMap<String, String> mahasiswa = new HashMap<>();
                        mahasiswa.put(config.KEY_ID, id);
                        mahasiswa.put(config.KEY_VOLT, volt);

                        list.add(mahasiswa);
                        //Toast.makeText(getApplication(),"Data Mahasiswa "+nama,Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }

        }

        GetJSON gj = new GetJSON();
        gj.execute();


    }



    }
