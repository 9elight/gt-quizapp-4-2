package com.geektech.quizapp_gt_4_2.presentation.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.presentation.main.MainActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public static void start(Context context){
        context.startActivity(new Intent(context, ResultActivity.class));
    }
}
