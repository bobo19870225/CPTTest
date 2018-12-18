package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.db.dao.TestDao;
import com.jinkan.www.cpttest.db.entity.TestEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/16.
 * CPTTest
 */

public class OrdinaryTestViewModel extends BaseViewModel {

    public OrdinaryTestViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<TestEntity>> allTestes = new MutableLiveData<>();
    private TestDao testDao;
    public MutableLiveData<String> action = new MutableLiveData<>();

    public void newTest() {
        action.setValue("NewTest");
    }

    public void reDoTest() {
        allTestes = testDao.getAllTestes();
    }

    public LiveData<List<TestEntity>> getAllTestes() {
        return allTestes;
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

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }
}
