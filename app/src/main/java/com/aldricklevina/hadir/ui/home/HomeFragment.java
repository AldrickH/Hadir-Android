package com.aldricklevina.hadir.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldricklevina.hadir.ClassDetails;
import com.aldricklevina.hadir.Explore;
import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.ClassInfo;
import com.aldricklevina.hadir.Model.ClassInfoAdapter;
import com.aldricklevina.hadir.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private LinearLayout layoutSun, layoutMon, layoutTue, layoutWed, layoutThu, layoutFri, layoutSat;
    private ImageView imgSearch, imgBackSchedule, imgNextSchedule;
    private TextView txtSunDate, txtMonDate, txtTueDate, txtWedDate, txtThuDate, txtFriDate, txtSatDate;
    private TextView txtName, txtEmail, txtSchedule;

    private SimpleDateFormat dateFormat;

    private boolean layoutSunClicked, layoutMonClicked, layoutTueClicked, layoutWedClicked, layoutThuClicked, layoutFriClicked, layoutSatClicked;
    private String todayDay;
    private ArrayList<ClassInfo> listClassInfo;

    private Calendar calendar;

    private RecyclerView recViewClassInfo;
    private ClassInfoAdapter recViewClassInfoAdapter;
    private RecyclerView.LayoutManager recViewClassInfoLayoutManager;

    private App app;

    private HomeViewModel homeViewModel;

    public HomeFragment(App _app) {
        this.app = _app;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listClassInfo = new ArrayList<>();

        dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        calendar = Calendar.getInstance();

        todayDay = dateFormat.format(calendar.getTime());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtName = view.findViewById(R.id.txtName_home);
        txtEmail = view.findViewById(R.id.txtEmail_home);
        txtSchedule = view.findViewById(R.id.txtSchedule_home);
        txtSunDate = view.findViewById(R.id.txtSunDate_home);
        txtMonDate = view.findViewById(R.id.txtMonDate_home);
        txtTueDate = view.findViewById(R.id.txtTueDate_home);
        txtWedDate = view.findViewById(R.id.txtWedDate_home);
        txtThuDate = view.findViewById(R.id.txtThuDate_home);
        txtFriDate = view.findViewById(R.id.txtFriDate_home);
        txtSatDate = view.findViewById(R.id.txtSatDate_home);

        imgSearch = view.findViewById(R.id.imgSearch_home);
        imgBackSchedule = view.findViewById(R.id.imgBackSchedule_home);
        imgNextSchedule = view.findViewById(R.id.imgNextSchedule_home);

        layoutSun = view.findViewById(R.id.layoutSun_home);
        layoutMon = view.findViewById(R.id.layoutMon_home);
        layoutTue = view.findViewById(R.id.layoutTue_home);
        layoutWed = view.findViewById(R.id.layoutWed_home);
        layoutThu = view.findViewById(R.id.layoutThu_home);
        layoutFri = view.findViewById(R.id.layoutFri_home);
        layoutSat = view.findViewById(R.id.layoutSat_home);

        recViewClassInfo = view.findViewById(R.id.recViewClassInfo);

        txtName.setText(app.acc.getFullname());
        txtEmail.setText(app.acc.getEmail());

        initializeCalendar();

        recViewClassInfoLayoutManager = new LinearLayoutManager(getActivity());
        recViewClassInfoAdapter = new ClassInfoAdapter(listClassInfo);

        recViewClassInfo.setLayoutManager(recViewClassInfoLayoutManager);
        recViewClassInfo.setAdapter(recViewClassInfoAdapter);

        recViewClassInfoAdapter.setOnItemClickListener(new ClassInfoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                app.classInfo = recViewClassInfoAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), ClassDetails.class);
                startActivity(intent);
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Explore.class);
                startActivity(intent);
            }
        });

        imgBackSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE, -7);

                initializeCalendar();
                lastClicked();
            }
        });

        imgNextSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE, 7);

                initializeCalendar();
                lastClicked();
            }
        });

        layoutSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutSun.setBackgroundResource(R.drawable.bg_lightblue);
                layoutSunClicked = true;

                filterClassInfo(layoutSun.getTag().toString());

                recViewClassInfo.setFocusable(false);
            }
        });

        layoutMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutMon.setBackgroundResource(R.drawable.bg_lightblue);
                layoutMonClicked = true;

                filterClassInfo(layoutMon.getTag().toString());

                recViewClassInfo.setFocusable(false);
            }
        });

        layoutTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutTue.setBackgroundResource(R.drawable.bg_lightblue);
                layoutTueClicked = true;

                filterClassInfo(layoutTue.getTag().toString());

                recViewClassInfo.setFocusable(false);
            }
        });

        layoutWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutWed.setBackgroundResource(R.drawable.bg_lightblue);
                layoutWedClicked = true;

                filterClassInfo(layoutWed.getTag().toString());

                recViewClassInfo.setFocusable(false);
            }
        });

        layoutThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutThu.setBackgroundResource(R.drawable.bg_lightblue);
                layoutThuClicked = true;

                filterClassInfo(layoutThu.getTag().toString());

                recViewClassInfo.setFocusable(false);
            }
        });

        layoutFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutFri.setBackgroundResource(R.drawable.bg_lightblue);
                layoutFriClicked = true;

                filterClassInfo(layoutFri.getTag().toString());

                recViewClassInfo.setFocusable(false);
            }
        });

        layoutSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutSat.setBackgroundResource(R.drawable.bg_lightblue);
                layoutSatClicked = true;

                filterClassInfo(layoutSat.getTag().toString());

                recViewClassInfo.setFocusable(false);
            }
        });

        clickToday(todayDay);
    }

    private void refreshCalendar() {
        layoutSun.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutMon.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutTue.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutWed.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutThu.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutFri.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutSat.setBackgroundResource(R.drawable.bg_blue_stroke);

        layoutSunClicked = layoutMonClicked = layoutTueClicked = layoutWedClicked = layoutThuClicked = layoutFriClicked = layoutSatClicked = false;
    }

    private void filterClassInfo(String _date) {
        ArrayList<ClassInfo> result = new ArrayList<>();

        for (ClassInfo classInfo : app.listClassInfo) {
            if (classInfo.getDate().equals(_date)) {
                result.add(classInfo);
            }
        }

        recViewClassInfoAdapter.refreshList(result);
    }

    private void lastClicked() {
        if (layoutSunClicked) layoutSun.performClick();
        else if (layoutMonClicked) layoutMon.performClick();
        else if (layoutTueClicked) layoutTue.performClick();
        else if (layoutWedClicked) layoutWed.performClick();
        else if (layoutThuClicked) layoutThu.performClick();
        else if (layoutFriClicked) layoutFri.performClick();
        else layoutSat.performClick();
    }

    private void clickToday(String _todayDay) {
        if (_todayDay.equals(layoutSun.getTag().toString())) {
            layoutSun.performClick();
            layoutSunClicked = true;
        } else if (_todayDay.equals(layoutMon.getTag().toString())) {
            layoutMon.performClick();
            layoutMonClicked = true;
        } else if (_todayDay.equals(layoutTue.getTag().toString())) {
            layoutTue.performClick();
            layoutTueClicked = true;
        } else if (_todayDay.equals(layoutWed.getTag().toString())) {
            layoutWed.performClick();
            layoutWedClicked = true;
        } else if (_todayDay.equals(layoutThu.getTag().toString())) {
            layoutThu.performClick();
            layoutThuClicked = true;
        } else if (_todayDay.equals(layoutFri.getTag().toString())) {
            layoutFri.performClick();
            layoutFriClicked = true;
        } else {
            layoutSat.performClick();
            layoutSatClicked = true;
        }
    }

    private void initializeCalendar() {
        String weekSchedule = "Week " + calendar.get(Calendar.WEEK_OF_MONTH) + ", " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + calendar.get(Calendar.YEAR);
        txtSchedule.setText(weekSchedule);

        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        for (int i = 0; i < 7; i++) {
            String date = String.valueOf(calendar.get(Calendar.DATE));
            String dateLong = dateFormat.format(calendar.getTime());

            if (i == 0) {
                layoutSun.setTag(dateLong);
                txtSunDate.setText(date);
            } else if (i == 1) {
                layoutMon.setTag(dateLong);
                txtMonDate.setText(date);
            } else if (i == 2) {
                layoutTue.setTag(dateLong);
                txtTueDate.setText(date);
            } else if (i == 3) {
                layoutWed.setTag(dateLong);
                txtWedDate.setText(date);
            } else if (i == 4) {
                layoutThu.setTag(dateLong);
                txtThuDate.setText(date);
            } else if (i == 5) {
                layoutFri.setTag(dateLong);
                txtFriDate.setText(date);
            } else {
                layoutSat.setTag(dateLong);
                txtSatDate.setText(date);
            }

            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        calendar.add(Calendar.DATE, -7);
    }
}