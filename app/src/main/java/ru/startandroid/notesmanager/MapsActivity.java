package ru.startandroid.notesmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

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
        AppDatabase db = new AppDatabase(MapsActivity.this);

        List<Note> nl = db.getAllNotes();
        for (int i = 0; i < nl.size(); i++) {
            if (i % 2 == 0) {
                LatLng lat = new LatLng(Double.valueOf(nl.get(i).getNote_width()), Double.valueOf(nl.get(i).getNote_length()));
                //mMap.addMarker(new MarkerOptions().position(lat).title(String.valueOf(nl.get(i).getId()) + " " + nl.get(i).getNote_text()));
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                        .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                        .position(lat)
                        .title(String.valueOf(nl.get(i).getId()) + " " + nl.get(i).getNote_text()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(Double.valueOf(nl.get(0).getNote_width()), Double.valueOf(nl.get(0).getNote_length())), 15));

                // Other supported types include: MAP_TYPE_NORMAL,
                // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    /*   mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mip))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));*/
            } else if (i == 0) {
                LatLng lat = new LatLng(Double.valueOf(nl.get(i).getNote_width()), Double.valueOf(nl.get(i).getNote_length()));
                //mMap.addMarker(new MarkerOptions().position(lat).title(String.valueOf(nl.get(i).getId()) + " " + nl.get(i).getNote_text()));
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mr1))
                        .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                        .position(lat)
                        .title(String.valueOf(nl.get(i).getId()) + " " + nl.get(i).getNote_text()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(Double.valueOf(nl.get(0).getNote_width()), Double.valueOf(nl.get(0).getNote_length())), 15));

                // Other supported types include: MAP_TYPE_NORMAL,
                // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    /*   mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mip))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));*/
            } else {
                LatLng lat = new LatLng(Double.valueOf(nl.get(i).getNote_width()), Double.valueOf(nl.get(i).getNote_length()));
                //mMap.addMarker(new MarkerOptions().position(lat).title(String.valueOf(nl.get(i).getId()) + " " + nl.get(i).getNote_text()));
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mr2))
                        .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                        .position(lat)
                        .title(String.valueOf(nl.get(i).getId()) + " " + nl.get(i).getNote_text()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(Double.valueOf(nl.get(0).getNote_width()), Double.valueOf(nl.get(0).getNote_length())), 15));

                // Other supported types include: MAP_TYPE_NORMAL,
                // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    /*   mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mip))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));*/
            }


        }


    }

    // Add a marker in Sydney and move the camera

}
