package com.geektech.quizapp_gt_4_2.presentation.quiz.recycler;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.EType;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    private TextView question_text;
    private ProgressBar progressBar;
    private Listener listener;

    private Button q_btn1;
    private Button q_btn2;
    private Button q_btn3;
    private Button q_btn4;
    private Button skip;
    private Button boolean_btn1;
    private Button boolean_btn2;

    private ConstraintLayout multiply_container;
    private ConstraintLayout boolean_container;
    public QuizViewHolder(@NonNull View itemView,
    Listener listener) {
        super(itemView);
        initViews();
        this.listener = listener;
    }

    private void initViews(){
        multiply_container = itemView.findViewById(R.id.multiply_container);
        boolean_container = itemView.findViewById(R.id.boolean_container);

        question_text = itemView.findViewById(R.id.question_text_view);
        boolean_btn1 = itemView.findViewById(R.id.boolean_btn1);
        boolean_btn2 = itemView.findViewById(R.id.boolean_btn2);
        q_btn1 = itemView.findViewById(R.id.quiz_btn1);
        q_btn2 = itemView.findViewById(R.id.quiz_btn2);
        q_btn3 = itemView.findViewById(R.id.quiz_btn3);
        q_btn4 = itemView.findViewById(R.id.quiz_btn4);
        skip = itemView.findViewById(R.id.skip_btn);
        progressBar = itemView.findViewById(R.id.quiz_progressBar);
    }

    public void onBind(Question question) {
            question_text.setText(question.getQuestion());
            if (question.getType() == EType.MULTIPLE){
                boolean_container.setVisibility(View.INVISIBLE);
                multiply_container.setVisibility(View.VISIBLE);
                q_btn1.setText(question.getAnswers().get(0));
                q_btn2.setText(question.getAnswers().get(1));
                q_btn3.setText(question.getAnswers().get(2));
                q_btn4.setText(question.getAnswers().get(3));
            }else{
                boolean_btn1.setText(question.getAnswers().get(0));
                boolean_btn2.setText(question.getAnswers().get(1));
            }

    }


    public interface Listener{
        void onAnswerClick(int position,int selectedAnswerPosition);
    }
}
