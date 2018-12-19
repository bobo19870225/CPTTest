/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.util.acp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

/**
 * Created by hupei on 2016/4/26.
 */
class AcpService {
    private static final String TAG = "AcpService";

    /**
     * 检查权限授权状态
     *
     * @param context
     * @param permission
     * @return
     */
    int checkSelfPermission(Context context, String permission) {
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            int targetSdkVersion = info.applicationInfo.targetSdkVersion;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (targetSdkVersion >= Build.VERSION_CODES.M) {
                    Log.i(TAG, "targetSdkVersion >= Build.VERSION_CODES.M");
                    return ContextCompat.checkSelfPermission(context, permission);
                } else {
                    return PermissionChecker.checkSelfPermission(context, permission);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ContextCompat.checkSelfPermission(context, permission);
    }

    /**
     * 向系统请求权限
     *
     * @param activity
     * @param permissions
     * @param requestCode
     */
    void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /**
     * 检查权限是否存在拒绝不再提示
     *
     * @param activity
     * @param permission
     * @return
     */
    boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        boolean shouldShowRational = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
        Log.i(TAG, "shouldShowRational = " + shouldShowRational);
        return shouldShowRational;
    }
}
