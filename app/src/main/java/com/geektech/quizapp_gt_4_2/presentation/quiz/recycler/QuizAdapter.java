package com.geektech.quizapp_gt_4_2.presentation.quiz.recycler;

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
    private QuizViewHolder.Listener listener;
    String currentCategory;

    public QuizAdapter(QuizViewHolder.Listener listener){
        this.listener = listener;
    }
    public void updateQuestions(List<Question> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_quiz,parent,false);
        QuizViewHolder viewHolder = new QuizViewHolder(view,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
            holder.onBind(list.get(position));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
