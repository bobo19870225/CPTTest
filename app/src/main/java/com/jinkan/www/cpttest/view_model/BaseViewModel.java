/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view_model;

import android.content.Intent;

import com.jinkan.www.cpttest.view.MVVMView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import androidx.lifecycle.ViewModel;

/**
 * Created by lushengbo on 2018/1/12.
 * MVVM ViewModel 基类
 */

public abstract class BaseViewModel<V extends MVVMView> extends ViewModel {
    protected Reference<V> myView;


    public void attachView(V view) {
        myView = new WeakReference<>(view);
    }

    public V getView() {
        return myView.get();
    }


    public void detachView() {
        myView = null;
    }

    public abstract void init(Object data);

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    public abstract void clear();


}
