package com.geektech.quizapp_gt_4_2.data.remote;

import android.provider.Settings;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class GlobalResponse {
    @SerializedName("overall")
    private Settings.Global global;
    @SerializedName("categories")
    private Map<String, Settings.Global> categories;

    public GlobalResponse(Settings.Global global, Map<String, Settings.Global> globalMap) {
        this.global = global;
        this.categories = globalMap;
    }

    public Settings.Global getGlobal() {
        return global;
    }

    public void setGlobal(Settings.Global global) {
        this.global = global;
    }

    public Map<String, Settings.Global> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Settings.Global> categories) {
        this.categories = categories;
    }
}
