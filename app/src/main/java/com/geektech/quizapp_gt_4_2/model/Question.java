package com.geektech.quizapp_gt_4_2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
    @SerializedName("category")
    private String category;
    @SerializedName("type")
    private EType type;
    @SerializedName("difficulty")
    private EDifficulty difficulty;
    @SerializedName("question")
    private String question;
    @SerializedName("correct_answer")
    private String correctAnswer;
    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;
    private boolean isAnswered;

    private List<String> answers;

    private Integer selectedAnswerPosition;
    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getSelectedAnswerPosition() {
        return selectedAnswerPosition;
    }

    public void setSelectedAnswerPosition(Integer selectedAnswerPosition) {
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    public Question(String category, EType type, EDifficulty difficulty, String question
            , String correctAnswer, List<String> incorrectAnswers, boolean isAnswered,
                    List<String> answers, Integer selectedAnswerPosition) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.isAnswered = isAnswered;
        this.answers = answers;
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answerd) {
        isAnswered = answerd;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public EDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(EDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }
}
