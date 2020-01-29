package com.geektech.quizapp_gt_4_2.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Intent intent;
    private QuizAdapter adapter;
    private TextView categoryTitle;
    private int q_amount;
    private int category;
    private String difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        q_amount = intent.getIntExtra("q_amount",25);
        category = intent.getIntExtra("category",1);
        difficulty = intent.getStringExtra("difficult");
        categoryTitle = findViewById(R.id.categoryTitle);
        initViews();
        rv_builder();
        getQuestions();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.quiz_recyclerView);
    }

    private void rv_builder(){
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuizAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getQuestions(){
        App.quizApiClient.getQuestions(q_amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                Log.e("tag", "onSuccess: " );
                adapter.updateQuestions(questions);
                categoryTitle.setText(questions.get(adapter.pos).getCategory());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public static void start(Context context,int amount,int category,String difficult){
        context.startActivity(new Intent(context,QuizActivity.class).putExtra("amount",amount)
        .putExtra("category",category).putExtra("difficult",difficult));
    }
}
