package com.jinkan.www.cpttest.view_model;

import android.content.Intent;

import com.jinkan.www.cpttest.view.MainActivity;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/13.
 * CPTTest
 */
public class MainViewModel extends BaseViewModel<MainActivity> {
    public MutableLiveData<String> obsProjectNumber = new MutableLiveData<>();

    public MainViewModel() {
        obsProjectNumber.setValue("测试");
    }

    public String getV() {
        return obsProjectNumber.getValue();
    }

    @Override
    public void init(Object data) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }
}
