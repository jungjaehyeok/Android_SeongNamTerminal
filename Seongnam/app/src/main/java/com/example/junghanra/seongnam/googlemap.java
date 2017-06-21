package com.example.junghanra.seongnam;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class googlemap extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {

        LatLng startingPoint = new LatLng(37.413128, 127.126832);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(startingPoint);
        markerOptions.title("성남종합터미널");
        markerOptions.snippet("경기도 성남시 분당구 야탑동 341");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(startingPoint));
        map.animateCamera(CameraUpdateFactory.zoomTo(16));
    }

}
