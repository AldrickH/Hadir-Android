package com.aldricklevina.hadir.ui.myclass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldricklevina.hadir.AddClass;
import com.aldricklevina.hadir.ClassDetails;
import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.ClassInfo;
import com.aldricklevina.hadir.Model.ClassInfoAdapter;
import com.aldricklevina.hadir.R;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MyClassFragment extends Fragment {

    private ArrayList<ClassInfo> listClass;

    private RecyclerView recViewMyClass;
    private ClassInfoAdapter recViewMyClassAdapter;
    private RecyclerView.LayoutManager recViewMyClassLayoutManager;
    private ItemTouchHelper itemTouchHelper;
    private ItemTouchHelper.SimpleCallback itemTouchCallback;

    private ImageView imgAddClass;

    private TextView txtName_myClass;

    private App app;

    private MyClassViewModel myClassViewModel;

    public MyClassFragment(App _app) {
        this.app = _app;
    }

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listClass = getClassByEmail(app.acc.getEmail());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recViewMyClass = view.findViewById(R.id.recViewMyClass);

        imgAddClass = view.findViewById(R.id.imgAddClass);

        txtName_myClass = view.findViewById(R.id.txtName_myClass);

        txtName_myClass.setText("Hi, " + app.acc.getFullname());

        recViewMyClassLayoutManager = new LinearLayoutManager(getActivity());
        recViewMyClassAdapter = new ClassInfoAdapter(listClass);

        recViewMyClass.setLayoutManager(recViewMyClassLayoutManager);
        recViewMyClass.setAdapter(recViewMyClassAdapter);

        recViewMyClass.setFocusable(false);

        recViewMyClassAdapter.setOnItemClickListener(new ClassInfoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                app.classInfo = recViewMyClassAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), ClassDetails.class);
                startActivity(intent);
            }
        });

        itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                app.listClassInfo.remove(recViewMyClassAdapter.getItem(position));
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        app.listClassInfo.remove(recViewMyClassAdapter.getItem(position));
                        recViewMyClassAdapter.refreshList(getClassByEmail(app.acc.getEmail()));
                        break;
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(getActivity(), c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorRed))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_white_24dp)
                        .create().decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recViewMyClass);

        imgAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddClass.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        recViewMyClassAdapter.refreshList(getClassByEmail(app.acc.getEmail()));
    }

    private ArrayList<ClassInfo> getClassByEmail(String _username) {
        ArrayList<ClassInfo> result = new ArrayList<>();

        for (ClassInfo classInfo : app.listClassInfo) {
            if (classInfo.getLecturer().equals(_username)) {
                result.add(classInfo);
            }
        }

        return result;
    }
}