package com.geektech.quizapp_gt_4_2.quiz;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    private TextView question_text;
    private ProgressBar progressBar;
    private Button q_btn1;
    private Button q_btn2;
    private Button q_btn3;
    private Button q_btn4;
    private Button skip;
    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews(){
        question_text = itemView.findViewById(R.id.question_text_view);
        q_btn1 = itemView.findViewById(R.id.quiz_btn1);
        q_btn2 = itemView.findViewById(R.id.quiz_btn2);
        q_btn3 = itemView.findViewById(R.id.quiz_btn3);
        q_btn4 = itemView.findViewById(R.id.quiz_btn4);
        skip = itemView.findViewById(R.id.skip_btn);
        progressBar = itemView.findViewById(R.id.quiz_progressBar);
    }

    public void onBind(Question question) {
            question_text.setText(question.getQuestion());

    }
}
