package com.aldricklevina.hadir.ui.myclass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aldricklevina.hadir.R;

public class MyClassFragment extends Fragment {

    private MyClassViewModel myClassViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myClassViewModel = ViewModelProviders.of(this).get(MyClassViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myclass, container, false);
//        final TextView textView = root.findViewById(R.id.text_myclass);
        myClassViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
}