package com.jinkan.www.cpttest.view.base;

import android.annotation.SuppressLint;

import com.jinkan.www.cpttest.view.adapter.MyBaseAdapter;
import com.jinkan.www.cpttest.view_model.base.BaseListViewModel;

import androidx.databinding.ViewDataBinding;

/**
 * Created by Sampson on 2018/12/24.
 * CPTTest
 */
@SuppressLint("Registered")
public abstract class ListMVVMActivity<VM extends BaseListViewModel, VDB extends ViewDataBinding> extends BaseMVVMDaggerActivity<VM, VDB> {

    protected MyBaseAdapter myBaseAdapter;

    @Override
    protected void setMVVMView() {
        mViewDataBinding.listView.setAdapter();
    }


}
