package com.jinkan.www.cpttest.view_model.base;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2018/12/24.
 * CPTTest
 */
public class BaseListViewModel extends BaseViewModel {
    public BaseListViewModel(@NonNull Application application) {
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
