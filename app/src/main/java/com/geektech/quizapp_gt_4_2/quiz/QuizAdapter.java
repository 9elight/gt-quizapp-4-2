package com.geektech.quizapp_gt_4_2.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private List<Question> list = new ArrayList<>();
    int pos;

    public QuizAdapter(){

    }
    public void updateQuestions(List<Question> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quiz_view_holder,parent,false);
        QuizViewHolder viewHolder = new QuizViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
            holder.onBind(list.get(position));
            pos = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
