package com.khilman.www.aplikasiportalberita;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class user extends AppCompatActivity {
    Button btnsimpan, btnlihat,reset;
    EditText etnama, etnpm, etprodi, etfakultas,ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnsimpan = (Button) findViewById(R.id.btnubah);
        etnpm = (EditText) findViewById(R.id.txt1);
        etnama = (EditText) findViewById(R.id.txt2);
        etprodi = (EditText) findViewById(R.id.txt3);
        etfakultas = (EditText) findViewById(R.id.txt4);
        ff = (EditText) findViewById(R.id.txt5);
       // btnlihat = (Button) findViewById(R.id.btnlihat);




        reset = (Button) findViewById(R.id.reset);


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanMahasiwa(etnpm.getText().toString().trim(),
                        etnama.getText().toString().trim(),
                        etprodi.getText().toString().trim(),
                        etfakultas.getText().toString().trim(),
                        ff.getText().toString().trim());

            }
        });




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etnpm.setText("");
                etnama.setText("");
                etprodi.setText("");
                etfakultas.setText("");
                ff.setText("");
            }
            });
    }

        public void simpanMahasiwa(final String npm, final String nama, final String prodi, final String fakultas,final String ff){

            class SendPostReqAsyncTask extends AsyncTask<String, Void, String>{
                @Override
                protected String doInBackground(String... strings) {

                    List<NameValuePair> nameValuePairs = new ArrayList<>();
                    nameValuePairs.add(new BasicNameValuePair(simpan.KEY_NPM, npm));
                    nameValuePairs.add(new BasicNameValuePair(simpan.KEY_NAMA, nama));
                    nameValuePairs.add(new BasicNameValuePair(simpan.KEY_PRODI, prodi));
                    nameValuePairs.add(new BasicNameValuePair(simpan.KEY_FAKULTAS, fakultas));
                    nameValuePairs.add(new BasicNameValuePair(simpan.KEY_FAKULTA, ff));

                    try {
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(simpan.URL_simpanMhs);
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                        HttpResponse response = httpClient.execute(httpPost);

                        HttpEntity entity = response.getEntity();

                    } catch (ClientProtocolException e){
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    return "success";
                }

                @Override
                protected void onPostExecute(String result){
                    super.onPostExecute(result);
                    if (result.equalsIgnoreCase("success")){
                        Toast.makeText(getApplicationContext(),"ubah data berhasil",Toast.LENGTH_LONG).show();
                        reset();

                    } else {
                        Toast.makeText(getApplicationContext(),"Gagal Tersimpan",Toast.LENGTH_LONG).show();
                    }
                }
            }
            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
            sendPostReqAsyncTask.execute(npm, nama, prodi, fakultas,ff);



}

    private void reset() {
        etnpm.setText("");
        etnama.setText("");
        etprodi.setText("");
        etfakultas.setText("");
        ff.setText("");
    }
}
