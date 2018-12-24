package com.jinkan.www.cpttest.view;

import android.view.MenuItem;
import android.view.View;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.view.base.BaseTestActivity;
import com.jinkan.www.cpttest.view.chart.SingleBridgeMuStrategy;

import static com.jinkan.www.cpttest.util.SystemConstant.EMAIL_TYPE_CORRECT_TXT;
import static com.jinkan.www.cpttest.util.SystemConstant.EMAIL_TYPE_HN_111;
import static com.jinkan.www.cpttest.util.SystemConstant.EMAIL_TYPE_LY_DAT;
import static com.jinkan.www.cpttest.util.SystemConstant.EMAIL_TYPE_LY_TXT;
import static com.jinkan.www.cpttest.util.SystemConstant.EMAIL_TYPE_LZ_TXT;
import static com.jinkan.www.cpttest.util.SystemConstant.SAVE_TYPE_CORRECT_TXT;
import static com.jinkan.www.cpttest.util.SystemConstant.SAVE_TYPE_HN_111;
import static com.jinkan.www.cpttest.util.SystemConstant.SAVE_TYPE_LY_DAT;
import static com.jinkan.www.cpttest.util.SystemConstant.SAVE_TYPE_LY_TXT;
import static com.jinkan.www.cpttest.util.SystemConstant.SAVE_TYPE_LZ_TXT;
import static com.jinkan.www.cpttest.util.SystemConstant.SINGLE_BRIDGE_MULTI_TEST;

/**
 * Created by Sampson on 2018/12/21.
 * CPTTest
 */
public class SingleBridgeMultifunctionTestActivity extends BaseTestActivity {
    @Override
    protected void setMVVMView() {
        setToolBar(SINGLE_BRIDGE_MULTI_TEST, R.menu.test);
        mViewDataBinding.rlFs.setVisibility(View.GONE);
        mViewDataBinding.rlFsLimit.setVisibility(View.GONE);
        mViewDataBinding.rlFa.setVisibility(View.VISIBLE);
        drawChartHelper.setStrategy(new SingleBridgeMuStrategy(this, mViewDataBinding.lineChart));
        super.setMVVMView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save://保存数据到sd卡
                saveItems = new String[]{SAVE_TYPE_LY_TXT,
                        SAVE_TYPE_LY_DAT,
                        SAVE_TYPE_HN_111,
                        SAVE_TYPE_LZ_TXT,
                        SAVE_TYPE_CORRECT_TXT};
                showSaveDataDialog();
                return false;
            case R.id.email://发送邮件到指定邮箱
                emailItems = new String[]{EMAIL_TYPE_LY_TXT,
                        EMAIL_TYPE_LY_DAT,
                        EMAIL_TYPE_HN_111,
                        EMAIL_TYPE_LZ_TXT,
                        EMAIL_TYPE_CORRECT_TXT};
                showEmailDataDialog();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
