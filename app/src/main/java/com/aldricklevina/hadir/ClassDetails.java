package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.Student;
import com.aldricklevina.hadir.Model.StudentAdapter;

import java.util.ArrayList;

public class ClassDetails extends AppCompatActivity implements BS_Student.ItemClickListener {

    private RecyclerView recViewStudent;
    private RecyclerView.LayoutManager recViewStudentLayoutManager;
    private StudentAdapter recViewStudentAdapter;

    private ImageView imgBack;
    private TextView txtSubject, txtTime;

    private ArrayList<Student> listStudent;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        if (app == null) app = (App) this.getApplication();

        listStudent = getClassStudent(app.classInfo.getId());

        imgBack = findViewById(R.id.imgBack_classDet);

        txtSubject = findViewById(R.id.txtSubject_classDet);
        txtTime = findViewById(R.id.txtTime_classDet);

        recViewStudent = findViewById(R.id.recViewStudentClass);

        txtSubject.setText(app.classInfo.getClassName());

        recViewStudentLayoutManager = new LinearLayoutManager(this);
        recViewStudentAdapter = new StudentAdapter(listStudent);

        recViewStudent.setLayoutManager(recViewStudentLayoutManager);
        recViewStudent.setAdapter(recViewStudentAdapter);

        recViewStudent.setFocusable(false);

        recViewStudentAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                BS_Student bs_student = new BS_Student(recViewStudentAdapter.getItem(position));
                bs_student.show(getSupportFragmentManager().beginTransaction(), bs_student.getTag());
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public ArrayList<Student> getClassStudent(String _classId) {
        ArrayList<Student> result = new ArrayList<>();

        for (Student student : app.listStudent) {
            if (student.classId.equals(_classId)) {
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public void onItemClick(String item) {
        Log.i("berhasil", "onItemClick: berhasil" + item);
    }
}