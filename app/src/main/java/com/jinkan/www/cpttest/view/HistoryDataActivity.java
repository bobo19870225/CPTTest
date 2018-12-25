/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityHistoryDataBinding;
import com.jinkan.www.cpttest.db.dao.TestDao;
import com.jinkan.www.cpttest.db.entity.TestEntity;
import com.jinkan.www.cpttest.view.adapter.HistoryDataAdapter;
import com.jinkan.www.cpttest.view.adapter.ItemHistoryData;
import com.jinkan.www.cpttest.view.adapter.ItemHistoryDataClickCallback;
import com.jinkan.www.cpttest.view.base.ListMVVMActivity;
import com.jinkan.www.cpttest.view_model.HistoryDataViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class HistoryDataActivity extends ListMVVMActivity<HistoryDataViewModel, ActivityHistoryDataBinding> {
    //    @BindView(id = R.id.lv_history)
//    private ListView lv_history;
//    @BindView(id = R.id.empty)
//    private TextView empty;
    @Inject
    TestDao testDao;
    private List<TestEntity> mTestEntitys;
    private TestEntity testModel;


    @SuppressWarnings("unchecked")
    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.srl;
    }

    @Override
    protected void setListData(Object o) {
        HistoryDataAdapter historyDataAdapter = new HistoryDataAdapter(R.layout.item_history_data, (ItemHistoryDataClickCallback) itemHistoryData -> {

        });
        mViewDataBinding.listView.setAdapter(historyDataAdapter);
        if (o == null) {
            mViewModel.isEmpty.setValue(true);
        } else {
            mViewModel.isEmpty.setValue(false);
            historyDataAdapter.setList((List) o);
        }

    }


    @Override
    public int initView() {
        return R.layout.activity_history_data;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "删除");
        menu.add(0, 0, 1, "取消");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                if (testModel != null) {
                    mViewModel.deleteOneHistoryData(testModel);
                }
                break;
            case 1:

                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected Object[] injectToViewModel() {
        return new Object[]{mData, testDao};
    }


    @Override
    protected void setViewWithOutListView() {

    }

    @Override
    public HistoryDataViewModel createdViewModel() {
        return ViewModelProviders.of(this).get(HistoryDataViewModel.class);
    }


//    @Override
//    public void showHistoryData(List<TestEntity> testModels) {
//        mTestEntitys.clear();
//        if (testModels != null) {
//            mTestEntitys.addAll(testModels);
//        }
//        listHistoryAdapter.notifyDataSetChanged();
//    }
}
