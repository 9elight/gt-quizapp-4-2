package com.geektech.quizapp_gt_4_2.data.history;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {
    private HistoryDao dao;
    public HistoryStorage(HistoryDao historyDao) {
        dao = historyDao;
    }

    @Override
    public void save(QuizResult result) {

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
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult result) {
        Log.e("ololo", "saveQuizResult: " );
        return (int) dao.insert(result);
    }
}
