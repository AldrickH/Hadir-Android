package com.aldricklevina.hadir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.ui.home.HomeFragment;
import com.aldricklevina.hadir.ui.myclass.MyClassFragment;
import com.aldricklevina.hadir.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private BottomNavigationView navView;
    private Fragment selectedFrag;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (app == null) app = (App) this.getApplication();

        navView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(app)).commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectedFrag = null;

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        selectedFrag = new HomeFragment(app);
                        break;
                    case R.id.navigation_myclass:
                        selectedFrag = new MyClassFragment(app);
                        break;
                    case R.id.navigation_profile:
                        selectedFrag = new ProfileFragment(app);
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFrag).commit();

                return true;
            }
        });
    }
}
