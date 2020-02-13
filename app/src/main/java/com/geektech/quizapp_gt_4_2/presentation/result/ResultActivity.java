package com.geektech.quizapp_gt_4_2.presentation.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.R;

public class ResultActivity extends AppCompatActivity {

    private static String EXTRA_QUIZ_ID = "result_id";
    private Integer id;
    private ResultViewModel rViewModel;
    private TextView difficultyValue;
    private TextView correctAnswerAmount;
    private TextView resultPercent;
    private Button finishBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        id = getIntent().getIntExtra(EXTRA_QUIZ_ID,0);
        initView();
        rViewModel.getResult(id);
        setViewContent();

        finishBtn.setOnClickListener(v -> finish());

    }
    private void initView(){
        rViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        difficultyValue = findViewById(R.id.result_difficulty_value);
        correctAnswerAmount = findViewById(R.id.correct_answers_amount);
        resultPercent = findViewById(R.id.result_percent_value);
        finishBtn = findViewById(R.id.finish_btn);
    }

    private void setViewContent(){
       rViewModel.quizResult.observe(this, quizResult -> {
           difficultyValue.setText(quizResult.getDifficulty());
           correctAnswerAmount.setText(quizResult.getCorrectAnswerAmount() + "/" + quizResult.getQuestions().size());
           int correctAnswersPercent = (int)((double)quizResult.getCorrectAnswerAmount()/quizResult.getQuestions().size() * 100);
           resultPercent.setText(correctAnswersPercent + " %");
       });
    }


    public static void start(Context context,Integer result_id){
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_QUIZ_ID,result_id));
    }
}
