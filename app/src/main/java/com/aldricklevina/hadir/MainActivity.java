package com.aldricklevina.hadir;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.aldricklevina.hadir.Model.Account;
import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.ClassInfo;
import com.aldricklevina.hadir.Model.Student;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private App app;
    private Handler handler;

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
            app.listClassInfo.add(new ClassInfo("K0001", "21 November 2019", "Kalkulus", "admin", "12.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0002", "22 November 2019", "DATA", "levina@gmail.com", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0003", "21 November 2019", "English", "levina@gmail.com", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0004", "19 November 2019", "Mobile Programming", "aldrick@gmail.com", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0005", "15 November 2019", "Cloud Computing", "aldrick@gmail.com", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0006", "27 November 2019", "Computer Security", "admin", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0007", "27 November 2019", "Matematika Diskrit", "admin", "15.00 AM", "17.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0008", "28 November 2019", "English", "admin", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0009", "26 November 2019", "Cloud Computing", "admin", "09.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0010", "27 November 2019", "Mobile Programming", "aldrick@gmail.com", "11.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0011", "27 November 2019", "Kelas 1", "admin", "09.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0012", "27 November 2019", "Kelas 2", "admin", "09.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0013", "26 November 2019", "Kelas 3", "admin", "09.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0014", "26 November 2019", "Kelas 4", "admin", "09.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0015", "26 November 2019", "Kelas 5", "admin", "09.00 AM", "12.00 AM"));
            app.listClassInfo.add(new ClassInfo("K0016", "1 January 2020", "Future Class", "admin", "09.00 AM", "12.00 AM"));
        }

        if (app.listStudent == null) {
            app.listStudent = new ArrayList<>();
            app.listStudent.add(new Student("S0001", "Aldrick Handinata", "K0001", ""));
            app.listStudent.add(new Student("S0002", "Levina Khomulia", "K0001", ""));
            app.listStudent.add(new Student("S0003", "Griselda Guinarto", "K0002", ""));
            app.listStudent.add(new Student("S0004", "Muhammad Alibaba", "K0002", ""));
            app.listStudent.add(new Student("S0005", "Ali daei", "K0003", ""));
            app.listStudent.add(new Student("S0006", "Thomas", "K0003", ""));
            app.listStudent.add(new Student("S0007", "Rey", "K0004", ""));
            app.listStudent.add(new Student("S0008", "Robin acek", "K0004", ""));
            app.listStudent.add(new Student("S0009", "Cek Dis", "K0006", ""));
            app.listStudent.add(new Student("S0010", "Martin", "K0006", ""));
            app.listStudent.add(new Student("S0011", "Acek", "K0006", ""));
            app.listStudent.add(new Student("S0012", "Budi", "K0006", ""));
            app.listStudent.add(new Student("S0013", "Cynthia", "K0006", ""));
            app.listStudent.add(new Student("S0014", "Doraemon", "K0006", ""));
            app.listStudent.add(new Student("S0015", "Acek Budi", "K0006", ""));
            app.listStudent.add(new Student("S0016", "Acek Botak", "K0006", ""));

        }

        if (app.openApp) {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switchActivity();
                }
            }, 3000);
        } else {
            switchActivity();
        }
    }

    public void switchActivity() {
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
