package com.jinkan.www.cpttest.view_model.new_test;

import com.jinkan.www.cpttest.di.ActivityScoped;
import com.jinkan.www.cpttest.view.NewTestActivity;

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
    NewTestViewModel providesNewTestViewModel(NewTestActivity newTestActivity) {
        return ViewModelProviders.of(newTestActivity).get(NewTestViewModel.class);
    }

}
