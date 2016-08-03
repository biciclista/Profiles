package com.irf.profiles;

import android.app.Application;
import android.content.Context;

import com.irf.profiles.data.DaoMaster;
import com.irf.profiles.data.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Application class for Profiles.
 */
public class App extends Application {
    private static App instance;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        // App instance.
        instance = this;
        // Create DAO session.
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "profiles-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }

    public static App getInstance() {
        return instance;
    }
}
