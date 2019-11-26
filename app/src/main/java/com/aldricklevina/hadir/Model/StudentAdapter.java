package com.aldricklevina.hadir.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aldricklevina.hadir.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> listStudent;
    private OnItemClickListener clickListener;

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView txtStudentName, txtStudentId;
        private ImageView imgStatus;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStudentName = itemView.findViewById(R.id.txtStudentName);
            txtStudentId = itemView.findViewById(R.id.txtStudentId);
            imgStatus = itemView.findViewById(R.id.imgStatus);

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

    public StudentAdapter(ArrayList<Student> _listStudent) {
        this.listStudent = _listStudent;
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
        String status = currentItem.getStatus();

        holder.txtStudentName.setText(currentItem.getFullName());
        holder.txtStudentId.setText(currentItem.getId());

        if (status.equals("present")) {
            holder.imgStatus.setBackgroundResource(R.drawable.present);
        } else if (status.equals("late")) {
            holder.imgStatus.setBackgroundResource(R.drawable.late);
        } else if (status.equals("absent")){
            holder.imgStatus.setBackgroundResource(R.drawable.absent);
        } else {
            holder.imgStatus.setBackgroundResource(R.drawable.default_status);
        }

    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(StudentAdapter.OnItemClickListener _clickListener) {
        this.clickListener = _clickListener;
    }


    public Student getItem(int position) {
        return listStudent.get(position);
    }

    public int getItemCountBy(String str) {
        int result = 0;

        for (Student student : listStudent) {
            if (student.getStatus().equalsIgnoreCase(str)) result += 1;
        }

        return result;
    }

    public void filterList(ArrayList<Student> filteredList) {
        listStudent = filteredList;
        notifyDataSetChanged();
    }

    public void setStudentStatus(Student _student) {
        for (Student student : listStudent) {
            if (student.getId().equals(_student.getId())) student.setStatus(_student.getStatus());
        }

        notifyDataSetChanged();
    }
}
