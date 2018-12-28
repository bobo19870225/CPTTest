package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.db.dao.WirelessTestDao;
import com.jinkan.www.cpttest.db.entity.WirelessTestEntity;
import com.jinkan.www.cpttest.view_model.base.BaseViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/27.
 * CPTTest
 */
public class WirelessTestViewModel extends BaseViewModel {

    public final MutableLiveData<Integer> action = new MutableLiveData<>();
    public final MediatorLiveData<List<WirelessTestEntity>> listMediatorLiveData = new MediatorLiveData<>();

    private WirelessTestDao wirelessTestDao;

    public WirelessTestViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void inject(Object... objects) {
        wirelessTestDao = (WirelessTestDao) objects[1];
    }

    public void newTest() {
        action.setValue(0);
    }

    public void reTest() {
        listMediatorLiveData.addSource(wirelessTestDao.getAllWirelessTestEntity(), listMediatorLiveData::setValue);
    }

    public void markupFile() {
        action.setValue(1);
    }

    public void wirelessProbe() {
        action.setValue(2);

    }

    public void dataSync() {
        action.setValue(3);
    }

    public void testData() {
        action.setValue(4);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }
}
