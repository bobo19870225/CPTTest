package com.jinkan.www.cpttest.view_model.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

/**
 * Created by Sampson on 2018/12/24.
 * CPTTest
 */
public abstract class BaseListViewModel<T> extends BaseViewModel {
    public final MediatorLiveData<T> listData = new MediatorLiveData<>();
    public BaseListViewModel(@NonNull Application application) {
        super(application);
    }

    public abstract LiveData<T> loadListViewData();

}
