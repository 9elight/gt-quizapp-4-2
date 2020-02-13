package com.geektech.quizapp_gt_4_2.presentation.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    List<History> list = new ArrayList<>();

    public HistoryAdapter() {

    }
    public void updateHistory(List<History> list){
        this.list = list;
        notifyDataSetChanged();
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
        private TextView tv_category,tv_correctAnswers,tv_difficulty,tv_date;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            initView();
        }

        private void initView(){
            tv_category = itemView.findViewById(R.id.history_category_value);
            tv_difficulty = itemView.findViewById(R.id.history_difficulty_value);
            tv_correctAnswers = itemView.findViewById(R.id.history_answers_value);
            tv_date = itemView.findViewById(R.id.date);
        }

        public void onBind(History history) {
            tv_category.setText(history.getCategory());
            tv_difficulty.setText(history.getDifficulty());
            tv_correctAnswers.setText( history.getCorrectAnswers()+ "/" +history.getAmount());
            tv_date.setText(history.getCreatedAt().toString());
        }
    }
}


