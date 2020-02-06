package com.geektech.quizapp_gt_4_2.data.remote.model;

import com.geektech.quizapp_gt_4_2.model.QuestionsCount;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class QuizQuestionsCount {
    @SerializedName("category_id")
    private Integer categoryId;

    @SerializedName("category_question_count")
    private QuestionsCount categoryQuestionCount;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public QuestionsCount getCategoryQuestionCount() {
        return categoryQuestionCount;
    }

    public void setCategoryQuestionCount(QuestionsCount categoryQuestionCount) {
        this.categoryQuestionCount = categoryQuestionCount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
