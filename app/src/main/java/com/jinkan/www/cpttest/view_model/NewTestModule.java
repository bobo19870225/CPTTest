package com.jinkan.www.cpttest.view_model;

import com.jinkan.www.cpttest.di.ActivityScoped;
import com.jinkan.www.cpttest.view.NewTestDaggerActivity;

import androidx.lifecycle.ViewModelProviders;
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
    NewTestViewModel providesNewTestViewModel(NewTestDaggerActivity newTestDaggerActivity) {
        return ViewModelProviders.of(newTestDaggerActivity).get(NewTestViewModel.class);
    }

}
