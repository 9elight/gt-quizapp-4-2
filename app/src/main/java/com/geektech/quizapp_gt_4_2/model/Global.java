package com.geektech.quizapp_gt_4_2.model;

import com.google.gson.annotations.SerializedName;

public class Global {
    @SerializedName("total_num_of_questions")
    private String  totalNumOfQuestions;
    @SerializedName("total_num_of_pending_questions")
    private String totalNumOfPendingQuestions;
    @SerializedName("total_num_of_verified_questions")
    private String totalNumOfVerifiedQuestions;
    @SerializedName("total_num_of_rejected_questions")
    private String totalNumOfRejectedQuestions;

    public String getTotalNumOfQuestions() {
        return totalNumOfQuestions;
    }

    public void setTotalNumOfQuestions(String totalNumOfQuestions) {
        this.totalNumOfQuestions = totalNumOfQuestions;
    }

    public String getTotalNumOfPendingQuestions() {
        return totalNumOfPendingQuestions;
    }

    public void setTotalNumOfPendingQuestions(String totalNumOfPendingQuestions) {
        this.totalNumOfPendingQuestions = totalNumOfPendingQuestions;
    }

    public String getTotalNumOfVerifiedQuestions() {
        return totalNumOfVerifiedQuestions;
    }

    public void setTotalNumOfVerifiedQuestions(String totalNumOfVerifiedQuestions) {
        this.totalNumOfVerifiedQuestions = totalNumOfVerifiedQuestions;
    }

    public String getTotalNumOfRejectedQuestions() {
        return totalNumOfRejectedQuestions;
    }

    public void setTotalNumOfRejectedQuestions(String totalNumOfRejectedQuestions) {
        this.totalNumOfRejectedQuestions = totalNumOfRejectedQuestions;
    }
}
