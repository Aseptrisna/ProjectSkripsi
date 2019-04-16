package com.khilman.www.aplikasiportalberita;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import com.khilman.www.aplikasiportalberita.models.ListLocationModel;
import com.khilman.www.aplikasiportalberita.models.LocationModel;
import com.khilman.www.aplikasiportalberita.services.ApiClient;
import retrofit2.Response;
import com.khilman.www.aplikasiportalberita.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<LocationModel> mListMarker = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getAllDataLocationLatLng();
    }

    /**
     * method ini digunakan menampilkan data marker dari database
     */
    private void getAllDataLocationLatLng(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Menampilkan posisi kendaraan ..");
        dialog.show();

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ListLocationModel> call = apiService.getAllLocation();
        call.enqueue(new Callback<ListLocationModel>() {
            @Override
            public void onResponse(Call<ListLocationModel> call, Response<ListLocationModel> response) {
                dialog.dismiss();
                mListMarker = response.body().getmData();
                initMarker(mListMarker);
            }

            @Override
            public void onFailure(Call<ListLocationModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Method ini digunakan untuk menampilkan semua marker di maps dari data yang didapat dari database
     * @param listData
     */
    private void initMarker(List<LocationModel> listData){
        //iterasi semua data dan tampilkan markernya
        for (int i=0; i<mListMarker.size(); i++){
            //set latlng nya
            LatLng location = new LatLng(Double.parseDouble(mListMarker.get(i).getLatutide()),
                    Double.parseDouble(mListMarker.get(i).getLongitude()));
            //tambahkan markernya
            mMap.addMarker(new MarkerOptions().position(location).title(mListMarker.get(i).getImageLocationName()));
            //set latlng index ke 0
            LatLng latLng = new LatLng(Double.parseDouble(mListMarker.get(0).getLatutide()),
                    Double.parseDouble(mListMarker.get(0).getLongitude()));
            //lalu arahkan zooming ke marker index ke 0
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude,latLng.longitude), 17.0f));
        }
    }
}