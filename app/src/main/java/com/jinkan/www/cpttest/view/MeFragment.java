/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.FragmentMeBinding;
import com.jinkan.www.cpttest.util.CallbackMessage;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerFragment;
import com.jinkan.www.cpttest.view_model.MeViewModel;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;


public class MeFragment extends BaseMVVMDaggerFragment<MeViewModel, FragmentMeBinding> {

    @Inject
    public MeFragment() {

    }

    @Override
    protected void setView() {
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "MyLinkerActivity":
                    goTo(MyLinkerActivity.class, "设置连接器");
                    break;
                case "SetEmailActivity":
                    goTo(SetEmailActivity.class, null);
                    break;
                case "VideoActivity":
                    goTo(VideoActivity.class, null);
                    break;
                case "VersionInfoActivity":
                    goTo(VersionInfoActivity.class, null);
                    break;
                case "MyMsgActivity":
                    goTo(MyMsgActivity.class, null);
                    break;
            }
        });
    }


    @Override
    protected Object[] injectToViewModel() {
        return new Object[0];
    }

    @Override
    protected int setLayOutId() {
        return R.layout.fragment_me;
    }

    @Override
    public MeViewModel createdViewModel() {
        return ViewModelProviders.of(this).get(MeViewModel.class);
    }

    @Override
    public void callback(CallbackMessage callbackMessage) {

    }
}
