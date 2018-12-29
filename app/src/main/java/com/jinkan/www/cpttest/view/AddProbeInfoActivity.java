/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityAddProbeInfoBinding;
import com.jinkan.www.cpttest.db.dao.ProbeDao;
import com.jinkan.www.cpttest.db.entity.ProbeEntity;
import com.jinkan.www.cpttest.util.CallbackMessage;
import com.jinkan.www.cpttest.view.adapter.OneTextListAdapter;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerActivity;
import com.jinkan.www.cpttest.view_model.AddProbeInfoVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;

public class AddProbeInfoActivity extends BaseMVVMDaggerActivity<AddProbeInfoVM, ActivityAddProbeInfoBinding> {

    @Inject
    SingleBridgeFragment singleBridgeFragment;
    @Inject
    DoubleBridgeFragment doubleBridgeFragment;
    @Inject
    CrossFragment crossFragment;
    @Inject
    ProbeDao probeDao;


    @Override
    protected Object[] injectToViewModel() {
        return new Object[]{mData, probeDao};
    }

    @Override
    protected void setMVVMView() {
        mViewModel.titleProbeType.setValue("选择探头类型");
        if (mData instanceof ProbeEntity) {
            setToolBar("编辑探头参数");
            ProbeEntity mProbeModel = (ProbeEntity) mData;
            mViewModel.titleProbeType.setValue("探头类型：");
            String type = mProbeModel.type;
            mViewModel.probeType.setValue(type);
            mViewModel.sn.setValue(mProbeModel.sn);
            mViewModel.number.setValue(mProbeModel.number);
            setMyFragment(type, new String[]{mProbeModel.qc_area,
                    String.valueOf(mProbeModel.qc_coefficient),
                    String.valueOf(mProbeModel.qc_limit),
                    mProbeModel.fs_area,
                    String.valueOf(mProbeModel.fs_coefficient),
                    String.valueOf(mProbeModel.fs_limit)});
        } else {
            setToolBar("填写探头参数");
        }

    }

    @Override
    public int initView() {
        return R.layout.activity_add_probe_info;
    }

    private void showChoseTypeWindow() {
        View v = getLayoutInflater().inflate(R.layout.theo, null);
        final PopupWindow popupWindow = new PopupWindow(v);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        ListView lv_list = v.findViewById(R.id.lv_item);
        List<String> list = new ArrayList<>();
        list.add("单桥");
        list.add("单桥测斜");
        list.add("双桥");
        list.add("双桥测斜");
        list.add("十字板");
        OneTextListAdapter adapter = new OneTextListAdapter(AddProbeInfoActivity.this, R.layout.listitem, list);
        lv_list.setAdapter(adapter);
        lv_list.setOnItemClickListener((parent, view, position, id) -> {
            TextView t = view.findViewById(R.id.TextView);
            mViewModel.titleProbeType.setValue("探头类型：");
            String type = t.getText().toString();
            mViewModel.probeType.setValue(type);
            setMyFragment(type);
            popupWindow.dismiss();

        });
        popupWindow.showAtLocation(mViewDataBinding.add, Gravity.CENTER, 0, 0);

    }

    private void setMyFragment(String type) {
        switch (type) {
            case "单桥":
            case "单桥测斜":
                setFragment(R.id.change, new SingleBridgeFragment());
                break;
            case "双桥":
            case "双桥测斜":
                setFragment(R.id.change, new DoubleBridgeFragment());
                break;
            default:
                setFragment(R.id.change, new CrossFragment());
                break;
        }
    }

    private void setMyFragment(String type, String[] strings) {
        switch (type) {
            case "单桥":
            case "单桥测斜":
                setFragment(R.id.change, singleBridgeFragment, strings);
                break;
            case "双桥":
            case "双桥测斜":
                setFragment(R.id.change, doubleBridgeFragment, strings);
                break;
            default:
                setFragment(R.id.change, crossFragment, strings);
                break;
        }
    }


    @Override
    public AddProbeInfoVM createdViewModel() {
        return ViewModelProviders.of(this).get(AddProbeInfoVM.class);
    }

    @Override
    public void callback(CallbackMessage callbackMessage) {
        switch (callbackMessage.what) {
            case 0:
                showChoseTypeWindow();
                break;
            case 1:
                goTo(OrdinaryProbeActivity.class, null, true);
                break;
        }
    }
}
