package com.jinkan.www.cpttest.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.FragmentOrdinaryTestBinding;
import com.jinkan.www.cpttest.db.dao.TestDao;
import com.jinkan.www.cpttest.db.entity.TestEntity;
import com.jinkan.www.cpttest.di.ActivityScoped;
import com.jinkan.www.cpttest.util.PreferencesUtil;
import com.jinkan.www.cpttest.util.StringUtil;
import com.jinkan.www.cpttest.util.SystemConstant;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerFragment;
import com.jinkan.www.cpttest.view_model.OrdinaryTestViewModel;

import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Sampson on 2018/12/16.
 * CPTTest
 */
@ActivityScoped
public class OrdinaryTestFragment extends BaseMVVMDaggerFragment<OrdinaryTestViewModel, FragmentOrdinaryTestBinding> {
    @Inject
    public OrdinaryTestFragment() {
        // Requires empty public constructor
    }

    @Inject
    TestDao testDao;
    @Inject
    PreferencesUtil preferencesUtil;
    @Override
    protected Object[] injectToViewModel() {
        return new Object[0];
    }

    @Override
    protected int setLayOutId() {
        return R.layout.fragment_ordinary_test;
    }

    @Override
    public OrdinaryTestViewModel createdViewModel() {
        return ViewModelProviders.of(this).get(OrdinaryTestViewModel.class);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return null;
    }

    @Override
    protected void setView() {
        mViewModel.setTestDao(testDao);
        mViewModel.allTestes.observe(this, testEntities -> {
            TestEntity testEntity = testEntities.get(0);
            if (testEntity != null) {
                Map<String, String> linkerPreferences = preferencesUtil.getLinkerPreferences();
                String add = linkerPreferences.get("add");
                if (StringUtil.isEmpty(add)) {
                    goTo(LinkBluetoothActivity.class, new String[]{testEntity.projectNumber, testEntity.holeNumber, testEntity.testType});
                } else {//mac地址，工程编号，孔号，试验类型。
                    String[] strings = {add, testEntity.projectNumber, testEntity.holeNumber};
                    switch (testEntity.testType) {
                        case SystemConstant.SINGLE_BRIDGE_TEST:
                            goTo(SingleBridgeTestActivity.class, strings);
                            break;
                        case SystemConstant.SINGLE_BRIDGE_MULTI_TEST:
                            goTo(SingleBridgeMultifunctionTestActivity.class, strings);
                            break;
                        case SystemConstant.DOUBLE_BRIDGE_TEST:
                            goTo(DoubleBridgeTestActivity.class, strings);
                            break;
                        case SystemConstant.DOUBLE_BRIDGE_MULTI_TEST:
                            goTo(DoubleBridgeMultifunctionTestActivity.class, strings);
                            break;
                        case SystemConstant.VANE_TEST:
                            goTo(CrossTestActivity.class, strings);
                            break;
                    }

                }
            } else {
                showToast("暂无可进行二次测量的试验");
            }

        });

        mViewModel.action.observe(this, s -> {
            if (s.equals("NewTest")) {
                goTo(NewTestActivity.class, null);
            }
        });

    }


}
