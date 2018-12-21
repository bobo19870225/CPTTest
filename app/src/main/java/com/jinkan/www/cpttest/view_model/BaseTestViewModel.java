package com.jinkan.www.cpttest.view_model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import com.jinkan.www.cpttest.db.dao.ProbeDao;
import com.jinkan.www.cpttest.db.dao.TestDao;
import com.jinkan.www.cpttest.db.dao.TestDataDao;
import com.jinkan.www.cpttest.db.entity.ProbeEntity;
import com.jinkan.www.cpttest.db.entity.TestDataEntity;
import com.jinkan.www.cpttest.db.entity.TestEntity;
import com.jinkan.www.cpttest.util.DataUtil;
import com.jinkan.www.cpttest.util.StringUtil;
import com.jinkan.www.cpttest.util.VibratorUtil;
import com.jinkan.www.cpttest.util.bluetooth.BluetoothCommService;
import com.jinkan.www.cpttest.util.bluetooth.BluetoothUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Sampson on 2018/4/12.
 * LastCPT
 */
public class BaseTestViewModel extends BaseViewModel {
    public final ObservableField<String> obsProjectNumber = new ObservableField<>("");
    public final ObservableField<String> obsHoleNumber = new ObservableField<>("");
    public final ObservableField<String> obsProbeNumber = new ObservableField<>("");
    public final ObservableField<String> obsQcCoefficient = new ObservableField<>("");
    public final ObservableField<String> obsQcLimit = new ObservableField<>("");
    public final ObservableField<String> obsFsCoefficient = new ObservableField<>("");
    public final ObservableField<String> obsFsLimit = new ObservableField<>("");


    public final ObservableField<Float> obsQcInitialValue = new ObservableField<>(0f);
    public final ObservableField<Float> obsFsInitialValue = new ObservableField<>(0f);
    public final ObservableField<Float> obsQcEffectiveValue = new ObservableField<>(0f);
    public final ObservableField<Float> obsFsEffectiveValue = new ObservableField<>(0f);
    public final ObservableField<Float> obsFaEffectiveValue = new ObservableField<>(0f);
    public final ObservableField<Float> obsTestDeep = new ObservableField<>(0f);
    public final ObservableField<String> obsStringDeepDistance = new ObservableField<>("0.1");
    public final ObservableField<Boolean> obsIsShock = new ObservableField<>(false);
    public final MutableLiveData<String> action = new MutableLiveData<>();
    public final MutableLiveData<String> toast = new MutableLiveData<>();
    public final MediatorLiveData<List<ProbeEntity>> loadProbe = new MediatorLiveData<>();
    public final MutableLiveData<float[]> recordValue = new MutableLiveData<>();
    private boolean isIdentification;
    private String probeID;
    private TestEntity testModel;
    private TestDataDao testDataDao;
    private ProbeDao probeDao;
    private VibratorUtil vibratorUtil;
    private BluetoothUtil bluetoothUtil;
    private ISkip iSkip;
    private BluetoothCommService bluetoothCommService;

