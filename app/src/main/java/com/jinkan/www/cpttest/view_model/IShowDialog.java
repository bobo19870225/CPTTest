/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.view_model;

/**
 * Created by lushengbo on 2018/1/19.
 * 等待对话框
 */

public interface IShowDialog {
    void showWaitDialog(String msg, boolean isTransBg, boolean isCancelable);

    void closeWaitDialog();
}
