package com.tanobomretiro.gabriel.tanobomretiro;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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


        // adicionar mardador
        LatLng location = new LatLng(-23.564171, -46.532283);
        mMap.addMarker(new MarkerOptions().position(location).title("Bom Retiro"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        // Move a câmera para Framework System com zoom 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);






        if (ContextCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Verifica se já mostramos o alerta e o usuário negou na 1ª vez.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,ACCESS_FINE_LOCATION)) {
                // Caso o usuário tenha negado a permissão anteriormente, e não tenha marcado o check "nunca mais mostre este alerta"
                // Podemos mostrar um alerta explicando para o usuário porque a permissão é importante.
            } else {
                // Solicita a permissão
                ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},0);
            }
        } else {
            // Tudo OK, podemos prosseguir.
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.setMyLocationEnabled(true);
        }
    }
}


