package com.aldricklevina.hadir.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aldricklevina.hadir.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> listStudent;
    private OnItemClickListener clickListener;

    public StudentAdapter(ArrayList<Student> _listStudent) {
        this.listStudent = _listStudent;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView txtStudentName;
        TextView txtStudentId;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStudentName = itemView.findViewById(R.id.txtStudentName);
            txtStudentId = itemView.findViewById(R.id.txtStudentId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(StudentAdapter.OnItemClickListener _clickListener) {
        this.clickListener = _clickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_student, parent, false);

        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student currentItem = listStudent.get(position);

        holder.txtStudentName.setText(currentItem.getFullName());
        holder.txtStudentId.setText(currentItem.getId());
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public Student getItem(int position) {
        return listStudent.get(position);
    }
}
