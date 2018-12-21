package com.jinkan.www.cpttest.view;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityLinkBluetoothBinding;
import com.jinkan.www.cpttest.util.bluetooth.BluetoothUtil;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerActivity;
import com.jinkan.www.cpttest.view_model.LinkBluetoothViewModel;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Sampson on 2018/12/21.
 * CPTTest
 */
public class LinkBluetoothActivity extends BaseMVVMDaggerActivity<LinkBluetoothViewModel, ActivityLinkBluetoothBinding> {
    @Inject
    BluetoothUtil bluetoothUtil;

    @Override
    protected Object[] injectToViewModel() {
        return new Object[]{mData, bluetoothUtil};
    }

    @Override
    protected void setMVVMView() {
        mViewModel.toast.observe(this, this::showToast);
    }

    @Override
    public LinkBluetoothViewModel createdViewModel() {
        return ViewModelProviders.of(this).get(LinkBluetoothViewModel.class);
    }

    @Override
    public int initView() {
        return R.layout.activity_link_bluetooth;
    }
}
