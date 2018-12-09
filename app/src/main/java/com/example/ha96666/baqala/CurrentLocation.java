package com.example.ha96666.baqala;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class CurrentLocation extends FragmentActivity implements OnMapReadyCallback {




    //private static final double NAHDA1_LAT = 25.2950;
    //private static final double NAHDA1_LNG = 55.3217;

    //private static final double BARSHA2_LAT = 25.2247574;
    //private static final double BARSHA2_LNG = 55.1781069;

    //private static final double JUMEIRAH_LAT = 25.2284566;
    //private static final double JUMEIRAH_LNG = 55.2590668;

    //private static final double DUBAI_LAT = 25.168905;
    //private static final double DUBAI_LNG = 55.304428;

    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
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
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;



        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        mMap.setMyLocationEnabled(true);




    }

    private static final int REQUEST_CODE_LOCATION = 100;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                          @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_LOCATION)
        {
            if (grantResults.length > 0 &&
                 grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                if (ActivityCompat.checkSelfPermission(this,
                     Manifest.permission.ACCESS_FINE_LOCATION) ==
                     PackageManager.PERMISSION_GRANTED &&
                     ActivityCompat.checkSelfPermission(this,
                             Manifest.permission.ACCESS_COARSE_LOCATION) ==
                             PackageManager.PERMISSION_GRANTED)
                {
                 ActivityCompat.requestPermissions(this,
                         new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                         REQUEST_CODE_LOCATION);
                 mMap.setMyLocationEnabled(true);
                }

            }
        }

    }






    public void searchAndShowOnMap(View view) throws IOException {

        EditText etPlaceToSearch = findViewById(R.id.etPlaceToSearch);
        String placeToSearch = etPlaceToSearch.getText().toString();

        if (placeToSearch.isEmpty()) {
            Toast.makeText(this, "Enter place to search", Toast.LENGTH_SHORT).show();
            return;
        }

        Geocoder geocoder = new Geocoder(this);
        //Toast.makeText(this, "After geocoder", Toast.LENGTH_SHORT).show();

        List<Address> list = geocoder.getFromLocationName(placeToSearch, 1);
        //Toast.makeText(this, "After list", Toast.LENGTH_SHORT).show();

        if (list.size() > 0) {

            Address address = list.get(0);
            //Toast.makeText(this, "After address", Toast.LENGTH_SHORT).show();

            String locality = address.toString();

            Toast.makeText(this, "Found "+locality, Toast.LENGTH_SHORT).show();

            double latitude = address.getLatitude();
            double longitude = address.getLongitude();

            gotoLocation(latitude, longitude, 15, locality);
        }
    }


    public void gotoLocation(double latitude, double longitude, int zoomLevel, String title)
    {
        //Add marker and move camera

        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(title));
        mMap.setMinZoomPreference(zoomLevel);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, zoomLevel);
        //mMap.moveCamera(cameraUpdate);
    }


}
