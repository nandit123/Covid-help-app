package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Toast toast = Toast.makeText(getApplicationContext(), "Donation Checked in to current Location", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        // initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set about selected
        bottomNavigationView.setSelectedItemId(R.id.about);


        // perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

}
