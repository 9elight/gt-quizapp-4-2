package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.core.CoreCallback;
import com.geektech.quizapp_gt_4_2.data.remote.model.GlobalResponse;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizCategoriesResponse;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizQuestionsCount;
import com.geektech.quizapp_gt_4_2.data.remote.model.QuizQuestionsResponse;

import retrofit2.Call;
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
    public void getQuestions(int amount, Integer category, String difficulty, final QuestionsCallback callback) {
        Call<QuizQuestionsResponse> call = client.getQuestions(
                amount,
                category,
                difficulty);
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

    @Override
    public void getGlobal(final GlobalCallback globalCallback) {
        Call<GlobalResponse> call = client.getGlobal();
        call.enqueue(new CoreCallback<GlobalResponse>() {
            @Override
            public void onSuccess(GlobalResponse result) {
                globalCallback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                globalCallback.onFailure(e);
            }
        });
    }

    @Override
    public void getQuestionCount(Integer category, final CountCallback countCallback) {
        Call<QuizQuestionsCount> call = client.getQuestionsCount(category);
        call.enqueue(new CoreCallback<QuizQuestionsCount>() {
            @Override
            public void onSuccess(QuizQuestionsCount result) {
                countCallback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    private interface QuizApi{
        @GET("api.php")
        Call<QuizQuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );

        @GET("api_category.php")
        Call<QuizCategoriesResponse> getCategories(

        );

        @GET("api_count_global.php")
        Call<GlobalResponse> getGlobal();

        @GET("api_count.php")
        Call<QuizQuestionsCount> getQuestionsCount(
                @Query("category")Integer category
        );
    }


}
