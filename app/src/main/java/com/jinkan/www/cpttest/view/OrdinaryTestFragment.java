package com.jinkan.www.cpttest.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.FragmentOrdinaryTestBinding;
import com.jinkan.www.cpttest.db.dao.TestDao;
import com.jinkan.www.cpttest.db.entity.TestEntity;
import com.jinkan.www.cpttest.di.ActivityScoped;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerFragment;
import com.jinkan.www.cpttest.view_model.OrdinaryTestViewModel;

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
            goTo(SingleBridgeTestActivityMVVM.class,
                    new String[]{testEntity.projectNumber, testEntity.holeNumber});
        });

        mViewModel.action.observe(this, s -> {
            if (s.endsWith("NewTest")) {
                goTo(NewTestActivity.class, null);
            }
        });

    }


}
