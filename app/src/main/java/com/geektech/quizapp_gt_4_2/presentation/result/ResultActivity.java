package com.geektech.quizapp_gt_4_2.presentation.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.geektech.quizapp_gt_4_2.R;

public class ResultActivity extends AppCompatActivity {

    private static String EXTRA_QUIZ_ID = "result_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public static void start(Context context,Integer quiz_id){
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_QUIZ_ID,quiz_id));
    }
}
