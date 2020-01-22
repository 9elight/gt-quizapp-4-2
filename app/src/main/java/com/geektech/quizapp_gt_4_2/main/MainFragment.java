package com.geektech.quizapp_gt_4_2.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.geektech.quizapp_gt_4_2.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private Button plus_btn;
    private Button minus_btn;
    private SeekBar seekBar;
    private TextView question_amount;

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
        plus_btn = view.findViewById(R.id.plus_btn);
        minus_btn = view.findViewById(R.id.minus_btn);
        seekBar = view.findViewById(R.id.seekBar);
        question_amount = view.findViewById(R.id.amount);
        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.onPlusBtnClick();

            }
        });
        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.onMinusBtnClick();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity())
                .get(MainViewModel.class);

        mViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("ololo", s);
            }
        });
        mViewModel.counter.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("ololo", "onChanged: " + integer);
            }
        });

        mViewModel.onLoginClick();
        setSeekBarAmount();
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
