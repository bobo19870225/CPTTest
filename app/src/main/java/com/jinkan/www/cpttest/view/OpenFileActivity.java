/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

import com.jinkan.www.cpttest.R;
import com.jinkan.www.cpttest.databinding.ActivityOpenFileBinding;
import com.jinkan.www.cpttest.util.CallbackMessage;
import com.jinkan.www.cpttest.view.adapter.BaseItemCallback;
import com.jinkan.www.cpttest.view.adapter.ItemFile;
import com.jinkan.www.cpttest.view.adapter.OpenFileAdapter;
import com.jinkan.www.cpttest.view.base.ListMVVMActivity;
import com.jinkan.www.cpttest.view_model.OpenFileViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by lushengbo on 2018/1/17.
 * 打开文件
 */

public class OpenFileActivity extends ListMVVMActivity<OpenFileViewModel, ActivityOpenFileBinding, OpenFileAdapter> {
    public static String EXTRA_FILE_DATES = "file_dates";

    @Override
    public OpenFileViewModel createdViewModel() {
        return ViewModelProviders.of(this).get(OpenFileViewModel.class);
    }


    @Override
    protected void setViewWithOutListView() {
        setToolBar("打开标记文件");
    }


    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.srl;
    }

    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.listView;
    }

    @Override
    protected OpenFileAdapter setAdapter() {
        return new OpenFileAdapter(R.layout.item_file, (BaseItemCallback<ItemFile>) itemData -> {
            File file = itemData.getFile();
            String[] str = new String[2];
            str[0] = file.getName();
            ByteArrayOutputStream byteArrayOutputStream = readFile(file);
            if (byteArrayOutputStream != null) {
                str[1] = byteArrayOutputStream.toString();
                Intent intent = new Intent();
                intent.putExtra(EXTRA_FILE_DATES, str);
                // Set result and finish this Activity
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public int initView() {
        return R.layout.activity_open_file;
    }

    /**
     * 读文件
     */
    public static ByteArrayOutputStream readFile(final File file) {
        try {
            File readFile = new File(file.getPath());
            if (!readFile.exists()) {
                return null;
            }
            FileInputStream inStream = new FileInputStream(readFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inStream.read(buffer)) != -1) {
                stream.write(buffer, 0, length);
            }
            /*
             * stream.write(buffer, 0, 64); str = stream.toString();
             */
            stream.close();
            inStream.close();
            return stream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (parentFile != null && !parentFile.getName().equals("")
//                    && !parentFile.getName().equals("mnt")) {
//                listFiles = parentFile.listFiles(new MyFileFilter());
//                listFiles = FileUtil.sort(listFiles);
//                mViewModel.getAdapter().update(listFiles);
//                // parentFile == mnt
//                parentFile = parentFile.getParentFile();
//                // 更新路径信息
//                // SDCard/Android/data/com.itcast.explorer
//                String path = mViewModel.pathInfo.get();
//                int indexOf = path.lastIndexOf("/");
//                path = path.substring(0, indexOf);
//                mViewModel.pathInfo.set(path);
//
//            } else {
//                finish();
//            }
//        }

        return true;
    }


    @Override
    protected Object[] injectToViewModel() {
        return new Object[0];
    }

    @Override
    public void callback(CallbackMessage callbackMessage) {

    }
}
