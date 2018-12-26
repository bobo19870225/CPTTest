package com.jinkan.www.cpttest.view.base;

import android.annotation.SuppressLint;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.view.adapter.MyBaseAdapter;
import com.jinkan.www.cpttest.view_model.base.BaseListViewModel;

import java.util.List;

import androidx.databinding.ViewDataBinding;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by Sampson on 2018/12/24.
 * CPTTest
 */
@SuppressLint("Registered")
public abstract class ListMVVMActivity<VM extends BaseListViewModel, VDB extends ViewDataBinding, A extends MyBaseAdapter> extends BaseMVVMDaggerActivity<VM, VDB> {
    protected A mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = this::loadListData;

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
        mAdapter = setAdapter();
        setViewWithOutListView();
    }

    protected abstract A setAdapter();

    protected abstract void setViewWithOutListView();

    /**
     * 停止加载数据，一般在加载结束时调用
     */
    public void stopLoading() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void toRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
            mSwipeRefreshLayout.setRefreshing(true);//不会出发onRefresh
            onRefreshListener.onRefresh();//强制刷新
        } else {
            loadListData();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadListData() {
        stopLoading();
        mViewModel.loadListViewData().observe(this, o -> {
            if (o == null) {
                mViewModel.isEmpty.setValue(true);
            } else {
                mViewModel.isEmpty.setValue(false);
                mAdapter.setList((List) o);
            }

        });
    }


}
