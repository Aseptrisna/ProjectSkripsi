package com.khilman.www.aplikasiportalberita;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class tampiltegangan extends AppCompatActivity {


    private ListView listview;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilvolt);
        getJOSN();
        listview = (ListView)findViewById(R.id.listview);
    }
    private void getJOSN(){
        class GetJSON extends AsyncTask<Void, Void, String>{

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
                loading = ProgressDialog.show(tampiltegangan.this,"Mengambil Data...","Mohon Tunggu",false,false);
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
                ListAdapter adapter = new SimpleAdapter(
                        tampiltegangan.this, list, R.layout.data,
                        new String[]{config.KEY_ID,config.KEY_VOLT},
                        new int[]{R.id.id,R.id.volt});
                listview.setAdapter(adapter);
            }

        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
