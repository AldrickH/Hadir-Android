package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class StudentProfile extends AppCompatActivity {

    private ImageView imgBackStudentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.progressBarBiru);
//        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.progressBarColor));
//        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
//        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
//        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2000; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(90, animationDuration); // Default duration = 1500ms

        ImageView imageStudent = findViewById(R.id.fotoStudent);
        imageStudent.setImageResource(R.drawable.foto_student);

        imgBackStudentProfile = findViewById(R.id.imgBack_studentProfile);
        imgBackStudentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
