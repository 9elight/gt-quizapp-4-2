package com.geektech.quizapp_gt_4_2.presentation.settings;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.presentation.main.MainViewModel;

public class SettingsFragment extends CoreFragment {

    private SettingsViewModel mViewModel;
    private View view;
    private MainViewModel mainViewModel;
    private TextView share,rateUs,feedback,clear;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener(view);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"delete",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Внимание!!");
                builder.setMessage("Вы точно хотите очистить историю?");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.quizDatabase.historyDao().deleteAll();
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                App.quizDatabase.historyDao().deleteAll();
            }
        });
    }

    private void initListener(View v){
        share = v.findViewById(R.id.settings_share);
        rateUs = v.findViewById(R.id.settings_rate_us);
        feedback = v.findViewById(R.id.settings_leave_feedback);
        clear = v.findViewById(R.id.settings_clear_history);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);


        }


    @Override
    protected int getLayoutId() {
        return R.layout.settings_fragment;
    }
}
