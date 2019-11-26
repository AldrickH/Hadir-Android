package com.aldricklevina.hadir;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.Student;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class BS_Student extends BottomSheetDialogFragment {

    private TextView txtStudentName, txtStudentId, txtViewProf;
    private Button btnSubmit;

    private ItemClickListener listener;

    private Student student;

    private App app;

    public BS_Student(Student _student) {
        this.student = _student;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bs_student, container, false);

        if (app == null) app = (App) Objects.requireNonNull(this.getActivity()).getApplication();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtStudentName = view.findViewById(R.id.txtStudentName_bs);
        txtStudentId = view.findViewById(R.id.txtStudentId_bs);
        txtViewProf = view.findViewById(R.id.txtViewProf_bs);

        btnSubmit = view.findViewById(R.id.btnSubmit_bs);

        txtStudentName.setText(student.getFullName());
        txtStudentId.setText(student.getId());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick("BANGSATT");
                dismiss();
            }
        });

        txtViewProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.student = student;
                Intent intent = new Intent(getActivity(), StudentProfile.class);
                startActivity(intent);
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof ItemClickListener) {
            listener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " mus implement ItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }
}
