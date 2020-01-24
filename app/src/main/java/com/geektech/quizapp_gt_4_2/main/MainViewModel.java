package com.geektech.quizapp_gt_4_2.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> message = new MutableLiveData<>();
    public MainViewModel() {

    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
