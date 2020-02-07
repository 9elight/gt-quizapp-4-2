package com.geektech.quizapp_gt_4_2.presentation.history;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.presentation.main.MainViewModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private RecyclerView recyclerView;
    private View view;
    private HistoryAdapter adapter;


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_fragment, container, false);
        rv_builder();
        adapter.updateHistory(new ArrayList<>());
        return view;
    }

    private void rv_builder(){
        recyclerView = view.findViewById(R.id.history_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HistoryAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);

    }

}
