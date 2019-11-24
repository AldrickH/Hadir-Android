package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.ClassInfo;
import com.aldricklevina.hadir.Model.ClassInfoAdapter;
import com.aldricklevina.hadir.Model.Search;
import com.aldricklevina.hadir.Model.SearchAdapter;
import com.aldricklevina.hadir.Model.Student;
import com.aldricklevina.hadir.Model.StudentAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Explore extends AppCompatActivity {

    private ImageView imgBack;
    private TextView txtClass;
    private EditText editTextQuery;

    private RecyclerView recViewSearch;
    private SearchAdapter recViewSearchAdapter;
    private RecyclerView.LayoutManager recViewSearchLayoutManager;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        if (app == null) app = (App) this.getApplication();

        imgBack = findViewById(R.id.imgBack_explore);

        txtClass = findViewById(R.id.txtClass_explore);

        editTextQuery = findViewById(R.id.editTextQuery_explore);

        recViewSearch = findViewById(R.id.recViewSearch_explore);

        txtClass.setVisibility(View.GONE);

        recViewSearch.setVisibility(View.GONE);

        recViewSearchLayoutManager = new LinearLayoutManager(Explore.this);
        recViewSearchAdapter = new SearchAdapter(new ArrayList<Search>());

        recViewSearch.setLayoutManager(recViewSearchLayoutManager);
        recViewSearch.setAdapter(recViewSearchAdapter);

        recViewSearchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent;
                Search search = recViewSearchAdapter.getItem(position);

                if (search.getItemType().equals("Class")) {
                    app.classInfo = getClassById(search.getItemId());
                    intent = new Intent(Explore.this, ClassDetails.class);
                } else {
                    app.student = getStudentById(search.getItemId());
                    intent = new Intent(Explore.this, StudentProfile.class);
                }

                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editTextQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterSearch(s.toString());

                if (s.toString().length() > 0) {
                    String str = recViewSearchAdapter.getItemCount() + " item found.";

                    recViewSearch.setVisibility(View.VISIBLE);
                    txtClass.setText(str);
                    txtClass.setVisibility(View.VISIBLE);
                } else {
                    recViewSearch.setVisibility(View.VISIBLE);
                    txtClass.setVisibility(View.GONE);
                }
            }
        });
    }

    private void filterSearch(String _query) {
        ArrayList<Search> result = new ArrayList<>();

        if (_query.length() > 0) {
            for (ClassInfo classInfo : app.listClassInfo) {
                if (classInfo.getClassName().toLowerCase().contains(_query.toLowerCase())) {
                    result.add(new Search(classInfo.getId(), classInfo.getClassName(), "Class"));
                }
            }

            for (Student student : app.listStudent) {
                if (student.getFullName().toLowerCase().contains(_query.toLowerCase())) {
                    result.add(new Search(student.getId(), student.getFullName(), "Student"));
                }
            }

            // Sort array list
            Collections.sort(result, new Comparator<Search>() {
                @Override
                public int compare(Search o1, Search o2) {
                    return o1.getItemName().compareTo(o2.getItemName());
                }
            });

        }

        recViewSearchAdapter.filterList(result);
    }

    private ClassInfo getClassById(String _id) {
        for (ClassInfo classInfo : app.listClassInfo) {
            if (classInfo.getId().equals(_id)) return classInfo;
        }

        return null;
    }

    private Student getStudentById(String _id) {
        for (Student student : app.listStudent) {
            if (student.getId().equals(_id)) return student;
        }

        return null;
    }
}

