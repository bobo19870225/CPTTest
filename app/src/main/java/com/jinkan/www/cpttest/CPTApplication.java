package com.jinkan.www.cpttest;

import com.jinkan.www.cpttest.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Sampson on 2018/12/15.
 * CPTTest
 */
public class CPTApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        return DaggerAppComponent.builder().application(this).build();
    }
}
