package com.geektech.quizapp_gt_4_2;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.geektech.quizapp_gt_4_2.data.QuizRepository;
import com.geektech.quizapp_gt_4_2.data.db.QuizDatabase;
import com.geektech.quizapp_gt_4_2.data.history.HistoryStorage;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.QuizApiClient;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

public class App extends Application {
    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizRepository repository;
    public static QuizDatabase quizDatabase;
    public static FirebaseTranslator myTranslator;


    @Override
    public void onCreate() {
        super.onCreate();

        quizDatabase = Room.databaseBuilder(this,QuizDatabase.class,"quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        quizDatabase.historyDao();
        repository = new QuizRepository(new QuizApiClient(),new HistoryStorage(quizDatabase.historyDao()));

        quizApiClient = repository;
        historyStorage = repository;
        translateBuilder();

    }
    private void translateBuilder(){
        FirebaseTranslatorOptions options =
                new FirebaseTranslatorOptions.Builder()
                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                        .setTargetLanguage(FirebaseTranslateLanguage.RU)
                        .build();
        final FirebaseTranslator englishRussianTranslator =
                FirebaseNaturalLanguage.getInstance().getTranslator(options);

        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
                .requireWifi()
                .build();

        englishRussianTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(
                        v -> {
                            myTranslator = englishRussianTranslator;
                            Log.e("tag", "translateBuilder: ");
                        })
                .addOnFailureListener(
                        e -> {

                        });

    }



}
