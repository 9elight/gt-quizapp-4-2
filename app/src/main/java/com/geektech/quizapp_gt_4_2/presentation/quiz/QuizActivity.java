package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.presentation.quiz.recycler.QuizAdapter;
import com.geektech.quizapp_gt_4_2.presentation.quiz.recycler.QuizViewHolder;
import com.geektech.quizapp_gt_4_2.presentation.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;


public class QuizActivity extends AppCompatActivity implements QuizViewHolder.Listener {
    //region Static
    private static String EXTRA_AMOUNT = "q_amount";
    private static String EXTRA_CATEGORY = "category";
    private static String EXTRA_DIFFICULTY = "difficult";
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private TextView categoryTitle,tv_question_amount;
    private ImageView back_ic;
    private Button skipButton;
    private QuizViewModel qViewModel;
    private ProgressBar progressBar;
    private LottieAnimationView loading_animation;
    private List<Question> questions = new ArrayList<>();
    private int q_amount;
    private Integer category;
    private String difficulty;
    public static void start(Context context, int amount, int category, String difficult) {
        context.startActivity(new Intent(context, QuizActivity.class).putExtra(EXTRA_AMOUNT, amount)
                .putExtra(EXTRA_CATEGORY, category).putExtra(EXTRA_DIFFICULTY, difficult));
    }
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        qViewModel = ViewModelProviders.of(this) .get(QuizViewModel.class);
        initViews();
        rv_builder();
        getQuestions();

       qViewModel.finishEvent.observe(this, aVoid -> {
           finish();
       });
       qViewModel.openResultEvent.observe(this, new Observer<Integer>() {
           @Override
           public void onChanged(Integer integer) {
               ResultActivity.start(QuizActivity.this,integer);
           }
       });


    }

    private void initViews() {
        recyclerView = findViewById(R.id.quiz_recyclerView);
        tv_question_amount = findViewById(R.id.currentProgress);
        progressBar = findViewById(R.id.quiz_progressBar);
        categoryTitle = findViewById(R.id.categoryTitle);
        loading_animation = findViewById(R.id.loading_animation);
        skipButton = findViewById(R.id.skip_btn);
        back_ic = findViewById(R.id.back_ic);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void rv_builder() {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuizAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);

    }

    private void getQuestions() {
        q_amount = getIntent().getIntExtra(EXTRA_AMOUNT, 25);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        if (category == 8) {
            category = null;
        }
        qViewModel.getQuestions(q_amount, category, difficulty);
        qViewModel.question.observe(this, list -> {
            questions = list;
            Log.e("порядок","1" );
            recyclerView.setVisibility(View.VISIBLE);
            adapter.updateQuestions(list);
            Log.e("tag", "onChanged: " );
            getPosition();
            loading_animation.setVisibility(View.INVISIBLE);
            skipButton.setVisibility(View.VISIBLE);

        });
    }

    private void getPosition(){
        qViewModel.currentQuestionPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.scrollToPosition(integer);
                tv_question_amount.setText(integer + 1  + "/" + q_amount );
                progressBar.setVisibility(View.VISIBLE);
                tv_question_amount.setVisibility(View.VISIBLE);
                progressBar.setProgress(integer + 1);
                progressBar.setMax(q_amount);
                categoryTitle.setVisibility(View.VISIBLE);
                categoryTitle.setText(questions.get(integer).getCategory());
                back_ic.setVisibility(View.VISIBLE);
                Log.e("порядок","2 " + integer );
            }
        });
    }

    public void skipClick(View view) {
        qViewModel.onSkipClick();
    }

    public void backClick(View view) {
        qViewModel.onBackPressed();
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
        qViewModel.onAnswerClick(position,selectedAnswerPosition);
    }

    @Override
    public void onBackPressed() {
        qViewModel.onBackPressed();
    }

}
