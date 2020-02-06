package com.geektech.quizapp_gt_4_2.main;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.model.GlobalResponse;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizQuestionsCount;
import com.geektech.quizapp_gt_4_2.model.Categories;

import java.util.List;

public class MainViewModel extends ViewModel {
    private IQuizApiClient quizApiClient = App.quizApiClient;
    public MutableLiveData<List<Categories>> categoriesLiveData = new MutableLiveData<>();
    public MutableLiveData<GlobalResponse> globalLiveData = new MutableLiveData<>();
    public MutableLiveData<QuizQuestionsCount> questionCount = new MutableLiveData<>();

    public void getCategories(){
        quizApiClient.getCategories(new IQuizApiClient.CategoriesCallback() {
            @Override
            public void onSuccess(List<Categories> result) {
                categoriesLiveData.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getGlobal(){
       quizApiClient.getGlobal(new IQuizApiClient.GlobalCallback() {
            @Override
            public void onSuccess(GlobalResponse result) {
                globalLiveData.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getQuestionCount(Integer integer){
        quizApiClient.getQuestionCount(integer, new IQuizApiClient.CountCallback() {
            @Override
            public void onSuccess(QuizQuestionsCount result) {
                questionCount.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    public MainViewModel() {

    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
