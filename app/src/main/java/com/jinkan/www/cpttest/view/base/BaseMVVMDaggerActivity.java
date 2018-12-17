/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view.base;

import android.content.Intent;
import android.os.Bundle;

import com.jinkan.www.cpttest.BR;
import com.jinkan.www.cpttest.view.MVVMView;
import com.jinkan.www.cpttest.view_model.BaseViewModel;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by lushengbo on 2018/1/12.
 * MVVM View基类
 */

public abstract class BaseMVVMDaggerActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends BaseDaggerActivity
        implements MVVMView<VM, VDB> {
    protected VM mViewModel;
    protected VDB mViewDataBinding;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected final void setView() {
        if (createdViewModel() == null) {
            throw new RuntimeException("ViewModel can't be null!");
        } else {
            mViewModel = createdViewModel();
            mViewDataBinding.setVariable(BR.model, mViewModel);
        }
        setMVVMView();
    }

    protected abstract void setMVVMView();

    @Override
    public VDB setViewDataBinding(int layOutId) {
        VDB viewDataBinding = DataBindingUtil.setContentView(this, layOutId);
//        viewDataBinding.setVariable(BR.model, mViewModel);
        viewDataBinding.setLifecycleOwner(this);
        return viewDataBinding;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.clear();
//        mViewModel.detachView();
    }

    @Override
    protected void init(int viewId) {
        mViewDataBinding = setViewDataBinding(viewId);
//         ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory()).get(NewTestViewModel.class);
        mRootView = mViewDataBinding.getRoot();
        mFragmentManager = getSupportFragmentManager();
    }


    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode 请求码
     * @param resultCode  应答码
     * @param data        数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }
}
