package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/16.
 * CPTTest
 */

public class OrdinaryTestViewModel extends BaseViewModel {

    public OrdinaryTestViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> action = new MutableLiveData<>();
    public void newTest() {
        action.setValue("NewTest");

    }

    public void reDoTest() {
    }

    public void showHistoryData() {
    }

    public void showOrdinaryProbe() {
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
