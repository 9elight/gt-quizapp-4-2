package com.geektech.quizapp_gt_4_2.main;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<Integer> counter = new MutableLiveData<>();
    private Integer count;
    public MainViewModel() {
        Log.d("ololo", "View model create");
        count = 0;
        message.setValue("Hello Observer");
    }

    public void onLoginClick() {

    }

    public void onPlusBtnClick(){
        count++;
        counter.setValue(count);

    }
    public void onMinusBtnClick(){
        count--;
        counter.setValue(count);

    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
