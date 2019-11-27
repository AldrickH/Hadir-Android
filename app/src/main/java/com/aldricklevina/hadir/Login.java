package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aldricklevina.hadir.Model.Account;
import com.aldricklevina.hadir.Model.App;

public class Login extends AppCompatActivity {

    private Button btnLogin, btnRegis;
    private EditText editTextPass, editTextEmail;
    private Intent intent;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (app == null) {
            app = (App) this.getApplication();
        }

        btnLogin = findViewById(R.id.btnLogin_login);
        btnRegis = findViewById(R.id.btnRegis_login);
        editTextEmail = findViewById(R.id.editTextEmail_login);
        editTextPass = findViewById(R.id.editTextPass_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Account acc : app.listAcc) {
                    if (editTextEmail.getText().toString().equals(acc.email) && editTextPass.getText().toString().equals(acc.password)) {
                        app.isLogin = true;
                        app.openApp = false;
                        app.acc = acc;
                        break;
                    }
                }

                if (app.isLogin) {
                    intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    editTextEmail.setError("Wrong Email");
                    editTextPass.setError("Wrong Password");
                }
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
