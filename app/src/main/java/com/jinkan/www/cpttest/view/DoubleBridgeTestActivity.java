package com.jinkan.www.cpttest.view;

import android.view.MenuItem;
import android.view.View;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.view.base.BaseTestActivity;
import com.jinkan.www.cpttest.view.chart.DoubleBridgeStrategy;

/**
 * Created by Sampson on 2018/12/21.
 * CPTTest
 */
public class DoubleBridgeTestActivity extends BaseTestActivity {
    @Override
    protected void setMVVMView() {
        setToolBar("双桥试验", R.menu.test);
        mViewDataBinding.rlFs.setVisibility(View.VISIBLE);
        mViewDataBinding.rlFsLimit.setVisibility(View.VISIBLE);
        mViewDataBinding.rlFa.setVisibility(View.GONE);
        drawChartHelper.setStrategy(new DoubleBridgeStrategy(this, mViewDataBinding.lineChart));
        super.setMVVMView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save://保存数据到sd卡
                showSaveDataDialog();
                return false;
            case R.id.email://发送邮件到指定邮箱
                showEmailDataDialog();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
