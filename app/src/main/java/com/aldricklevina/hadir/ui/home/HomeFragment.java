package com.aldricklevina.hadir.ui.home;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aldricklevina.hadir.Model.ClassInfo;
import com.aldricklevina.hadir.Model.ClassInfoAdapter;
import com.aldricklevina.hadir.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<ClassInfo> listClass;
    private ListView listViewClass;
    private ArrayAdapter listAdapter;

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

        listViewClass = view.findViewById(R.id.listViewClass);

        listClass = new ArrayList<>();

        listClass.add(new ClassInfo("Math", "Kalkulus", "12.00 AM"));
        listClass.add(new ClassInfo("Math", "DATA", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));
        listClass.add(new ClassInfo("Math", "Nanti Baru isi", "11.00 AM"));

        listAdapter = new ClassInfoAdapter(getActivity(), listClass);

        listViewClass.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(listViewClass);
        listViewClass.setFocusable(false);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);

            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));
            }

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}