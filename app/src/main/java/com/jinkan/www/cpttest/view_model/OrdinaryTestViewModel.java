package com.jinkan.www.cpttest.view_model;

import android.content.Intent;

import com.jinkan.www.cpttest.view.NewTestDaggerActivity;
import com.jinkan.www.cpttest.view.OrdinaryTestFragment;

/**
 * Created by Sampson on 2018/12/16.
 * CPTTest
 */

public class OrdinaryTestViewModel extends BaseViewModel<OrdinaryTestFragment> {

    public void newTest() {
        getView().goTo(NewTestDaggerActivity.class, null);
    }

    public void reDoTest() {
    }

    public void showHistoryData() {
    }

    public void showOrdinaryProbe() {
    }

    @Override
    public void init(Object data) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }
}
