package com.geektech.quizapp_gt_4_2.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.main.MainActivity;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.quiz.recycler.QuizAdapter;
import com.geektech.quizapp_gt_4_2.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;


public class QuizActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private TextView categoryTitle;
    private TextView tv_question_amount;
    private QuizViewModel qViewModel;
    private ProgressBar progressBar;
    private List<Question> questions = new ArrayList<>();
    private int q_amount;
    private Integer category;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        qViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        rv_builder();
        getQuestions();


    }

    private void initViews() {
        recyclerView = findViewById(R.id.quiz_recyclerView);
        tv_question_amount = findViewById(R.id.currentProgress);
        progressBar = findViewById(R.id.quiz_progressBar);
        categoryTitle = findViewById(R.id.categoryTitle);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void rv_builder() {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuizAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

    }
    private void getQuestions() {
        q_amount = getIntent().getIntExtra("q_amount", 25);
        category = getIntent().getIntExtra("category", 0);
        difficulty = getIntent().getStringExtra("difficult");
        if (category == 8) {
            category = null;
        }
        qViewModel.getQuestions(q_amount, category, difficulty);
        qViewModel.question.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> list) {
                questions = list;
                Log.e("порядок","1" );
                adapter.updateQuestions(list);
                Log.e("tag", "onChanged: " );
                getPosition();
            }
        });
    }
    private void getPosition(){
        qViewModel.rv_position.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.scrollToPosition(integer - 1);
                tv_question_amount.setText(integer + "/" + q_amount );
                progressBar.setProgress(integer);
                progressBar.setMax(q_amount);
                if (questions.size() > 0)categoryTitle.setText(questions.get(integer -1).getCategory());
                Log.e("порядок","2" );

            }
        });
    }

    public static void start(Context context, int amount, int category, String difficult) {
        context.startActivity(new Intent(context, QuizActivity.class).putExtra("q_amount", amount)
                .putExtra("category", category).putExtra("difficult", difficult));
    }

    public void skip_click(View view) {
        if (progressBar.getProgress() < q_amount){
            qViewModel.nextPage();
        }else {
            ResultActivity.start(this);
        }


    }

    public void back_click(View view) {
        if (progressBar.getProgress() != 1){
            qViewModel.prevPage();
        }else{
            MainActivity.start(this);
            finish();
        }
    }
}
