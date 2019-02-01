package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.view.AnalogCalibrationActivity;
import com.jinkan.www.cpttest.view.DigitalCalibrationActivity;
import com.jinkan.www.cpttest.view_model.base.BaseViewModel;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/1/15.
 * CPTTest
 */
public class InstrumentCalibrationFragmentVM extends BaseViewModel {

    public InstrumentCalibrationFragmentVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void inject(Object... objects) {

    }

    public void analogCalibration() {
        goTo(AnalogCalibrationActivity.class, null);
    }

    public void digitalCalibration() {
        goTo(DigitalCalibrationActivity.class, null);
    }

    public void test() {
        callbackMessage.setValue(2);
        getView().action(callbackMessage);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }
}
