package com.jinkan.www.cpttest.di;

import com.jinkan.www.cpttest.view.base.BaseTestActivityMVVM;
import com.jinkan.www.cpttest.view.main.MainActivity;
import com.jinkan.www.cpttest.view.NewTestActivity;
import com.jinkan.www.cpttest.view.SingleBridgeTestActivityMVVM;
import com.jinkan.www.cpttest.view_model.BaseModule;
import com.jinkan.www.cpttest.view_model.main.MainModule;
import com.jinkan.www.cpttest.view_model.new_test.NewTestModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = NewTestModule.class)
    abstract NewTestActivity newTestDaggerActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = BaseModule.class)
    abstract SingleBridgeTestActivityMVVM singleBridgeTestActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = BaseModule.class)
    abstract BaseTestActivityMVVM baseTestDaggerActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
//    @ActivityScoped
//    @ContributesAndroidInjector(modules = AddEditTaskModule.class)
//    abstract AddEditTaskActivity addEditTaskActivity();
//
//    @ActivityScoped
//    @ContributesAndroidInjector(modules = StatisticsModule.class)
//    abstract StatisticsActivity statisticsActivity();
//
//    @ActivityScoped
//    @ContributesAndroidInjector(modules = TaskDetailPresenterModule.class)
//    abstract TaskDetailActivity taskDetailActivity();
}
