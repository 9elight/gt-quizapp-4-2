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
    private int id = 0;
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
                id++;
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
                getCategory(),
                getDifficulty(),
                mQuestion,
                getCorrectAnswersAmount(),
                new Date()
        );
        int resultId = App.historyStorage.saveQuizResult(result);
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    void onSkipClick() {
        currentQuestionPosition.setValue(++count);
        mQuestion.get(currentQuestionPosition.getValue()).setSelectedAnswerPosition(5);
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

    private String getCategory(){
        String category = "Mixed";
            if (mQuestion.get(0).getCategory().equals(mQuestion.get(1).getCategory())){
                category = mQuestion.get(0).getCategory();
            }
        return category;
    }
    private String getDifficulty(){
        String category = "All";
            if (mQuestion.get(0).getDifficulty().equals(mQuestion.get(1).getDifficulty())){
                category = mQuestion.get(0).getDifficulty().toString();
            }
        return category;
    }

    public void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestion.size() > position && position >= 0) {
            if (mQuestion.get(position).getSelectedAnswerPosition() == null){
            mQuestion.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
            Log.e("ololo", "setAnswer: " + position + selectedAnswerPosition);
            }
            question.setValue(mQuestion);
            if (position + 1 == mQuestion.size()) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(++count);
            }
        }
        Log.e("ololo", "onAnswerClick: " + position + " " + mQuestion.get(position).getSelectedAnswerPosition());

    }
}

