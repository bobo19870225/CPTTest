/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view_model.base;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by lushengbo on 2018/1/12.
 * MVVM ViewModel 基类
 * 这种写法存在问题，ViewModel不应该持有Activity
 */

public abstract class BaseViewModel extends AndroidViewModel {

    public final MutableLiveData<String> toast = new MutableLiveData<>();
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


    public abstract void inject(Object... objects);

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    public abstract void clear();


}
