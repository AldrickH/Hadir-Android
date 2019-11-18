package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aldricklevina.hadir.Model.Account;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText editTextEmail, editTextName, editTextPass, editTextRePass;
    private ImageView imgBack;
    private Button btnRegis;
    private Intent intent;
    private String email, name, pass, repass;
    private ArrayList<Account> listAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (intent != null) {
            listAcc = intent.getParcelableArrayListExtra("listAcc");
        }

        imgBack = findViewById(R.id.imgBack_regis);
        btnRegis = findViewById(R.id.btnRegis_regis);
        editTextEmail = findViewById(R.id.editTextEmail_regis);
        editTextName = findViewById(R.id.editTextName_regis);
        editTextPass = findViewById(R.id.editTextPass_regis);
        editTextRePass = findViewById(R.id.editTextRePass_regis);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString();
                name = editTextName.getText().toString();
                pass = editTextRePass.getText().toString();
                repass = editTextRePass.getText().toString();

                if (!email.equals("") && !name.equals("") && !pass.equals("") && !repass.equals("")) {
                    if (pass.equals(repass)) {
                        listAcc.add(new Account(email, name, pass));

                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        intent.putParcelableArrayListExtra("listAcc", listAcc);
                        finish();
                    } else {
                        editTextRePass.setError("Password not Match");
                    }
                } else {
                    if (email.equals("")) {
                        editTextRePass.setError("Wrong email");
                    }

                    if (name.equals("")) {
                        editTextName.setError("Wrong Name");
                    }

                    if (pass.equals("")) {
                        editTextPass.setError("Wrong password");
                    }

                    if (repass.equals("")) {
                        editTextPass.setError("Wrong password");
                    }
                }
            }
        });
    }

}
