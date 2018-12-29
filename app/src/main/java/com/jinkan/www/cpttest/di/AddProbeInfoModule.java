package com.jinkan.www.cpttest.di;

import com.jinkan.www.cpttest.view.CrossFragment;
import com.jinkan.www.cpttest.view.DoubleBridgeFragment;
import com.jinkan.www.cpttest.view.SingleBridgeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2018/12/29.
 * CPTTest
 */
@Module
public abstract class AddProbeInfoModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SingleBridgeFragment singleBridgeFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract DoubleBridgeFragment doubleBridgeFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CrossFragment crossFragment();
}
