package com.jinkan.www.cpttest.di;

import com.jinkan.www.cpttest.view.AddProbeActivity;
import com.jinkan.www.cpttest.view.AddProbeInfoActivity;
import com.jinkan.www.cpttest.view.CalibrationParameterActivity;
import com.jinkan.www.cpttest.view.DataSyncActivity;
import com.jinkan.www.cpttest.view.HistoryDataActivity;
import com.jinkan.www.cpttest.view.LinkBluetoothActivity;
import com.jinkan.www.cpttest.view.MarkFileActivity;
import com.jinkan.www.cpttest.view.MyLinkerActivity;
import com.jinkan.www.cpttest.view.MyMsgActivity;
import com.jinkan.www.cpttest.view.NewTestActivity;
import com.jinkan.www.cpttest.view.OpenFileActivity;
import com.jinkan.www.cpttest.view.OrdinaryProbeActivity;
import com.jinkan.www.cpttest.view.SetEmailActivity;
import com.jinkan.www.cpttest.view.SingleBridgeTestActivity;
import com.jinkan.www.cpttest.view.TestDataDetailsActivity;
import com.jinkan.www.cpttest.view.TimeSynchronizationActivity;
import com.jinkan.www.cpttest.view.VersionInfoActivity;
import com.jinkan.www.cpttest.view.VideoActivity;
import com.jinkan.www.cpttest.view.WirelessProbeActivity;
import com.jinkan.www.cpttest.view.WirelessTestActivity;
import com.jinkan.www.cpttest.view.base.BaseTestActivity;
import com.jinkan.www.cpttest.view.main.MainActivity;
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
    @ContributesAndroidInjector
    abstract SingleBridgeTestActivity singleBridgeTestActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract BaseTestActivity baseTestDaggerActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract LinkBluetoothActivity linkBluetoothActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract HistoryDataActivity historyDataActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract OrdinaryProbeActivity ordinaryProbeActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract TestDataDetailsActivity testDataDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract MyLinkerActivity myLinkerActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract MyMsgActivity myMsgActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract VideoActivity videoActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract VersionInfoActivity versionInfoActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract MarkFileActivity markFileActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract AddProbeActivity addProbeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AddProbeInfoModule.class)
    abstract AddProbeInfoActivity addProbeInfoActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AddProbeInfoModule.class)
    abstract WirelessProbeActivity wirelessProbeActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract TimeSynchronizationActivity timeSynchronizationActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract OpenFileActivity openWFileActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract DataSyncActivity dataSyncActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract WirelessTestActivity wirelessTestActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract CalibrationParameterActivity calibrationParameterActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract SetEmailActivity setEmailActivity();


}
