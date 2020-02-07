package com.geektech.quizapp_gt_4_2.model;

import androidx.room.Entity;

import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

@Entity
public class QuizResult {
    private int id;
    private String category;
    private String difficulty;
    private List<Question> questions;
    private int correctAnswerAmount;

    public QuizResult(int id, String category, String difficulty, List<Question> questions, int correctAnswerAmount) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.correctAnswerAmount = correctAnswerAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrectAnswerAmount() {
        return correctAnswerAmount;
    }

    public void setCorrectAnswerAmount(int correctAnswerAmount) {
        this.correctAnswerAmount = correctAnswerAmount;
    }
}
