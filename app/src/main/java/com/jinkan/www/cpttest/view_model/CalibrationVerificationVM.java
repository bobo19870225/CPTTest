package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.view_model.base.BaseViewModel;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2018/12/21.
 * CPTTest
 */
public class CalibrationVerificationVM extends BaseViewModel {
    public CalibrationVerificationVM(@NonNull Application application) {
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
}
