package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ClassDetails extends AppCompatActivity {

    private ArrayList<String> listStudent;
    private ListView listViewStudent;
    private TextView txtSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        listViewStudent = findViewById(R.id.listViewStudentClass);
        txtSchedule = findViewById(R.id.txtSchedule);
        listStudent = new ArrayList<String>();

        listStudent.add(0, "Aldrick Handinata");
        listStudent.add(1, "Levina Khomulia");
        listStudent.add(2, "Secretary Han");

        ListAdapter listAdapter = new ArrayAdapter<String>(ClassDetails.this, android.R.layout.simple_list_item_multiple_choice, listStudent) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Set the border of View (ListView Item)
                view.setBackground(getContext().getDrawable(R.drawable.rounded_corner));

                // panggil layout list_mhs
//                listView.Layout
//                listView.setLayoutMode();
//                LayoutInflater inflater = (LayoutInflater) .getLayoutInflater();
//                View v = inflater.inflate(R.layout.list_mhs, null);
//
//                TextView namaMhs;
//                namaMhs = v.findViewById(R.id.txtNamaMhs);
//                namaMhs.setText(listView[position]);

                // Return the view
                return view;
            }
        };

        listViewStudent.setAdapter(listAdapter);

//        listStudent.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayMurid));
        listViewStudent.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        txtSchedule.setText(currentDate);

//        listStudent.setBackgroundResource(R.drawable.rounded_corner);

    }
}
