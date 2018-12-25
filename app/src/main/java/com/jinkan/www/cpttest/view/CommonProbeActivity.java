package com.jinkan.www.cpttest.view;

import com.jinkan.www.cpttest.view.base.ListMVVMActivity;

import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by Sampson on 2018/12/24.
 * CPTTest
 */
public class CommonProbeActivity extends ListMVVMActivity {
    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return null;
    }

    @Override
    protected void setViewWithOutListView() {

    }

    @Override
    protected void setListData(Object o) {

    }

    @Override
    protected Object[] injectToViewModel() {
        return new Object[0];
    }

    @Override
    public int initView() {
        return 0;
    }

    @Override
    public ViewModel createdViewModel() {
        return null;
    }
}
