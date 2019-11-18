package com.aldricklevina.hadir.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldricklevina.hadir.ClassDetails;
import com.aldricklevina.hadir.Model.ClassInfo;
import com.aldricklevina.hadir.Model.ClassInfoAdapter;
import com.aldricklevina.hadir.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private LinearLayout layoutMon_home, layoutTue_home, layoutWed_home, layoutThu_home, layoutFri_home;

    private ArrayList<ClassInfo> listClassInfo;

    private RecyclerView recViewClassInfo;
    private ClassInfoAdapter recViewClassInfoAdapter;
    private RecyclerView.LayoutManager recViewClassInfoLayoutManager;

    private HomeViewModel homeViewModel;

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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutMon_home = view.findViewById(R.id.layoutMon_home);
        layoutTue_home = view.findViewById(R.id.layoutTue_home);
        layoutWed_home = view.findViewById(R.id.layoutWed_home);
        layoutThu_home = view.findViewById(R.id.layoutThu_home);
        layoutFri_home = view.findViewById(R.id.layoutFri_home);

        recViewClassInfo = view.findViewById(R.id.recViewClassInfo);

        recViewClassInfoLayoutManager = new LinearLayoutManager(getActivity());

        listClassInfo = new ArrayList<>();
        listClassInfo.add(new ClassInfo("Math", "Kalkulus", "12.00 AM"));
        listClassInfo.add(new ClassInfo("Math", "DATA", "11.00 AM"));
        listClassInfo.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClassInfo.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClassInfo.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClassInfo.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClassInfo.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClassInfo.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));

        recViewClassInfoAdapter = new ClassInfoAdapter(listClassInfo);

        recViewClassInfo.setLayoutManager(recViewClassInfoLayoutManager);
        recViewClassInfo.setAdapter(recViewClassInfoAdapter);

        recViewClassInfo.setFocusable(false);

        recViewClassInfoAdapter.setOnItemClickListener(new ClassInfoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                String tipe = listClassInfo.get(position).type;

                if (tipe.equals("Math")) {
                    Intent i = new Intent(getActivity(), ClassDetails.class);
                    startActivity(i);
                }
            }
        });

        layoutMon_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutMon_home.setBackgroundResource(R.drawable.bg_lightblue);
            }
        });

        layoutTue_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutTue_home.setBackgroundResource(R.drawable.bg_lightblue);
            }
        });

        layoutWed_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutWed_home.setBackgroundResource(R.drawable.bg_lightblue);
            }
        });

        layoutThu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutThu_home.setBackgroundResource(R.drawable.bg_lightblue);
            }
        });

        layoutFri_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCalendar();
                layoutFri_home.setBackgroundResource(R.drawable.bg_lightblue);
            }
        });
    }

    private void refreshCalendar() {
        layoutMon_home.setBackgroundResource(R.drawable.bg_white_stroke);
        layoutTue_home.setBackgroundResource(R.drawable.bg_white_stroke);
        layoutWed_home.setBackgroundResource(R.drawable.bg_white_stroke);
        layoutThu_home.setBackgroundResource(R.drawable.bg_white_stroke);
        layoutFri_home.setBackgroundResource(R.drawable.bg_white_stroke);
    }
}