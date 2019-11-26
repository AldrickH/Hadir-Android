package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aldricklevina.hadir.Model.App;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class StudentProfile extends AppCompatActivity {

    private TextView txtAbsent, txtPresent, txtName, txtId;
    private ImageView imgBackStudentProfile;
    private ProgressBar pbarPresent, pbarAbsent;
    private PBarAnimation pbarPresentAnim, pbarAbsentAnim;

    private int present, absent, total;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        if (app == null) app = (App) this.getApplication();

        txtPresent = findViewById(R.id.txtPresent_studentProf);
        txtAbsent = findViewById(R.id.txtAbsent_studentProf);
        txtName = findViewById(R.id.txtName_studentProf);
        txtId = findViewById(R.id.txtId_studentProf);

        pbarPresent = findViewById(R.id.pbarPresent_studentProf);
        pbarAbsent = findViewById(R.id.pbarAbsent_studentProf);

        present = 87;
        absent = 13;
        total = 100;

        float presentPercentage = ((float) present / total) * 100;
        float absentPercentage = ((float) absent / total) * 100;

        pbarPresentAnim = new PBarAnimation(pbarPresent, 0, presentPercentage);
        pbarAbsentAnim = new PBarAnimation(pbarAbsent, 0, absentPercentage + presentPercentage);

        pbarPresentAnim.setDuration(2000);
        pbarAbsentAnim.setDuration(2000);

        txtAbsent.setText(String.format("Absent: %s %%", absentPercentage));
        txtPresent.setText(String.format("Present: %s %%", presentPercentage));
        txtName.setText(app.student.getFullName());
        txtId.setText(app.student.getId());

        pbarPresent.startAnimation(pbarPresentAnim);
        pbarAbsent.startAnimation(pbarAbsentAnim);

        imgBackStudentProfile = findViewById(R.id.imgBack_studentProf);

        imgBackStudentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
