package com.geektech.quizapp_gt_4_2.presentation.history;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.model.History;

import java.util.List;

public class HistoryFragment extends CoreFragment implements HistoryAdapter.HistoryListener {

    private HistoryViewModel mViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private ImageView popUp_dots;
    private List<History> currentHistories;


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.history_fragment;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        rv_builder(view);

    }

    private void rv_builder(View view){
        recyclerView = view.findViewById(R.id.history_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HistoryAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initViews(View v){

    }

    private void showPopUp(View v, int position){
        PopupMenu popupMenu = new PopupMenu(getContext(),v);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.delete:
                            App.quizDatabase.historyDao().deleteById(currentHistories.get(position).getId());
                            return true;
                case R.id.share:
                    Toast.makeText(getActivity(),"share",Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        });

        popupMenu.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        mViewModel.historyLiveData.observe(getActivity(), new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                if (histories != null)
                adapter.updateHistory(histories);
                currentHistories = histories;
            }
        });

    }

    @Override
    public void onDotsClick(int position,View v) {
        showPopUp(v,position);
    }
}
