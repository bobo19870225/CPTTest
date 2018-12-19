/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivitySetEmailBinding;
import com.jinkan.www.cpttest.util.PreferencesUtil;
import com.jinkan.www.cpttest.util.StringUtil;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerActivity;
import com.jinkan.www.cpttest.view_model.SetEmailViewModel;

import java.util.Map;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;

/**
 * Created by lushengbo on 2017/8/9.
 * 设置邮箱界面
 */

public class SetEmailActivity extends BaseMVVMDaggerActivity<SetEmailViewModel, ActivitySetEmailBinding> {
    private TextInputLayout tt_email;
    private TextInputLayout tt_password;
    private TextInputLayout tt_receive_email;
    private EditText email;
    private EditText password;
    private EditText receive_email;
    private Button test;
    @Inject
    PreferencesUtil preferencesUtil;


    @Override
    protected void setMVVMView() {
        setToolBar("设置我的邮箱", R.menu.email);
        setEditText();
    }

    private void setEditText() {
        Map<String, String> emailPreferences = preferencesUtil.getEmailPreferences();
        email.setText(emailPreferences.get("sEmail"));
        password.setText(emailPreferences.get("sEmailPassword"));
        receive_email.setText(emailPreferences.get("rEmail"));
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tt_email.setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tt_password.setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        receive_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tt_receive_email.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int initView() {
        return R.layout.activity_set_email;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                String StrEmail = email.getText().toString();
                String StrPassword = password.getText().toString();
                String strReceiveEmail = receive_email.getText().toString();
                if (StringUtil.isEmail(StrEmail)) {
                    if (StringUtil.isEmpty(StrPassword)) {
                        tt_password.setError("密码不能为空");
                    } else {
                        if (StringUtil.isEmail(strReceiveEmail)) {
                            showToast("设置成功");
                            preferencesUtil.saveEmail(StrEmail, StrPassword, strReceiveEmail);
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            tt_receive_email.setError("联系人邮箱不能为空");
                        }

                    }
                } else {
                    tt_email.setError("邮箱地址有误");
                }
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.test:
//                MailSenderRunnable senderRunnable = new MailSenderRunnable(SetEmailActivity.this,
//                        "rain19870225@163.com", "e424291182");
//                senderRunnable.setMail("this is the test subject",
//                        "this is the test body", "rain19870225@163.com", Environment.getExternalStorageDirectory().getPath() + "/JKD10-4-020锥头标定.txt");
//                new Thread(senderRunnable).start();
//                break;
//        }
//    }


    @Override
    public SetEmailViewModel createdViewModel() {
        return ViewModelProviders.of(this).get(SetEmailViewModel.class);
    }
}
