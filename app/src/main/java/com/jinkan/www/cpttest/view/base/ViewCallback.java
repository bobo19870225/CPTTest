package com.jinkan.www.cpttest.view.base;

import com.jinkan.www.cpttest.util.CallbackMessage;

/**
 * Created by Sampson on 2018/12/28.
 * CPTTest
 */
public interface ViewCallback {
    void action(CallbackMessage callbackMessage);

    void toast(CallbackMessage callbackMessage);
}
