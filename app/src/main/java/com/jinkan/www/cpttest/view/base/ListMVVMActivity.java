package com.jinkan.www.cpttest.view.base;

import android.annotation.SuppressLint;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.view_model.base.BaseListViewModel;

import androidx.databinding.ViewDataBinding;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by Sampson on 2018/12/24.
 * CPTTest
 */
@SuppressLint("Registered")
public abstract class ListMVVMActivity<VM extends BaseListViewModel, VDB extends ViewDataBinding> extends BaseMVVMDaggerActivity<VM, VDB> {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> mViewModel.loadListViewData().observe(this, this::setListData);

    /**
     * 设置刷新控件
     *
     * @param <SRL> 刷新控件
     * @return 刷新控件
     */
    protected abstract <SRL extends SwipeRefreshLayout> SRL setSwipeRefreshLayout();

    @Override
    protected final void setMVVMView() {
        mSwipeRefreshLayout = setSwipeRefreshLayout();
        if (mSwipeRefreshLayout != null) {
            // 设置下拉进度的主题颜色
            mSwipeRefreshLayout.setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_blue_bright,
                    R.color.colorPrimaryDark,
                    android.R.color.holo_orange_dark,
                    android.R.color.holo_red_dark,
                    android.R.color.holo_purple);
        }
        setViewWithOutListView();
    }

    protected abstract void setViewWithOutListView();

    @Override
    protected void toRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
            mSwipeRefreshLayout.setRefreshing(true);//不会出发onRefresh
            onRefreshListener.onRefresh();//强制刷新
        } else {
            mViewModel.loadListViewData().observe(this, this::setListData);
        }
    }

    protected abstract void setListData(Object o);


}
