/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view;

import android.app.AlertDialog;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityMainBinding;
import com.jinkan.www.cpttest.view.adapter.MyPagerAdapter;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerActivity;
import com.jinkan.www.cpttest.view_model.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends BaseMVVMDaggerActivity<MainViewModel, ActivityMainBinding> {

    @Inject
    OrdinaryTestFragment ordinaryTestFragment;
    private ViewPager view_page;
    private BottomNavigationView navigation;
    private int selectedIndex = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    view_page.setCurrentItem(0);
                    selectedIndex = 0;
                    return true;
                case R.id.navigation_dashboard:
                    view_page.setCurrentItem(1);
                    selectedIndex = 1;
                    return true;
                case R.id.navigation_notifications:
                    view_page.setCurrentItem(2);
                    selectedIndex = 2;
                    return true;
                case R.id.navigation_me:
                    view_page.setCurrentItem(3);
                    selectedIndex = 3;
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void setView() {
        view_page = mViewDataBinding.viewPage;
        navigation = mViewDataBinding.navigation;
//        JPushInterface.init(getApplicationContext());//初始化JPush
        initViewPage();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initViewPage() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(ordinaryTestFragment);
        fragmentList.add(new OrdinaryTestFragment());
        fragmentList.add(new OrdinaryTestFragment());
        fragmentList.add(new OrdinaryTestFragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(mFragmentManager, fragmentList);
        view_page.setAdapter(myPagerAdapter);
        view_page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Menu menu = navigation.getMenu();
                menu.getItem(position).setChecked(true);
                selectedIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void toRefresh() {
        view_page.setCurrentItem(selectedIndex);
    }

    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel createdViewModel() {
        return new MainViewModel();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .create();
            alertDialog.show();
            Window window = alertDialog.getWindow();
            if (window != null) {
                window.setGravity(Gravity.CENTER);
                window.setWindowAnimations(R.style.exitStyle);
                window.setContentView(R.layout.dialog_exit);
                Button ok = window.findViewById(R.id.ok);
                View cancel = window.findViewById(R.id.cancel);
                ok.setOnClickListener(view -> {
                    alertDialog.dismiss();
                    new Handler().postDelayed(this::finish, 500);

                });
                cancel.setOnClickListener(view -> alertDialog.dismiss());
            }


        }
        return super.onKeyDown(keyCode, event);
    }
}
