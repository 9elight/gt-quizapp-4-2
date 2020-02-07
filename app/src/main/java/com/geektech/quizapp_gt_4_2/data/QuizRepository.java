package com.geektech.quizapp_gt_4_2.data;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient,IHistoryStorage {
    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;

    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = historyStorage;
    }

    @Override
    public void save(QuizResult result) {

    }

    @Override
    public void delete(QuizResult result) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public QuizResult get(int id) {
        return null;
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void getQuestions(int amount, Integer category, String difficulty,final QuestionsCallback callback) {
        quizApiClient.getQuestions(amount, category, difficulty, new QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    result.set(i,shuffleAnswers(result.get(i)));

                }
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void getCategories(CategoriesCallback categoriesCallback) {

    }

    @Override
    public void getGlobal(GlobalCallback globalCallback) {

    }

    @Override
    public void getQuestionCount(Integer category, CountCallback countCallback) {

    }


    private Question shuffleAnswers(Question question){
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());
        Collections.shuffle(answers);
        question.setAnswers(answers);
        return question;
    }
}
