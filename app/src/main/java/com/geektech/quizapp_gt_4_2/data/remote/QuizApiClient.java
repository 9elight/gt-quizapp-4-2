package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.core.CoreCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(int amount, String category, String difficulty, final QuestionsCallback callback) {
        Call<QuizQuestionsResponse> call = client.getQuestions(amount,category,difficulty);
        call.enqueue(new CoreCallback<QuizQuestionsResponse>() {
            @Override
            public void onSuccess(QuizQuestionsResponse result) {
                callback.onSuccess(result.getResults());
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void getCategories(final CategoriesCallback categoriesCallback) {
        Call<QuizCategoriesResponse> call = client.getCategories();
        call.enqueue(new CoreCallback<QuizCategoriesResponse>() {
            @Override
            public void onSuccess(QuizCategoriesResponse result) {
                categoriesCallback.onSuccess(result.getTriviaCategories());
            }

            @Override
            public void onFailure(Exception e) {
                categoriesCallback.onFailure(e);
            }
        });
    }


    private interface QuizApi{
        @GET("api.php")
        Call<QuizQuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") String category,
                @Query("difficulty") String difficulty
        );
        @GET("api_category.php")
        Call<QuizCategoriesResponse> getCategories();

    }
}
