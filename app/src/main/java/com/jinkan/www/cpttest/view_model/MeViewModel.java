package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.view_model.base.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/27.
 * CPTTest
 */
public class MeViewModel extends BaseViewModel {
    public final MutableLiveData<String> action = new MutableLiveData<>();

    public MeViewModel(@NonNull Application application) {
        super(application);
    }

    public void setLinker() {
        action.setValue("MyLinkerActivity");
    }

    public void setEmail() {
        action.setValue("SetEmailActivity");
    }

    public void setVideo() {
        action.setValue("VideoActivity");
    }

    public void checkVersion() {
        action.setValue("VersionInfoActivity");
    }

    public void myMsg() {
        action.setValue("MyMsgActivity");
    }


    @Override
    public void inject(Object... objects) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }
}
