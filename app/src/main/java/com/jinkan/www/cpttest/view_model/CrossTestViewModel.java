package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2018/12/21.
 * CPTTest
 */
public class CrossTestViewModel extends BaseViewModel {

    public CrossTestViewModel(@NonNull Application application) {
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
