package com.geektech.quizapp_gt_4_2.main;

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
import com.geektech.quizapp_gt_4_2.quiz.QuizActivity;
import com.geektech.quizapp_gt_4_2.utils.SimpleSeekBarChangeListener;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private SeekBar seekBar;
    private TextView question_amount;
    private Button start_btn;
    private NiceSpinner categorySpinner;
    private NiceSpinner difficultlySpinner;
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



    }

    private void initViews(View view) {
        seekBar = view.findViewById(R.id.seekBar);
        question_amount = view.findViewById(R.id.amount);
        start_btn = view.findViewById(R.id.start_btn);
        categorySpinner =(NiceSpinner) view.findViewById(R.id.category_spinner);
        difficultlySpinner =(NiceSpinner) view.findViewById(R.id.difficultly_spinner);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity())
                .get(MainViewModel.class);
        setSeekBarAmount();

        start_btn.setOnClickListener(v -> {
            category = categorySpinner.getSelectedIndex() + 8;
            difficultlySpinner.getSelectedIndex();

            QuizActivity.start(getActivity(),q_amount,category, getDiffuculty());
            Log.e("tag", "onClick: " );
        });
    }
    private String getDiffuculty(){
        switch (difficultlySpinner.getSelectedIndex()) {
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
                return diffucult;
    }

    private void setSeekBarAmount() {
        question_amount.setText(String.valueOf(seekBar.getProgress()));
        q_amount = seekBar.getProgress();
        seekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                question_amount.setText(String.valueOf(progress));
                q_amount = progress;
            }
        });
    }


    private void setSpinners(String[] array,NiceSpinner spinner) {
        List<String> list = new LinkedList<>(Arrays.asList(array));
        spinner.attachDataSource(list);
    }

}
