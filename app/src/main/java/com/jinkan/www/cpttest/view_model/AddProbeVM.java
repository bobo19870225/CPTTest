package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.view_model.base.BaseViewModel;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2018/12/28.
 * CPTTest
 */
public class AddProbeVM extends BaseViewModel {
    public AddProbeVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void inject(Object... objects) {

    }

    public void scanCode() {
        callbackMessage.setValue(0);
        getView().callback(callbackMessage);
    }

    public void inPut() {
        callbackMessage.setValue(1);
        getView().callback(callbackMessage);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }
}
