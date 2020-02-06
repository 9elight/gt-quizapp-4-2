package com.geektech.quizapp_gt_4_2.data.history;

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

}
