package com.geektech.quizapp_gt_4_2.data.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {
    private HistoryDao dao;
    public HistoryStorage(HistoryDao historyDao) {
        dao = historyDao;
    }


    @Override
    public void delete(QuizResult result) {
        dao.delete(result);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public QuizResult get(int id) {
        return dao.get(id);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return Transformations.map(getAll(),quizResult -> {
            ArrayList<History> histories = new ArrayList<>();
            if (quizResult.size() > 0) {
                for (int i = 0; i < quizResult.size(); i++) {
                    histories.add(i,new History(quizResult.get(i).getId(),
                            quizResult.get(i).getCategory(),
                            quizResult.get(i).getDifficulty(),
                            quizResult.get(i).getCorrectAnswerAmount(),
                            quizResult.get(i).getQuestions().size(),
                            quizResult.get(i).getCreatedAt()));
                }
            }
            return histories;
        });
    }

    @Override
    public int saveQuizResult(QuizResult result) {
        return (int) dao.insert(result);
    }
}
