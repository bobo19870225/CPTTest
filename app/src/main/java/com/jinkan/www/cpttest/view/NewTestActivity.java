package com.jinkan.www.cpttest.view;

import android.app.ActionBar;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityNewTestBinding;
import com.jinkan.www.cpttest.db.dao.TestDaoHelper;
import com.jinkan.www.cpttest.util.CallbackMessage;
import com.jinkan.www.cpttest.util.PreferencesUtil;
import com.jinkan.www.cpttest.util.SystemConstant;
import com.jinkan.www.cpttest.view.adapter.OneTextListAdapter;
import com.jinkan.www.cpttest.view.base.BaseMVVMDaggerActivity;
import com.jinkan.www.cpttest.view_model.new_test.NewTestViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.jinkan.www.cpttest.util.SystemConstant.DOUBLE_BRIDGE_MULTI_TEST;
import static com.jinkan.www.cpttest.util.SystemConstant.DOUBLE_BRIDGE_TEST;
import static com.jinkan.www.cpttest.util.SystemConstant.SINGLE_BRIDGE_MULTI_TEST;
import static com.jinkan.www.cpttest.util.SystemConstant.SINGLE_BRIDGE_TEST;
import static com.jinkan.www.cpttest.util.SystemConstant.VANE_TEST;
import static com.jinkan.www.cpttest.view_model.new_test.NewTestViewModel.ACTION_LINK_BLUETOOTH;
import static com.jinkan.www.cpttest.view_model.new_test.NewTestViewModel.ACTION_SINGLE_BRIDGE;

public class NewTestActivity extends BaseMVVMDaggerActivity<NewTestViewModel, ActivityNewTestBinding> {

    private boolean isWireless;

    @Inject
    NewTestViewModel newTestViewModel;
    @Inject
    TestDaoHelper testDaoHelper;
    @Inject
    PreferencesUtil preferencesUtil;


    @Override
    protected Object[] injectToViewModel() {
        return new Object[]{mData, testDaoHelper, preferencesUtil};
    }

    @Override
    protected void setMVVMView() {
        setToolBar("新增试验");
        if (mData != null) {
            if (mData.equals("无缆试验"))
                isWireless = true;
        }
        mViewDataBinding.choseType.setOnClickListener(view -> showTestType());


    }

    private PopupWindow popupWindow;

    private void showTestType() {
        View contentView = getLayoutInflater().inflate(R.layout.theo, null);
        popupWindow = new PopupWindow(contentView);
        popupWindow.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 点击消失属性
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        if (isWireless) {
            String[] s = {SystemConstant.SINGLE_BRIDGE_MULTI_WIRELESS_TEST, SystemConstant.DOUBLE_BRIDGE_MULTI_WIRELESS_TEST};
            List<String> ls = new ArrayList<>();
            ls.add(s[0]);
            ls.add(s[1]);
            OneTextListAdapter adapter = new OneTextListAdapter(this, R.layout.listitem, ls);
            ListView listView = contentView.findViewById(R.id.lv_item);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {

                TextView tv_item = view.findViewById(R.id.TextView);
                mViewModel.setTypeText((String) tv_item.getText());
//                test_type.setText(tv_item.getText());
                popupWindow.dismiss();
            });
        } else {
            String[] s = {SINGLE_BRIDGE_TEST, SINGLE_BRIDGE_MULTI_TEST, DOUBLE_BRIDGE_TEST, DOUBLE_BRIDGE_MULTI_TEST, VANE_TEST};
            List<String> ls = new ArrayList<>();
            ls.add(s[0]);
            ls.add(s[1]);
            ls.add(s[2]);
            ls.add(s[3]);
            ls.add(s[4]);
            OneTextListAdapter adapter = new OneTextListAdapter(this, R.layout.listitem, ls);
            ListView listView = contentView.findViewById(R.id.lv_item);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {

                TextView tv_item = view.findViewById(R.id.TextView);
                mViewModel.setTypeText((String) tv_item.getText());
//                test_type.setText(tv_item.getText());
                popupWindow.dismiss();
            });
        }
        contentView.findViewById(R.id.cancel).setOnClickListener(view -> popupWindow.dismiss());
        // 显示在屏幕上
        popupWindow.showAtLocation(mViewDataBinding.choseType, Gravity.CENTER, 0, 0);

    }

    @Override
    public int initView() {
        return R.layout.activity_new_test;
    }

    @Override
    public NewTestViewModel createdViewModel() {
        return newTestViewModel;
//        return ViewModelProviders.of(this).get(NewTestViewModel.class);
    }

    @Override
    public void action(CallbackMessage callbackMessage) {

        switch (callbackMessage.what) {
            case ACTION_LINK_BLUETOOTH:
                goTo(LinkBluetoothActivity.class, callbackMessage.obj);
                break;
            case ACTION_SINGLE_BRIDGE:
                goTo(SingleBridgeTestActivity.class, callbackMessage.obj);
                break;
        }

    }
}
