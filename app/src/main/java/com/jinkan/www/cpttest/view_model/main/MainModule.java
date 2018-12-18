package com.jinkan.www.cpttest.view_model.main;

import com.jinkan.www.cpttest.di.FragmentScoped;
import com.jinkan.www.cpttest.view.OrdinaryTestFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2018/12/15.
 * CPTTest
 */
@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract OrdinaryTestFragment ordinaryTestFragment();
}
