package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.core.ICoreCallback;
import com.geektech.quizapp_gt_4_2.model.Categories;
import com.geektech.quizapp_gt_4_2.model.Global;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuestionsCount;

import java.util.List;

public interface IQuizApiClient {
    void getQuestions(int amount,Integer category,String difficulty,QuestionsCallback callback);
    void getCategories(CategoriesCallback categoriesCallback);
    void getGlobal(GlobalCallback globalCallback);
    void getQuestionCount(Integer category,CountCallback countCallback);

     interface CategoriesCallback extends ICoreCallback<List<Categories>> {

    }
     interface QuestionsCallback extends ICoreCallback<List<Question>>{

    }
     interface  GlobalCallback extends  ICoreCallback<GlobalResponse>{

    }
     interface  CountCallback extends  ICoreCallback<QuizQuestionsCount>{

    }
}
