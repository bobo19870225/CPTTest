package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.db.dao.TestDao;
import com.jinkan.www.cpttest.db.entity.TestEntity;
import com.jinkan.www.cpttest.view_model.base.BaseViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;

/**
 * Created by Sampson on 2018/12/16.
 * CPTTest
 */

public class OrdinaryTestViewModel extends BaseViewModel {

    public OrdinaryTestViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void inject(Object... objects) {
        testDao = (TestDao) objects[1];
    }

    private TestDao testDao;
    public final MediatorLiveData<List<TestEntity>> allTestes = new MediatorLiveData<>();
    public void newTest() {
        callbackMessage.setValue(0);
        getView().callback(callbackMessage);
//        action.setValue("NewTest");
    }

    public void reDoTest() {
        allTestes.addSource(testDao.getAllTestes(), allTestes::setValue);
    }

    public void showHistoryData() {
        callbackMessage.setValue(1);
        getView().callback(callbackMessage);
//        action.setValue("HistoryDataActivity");
    }

    public void showOrdinaryProbe() {
        callbackMessage.setValue(2);
        getView().callback(callbackMessage);
//        action.setValue("OrdinaryProbeActivity");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }


}
