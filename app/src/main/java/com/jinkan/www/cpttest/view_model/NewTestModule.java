package com.jinkan.www.cpttest.view_model;

import com.jinkan.www.cpttest.di.ActivityScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sampson on 2018/12/17.
 * CPTTest
 */
@Module
public class NewTestModule {
    @ActivityScoped
    @Provides
    NewTestViewModel providesNewTestViewModel() {
        return new NewTestViewModel();
    }

}
