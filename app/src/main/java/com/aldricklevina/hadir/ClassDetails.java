package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private TextView txtSubject, txtTime, txtDate, txtTotal, txtPresent, txtLate, txtAbsent;
    private LinearLayout layoutTotal, layoutPresent, layoutLate, layoutAbsent;
    private boolean layoutTotalClicked, layoutPresentClicked, layoutLateClicked, layoutAbsentClicked;

    private ArrayList<Student> listStudent, emptyListStudent;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        if (app == null) app = (App) this.getApplication();

        listStudent = getClassStudent(app.classInfo.getId());

        emptyListStudent = new ArrayList<Student>();

        imgBack = findViewById(R.id.imgBack_classDet);

        txtSubject = findViewById(R.id.txtSubject_classDet);
        txtTime = findViewById(R.id.txtTime_classDet);
        txtDate = findViewById(R.id.txtDate_classDet);
        txtTotal = findViewById(R.id.txtTotal_classDet);
        txtPresent = findViewById(R.id.txtPresent_classDet);
        txtLate = findViewById(R.id.txtLate_classDet);
        txtAbsent = findViewById(R.id.txtAbsent_classDet);

        layoutTotal = findViewById(R.id.layoutTotal_cd);
        layoutPresent = findViewById(R.id.layoutPresent_cd);
        layoutAbsent = findViewById(R.id.layoutAbsent_cd);
        layoutLate = findViewById(R.id.layoutLate_cd);

        recViewStudent = findViewById(R.id.recViewStudentClass);

        txtSubject.setText(app.classInfo.getClassName());
        txtTime.setText(app.classInfo.getTimeStart() + " | ");
        txtDate.setText(app.classInfo.getDate());

        recViewStudentLayoutManager = new LinearLayoutManager(this);
        recViewStudentAdapter = new StudentAdapter(new ArrayList<Student>());

        recViewStudent.setLayoutManager(recViewStudentLayoutManager);
        recViewStudent.setAdapter(recViewStudentAdapter);
        recViewStudent.setFocusable(false);

        recViewStudent.setFocusable(false);

        recViewStudentAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                BS_Student bs_student = new BS_Student(recViewStudentAdapter.getItem(position), app);
                bs_student.show(getSupportFragmentManager().beginTransaction(), bs_student.getTag());
                recViewStudentAdapter.notifyDataSetChanged();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLinearLayout();
                layoutTotal.setBackgroundResource(R.drawable.bg_lightblue);

                refreshClick();
                layoutTotalClicked = true;

                recViewStudentAdapter.refreshList(getClassStudent(app.classInfo.getId()));
                recViewStudent.setFocusable(false);

                txtTotal.setText(String.valueOf(listStudent.size()));
            }
        });

        layoutPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLinearLayout();
                layoutPresent.setBackgroundResource(R.drawable.bg_lightblue);

                refreshClick();
                layoutPresentClicked = true;
                filterStudent(txtPresent.getTag().toString());
                recViewStudent.setFocusable(false);

                txtTotal.setText(Integer.toString(listStudent.size()));
            }
        });

        layoutAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLinearLayout();
                layoutAbsent.setBackgroundResource(R.drawable.bg_lightblue);

                refreshClick();
                layoutAbsentClicked = true;
                filterStudent(txtAbsent.getTag().toString());
                recViewStudent.setFocusable(false);

                txtTotal.setText(Integer.toString(listStudent.size()));
            }
        });

        layoutLate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLinearLayout();
                layoutLate.setBackgroundResource(R.drawable.bg_lightblue);

                refreshClick();
                layoutLateClicked = true;
                filterStudent(txtLate.getTag().toString());
                recViewStudent.setFocusable(false);

                txtTotal.setText(Integer.toString(listStudent.size()));
            }
        });

        initializeStatusTotal();
        layoutTotal.performClick();
    }

    public ArrayList<Student> getClassStudent(String _classId) {
        ArrayList<Student> result = new ArrayList<>();

        for (Student student : app.listStudent) {
            if (student.getClassId().equals(_classId)) {
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public void onItemClick(Student _student) {
        recViewStudentAdapter.setStudentStatus(_student);

        for (Student student: app.listStudent) {
            if (student.getId().equals(_student.getId())) student = _student;
        }

        initializeStatusTotal();

        if (layoutTotalClicked) layoutTotal.performClick();
        else if (layoutPresentClicked) layoutPresent.performClick();
        else if (layoutLateClicked) layoutLate.performClick();
        else layoutAbsent.performClick();
    }

    public void initializeStatusTotal() {
        recViewStudentAdapter.getItemCount();
        txtTotal.setText(String.valueOf(recViewStudentAdapter.getItemCount()));
        txtPresent.setText(String.valueOf(getTotalStudentByStatus(txtPresent.getTag().toString())));
        txtLate.setText(String.valueOf(getTotalStudentByStatus(txtLate.getTag().toString())));
        txtAbsent.setText(String.valueOf(getTotalStudentByStatus(txtAbsent.getTag().toString())));
    }

    private void refreshLinearLayout() {
        layoutTotal.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutPresent.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutAbsent.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutLate.setBackgroundResource(R.drawable.bg_blue_stroke);
    }

    public void refreshClick() {
        layoutTotalClicked = layoutPresentClicked = layoutLateClicked = layoutAbsentClicked = false;
    }

    public int getTotalStudentByStatus(String _status) {
        int result = 0;

        for (Student student : listStudent) {
            if (student.getStatus().equalsIgnoreCase(_status)) result += 1;
        }

        return result;
    }

    private void filterStudent(String _status) {
        ArrayList<Student> result = new ArrayList<>();

        for (Student student : app.listStudent) {
            if (student.getStatus().equals(_status)) {
                result.add(student);
            }
        }
        recViewStudentAdapter.refreshList(result);
    }
}