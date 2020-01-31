package com.geektech.quizapp_gt_4_2.data.remote;

import android.provider.Settings;

import com.geektech.quizapp_gt_4_2.model.Global;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class GlobalResponse {
    @SerializedName("overall")
    private Global global;
    @SerializedName("categories")
    private Map<String, Global> categories;

    public GlobalResponse(Global global, Map<String, Global> globalMap) {
        this.global = global;
        this.categories = globalMap;
    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public Map<String, Global> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Global> categories) {
        this.categories = categories;
    }
}
