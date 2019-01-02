package com.jinkan.www.cpttest.view;

import android.Manifest;
import android.content.Intent;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityAddProbeBinding;
import com.jinkan.www.cpttest.db.dao.ProbeDaoHelper;
import com.jinkan.www.cpttest.util.CallbackMessage;
import com.jinkan.www.cpttest.util.acp.Acp;
import com.jinkan.www.cpttest.util.acp.AcpListener;
import com.jinkan.www.cpttest.util.acp.AcpOptions;
import com.jinkan.www.cpttest.util.qrcode.qrSimple.CaptureActivity;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerActivity;
import com.jinkan.www.cpttest.view_model.AddProbeVM;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;


/**
 * Created by Sampson on 2018/12/28.
 * CPTTest
 */
public class AddProbeActivity extends BaseMVVMDaggerActivity<AddProbeVM, ActivityAddProbeBinding> {
    private final int OK = 0;

    @Inject
    ProbeDaoHelper probeDaoHelper;
    @Override
    protected Object[] injectToViewModel() {
        return new Object[]{mData, probeDaoHelper};
    }

    @Override
    protected void setMVVMView() {
        setToolBar("添加探头");
    }

    @Override
    public int initView() {
        return R.layout.activity_add_probe;
    }

    @Override
    public AddProbeVM createdViewModel() {
        return ViewModelProviders.of(this).get(AddProbeVM.class);
    }

    @Override
    public void callback(CallbackMessage callbackMessage) {
        switch (callbackMessage.what) {
            case 0:
                Acp.getInstance(AddProbeActivity.this).request(new AcpOptions.Builder()
                                .setPermissions(Manifest.permission.CAMERA)
                                .build(),
                        new AcpListener() {
                            @Override
                            public void onGranted() {
                                Intent intent = new Intent(AddProbeActivity.this, CaptureActivity.class);
                                startActivityForResult(intent, OK);
                            }

                            @Override
                            public void onDenied(List<String> permissions) {
                                showToast(permissions.toString() + "权限拒绝");
                            }
                        });
                break;
            case 1:
                goTo(AddProbeInfoActivity.class, null);
                break;
            case 2:
                goTo(OrdinaryProbeActivity.class, null, true);
                break;
        }
    }
}
