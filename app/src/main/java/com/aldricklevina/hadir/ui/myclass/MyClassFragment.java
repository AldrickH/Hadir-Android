package com.aldricklevina.hadir.ui.myclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class MyClassFragment extends Fragment {

    private ArrayList<ClassInfo> listClass;
    private RecyclerView recViewMyClass;
    private ClassInfoAdapter recViewMyClassAdapter;
    private RecyclerView.LayoutManager recViewMyClassLayoutManager;

    private MyClassViewModel myClassViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myClassViewModel = ViewModelProviders.of(this).get(MyClassViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myclass, container, false);
        myClassViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recViewMyClass = view.findViewById(R.id.recViewMyClass);
        recViewMyClassLayoutManager = new LinearLayoutManager(getActivity());

        listClass = new ArrayList<>();
        listClass.add(new ClassInfo("Math", "Kalkulus", "12.00 AM"));
        listClass.add(new ClassInfo("Math", "DATA", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));

        recViewMyClassAdapter = new ClassInfoAdapter(listClass);

        recViewMyClass.setLayoutManager(recViewMyClassLayoutManager);
        recViewMyClass.setAdapter(recViewMyClassAdapter);

        recViewMyClass.setFocusable(false);

        recViewMyClassAdapter.setOnItemClickListener(new ClassInfoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                String tipe = listClass.get(position).type;

                if (tipe.equals("Math")) {
                    Intent i = new Intent(getActivity(), ClassDetails.class);
                    startActivity(i);
                }
            }
        });
    }
}