package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.view_model.base.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/21.
 * CPTTest
 */
public class TimeSynchronizationVM extends BaseViewModel {
    public final MutableLiveData<String> strProjectNumber = new MutableLiveData<>();
    public final MutableLiveData<String> strHoleNumber = new MutableLiveData<>();
    public final MutableLiveData<String> strQcCoefficient = new MutableLiveData<>();
    public final MutableLiveData<String> strQcLimit = new MutableLiveData<>();
    public final MutableLiveData<Boolean> isDoubleBridge = new MutableLiveData<>();
    public final MutableLiveData<String> strFsCoefficient = new MutableLiveData<>();
    public final MutableLiveData<String> strFsLimit = new MutableLiveData<>();
    public final MutableLiveData<String> markingTime = new MutableLiveData<>();
    public final MutableLiveData<String> obsProbeNumber = new MutableLiveData<>();
    public final MutableLiveData<Boolean> linked = new MutableLiveData<>();

    public TimeSynchronizationVM(@NonNull Application application) {
        super(application);
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

    public void doSynchronization() {

    }
}
