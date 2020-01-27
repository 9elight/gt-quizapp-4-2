package com.geektech.quizapp_gt_4_2.main;

import androidx.lifecycle.Observer;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.quiz.QuizActivity;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private SeekBar seekBar;
    private TextView question_amount;
    private Button start_btn;
    private Spinner categorySpinner;
    private Spinner difficultlySpinner;
    private String[] defaultCategory = {"All","Sport","History","Music","Technology"};
    private String[] difficult = {"Any","Easy","Medium","Hard"};

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
        setSpinners(defaultCategory,categorySpinner);
        setSpinners(difficult,difficultlySpinner);

    }
    private void initViews(View view){
        seekBar = view.findViewById(R.id.seekBar);
        question_amount = view.findViewById(R.id.amount);
        start_btn = view.findViewById(R.id.start_btn);
        categorySpinner = view.findViewById(R.id.category_spinner);
        difficultlySpinner = view.findViewById(R.id.difficultly_spinner);
    }

    private void setSpinners(String[] list,Spinner spinner){
        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter<String>(getActivity()
                        ,android.R.layout.simple_spinner_item,list);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(categoryAdapter);


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
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    private void setSeekBarAmount(){
        question_amount.setText(String.valueOf(seekBar.getProgress()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                question_amount.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }




}
