package com.jinkan.www.cpttest.db;

import android.app.Application;

import com.jinkan.www.cpttest.db.dao.TestDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the {@link }.
 */
@Module
abstract public class RepositoryModule {

    private static final int THREAD_COUNT = 3;


    @Singleton
    @Provides
    static AppDatabase provideDb(Application context) {
        return AppDatabase.getInstance(context);
    }

    @Singleton
    @Provides
    static TestDao provideTasksDao(AppDatabase db) {
        return db.testDao();
    }


}
