package com.geektech.quizapp_gt_4_2.presentation.quiz.recycler;

import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.EType;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    private TextView question_text;
    private Listener listener;
    private Question question;
    private int position;
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
        initListeners();


    }

    private void initViews() {
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

    }

    private void initListeners() {
        q_btn1.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(),0);
        });
        q_btn2.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 1);
        });
        q_btn3.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 2);
        });
        q_btn4.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 3);
        });
        boolean_btn1.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        boolean_btn2.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));

    }

    private void setButtonsEnabled(boolean enabled) {
        boolean_btn1.setEnabled(enabled);
        boolean_btn2.setEnabled(enabled);
        q_btn1.setEnabled(enabled);
        q_btn2.setEnabled(enabled);
        q_btn3.setEnabled(enabled);
        q_btn4.setEnabled(enabled);
    }


    public void onBind(Question question, int position) {
        clearHolder();
        if (question.isAnswered()) {
            setButtonsEnabled(false);
        } else {
            setButtonsEnabled(true);
        }
        this.position = position;
        this.question = question;
        App.myTranslator.translate((Html.fromHtml(question.getQuestion())).toString())
                .addOnSuccessListener(s1 -> {
                    question_text.setText(s1);
                });

        if (question.getType() == EType.MULTIPLE) {
            boolean_container.setVisibility(View.INVISIBLE);
            multiply_container.setVisibility(View.VISIBLE);
            App.myTranslator.translate(Html.fromHtml(question.getAnswers().get(0)).toString())
                    .addOnSuccessListener(s -> {
                        q_btn1.setText(s);
                    });
            App.myTranslator.translate(Html.fromHtml(question.getAnswers().get(1)).toString())
                    .addOnSuccessListener(s -> {
                        q_btn2.setText(s);
                    });
            App.myTranslator.translate(Html.fromHtml(question.getAnswers().get(2)).toString())
                    .addOnSuccessListener(s -> {
                        q_btn3.setText(s);
                    });
            App.myTranslator.translate(Html.fromHtml(question.getAnswers().get(3)).toString())
                    .addOnSuccessListener(s -> {
                        q_btn4.setText(s);
                    });
        } else {
            multiply_container.setVisibility(View.INVISIBLE);
            boolean_container.setVisibility(View.VISIBLE);
            App.myTranslator.translate(Html.fromHtml(question.getAnswers().get(0)).toString())
                    .addOnSuccessListener(s -> {
                        boolean_btn1.setText(s);
                    });
            App.myTranslator.translate(Html.fromHtml(question.getAnswers().get(1)).toString())
                    .addOnSuccessListener(s -> {
                        boolean_btn2.setText(s);
                    });
        }
        if (question.isAnswered())
            btn_state(question);

    }

    public void clearHolder() {
        q_btn1.setBackgroundResource(R.drawable.btn_states);
        q_btn2.setBackgroundResource(R.drawable.btn_states);
        q_btn3.setBackgroundResource(R.drawable.btn_states);
        q_btn4.setBackgroundResource(R.drawable.btn_states);
        q_btn1.setTextColor(itemView.getResources().getColor(R.color.btn_color));
        q_btn2.setTextColor(itemView.getResources().getColor(R.color.btn_color));
        q_btn3.setTextColor(itemView.getResources().getColor(R.color.btn_color));
        q_btn4.setTextColor(itemView.getResources().getColor(R.color.btn_color));
        boolean_btn1.setBackgroundResource(R.drawable.btn_states);
        boolean_btn2.setBackgroundResource(R.drawable.btn_states);
        boolean_btn1.setTextColor(itemView.getResources().getColor(R.color.btn_color));
        boolean_btn2.setTextColor(itemView.getResources().getColor(R.color.btn_color));

    }

    private void btn_state(Question question) {
        if (question.getSelectedAnswerPosition() != null) {
            switch (question.getSelectedAnswerPosition()) {
                case 0:
                    if (question.getCorrectAnswer().equals(question.getAnswers().get(0))) {
                        q_btn1.setBackgroundResource(R.drawable.btn_correct_state);
                        boolean_btn1.setBackgroundResource(R.drawable.btn_correct_state);
                        q_btn1.setTextColor(Color.WHITE);
                        boolean_btn1.setTextColor(Color.WHITE);
                        Log.e("tag", "btn1_state: correct");
                    } else {
                        Log.e("tag", "btn2_state: incorrect");
                        q_btn1.setBackgroundResource(R.drawable.btn_wrong_state);
                        boolean_btn1.setBackgroundResource(R.drawable.btn_wrong_state);
                        q_btn1.setTextColor(Color.WHITE);
                        boolean_btn1.setTextColor(Color.WHITE);

                    }
                    break;
                case 1:
                    if (question.getCorrectAnswer().equals(question.getAnswers().get(1))) {
                        q_btn2.setBackgroundResource(R.drawable.btn_correct_state);
                        boolean_btn2.setBackgroundResource(R.drawable.btn_correct_state);
                        q_btn2.setTextColor(Color.WHITE);
                        boolean_btn2.setTextColor(Color.WHITE);

                        Log.e("tag", "btn2_state: correct");
                    } else {
                        q_btn2.setBackgroundResource(R.drawable.btn_wrong_state);
                        boolean_btn2.setBackgroundResource(R.drawable.btn_wrong_state);
                        q_btn2.setTextColor(Color.WHITE);
                        boolean_btn2.setTextColor(Color.WHITE);
                        Log.e("tag", "btn3_state: incorrect");
                    }

                    break;
                case 2:
                    if (question.getCorrectAnswer().equals(question.getAnswers().get(2))) {
                        q_btn3.setBackgroundResource(R.drawable.btn_correct_state);
                        Log.e("tag", "btn3_state: correct");
                        q_btn3.setTextColor(Color.WHITE);

                    } else {
                        q_btn3.setBackgroundResource(R.drawable.btn_wrong_state);
                        Log.e("tag", "btn3_state: incorrect");
                        q_btn3.setTextColor(Color.WHITE);

                    }
                    break;
                case 3:
                    if (question.getCorrectAnswer().equals(question.getAnswers().get(3))) {
                        q_btn4.setBackgroundResource(R.drawable.btn_correct_state);
                        q_btn4.setTextColor(Color.WHITE);

                        Log.e("tag", "btn4_state: correct");
                    } else {
                        q_btn4.setBackgroundResource(R.drawable.btn_wrong_state);
                        q_btn4.setTextColor(Color.WHITE);
                        Log.e("tag", "btn4_state: incorrect");
                    }
                    break;
            }
        }
    }


    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
