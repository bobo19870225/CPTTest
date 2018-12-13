package com.jinkan.www.cpttest.view;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityMainBinding;
import com.jinkan.www.cpttest.view_model.MainViewModel;

public class MainActivity extends BaseMVVMActivity<MainViewModel, ActivityMainBinding> {


    @Override
    protected void setView() {

    }

    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel createdViewModel() {
        return new MainViewModel();
    }
}
