package com.jinkan.www.cpttest.view;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityTimeSynchronizationBinding;
import com.jinkan.www.cpttest.view.base.DialogMVVMDaggerActivity;
import com.jinkan.www.cpttest.view_model.TimeSynchronizationVM;

import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Sampson on 2018/12/21.
 * CPTTest
 */
public class TimeSynchronizationActivity extends DialogMVVMDaggerActivity<TimeSynchronizationVM, ActivityTimeSynchronizationBinding> {
    @Override
    protected Object[] injectToViewModel() {
        return new Object[0];
    }

    @Override
    protected void setMVVMView() {

    }

    @Override
    public TimeSynchronizationVM createdViewModel() {
        return ViewModelProviders.of(this).get(TimeSynchronizationVM.class);
    }

    @Override
    public int initView() {
        return R.layout.activity_time_synchronization;
    }
}
