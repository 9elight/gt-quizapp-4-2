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
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private TextView categoryTitle;
    private QuizViewModel qViewModel;
    private int q_amount;
    private Integer category;
    private String difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        q_amount = getIntent().getIntExtra("q_amount",25);
        category = getIntent().getIntExtra("category",0);
        difficulty = getIntent().getStringExtra("difficult");
        categoryTitle = findViewById(R.id.categoryTitle);
        qViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        rv_builder();
        if (category == 8){
            category = null;
        }
        qViewModel.getQuestions(q_amount,category,difficulty);
        getQuestions();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.quiz_recyclerView);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void rv_builder(){
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
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

    private void getQuestions(){
        qViewModel.question.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> list) {
                adapter.updateQuestions(list);
            }
        });
    }

    public static void start(Context context,int amount,int category,String difficult){
        context.startActivity(new Intent(context,QuizActivity.class).putExtra("q_amount",amount)
        .putExtra("category",category).putExtra("difficult",difficult));
    }
}
