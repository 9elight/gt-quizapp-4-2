package com.geektech.quizapp_gt_4_2.main;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Categories;
import com.geektech.quizapp_gt_4_2.model.EDifficulty;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.quiz.QuizActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private SeekBar seekBar;
    private TextView question_amount;
    private Button start_btn;
    private Spinner categorySpinner;
    private Spinner difficultlySpinner;
    private int q_amount;
    private Integer category;
    private String diffucult;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setSpinners(getResources().getStringArray(R.array.categories_list),categorySpinner);
        setSpinners(getResources().getStringArray(R.array.difficult_list),difficultlySpinner);
        spinersListener();


    }

    private void initViews(View view) {
        seekBar = view.findViewById(R.id.seekBar);
        question_amount = view.findViewById(R.id.amount);
        start_btn = view.findViewById(R.id.start_btn);
        categorySpinner = view.findViewById(R.id.category_spinner);
        difficultlySpinner = view.findViewById(R.id.difficultly_spinner);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity())
                .get(MainViewModel.class);
        setSeekBarAmount();

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuizActivity.start(getActivity(),q_amount,category,diffucult);
                Log.e("tag", "onClick: " );
            }
        });
    }

    private void setSeekBarAmount() {
        question_amount.setText(String.valueOf(seekBar.getProgress()));
        q_amount = seekBar.getProgress();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                question_amount.setText(String.valueOf(progress));
                q_amount = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void setSpinners(String[] list,Spinner spinner) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity()
                        , android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private void spinersListener() {
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    category = position + 8;



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        difficultlySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        diffucult = null;
                        break;
                    case 2:
                        diffucult = "easy" ;
                        break;
                    case 3:
                        diffucult = "medium";
                        break;
                        case 4:
                        diffucult = "hard";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
