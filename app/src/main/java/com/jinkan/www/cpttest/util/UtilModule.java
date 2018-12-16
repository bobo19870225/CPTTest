package com.jinkan.www.cpttest.util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sampson on 2018/12/16.
 * CPTTest
 */
@Module
public abstract class UtilModule {


    @Singleton
    @Provides
    static StringUtil provideStringUtil() {
        return new StringUtil();
    }

}
