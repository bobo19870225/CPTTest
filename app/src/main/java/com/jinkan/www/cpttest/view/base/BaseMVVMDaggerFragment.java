package com.jinkan.www.cpttest.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinkan.www.cpttest.BR;
import com.jinkan.www.cpttest.view.MVVMView;
import com.jinkan.www.cpttest.view_model.BaseViewModel;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by Sampson on 2018/12/16.
 * CPTTest
 */
public abstract class BaseMVVMDaggerFragment<VM extends BaseViewModel, VDB extends ViewDataBinding> extends BaseDaggerFragment
        implements MVVMView<VM, VDB> {
    protected VM mViewModel;
    protected VDB mViewDataBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int viewId = setLayOutId();
        mViewDataBinding = DataBindingUtil.inflate(inflater, viewId, container, false);
        mViewModel = createdViewModel();
        if (mViewModel == null)
            throw new RuntimeException("ViewModel can't be null!");
        mViewDataBinding.setVariable(BR.model, mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
//         ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory()).get(NewTestViewModel.class);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public VDB setViewDataBinding(int layOutId) {
        return mViewDataBinding;
    }

    protected abstract @LayoutRes
    int setLayOutId();


}
