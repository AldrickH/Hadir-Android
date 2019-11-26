package com.aldricklevina.hadir;

import android.content.Intent;
import android.os.Bundle;

import com.aldricklevina.hadir.Model.Account;
import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.ClassInfo;
import com.aldricklevina.hadir.Model.Student;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (app == null) {
            app = (App) this.getApplication();
        }

        // Initialize List Account
        if (app.listAcc == null) {
            app.listAcc = new ArrayList<>();
            app.listAcc.add(new Account("admin", "ADMIN", "admin"));
            app.listAcc.add(new Account("levina@gmail.com", "Levina Khomulia", "1234"));
            app.listAcc.add(new Account("aldrick@gmail.com", "Aldrick Handinata", "1234"));
        }

        if (app.listClassInfo == null) {
            app.listClassInfo = new ArrayList<>();
            app.listClassInfo.add(new ClassInfo("C0001","21 November 2019", "Kalkulus", "admin", "12.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("C0002","22 November 2019", "DATA", "levina@gmail.com", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("C0003","21 November 2019", "Nanti Baru isi", "levina@gmail.com", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("C0004","19 November 2019", "Nanti Baru isi", "aldrick@gmail.com", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("C0005","15 November 2019", "Nanti Baru isi", "aldrick@gmail.com", "11.00 AM", "12.00 AM"));
        }

        if (app.listStudent == null) {
            app.listStudent = new ArrayList<>();
            app.listStudent.add(new Student("S0001", "Aldrick Handinata", "C0001"));
            app.listStudent.add(new Student("S0002", "Levina Khomulia", "C0001"));
            app.listStudent.add(new Student("S0003", "Griselda Guinarto", "C0002"));
            app.listStudent.add(new Student("S0004", "Muhammad Alibaba", "C0002"));
            app.listStudent.add(new Student("S0005", "Ali daei", "C0003"));
            app.listStudent.add(new Student("S0006", "Thomas", "C0003"));
            app.listStudent.add(new Student("S0007", "Rey", "C00004"));
            app.listStudent.add(new Student("S0008", "Robin acek", "C0004"));
        }

        if (app.isLogin) {
            intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish();
        } else {
            intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }
    }
}
