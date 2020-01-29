package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.model.Categories;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public interface IQuizApiClient {
    void getQuestions(int amount,int category,String difficulty,QuestionsCallback callback);
    void getCategories(CategoriesCallback categoriesCallback);
    public interface CategoriesCallback{
        void onSuccess(List<Categories> categories);
        void onFailure(Exception e);
    }
    public interface QuestionsCallback{
        void onSuccess(List<Question> questions);
        void onFailure(Exception e);
    }
}