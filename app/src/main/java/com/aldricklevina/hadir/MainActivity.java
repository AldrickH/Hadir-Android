package com.aldricklevina.hadir;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.aldricklevina.hadir.Model.Account;
import com.aldricklevina.hadir.ui.home.HomeFragment;
import com.aldricklevina.hadir.ui.myclass.MyClassFragment;
import com.aldricklevina.hadir.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Boolean isLogin = false;
    private ArrayList<Account> listAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();

        if (intent != null) {
            isLogin = intent.getBooleanExtra("isLogin", false);
            listAcc = intent.getParcelableArrayListExtra("listAcc");
        }

        if (listAcc == null) {
            listAcc = new ArrayList<>();
            listAcc.add(new Account("admin", "ADMIN", "admin"));
            listAcc.add(new Account("levina@gmail.com", "Levina Khomulia", "1234"));
            listAcc.add(new Account("aldrick@gmail.com", "Aldrick Handinata", "1234"));
        }

        if (isLogin) {
            intent = new Intent(MainActivity.this, Home.class);
            intent.putParcelableArrayListExtra("listAcc", listAcc);
            startActivity(intent);
            finish();
        } else {
            intent = new Intent(MainActivity.this, Login.class);
            intent.putParcelableArrayListExtra("listAcc", listAcc);
            startActivity(intent);
            finish();
        }
    }
}
