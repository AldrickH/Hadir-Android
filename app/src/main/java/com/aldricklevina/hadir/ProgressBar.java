package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ProgressBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.progressBarBiru);
//        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.progressBarColor));
//        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
//        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
//        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2000; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(90, animationDuration); // Default duration = 1500ms

        ImageView imageStudent = findViewById(R.id.fotoStudent);
        imageStudent.setImageResource(R.drawable.foto_student);

    }
}
