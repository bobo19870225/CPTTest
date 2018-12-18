/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view.base;

import android.annotation.SuppressLint;
import android.view.KeyEvent;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.db.dao.TestDao;
import com.jinkan.www.cpttest.db.entity.TestEntity;
import com.jinkan.www.cpttest.view.DialogDaggerActivity;
import com.jinkan.www.cpttest.view_model.BaseTestViewModel;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;


/**
 * 试验基类
 */


@SuppressLint("Registered")
public class BaseTestActivity extends DialogDaggerActivity {

    //    protected DrawChartHelper drawChartHelper;
    protected String strProjectNumber;
    protected String strHoleNumber;
    protected BaseTestViewModel baseTestViewModel;
    @Inject
    TestDao testDao;
    @Override
    protected void setView() {
        String[] strings = (String[]) mData;//1.mac,2.工程编号,3.孔号,4.试验类型
        mac = strings[0];
        strProjectNumber = strings[0];
        strHoleNumber = strings[1];
        baseTestViewModel = ViewModelProviders.of(this).get(BaseTestViewModel.class);
        baseTestViewModel.getTestParameters(testDao, strProjectNumber, strHoleNumber)
                .observe(this, testEntities -> {
                    if (testEntities != null && !testEntities.isEmpty()) {
                        TestEntity testEntity = testEntities.get(0);
                        baseTestViewModel.obsProjectNumber.set(testEntity.projectNumber);
                        baseTestViewModel.obsHoleNumber.set(testEntity.holeNumber);
                    }

                });
    }


    @Override
    public int initView() {
//        drawChartHelper = new DrawChartHelper();
        return R.layout.activity_base_test;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.link:
////                mViewModel.linkDevice(mac);
//                return false;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    private String emailType = EMAIL_TYPE_LY_TXT;
//    protected String[] emailItems = {EMAIL_TYPE_LY_TXT, EMAIL_TYPE_LY_DAT, EMAIL_TYPE_HN_111, EMAIL_TYPE_LZ_TXT};
//
//    protected void showEmailDataDialog() {
//        Dialog alertDialog = new AlertDialog.Builder(this)
//                .setTitle("请选择发送的数据类型")
//                .setSingleChoiceItems(emailItems, 0, (dialog, which) -> emailType = emailItems[which])
//                .setPositiveButton("确定", (dialog, which) -> {
//                    mViewModel.saveTestDataToSD(emailType);
//                    mViewModel.emailTestData(emailType);
//                })
//                .setNegativeButton("取消", (dialog, which) -> {
//                    emailType = emailItems[0];
//                    dialog.dismiss();
//                }).create();
//        alertDialog.show();
//    }
//
//    private String saveType = SAVE_TYPE_ZHD_TXT;
//    protected String[] saveItems = {SAVE_TYPE_ZHD_TXT, SAVE_TYPE_LY_TXT, SAVE_TYPE_LY_DAT, SAVE_TYPE_HN_111, SAVE_TYPE_LZ_TXT};

//    protected void showSaveDataDialog() {
//
//        Dialog alertDialog = new AlertDialog.Builder(this)
//                .setTitle("请选择要保存的数据类型")
//                .setSingleChoiceItems(saveItems, 0, (dialog, which) -> saveType = saveItems[which])
//                .setPositiveButton("确定", (dialog, which) -> mViewModel.saveTestDataToSD(saveType))
//                .setNegativeButton("取消", (dialog, which) -> {
//                    saveType = saveItems[0];
//                    dialog.dismiss();
//                }).create();
//        alertDialog.show();
//    }

    private String mac;

    @Override
    protected void toRefresh() {
        String[] strings = (String[]) mData;//1.mac,2.工程编号,3.孔号,4.试验类型
        mac = strings[0];
        strProjectNumber = strings[0];
        strHoleNumber = strings[1];
//        mViewModel.linkDevice(mac);
    }


//    public void showTestData(List<TestDataEntity> testDataModels) {
////        deep.setText(StringUtils.format(testDataModels.get(testDataModels.size() - 1).deep, 1));
//        List<float[]> listPoints = new ArrayList<>();
//        for (TestDataEntity testDataModel : testDataModels) {
//            listPoints.add(new float[]{testDataModel.qc,
//                    testDataModel.fs,
//                    testDataModel.fa,
//                    testDataModel.deep});
//        }
//        drawChartHelper.addPointsToChart(listPoints);
//    }

//    public void showRecordValue(float qc, float fs, float fa, float deep) {
//        drawChartHelper.addOnePointToChart(new float[]{qc, fs, fa, deep});
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
            baseTestViewModel.doRecord();
        }
        return true;
    }


//    public void showModifyDialog(String strDistance) {
//        LayoutInflater layoutInflater = getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.dialog_modify_distance, findViewById(R.id.dialog));
//        final Dialog alertDialog = new AlertDialog.Builder(BaseTestActivity.this)
//                .setView(view)
//                .create();
//        alertDialog.show();
//        final EditText distance = view.findViewById(R.id.distance);
//        distance.setText(strDistance);
//
//        Button ok = view.findViewById(R.id.ok);
//        ok.setOnClickListener(view1 -> {
//            String _distance = distance.getText().toString();
//            if (!_distance.equals(strDistance)) {
//                if (StringUtils.isFloat(_distance)) {
//                    mViewModel.setDistance(_distance);
//                } else {
//                    showToast("测量间距不合法");
//                }
//            }
//            alertDialog.dismiss();
//        });
//        Button cancel = view.findViewById(R.id.cancel);
//        cancel.setOnClickListener(view12 -> alertDialog.dismiss());
//    }
}
