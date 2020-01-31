package com.geektech.quizapp_gt_4_2.core;

import com.geektech.quizapp_gt_4_2.model.Categories;

import java.util.List;

public interface ICoreCallback<T> {
    void onSuccess(T result);
    void onFailure(Exception e);
}
