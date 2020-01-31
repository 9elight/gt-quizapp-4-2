package com.geektech.quizapp_gt_4_2.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {
    public MutableLiveData<List<Question>> question = new MutableLiveData<>();

    public void getQuestions(int amount,Integer category,String difficulty){
        App.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                question.postValue(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
