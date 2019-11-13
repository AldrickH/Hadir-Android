package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aldricklevina.hadir.Model.Student;
import com.aldricklevina.hadir.Model.StudentAdapter;

import java.util.ArrayList;

public class ClassDetails extends AppCompatActivity {

    private RecyclerView recViewStudent;
    private RecyclerView.LayoutManager recViewStudentLayoutManager;
    private StudentAdapter recViewStudentAdapter;

    private ArrayList<Student> listStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        recViewStudent = findViewById(R.id.recViewStudentClass);
        recViewStudentLayoutManager = new LinearLayoutManager(this);

        listStudent = new ArrayList<>();

        listStudent.add(new Student("Aldrick Handinata"));
        listStudent.add(new Student("Aldrick Handinata"));
        listStudent.add(new Student("Aldrick Handinata"));

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
    }
}
