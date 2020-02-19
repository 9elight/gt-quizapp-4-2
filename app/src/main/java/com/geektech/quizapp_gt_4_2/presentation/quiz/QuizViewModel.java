package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.os.CountDownTimer;
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
    private CountDownTimer countDownTimer;
    private List<Question> mQuestion;
    private Integer count;
    private int id;
    private String resultCategory, resultDifficulty;
    public MutableLiveData<List<Question>> question = new MutableLiveData<>();
    public MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    public MutableLiveData<Integer> timerState = new MutableLiveData<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Boolean> onFailure = new SingleLiveEvent<>();


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
                if (category != null && result.size() > 0) {
                    resultCategory = mQuestion.get(0).getCategory();
                } else {
                    resultCategory = "Mixed";
                }
                if (difficulty != null && result.size() > 0) {
                    resultDifficulty = mQuestion.get(0).getDifficulty().toString();
                } else {
                    resultDifficulty = "All";
                }
            }

            @Override
            public void onFailure(Exception e) {
                onFailure.setValue(true);
                Log.e("ololo", "onFailure: " + e.getLocalizedMessage());
            }
        });

    }

    void startTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerState.setValue((int) (millisUntilFinished / 1000));
                Log.e("tag", "onTick: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                onSkipClick();
            }
        }.start();

    }

    public int getCorrectAnswersAmount() {
        int correctAnswersAmount = 0;
        for (int i = 0; i <= mQuestion.size() - 1; i++) {
            if (mQuestion.get(i).getSelectedAnswerPosition() != null) {
                String correctAnswer = mQuestion.get(i).getCorrectAnswer();
                String selectedAnswer = mQuestion.get(i).getAnswers()
                        .get(mQuestion.get(i).getSelectedAnswerPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    correctAnswersAmount++;
                }
            }
        }
        return correctAnswersAmount;
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
        Log.e("tag", "finishQuiz: ");
    }

    void onSkipClick() {
        if (mQuestion.size() >= currentQuestionPosition.getValue()) {
            mQuestion.get(currentQuestionPosition.getValue()).setAnswered(true);
            question.setValue(mQuestion);
            currentQuestionPosition.setValue(++count);
            if (currentQuestionPosition.getValue() + 1 == mQuestion.size()) {
                finishQuiz();
            }
        }
    }

    void onBackPressed() {
        if (currentQuestionPosition.getValue() != 0) {
            currentQuestionPosition.setValue(--count);
        } else {
            finishEvent.call();
        }
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


