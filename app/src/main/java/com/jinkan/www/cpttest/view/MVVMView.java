/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view;


import com.jinkan.www.cpttest.view_model.BaseViewModel;

import androidx.annotation.LayoutRes;
import androidx.databinding.ViewDataBinding;

/**
 * Created by bobo on 2017/3/12.
 * 接口
 */

public interface MVVMView<VM extends BaseViewModel, VDB extends ViewDataBinding> {
    VM createdViewModel();

    VDB setViewDataBinding(@LayoutRes int layOutId);
}
