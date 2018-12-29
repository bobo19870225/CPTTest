package com.jinkan.www.cpttest.view_model;

import android.app.Application;
import android.content.Intent;

import com.jinkan.www.cpttest.db.dao.ProbeDao;
import com.jinkan.www.cpttest.db.entity.ProbeEntity;
import com.jinkan.www.cpttest.util.StringUtil;
import com.jinkan.www.cpttest.view_model.base.BaseViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/12/29.
 * CPTTest
 */
public class AddProbeInfoVM extends BaseViewModel {
    public final MutableLiveData<String> titleProbeType = new MutableLiveData<>();
    public final MutableLiveData<String> probeType = new MutableLiveData<>();
    public final MutableLiveData<String> sn = new MutableLiveData<>();
    public final MutableLiveData<String> number = new MutableLiveData<>();
    public final MutableLiveData<String> qcArea = new MutableLiveData<>();
    public final MutableLiveData<String> fsArea = new MutableLiveData<>();
    public final MutableLiveData<String> qcCoefficient = new MutableLiveData<>();
    public final MutableLiveData<String> fsCoefficient = new MutableLiveData<>();
    public final MutableLiveData<String> qcLimit = new MutableLiveData<>();
    public final MutableLiveData<String> fsLimit = new MutableLiveData<>();


    private ProbeDao probeDao;

    public AddProbeInfoVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void inject(Object... objects) {
        probeDao = (ProbeDao) objects[1];
    }

    public void choseType() {
        callbackMessage.setValue(0);
        getView().callback(callbackMessage);
    }

    public void addProbe() {
        String strSn = sn.getValue();
        if (strSn != null && strSn.length() == 8) {
            LiveData<List<ProbeEntity>> liveData = probeDao.getProbeByProbeId(strSn);
            List<ProbeEntity> probeEntities = liveData.getValue();
            if (probeEntities != null && !probeEntities.isEmpty()) {
                saveDataToLocal(true);
            } else {
                saveDataToLocal(false);
            }
            callbackMessage.setValue(1);
            getView().callback(callbackMessage);
        } else {
            toast("序列号错误，请查询！");
        }


    }

    private void saveDataToLocal(boolean isUpdate) {
        String strSn = sn.getValue();
        String strNumber = number.getValue();
        String strProbeType = probeType.getValue();
        ProbeEntity probeModel = new ProbeEntity();
        if (strSn == null) {
            return;
        }
        if (strNumber == null) {
            toast("探头编号不能为空");
            return;
        }
        if (strProbeType == null) {
            toast("探头类型不能为空");
            return;
        }
        probeModel.probeID = strSn;
        probeModel.sn = strSn;
        probeModel.number = strNumber;
        probeModel.type = strProbeType;
        //--------------------------------------------------------------
        if (StringUtil.isInteger(qcArea.getValue())) {
            probeModel.qc_area = qcArea.getValue();
        } else {
            if (strProbeType.equals("十字板")) {
                toast("板头面积不合法");
            } else {
                toast("锥底面积不合法");
            }
            return;
        }

        String strQcCoefficient = qcCoefficient.getValue();
        if (strQcCoefficient != null && StringUtil.isFloat(strQcCoefficient)) {
            probeModel.qc_coefficient = Float.parseFloat(strQcCoefficient);
        } else {
            if (strProbeType.equals("十字板")) {
                toast("板头标定系数不合法");
            } else {
                toast("锥头标定系数不合法");
            }

            return;
        }
        String strQcLimit = qcLimit.getValue();
        if (strQcLimit != null && StringUtil.isInteger(strQcLimit)) {
            probeModel.qc_limit = Integer.parseInt(strQcLimit);
        } else {

            if (strProbeType.equals("十字板")) {
                toast("板头限值不合法");
            } else {
                toast("锥头限值不合法");
            }
            return;
        }
        switch (strProbeType) {

            case "双桥":
            case "双桥测斜":
                String strFsArea = fsArea.getValue();
                if (strFsArea != null && StringUtil.isInteger(strFsArea)) {
                    probeModel.fs_area = strFsArea;
                } else {
                    toast("侧壁面积不合法");
                    return;
                }
                String strFsCoefficient = fsCoefficient.getValue();
                if (strFsCoefficient != null && StringUtil.isFloat(strFsCoefficient)) {
                    probeModel.fs_coefficient = Float.parseFloat(strFsCoefficient);
                } else {
                    toast("侧壁标定系数不合法");
                    return;
                }
                String strFsLimit = fsLimit.getValue();
                if (strFsLimit != null && StringUtil.isInteger(strFsLimit)) {
                    probeModel.fs_limit = Integer.parseInt(strFsLimit);
                } else {
                    toast("侧壁限值不合法");
                    return;
                }
                break;

        }
        if (isUpdate) {
            probeDao.upDateProbe(probeModel);
        } else {
            probeDao.insertProbeEntity(probeModel);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }
}