    public BaseTestViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void inject(Object... objects) {
        testDataDao = (TestDataDao) objects[0];
        probeDao = (ProbeDao) objects[1];
        vibratorUtil = (VibratorUtil) objects[2];
        bluetoothUtil = (BluetoothUtil) objects[3];
        bluetoothCommService = (BluetoothCommService) objects[4];
        iSkip = (ISkip) objects[5];
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void clear() {

    }

    public LiveData<List<TestEntity>> getTestParameters(TestDao testDao, String projectNumber, String holeNumber) {
        return testDao.getTestEntityByPrjNumberAndHoleNumber(projectNumber, holeNumber);
    }

    public void doRecord() {
        Float floatDeep = obsTestDeep.get();
        if (floatDeep != null) {
            if (StringUtil.isFloat(obsStringDeepDistance.get())) {
                obsTestDeep.set(floatDeep + Float.valueOf(obsStringDeepDistance.get()));
            } else {
                toast.setValue("测量间距不合法！");
                return;
            }
        }

        TestDataEntity testDataModel = new TestDataEntity();
        testDataModel.testDataID = testModel.projectNumber + "-" + testModel.holeNumber;
        testDataModel.probeID = probeID;
        Float aFloat = obsTestDeep.get();
        if (aFloat != null)
            testDataModel.deep = aFloat;
        Float qcEffectiveValue = obsQcEffectiveValue.get();
        if (qcEffectiveValue != null)
            testDataModel.qc = qcEffectiveValue;
        Float fsEffectiveValue = obsFsEffectiveValue.get();
        if (fsEffectiveValue != null)
            testDataModel.fs = fsEffectiveValue;
        Float faEffectiveValue = obsFaEffectiveValue.get();
        if (faEffectiveValue != null)
            testDataModel.fa = faEffectiveValue;
        testDataDao.insertTestDataEntity(testDataModel);
        Boolean aBoolean = obsIsShock.get();
        if (aBoolean != null)
            if (aBoolean) {
                vibratorUtil.Vibrate(200);
            }
        if (qcEffectiveValue != null && fsEffectiveValue != null && faEffectiveValue != null && aFloat != null)
            recordValue.setValue(new float[]{qcEffectiveValue, fsEffectiveValue, faEffectiveValue, aFloat});
//            myView.get().showRecordValue(qcEffectiveValue, fsEffectiveValue, faEffectiveValue, aFloat);
    }


    public LiveData<List<TestDataEntity>> loadTestData(String testDataID) {
        return testDataDao.getTestDataByTestId(testDataID);
    }


    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (myView != null) {
//                switch (msg.what) {
//                    case BluetoothCommService.MESSAGE_READ://读数据
//                        byte[] b = (byte[]) msg.obj;
//                        String mDate = new String(b);
//                        if (mDate.length() > 40) {
//                            if (mDate.contains("\r")) {
//                                mDate = mDate.substring(0, mDate.indexOf("\r"));
//                            }
//                            mDate = mDate.replace(" ", "");
//                            if (mDate.contains("Sn:")) {
//                                String sn = mDate.substring(mDate.indexOf("Sn:") + 3, mDate.indexOf("Sn:") + 11);
//                                identificationProbe(sn);
//                                Float qcInitialValue = obsQcInitialValue.get();
//                                if (qcInitialValue != null) {
//                                    obsQcEffectiveValue.set(getQcEffectiveValue(mDate, qcInitialValue));
//                                }
//                                Float fsInitialValue = obsFsInitialValue.get();
//                                if (fsInitialValue != null)
//                                    obsFsEffectiveValue.set(getFsEffectiveValue(mDate, fsInitialValue));
//                                obsFaEffectiveValue.set(getFaEffectiveValue(mDate));
//                            }
//                        }
//                        break;
//                    case BluetoothCommService.MESSAGE_TOAST://提示信息
//                        Bundle bundle = msg.getData();
//                        String s = bundle.getString(BluetoothCommService.TOAST);
//                        myView.get().showToast(s);
//                        break;
//                    case BluetoothCommService.MESSAGE_DEVICE_NAME:
//                        bundle = msg.getData();
//                        String string = bundle.getString(BluetoothCommService.DEVICE_NAME);
//                        myView.get().showToast(string);
//                        break;
//                    case BluetoothCommService.MESSAGE_STATE_CHANGE:
//                        if (msg.arg1 == BluetoothCommService.STATE_CONNECTED) {
//                            getView().closeWaitDialog();
//                            getView().showToast("连接成功");
//                        } else if (msg.arg1 == BluetoothCommService.STATE_CONNECT_FAILED) {
//                            getView().closeWaitDialog();
//                        }
//                        break;
//                }
//            }
//        }
//    };
//    private BluetoothCommService bluetoothCommService = new BluetoothCommService(mHandler);

    /**
     * 载入探头
     *
     * @param sn 探头序列号
     */
    private void identificationProbe(String sn) {
        if (!isIdentification) {
            isIdentification = true;
            probeID = sn;
            loadProbe.addSource(probeDao.getProbeByProbeId(sn), loadProbe::setValue);
        }

    }

    private float getQcEffectiveValue(String mDate, float qcInitialValue) {
        if (mDate.contains("Ps:") && mDate.contains("MPa")) {
            String qc = mDate.substring(mDate.indexOf("Ps:") + 3, mDate.indexOf("MPa"));
            if (StringUtil.isFloat(qc)) {
                return Float.parseFloat(qc) - qcInitialValue;
            } else {
                return 0;
            }

        } else if (mDate.contains("Qc:") && mDate.contains("MPa")) {
            String qc = mDate.substring(mDate.indexOf("Qc:") + 3, mDate.indexOf("MPa"));
            if (StringUtil.isFloat(qc)) {
                return Float.parseFloat(qc) - qcInitialValue;
            } else {
                return 0;
            }
        } else {
            return 0;
        }

    }

    private float getFsEffectiveValue(String mDate, float fsInitialValue) {
        if (mDate.contains("Fs:") && mDate.contains("kPa")) {
            String fs = mDate.substring(mDate.indexOf("Fs:") + 3, mDate.indexOf("kPa"));
            if (StringUtil.isFloat(fs)) {
                return Float.parseFloat(fs) - fsInitialValue;
            } else {
                return 0;
            }

        } else {
            return 0;
        }

    }

    private float getFaEffectiveValue(String mDate) {
        if (mDate.contains("Fa:") && mDate.contains("Dec")) {
            String fa = mDate.substring(mDate.indexOf("Fa:") + 3, mDate.indexOf("Dec"));
            if (StringUtil.isFloat(fa)) {
                return Float.parseFloat(fa);
            } else {
                return 0;
            }

        } else {
            return 0;
        }
    }

    public void doInitialValue() {
        obsQcInitialValue.set(obsQcEffectiveValue.get());
        obsFsInitialValue.set(obsFsEffectiveValue.get());
    }

    public void modifyDistance() {
        action.setValue("showModifyDialog");
//        getView().showModifyDialog(obsStringDeepDistance.get());
    }

    public void linkDevice(String mac) {
        action.setValue("showWaitDialog");
//        getView().showWaitDialog("正在连接蓝牙", false, false);
        BluetoothAdapter bluetoothAdapter = bluetoothUtil.getBluetoothAdapter();
        if (bluetoothAdapter.isEnabled()) {// 蓝牙已打开
            BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(mac);
            bluetoothCommService.connect(bluetoothDevice);
        } else {
            // 蓝牙没有打开，调用系统方法要求用户打开蓝牙
            action.setValue("startActivityForResult");
//            myView.get().startActivityForResult(intent, 0);
        }
    }

    private List mModels = new ArrayList();

    public LiveData<List<TestDataEntity>> saveTestDataToSD() {
        return testDataDao.getTestDataByTestId(testModel.projectNumber + "_" + testModel.holeNumber);

    }

    private String mFileType;

    public void emailTestData(String fileType, DataUtil dataUtil) {
        mFileType = fileType;
        sendEmail(dataUtil);
    }

    private void sendEmail(DataUtil dataUtil) {
        dataUtil.emailData(mModels, mFileType, testModel, iSkip);
    }

    public void setDistance(String distance) {
        obsStringDeepDistance.set(distance);
    }


}
