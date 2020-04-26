package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set dashboard selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        // perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboard:
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);

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
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("no success", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("no style", "Can't find style. Error: ", e);
        }


        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng amritsar = new LatLng(31.633980, 74.872261);
        double LL[][]  = {
                {31.622581,74.851411},
                {31.613517,74.871165},
                {31.620242,74.898306},
                {31.646696,74.911189},
                {31.659263,74.884220},
                {31.657199,74.869437}
        };

        double LL2[][]  = {

                {31.638640,74.884170},
                {31.604051,74.846131},
//                {31.610500,74.841130},
                {31.623895,74.827881}
//                {31.610133,74.831461}
        };

        mMap.addMarker(new MarkerOptions().position(amritsar).title("Amritsar").snippet("Save Punjab NGO Delivered 4 days back"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(amritsar));

        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);

        LatLng a1 = new LatLng(31.622581,74.851411);
        LatLng a2 = new LatLng(31.613517,74.871165);
        LatLng a3 = new LatLng(31.620242,74.898306);
        LatLng a4 = new LatLng(31.646696,74.911189);
        LatLng a5 = new LatLng(31.659263,74.884220);
        LatLng a6 = new LatLng(31.657199,74.869437);

        LatLng a7 = new LatLng(31.638640,74.884170);
        LatLng a8 = new LatLng(31.604051,74.846131);
        LatLng a9 = new LatLng(31.623895,74.827881);

        mMap.addMarker(new MarkerOptions().position(a1).title("Akash NGO").snippet("Food-1800, Medical-790 (Today)"));
        mMap.addMarker(new MarkerOptions().position(a2).title("Amritsar Helpline").snippet("Food-3000, Medical-1400 (1 day ago)"));
        mMap.addMarker(new MarkerOptions().position(a3).title("Save People NGO").snippet("Food-2300, Medical-2500 (1 day ago)"));
        mMap.addMarker(new MarkerOptions().position(a4).title("Veer Group").snippet("Food-1900, Medical-1790 (2 day ago)"));
        mMap.addMarker(new MarkerOptions().position(a5).title("UNE Society").snippet("Food-2800, Medical-2790 (3 day ago)"));
        mMap.addMarker(new MarkerOptions().position(a6).title("Goodwill NGO").snippet("Food-4500, Medical-4652 (4 day ago)"));


        mMap.addMarker(new MarkerOptions().position(a7).title("People People").snippet("Food-3999, Medical-4888 (8 day ago)"));
        mMap.addMarker(new MarkerOptions().position(a8).title("VSO Society").snippet("Food-2800, Medical-2790 (7 day ago)"));
        mMap.addMarker(new MarkerOptions().position(a9).title("Together We").snippet("Food-4500, Medical-4652 (8 day ago)"));
        // Instantiates a new Polygon object and adds points to define a rectangle
//        PolygonOptions rectOptions = new PolygonOptions()
//                .add(new LatLng(31.671061,74.894678),
//                        new LatLng(31.614943,74.946837),
//                        new LatLng(31.581022,74.858889),
//                        new LatLng(31.622544,74.773690),
//                        new LatLng(31.671061,74.894678));
//
//        // Get back the mutable Polygon
//
//        Polygon polygon = googleMap.addPolygon(rectOptions);
        // Instantiates a new CircleOptions object and defines the center and radius

        Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true).
                        add(new LatLng(31.671061,74.894678),
                                new LatLng(31.614943,74.946837),
                                new LatLng(31.581022,74.858889),
                                new LatLng(31.622544,74.773690),
                                new LatLng(31.671061,74.894678)));
        polygon1.setStrokeWidth(10);
        polygon1.setStrokePattern(PATTERN_POLYLINE_DOTTED);

        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(31.633980, 74.872261))
                .radius(1000); // In meters

        // Get back the mutable Circle
        Circle circle = googleMap.addCircle(circleOptions);
        circle.setFillColor(0x5500ff00);
        circle.setStrokeColor(0x00000000);

        for(int i=0;i<LL.length;i++)
        {
            circleOptions = new CircleOptions()
                    .center(new LatLng(LL[i][0], LL[i][1]))
                    .radius(1000);
            circle = googleMap.addCircle(circleOptions);
            circle.setFillColor(0x5500ff00);
            circle.setStrokeColor(0x00000000);
        }

        for(int i=0;i<LL2.length;i++)
        {
            circleOptions = new CircleOptions()
                    .center(new LatLng(LL2[i][0], LL2[i][1]))
                    .radius(1000);
            circle = googleMap.addCircle(circleOptions);
            circle.setFillColor(0x33ff0000);
            circle.setStrokeColor(0x00000000);
        }
    }
}
