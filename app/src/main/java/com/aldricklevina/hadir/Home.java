package com.aldricklevina.hadir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.aldricklevina.hadir.Model.Account;
import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.ui.home.HomeFragment;
import com.aldricklevina.hadir.ui.myclass.MyClassFragment;
import com.aldricklevina.hadir.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private BottomNavigationView navView;
    private Fragment selectedFrag;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectedFrag = null;

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        selectedFrag = new HomeFragment();
                        break;
                    case R.id.navigation_myclass:
                        selectedFrag = new MyClassFragment();
                        break;
                    case R.id.navigation_profile:
                        selectedFrag = new ProfileFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFrag).commit();

                return true;
            }
        });
    }
}
