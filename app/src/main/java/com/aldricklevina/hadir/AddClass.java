package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.ClassInfo;
import com.aldricklevina.hadir.Model.ClassInfoAdapter;
import com.aldricklevina.hadir.ui.home.HomeFragment;
import com.aldricklevina.hadir.ui.myclass.MyClassFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class AddClass extends AppCompatActivity {

    private App app;

    private Button btnAddClass;
    private ImageView btnClassDate, btnStartTime, btnEndTime, btnBack_addClass;

    private EditText editTextClassName, editTextClassDate, editTextStartTime, editTextEndTime;
    private String className, classDate, startTime, endTime;

    private int nextClassId;
    private String lastClassId, idOnly;

    private Calendar calendar;
    private Date date, start, end;
    private SimpleDateFormat dateFormat;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private int mYear, mMonth, mDay, mHourStart, mMinuteStart, mHourEnd, mMinuteEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        if (app == null) app = (App) Objects.requireNonNull(getApplication());

        btnAddClass = findViewById(R.id.btnAddClass);
        btnClassDate = findViewById(R.id.btnDate);
        btnStartTime = findViewById(R.id.btnStartTime);
        btnEndTime = findViewById(R.id.btnEndTime);

        editTextClassName = findViewById(R.id.editTextClassName);
        editTextClassDate = findViewById(R.id.editTextClassDate);
        editTextStartTime = findViewById(R.id.editTextStartTime);
        editTextEndTime = findViewById(R.id.editTextEndTime);

        btnBack_addClass = findViewById(R.id.imgBack_addClass);

        date = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String dateFormatString = dateFormat.format(date);
        editTextClassDate.setText(dateFormatString);

        start = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("h.mm a");
        dateFormatString = dateFormat.format(start);
        editTextStartTime.setText(dateFormatString);

        end = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("h.mm a");
        dateFormatString = dateFormat.format(end);
        editTextEndTime.setText(dateFormatString);

        btnBack_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnClassDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AddClass.this, R.style.dateTimePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);

                        editTextClassDate.setText(new SimpleDateFormat("dd MMMM yyyy").format(calendar.getTime()));
                    }
                }, mYear, mMonth, mDay);

                Date today = calendar.getTime();
                datePickerDialog.getDatePicker().setMinDate(today.getTime() - (today.getTime() % (24*60*60*1000)));
                datePickerDialog.show();
            }
        });

        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                calendar = Calendar.getInstance();
                mHourStart = calendar.get(Calendar.HOUR);
                mMinuteStart = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddClass.this, R.style.dateTimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        editTextStartTime.setText(new SimpleDateFormat("h.mm a").format(calendar.getTime()));
                    }
                }, mHourStart, mMinuteStart, false);
                timePickerDialog.show();
                
            }
        });

        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                calendar = Calendar.getInstance();
                mHourEnd = calendar.get(Calendar.HOUR);
                mMinuteEnd = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddClass.this, R.style.dateTimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        editTextEndTime.setText(new SimpleDateFormat("h.mm a").format(calendar.getTime()));
                    }
                }, mHourEnd, mMinuteEnd, false);
                timePickerDialog.show();
            }
        });

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                className = editTextClassName.getText().toString();
                classDate = editTextClassDate.getText().toString();
                startTime = editTextStartTime.getText().toString();
                endTime = editTextEndTime.getText().toString();

                for (ClassInfo classInfo : app.listClassInfo) {
                    if (editTextClassName.getText().toString().equals(classInfo.getClassName())) {
                        editTextClassName.setError("Class already existed");
                    }
                }

                if (editTextStartTime.getText().toString().equals(editTextEndTime.getText().toString())) {
                    editTextStartTime.setError("Start Time is invalid");
                    editTextEndTime.setError("End Time is invalid");
                }

                if (!className.equals("") && !classDate.equals("") && !startTime.equals("") && !endTime.equals("") && !editTextStartTime.getText().toString().equals(editTextEndTime.getText().toString())) {
                    for (ClassInfo classInfo : app.listClassInfo) {
                        lastClassId = classInfo.getId();
                    }

                    idOnly = lastClassId.substring(1, 5);
                    nextClassId = Integer.parseInt(idOnly) + 1;
                    app.listClassInfo.add(new ClassInfo("K" + String.format("%04d", nextClassId), classDate, className, app.acc.getEmail(), startTime, endTime));

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddClass.this);
                    builder.setTitle("Information")
                            .setMessage("Class has been added successfuly")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else {
                    if (className.equals("")) {
                        editTextClassName.setError("Shouldn't be empty");
                    }

                    if (editTextStartTime.getText().toString().equals(editTextEndTime.getText().toString())) {
                        editTextStartTime.setError("Start Time is invalid");
                        editTextEndTime.setError("End Time is invalid");
                    }
                }
            }
        });
    }
}
