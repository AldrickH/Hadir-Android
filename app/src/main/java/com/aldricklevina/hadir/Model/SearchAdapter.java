package com.aldricklevina.hadir.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aldricklevina.hadir.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<Search> listSearch;
    private OnItemClickListener clickListener;

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtType;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName_listExplore);
            txtType = itemView.findViewById(R.id.txtType_listExplore);

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

    public SearchAdapter(ArrayList<Search> _listSearch) {
        this.listSearch = _listSearch;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_explore, parent, false);

        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Search currentItem = listSearch.get(position);

        holder.txtName.setText(currentItem.getItemName());
        holder.txtType.setText(currentItem.getItemType());
    }

    @Override
    public int getItemCount() {
        return listSearch.size();
    }

    public Search getItem(int position) {
        return listSearch.get(position);
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener _clickListener) {
        this.clickListener = _clickListener;
    }

    public void filterList(ArrayList<Search> _filteredList) {
        this.listSearch = _filteredList;
        notifyDataSetChanged();
    }
}
