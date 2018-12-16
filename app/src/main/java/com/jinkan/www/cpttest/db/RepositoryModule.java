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

//    @Singleton
//    @Binds
//    @Local
//    abstract TasksDataSource provideTasksLocalDataSource(TasksLocalDataSource dataSource);

//    @Singleton
//    @Binds
//    @Remote
//    abstract TasksDataSource provideTasksRemoteDataSource(FakeTasksRemoteDataSource dataSource);

    @Singleton
    @Provides
    static AppDatabase provideDb(Application context) {
        return AppDatabase.getInstance(context);
    }

    @Singleton
    @Provides
    static TestDao provideTestDao(AppDatabase db) {
        return db.testDao();
    }

//    @Singleton
//    @Provides
//    static TestDaoHelper provideTestDaoHelper(TestDao testDao) {
//        return new TestDaoHelper(testDao);
//    }
//    @Singleton
//    @Provides
//    static AppExecutors provideAppExecutors() {
//        return new AppExecutors(new DiskIOThreadExecutor(),
//                Executors.newFixedThreadPool(THREAD_COUNT),
//                new AppExecutors.MainThreadExecutor());
//    }
}
