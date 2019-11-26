package com.aldricklevina.hadir;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.Model.Student;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class BS_Student extends BottomSheetDialogFragment implements View.OnClickListener {

    private TextView txtStudentName, txtStudentId, txtViewProf;
    private Button btnSubmit;
    private LinearLayout layoutPresent, layoutLate, layoutAbsent;

    private Student student;
    private String status;

    private ItemClickListener listener;

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

        layoutPresent = view.findViewById(R.id.layoutPresent_bs);
        layoutLate = view.findViewById(R.id.layoutLate_bs);
        layoutAbsent = view.findViewById(R.id.layoutAbsent_bs);

        txtViewProf.setOnClickListener(this);
        layoutPresent.setOnClickListener(this);
        layoutLate.setOnClickListener(this);
        layoutAbsent.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        txtStudentName.setText(student.getFullName());
        txtStudentId.setText(student.getId());
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

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.txtViewProf_bs) {
            app.student = student;
            Intent intent = new Intent(getActivity(), StudentProfile.class);
            startActivity(intent);
            dismiss();
        } else if (id == R.id.layoutPresent_bs) {
            refreshChosenLayout();

            status = "present";
            layoutPresent.setBackgroundResource(R.drawable.bg_lightblue);
        } else if (id == R.id.layoutLate_bs) {
            refreshChosenLayout();

            status = "late";
            layoutLate.setBackgroundResource(R.drawable.bg_lightblue);
        } else if (id == R.id.layoutAbsent_bs) {
            refreshChosenLayout();

            status = "absent";
            layoutAbsent.setBackgroundResource(R.drawable.bg_lightblue);
        } else if (id == R.id.btnSubmit_bs) {
            if (!status.equals("")) {
                student.setStatus(status);
                listener.onItemClick(student);
                dismiss();
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(Student student);
    }

    private void refreshChosenLayout() {
        layoutPresent.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutLate.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutAbsent.setBackgroundResource(R.drawable.bg_blue_stroke);
        layoutPresent.setBackgroundResource(R.drawable.bg_blue_stroke);
    }
}
