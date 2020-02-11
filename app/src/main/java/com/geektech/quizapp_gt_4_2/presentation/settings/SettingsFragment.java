package com.geektech.quizapp_gt_4_2.presentation.settings;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.presentation.main.MainViewModel;

public class SettingsFragment extends CoreFragment {

    private SettingsViewModel mViewModel;
    private View view;
    private MainViewModel mainViewModel;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);


        }


    @Override
    protected int getLayoutId() {
        return R.layout.settings_fragment;
    }
}
