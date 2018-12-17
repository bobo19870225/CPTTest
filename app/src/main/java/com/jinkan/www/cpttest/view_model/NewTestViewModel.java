package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.db.dao.TestDaoHelper;
import com.jinkan.www.cpttest.db.entity.TestEntity;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/13.
 * CPTTest
 */

public class NewTestViewModel extends BaseViewModel {
    public MutableLiveData<String> obsProjectNumber = new MutableLiveData<>();
    public MutableLiveData<String> obsHoleNumber = new MutableLiveData<>();
    public MutableLiveData<String> obsHoleHigh = new MutableLiveData<>();
    public MutableLiveData<String> obsWaterLevel = new MutableLiveData<>();
    public MutableLiveData<String> obsLocation = new MutableLiveData<>();
    public MutableLiveData<String> obsTester = new MutableLiveData<>();
    public MutableLiveData<String> obsTestType = new MutableLiveData<>();
    public MutableLiveData<String> toastMsg = new MutableLiveData<>();
    public MutableLiveData<Boolean> ifGoTo = new MutableLiveData<>();

    private TestDaoHelper testDaoHelper;
    public NewTestViewModel(@NonNull Application application) {
        super(application);
    }


    public void submit() {
        TestEntity testEntity = new TestEntity();
        if (obsProjectNumber.getValue() == null) {
            toastMsg.setValue("工程编号不能为空");
            return;
        }
        testEntity.projectNumber = obsProjectNumber.getValue();

        if (obsHoleNumber.getValue() == null) {
            toastMsg.setValue("孔号不能为空");
            return;
        }
        testEntity.holeNumber = obsHoleNumber.getValue();
        testEntity.testID = obsProjectNumber.getValue() + "_" + obsHoleNumber.getValue();
        if (obsHoleHigh.getValue() == null) {
            toastMsg.setValue("孔口高程不能为空");
            return;
        }
        testEntity.holeHigh = Float.valueOf(obsHoleHigh.getValue());

        if (obsWaterLevel.getValue() == null) {
            toastMsg.setValue("地下水位不能为空");
            return;
        }
        testEntity.waterLevel = Float.valueOf(obsWaterLevel.getValue());

        if (obsTester.getValue() == null) {
            toastMsg.setValue("操作员不能为空");
            return;
        }
        testEntity.tester = obsTester.getValue();

        if (obsTestType.getValue() == null) {
            toastMsg.setValue("试验类型不能为空");
            return;
        }
        testEntity.testType = obsTestType.getValue();
        testDaoHelper.addData(testEntity, () -> toastMsg.setValue("添加成功！"));
        ifGoTo.setValue(true);
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

    public void setTestDaoHelper(TestDaoHelper testDaoHelper) {
        this.testDaoHelper = testDaoHelper;
    }
}
