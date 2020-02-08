package com.geektech.quizapp_gt_4_2.presentation.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.presentation.history.HistoryFragment;
import com.geektech.quizapp_gt_4_2.presentation.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private BottomNavigationView mBottomNavigation;
    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewModel.getCategories();
        mViewModel.getGlobal();
        mViewModel.getQuestionCount(9);
        Log.e("tag", "onCreate: " );

    }
    private void initViews(){
        mViewPager = findViewById(R.id.main_view_pager);
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        setmBottomNavigation();
    }

    private void setmBottomNavigation(){
        mBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.explore:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.profile:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.map:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBottomNavigation.getMenu().findItem(R.id.explore).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigation.getMenu().findItem(R.id.profile).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigation.getMenu().findItem(R.id.map).setChecked(true);
                        break;
                }
            }

        });
    }



    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = MainFragment.newInstance();
                    break;
                case 1:
                    fragment = HistoryFragment.newInstance();
                    break;
                default:
                    fragment = SettingsFragment.newInstance();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onBackPressed() {
        switch (mViewPager.getCurrentItem()){
            case 2:
                mViewPager.setCurrentItem(1);
                break;
            case 1:
                mViewPager.setCurrentItem(0);
                break;
            case 0:
                super.onBackPressed();
        }
    }

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

}
