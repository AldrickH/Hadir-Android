package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.aldricklevina.hadir.Model.Student;
import com.aldricklevina.hadir.Model.StudentAdapter;

import java.util.ArrayList;

public class ClassDetails extends AppCompatActivity {

    private RecyclerView recViewStudent;
    private RecyclerView.LayoutManager recViewStudentLayoutManager;
    private StudentAdapter recViewStudentAdapter;
    private ArrayList<Student> listStudent;
    private ImageView imgBackClassDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        imgBackClassDet = findViewById(R.id.imgBack_classDet);
        recViewStudent = findViewById(R.id.recViewStudentClass);

        recViewStudentLayoutManager = new LinearLayoutManager(this);

        listStudent = new ArrayList<>();

        listStudent.add(new Student("Aldrick Handinata", "03082170001"));
        listStudent.add(new Student("Aldrick Handinata", "03082170002"));
        listStudent.add(new Student("Aldrick Handinata", "03082170003"));

        recViewStudentAdapter = new StudentAdapter(listStudent);

        recViewStudent.setLayoutManager(recViewStudentLayoutManager);
        recViewStudent.setAdapter(recViewStudentAdapter);

        recViewStudent.setFocusable(false);

        recViewStudentAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "info");
            }
        });

        imgBackClassDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}