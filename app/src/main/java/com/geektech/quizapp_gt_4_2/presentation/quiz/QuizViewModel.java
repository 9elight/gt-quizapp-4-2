package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.core.SingleLiveEvent;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {
    public MutableLiveData<List<Question>> question = new MutableLiveData<>();
    public MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    private List<Question> mQuestion;
    private Integer count;
    private int id;
    private String resultCategory,resultDifficulty;
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();

    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        count = 0;
    }


    public void getQuestions(int amount, Integer category, String difficulty) {
        App.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestion = result;
                question.postValue(mQuestion);
                if (category != null){
                    resultCategory = mQuestion.get(0).getCategory();
                }else{
                    resultCategory = "Mixed";
                }
                if (difficulty != null){
                   resultDifficulty = mQuestion.get(0).getDifficulty().toString();
                }else{
                    resultDifficulty = "All";
                }
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("ololo", "onFailure: " + e.getLocalizedMessage());
            }
        });

    }

    void finishQuiz() {
        QuizResult result = new QuizResult(
                id,
                resultCategory,
                resultDifficulty,
                mQuestion,
                getCorrectAnswersAmount(),
                new Date()
        );
        int resultId = App.historyStorage.saveQuizResult(result);
        finishEvent.call();
        openResultEvent.setValue(resultId);
        Log.e("tag", "finishQuiz: " );
    }

    void onSkipClick() {
        mQuestion.get(currentQuestionPosition.getValue()).setAnswered(true);
        question.setValue(mQuestion);
        currentQuestionPosition.setValue(++count);
    }

    void onBackPressed() {
        currentQuestionPosition.setValue(--count);
        Log.e("tag", "onBackPressed: " + currentQuestionPosition.getValue()
                + mQuestion.get(currentQuestionPosition.getValue()).getSelectedAnswerPosition());
    }

    public int getCorrectAnswersAmount() {
        int correctAnswersAmount = 0;
        for (int i = 0; i <= mQuestion.size() - 1; i++) {
            String correctAnswer = mQuestion.get(i).getCorrectAnswer();
            String selectedAnswer = mQuestion.get(i).getAnswers()
                     .get(mQuestion.get(i).getSelectedAnswerPosition());
            if (correctAnswer.equals(selectedAnswer)) {
                correctAnswersAmount++;
            }
        }
        return correctAnswersAmount;
    }


    public void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestion.size() > position && position >= 0) {
            mQuestion.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
            mQuestion.get(position).setAnswered(true);
            Log.e("ololo", "setAnswer: " + position + selectedAnswerPosition);
            question.setValue(mQuestion);
            if (position + 1 == mQuestion.size()) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(++count);
            }
        }
    }
}


