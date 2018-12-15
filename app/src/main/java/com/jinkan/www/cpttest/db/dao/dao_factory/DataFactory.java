
/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.cpttest.db.dao.dao_factory;

import android.content.Context;

import java.lang.reflect.Constructor;

/**
 * Created by lushengbo on 2018/1/3.
 * 数据工厂
 */
@SuppressWarnings("unchecked")
public class DataFactory {
    public static <T extends BaseDao> T getBaseData(Class<T> clz, Context context) {
        BaseDao baseDao = null;
        try {
            Class<?> aClass = Class.forName(clz.getName());
            Constructor constructor = aClass.getConstructor(Context.class);
            baseDao = (BaseDao) constructor.newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }

}
