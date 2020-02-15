package com.geektech.quizapp_gt_4_2.data.history;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.List;


public interface IHistoryStorage {

    int saveQuizResult(QuizResult result);

    void delete(int id);


    void deleteAll();


    QuizResult get(int id);


    LiveData<List<QuizResult>> getAll();

    LiveData<List<History>> getAllHistory();


}
