package com.jinkan.www.cpttest.view_model;

import android.content.Intent;

import com.jinkan.www.cpttest.db.dao.TestDaoHelper;
import com.jinkan.www.cpttest.db.entity.TestEntity;
import com.jinkan.www.cpttest.view.MainActivity;
import com.jinkan.www.cpttest.view.SingleBridgeTestActivity;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/13.
 * CPTTest
 */
public class MainViewModel extends BaseViewModel<MainActivity> {
    public MutableLiveData<String> obsProjectNumber = new MutableLiveData<>();
    public MutableLiveData<String> obsHoleNumber = new MutableLiveData<>();
    public MutableLiveData<String> obsHoleHigh = new MutableLiveData<>();
    public MutableLiveData<String> obsWaterLevel = new MutableLiveData<>();
    public MutableLiveData<String> obsLocation = new MutableLiveData<>();
    public MutableLiveData<String> obsTester = new MutableLiveData<>();
    public MutableLiveData<String> obsTestType = new MutableLiveData<>();
//    @Inject
//    TestDaoHelper testDaoHelper;

    public void submit() {
        TestEntity testEntity = new TestEntity();
        if (obsProjectNumber.getValue() == null) {
            getView().showToast("工程编号不能为空");
            return;
        }
        testEntity.projectNumber = obsProjectNumber.getValue();

        if (obsHoleNumber.getValue() == null) {
            getView().showToast("孔号不能为空");
            return;
        }
        testEntity.holeNumber = obsHoleNumber.getValue();
        testEntity.testID = obsProjectNumber.getValue() + "_" + obsHoleNumber.getValue();
        if (obsHoleHigh.getValue() == null) {
            getView().showToast("孔口高程不能为空");
            return;
        }
        testEntity.holeHigh = Float.valueOf(obsHoleHigh.getValue());

        if (obsWaterLevel.getValue() == null) {
            getView().showToast("地下水位不能为空");
            return;
        }
        testEntity.waterLevel = Float.valueOf(obsWaterLevel.getValue());

        if (obsTester.getValue() == null) {
            getView().showToast("操作员不能为空");
            return;
        }
        testEntity.tester = obsTester.getValue();

        if (obsTestType.getValue() == null) {
            getView().showToast("试验类型不能为空");
            return;
        }
        testEntity.testType = obsTestType.getValue();

//        TestDaoHelper testDaoHelper = DataFactory.getBaseData(TestDaoHelper.class, getView().getApplicationContext());

        getView().testDaoHelper.addData(testEntity, () -> getView().showToast("添加成功"));
        getView().goTo(SingleBridgeTestActivity.class, new String[]{"1", "2"});
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

    public void setTypeText(String testType) {
        obsTestType.setValue(testType);
    }
}
