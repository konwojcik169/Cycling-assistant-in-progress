package com.example.cyclistassistant;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    int latitute, longitude;
    String provider, locationProvider;
    LocationManager locationManager;
    Location location, lastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
        locationProvider = LocationManager.GPS_PROVIDER;
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

    protected void onResume() {
        super.onResume();
        int ACCESS_FINE_LOCATION = 123;
        int hasPermission = checkSelfPermission(Manifest.permission.
                ACCESS_FINE_LOCATION);
        String[] permissions = new String[] {Manifest.permission.
                ACCESS_FINE_LOCATION};
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, ACCESS_FINE_LOCATION);
        } else {
            location = locationManager.getLastKnownLocation(provider);
            lastKnownLocation =
                    locationManager.getLastKnownLocation(locationProvider);
            locationManager.requestLocationUpdates(provider, 0, 0, this);
            if (location != null) {
                onLocationChanged(location);
            } else {

                Toast.makeText(MapsActivity.this, "Location not available",
                        Toast.LENGTH_LONG).show();

            }
        }
    }

    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    public void onLocationChanged(Location location) {
//        float lat = (float) location.getLatitude();
//        float lng = (float) location.getLongitude();
//        latitute.setText("Latitute: " + String.valueOf(lat));
//        longitude.setText("Longitude: " + String.valueOf(lng));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double distance;
        float lat = (float) location.getLatitude();
        float lng = (float) location.getLongitude();
        // Add a marker in Sydney and move the camera
        LatLng yourPosition = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(yourPosition).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yourPosition));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7), 2000, null);

        LatLng dobreRoweryWojciechowska = new LatLng(51.247234, 22.513409);
        distance = CalculationByDistance(dobreRoweryWojciechowska);
//        String resultDistance = String.format("Distance %.2f", distance);
        mMap.addMarker(new MarkerOptions().position(dobreRoweryWojciechowska).title("Dobre Rowery Lublin, Wojciechowska 5A")
                .snippet(String.format("Distance: %.2f km", distance)).icon(BitmapDescriptorFactory.fromResource(R.drawable.tool_36dp)));

        LatLng dobreRoweryGesia = new LatLng(51.227989, 22.480193);
        distance = CalculationByDistance(dobreRoweryGesia);
        mMap.addMarker(new MarkerOptions().position(dobreRoweryGesia).title("Dobre Rowery Lublin, Gęsia 5")
                .snippet(String.format("Distance: %.2f km", distance)).icon(BitmapDescriptorFactory.fromResource(R.drawable.tool_36dp)));

        LatLng dobreRoweryGala = new LatLng(51.238928, 22.572800);
        distance = CalculationByDistance(dobreRoweryGala);
        mMap.addMarker(new MarkerOptions().position(dobreRoweryGala).title("Dobre Rowery Lublin, Fabryczna 2")
                .snippet(String.format("Distance: %.2f km", distance)).icon(BitmapDescriptorFactory.fromResource(R.drawable.tool_36dp)));




    }

    public double CalculationByDistance(LatLng latLng) {
        float lat = (float) location.getLatitude();
        float lng = (float) location.getLongitude();
        int Radius = 6371;// radius of earth in Km
        double lat1 = lat;
        double lat2 = latLng.latitude;
        double lon1 = lng;
        double lon2 = latLng.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return valueResult;
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onProviderDisabled(String provider) {}
}
