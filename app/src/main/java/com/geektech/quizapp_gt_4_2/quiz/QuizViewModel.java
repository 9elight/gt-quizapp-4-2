package com.geektech.quizapp_gt_4_2.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {
    public MutableLiveData<List<Question>> question = new MutableLiveData<>();
    public MutableLiveData<Integer> rv_position = new MutableLiveData<>();
    private List<Question> mQuestion;
    private Integer count;
        public QuizViewModel() {
        rv_position.setValue(1);
        count = 1;
    }


    public void getQuestions(int amount, Integer category, String difficulty){
        App.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestion = result;
                question.postValue(mQuestion);

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }



    public  void nextPage(){

        rv_position.setValue(++count);
    }
    public void prevPage(){
        rv_position.setValue(--count);
    }

    public void onAnswerClick(int position, int selectedAnswerPosition) {

                nextPage();
                mQuestion.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
                question.setValue(mQuestion);


    }
}
