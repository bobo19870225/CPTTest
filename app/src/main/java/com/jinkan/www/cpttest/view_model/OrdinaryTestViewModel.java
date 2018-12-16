package com.jinkan.www.cpttest.view_model;

import android.content.Intent;

import com.jinkan.www.cpttest.db.entity.TestEntity;
import com.jinkan.www.cpttest.parameter.SystemConstant;
import com.jinkan.www.cpttest.util.StringUtil;
import com.jinkan.www.cpttest.view.NewTestDaggerActivity;
import com.jinkan.www.cpttest.view.OrdinaryTestFragment;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;

/**
 * Created by Sampson on 2018/12/16.
 * CPTTest
 */

public class OrdinaryTestViewModel extends BaseViewModel<OrdinaryTestFragment> {


    @Override
    public void init(Object data) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }

    public void newOrdinaryTest() {
        getView().goTo(NewTestDaggerActivity.class, null);
    }

    public void reDoOrdinaryTest() {
        LiveData<List<TestEntity>> liveData = getView().testDao.getAllTestes();
        liveData.observe(getView(), testEntities -> {
            if (testEntities != null && !testEntities.isEmpty()) {
                Map<String, String> linkerPreferences = getView().preferencesUtil.getLinkerPreferences();
                String add = linkerPreferences.get("add");
                TestEntity testModel = testEntities.get(0);
                if (StringUtil.isEmpty(add)) {
//                    getView().goTo(LinkBluetoothActivity.class, new String[]{testModel.projectNumber, testModel.holeNumber, testModel.testType});
                } else {//mac地址，工程编号，孔号，试验类型。
                    switch (testModel.testType) {
                        case SystemConstant.SINGLE_BRIDGE_TEST:
//                            getView().goTo(SingleBridgeTestActivity.class, new String[]{add, testModel.projectNumber, testModel.holeNumber});
                            break;
                        case SystemConstant.SINGLE_BRIDGE_MULTI_TEST:
//                            getView().goTo(SingleBridgeMultifunctionTestActivity.class, new String[]{add, testModel.projectNumber, testModel.holeNumber});
                            break;
                        case SystemConstant.DOUBLE_BRIDGE_TEST:
//                            getView().goTo(DoubleBridgeTestActivity.class, new String[]{add, testModel.projectNumber, testModel.holeNumber});
                            break;
                        case SystemConstant.DOUBLE_BRIDGE_MULTI_TEST:
//                            getView().goTo(DoubleBridgeMultifunctionTestActivity.class, new String[]{add, testModel.projectNumber, testModel.holeNumber});
                            break;
                        case SystemConstant.VANE_TEST:
//                            getView().goTo(CrossTestActivity.class, new String[]{add, testModel.projectNumber, testModel.holeNumber});
                            break;
                    }

                }
            } else {
                getView().showToast("暂无可进行二次测量的试验");
            }
        });


    }

    public void showHistoryData() {
    }

    public void showOrdinaryProbe() {
    }

}
