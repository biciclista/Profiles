package com.irf.profiles;

import android.app.Application;

import com.irf.profiles.data.DaoMaster;
import com.irf.profiles.data.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Application class for Profiles.
 */
public class App extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "profiles-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
