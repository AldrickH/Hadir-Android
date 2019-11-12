package com.aldricklevina.hadir.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aldricklevina.hadir.R;

import java.util.ArrayList;

public class ClassInfoAdapter extends ArrayAdapter<ClassInfo> {

    private TextView txtClassType, txtClassName, txtClassSchedule;

    public ClassInfoAdapter(@NonNull Context context, ArrayList<ClassInfo> classInfo) {
        super(context, 0, classInfo);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ClassInfo classInfo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_class, parent, false);

            txtClassType = convertView.findViewById(R.id.txtClassType);
            txtClassName = convertView.findViewById(R.id.txtClassName);
            txtClassSchedule = convertView.findViewById(R.id.txtClassSchedule);
        }


        txtClassName.setText(classInfo.name);
        txtClassType.setText(classInfo.type);
        txtClassSchedule.setText(classInfo.schedule);

        return convertView;
    }
}
