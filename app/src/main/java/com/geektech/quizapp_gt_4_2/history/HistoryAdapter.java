package com.geektech.quizapp_gt_4_2.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    List<String> list = new ArrayList<>();

    public HistoryAdapter() {

    }
    public void updateHistory(List<String> list){
        this.list = list;
        list.add("asdasd");
        list.add("asdasd");
        list.add("asdasd");
        list.add("asdasd");
        list.add("asdasd");
        list.add("asdasd");
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_history,parent,false);
        HistoryViewHolder viewHolder = new HistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(String s) {

        }
    }
}


