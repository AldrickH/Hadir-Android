package com.aldricklevina.hadir;

import android.content.Context;
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

public class BS_Student extends BottomSheetDialogFragment {

    private TextView txtStudentName, txtStudentId;
    private TextView txtPresent_bs, txtLate_bs, txtAbsent_bs, txtExcuse_bs;
    private Button btnSubmit;

    private LinearLayout layoutPresent, layoutLate, layoutAbsent, layoutExcuse;

    private ItemClickListener listener;

    int status, present, late, absent, excuse;

    private Student student;

    private App app;

    private ClassDetails classDetails;

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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtStudentName = view.findViewById(R.id.txtStudentName_bs);
        txtStudentId = view.findViewById(R.id.txtStudentId_bs);

        txtPresent_bs = view.findViewById(R.id.txtPresent_bs);
        txtLate_bs = view.findViewById(R.id.txtLate_bs);
        txtAbsent_bs = view.findViewById(R.id.txtAbsent_bs);
        txtExcuse_bs = view.findViewById(R.id.txtExcuse_bs);

        btnSubmit = view.findViewById(R.id.btnSubmit_bs);

        layoutPresent = view.findViewById(R.id.layoutPresent);
        layoutLate = view.findViewById(R.id.layoutLate);
        layoutAbsent = view.findViewById(R.id.layoutAbsent);
        layoutExcuse = view.findViewById(R.id.layoutExcuse);

        txtStudentName.setText(student.getFullName());
        txtStudentId.setText(student.getId());

        txtPresent_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.status = "present";
                layoutPresent.setBackgroundResource(R.drawable.bg_lightblue);
                layoutLate.setBackgroundResource(R.drawable.bg_white);
                layoutAbsent.setBackgroundResource(R.drawable.bg_white);
                layoutExcuse.setBackgroundResource(R.drawable.bg_white);
            }
        });

        txtLate_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.status = "late";
                layoutLate.setBackgroundResource(R.drawable.bg_lightblue);
                layoutPresent.setBackgroundResource(R.drawable.bg_white);
                layoutAbsent.setBackgroundResource(R.drawable.bg_white);
                layoutExcuse.setBackgroundResource(R.drawable.bg_white);
            }
        });

        txtAbsent_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.status = "absent";
                layoutAbsent.setBackgroundResource(R.drawable.bg_lightblue);
                layoutPresent.setBackgroundResource(R.drawable.bg_white);
                layoutLate.setBackgroundResource(R.drawable.bg_white);
                layoutExcuse.setBackgroundResource(R.drawable.bg_white);
            }
        });

        txtExcuse_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.status = "excuse";
                layoutExcuse.setBackgroundResource(R.drawable.bg_lightblue);
                layoutPresent.setBackgroundResource(R.drawable.bg_white);
                layoutLate.setBackgroundResource(R.drawable.bg_white);
                layoutAbsent.setBackgroundResource(R.drawable.bg_white);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(student);
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
            throw new RuntimeException(context.toString() + " must implement ItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface ItemClickListener {
        int onItemClick(Student _student);
    }

    public void getJlhStatus(){
        classDetails.onItemClick(student);
        if (student.status.equals("present")){
            present++;
//            return present;
        }else if(student.status.equals("late")){
            late++;
//            return late;
        }else if(student.status.equals("absent")){
            absent++;
//            return absent;
        }else if(student.status.equals("excuse")){
            excuse++;
//            return excuse;
        }

//        return 0;
    }
}
