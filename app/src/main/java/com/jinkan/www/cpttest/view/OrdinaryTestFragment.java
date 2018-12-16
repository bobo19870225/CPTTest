package com.jinkan.www.cpttest.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.FragmentOrdinaryTestBinding;
import com.jinkan.www.cpttest.di.ActivityScoped;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerFragment;
import com.jinkan.www.cpttest.view_model.OrdinaryTestViewModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;

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

    @Override
    protected int setLayOutId() {
        return R.layout.fragment_ordinary_test;
    }

    @Override
    public OrdinaryTestViewModel createdViewModel() {
        return new OrdinaryTestViewModel();
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return null;
    }

    @Override
    protected void setView() {

    }

    @Override
    public void onClick(View view) {

    }
}
