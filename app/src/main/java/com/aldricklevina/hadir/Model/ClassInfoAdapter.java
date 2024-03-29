package com.aldricklevina.hadir.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aldricklevina.hadir.R;

import java.util.ArrayList;

public class ClassInfoAdapter extends RecyclerView.Adapter<ClassInfoAdapter.ClassInfoViewHolder> {

    private ArrayList<ClassInfo> listClassInfo;
    private OnItemClickListener clickListener;

    public class ClassInfoViewHolder extends RecyclerView.ViewHolder {

        private TextView txtClassType, txtClassName, txtClassSchedule;

        public ClassInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtClassType = itemView.findViewById(R.id.txtClassType);
            txtClassName = itemView.findViewById(R.id.txtClassName);
            txtClassSchedule = itemView.findViewById(R.id.txtClassSchedule);

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

    public ClassInfoAdapter(ArrayList<ClassInfo> _listClassInfo) {
        this.listClassInfo = _listClassInfo;
    }

    @NonNull
    @Override
    public ClassInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_class, parent, false);

        return new ClassInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassInfoViewHolder holder, int position) {
        ClassInfo currentItem = listClassInfo.get(position);

        holder.txtClassType.setText(currentItem.getDate());
        holder.txtClassName.setText(currentItem.getClassName());
        holder.txtClassSchedule.setText(currentItem.getTimeStart() + " - " + currentItem.getTimeEnd());
    }

    @Override
    public int getItemCount() {
        return listClassInfo.size();
    }

    public ClassInfo getItem(int position) {
        return listClassInfo.get(position);
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener _clickListener) {
        this.clickListener = _clickListener;
    }

    public void refreshList(ArrayList<ClassInfo> _list) {
        this.listClassInfo = _list;
        notifyDataSetChanged();
    }

    public void deleteItem(String _id) {
        for (ClassInfo classInfo : listClassInfo) {
            if (classInfo.getId().equals(_id)) listClassInfo.remove(classInfo);
        }
        notifyDataSetChanged();
    }
}