package com.irf.profiles.data;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.irf.profiles.App;
import com.irf.profiles.model.ConnectivityProperty;
import com.irf.profiles.model.Profile;
import com.irf.profiles.model.VolumeProperty;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by nacho on 2/08/16.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class DbTest {
    private static final String TAG = "DbTest";
    private DaoSession session;

    @Before
    public void prepare() {
        session = App.getInstance().getDaoSession();
    }

    @Test
    public void countProfiles() {
        ProfileDao profileDao = session.getProfileDao();
        List<Profile> list = profileDao.queryBuilder().where(ProfileDao.Properties.Id.gt(0L))
                .list();
        Log.i(TAG, "Profiles: " + list.size());
        for (Profile element : list) {
            Log.i(TAG, element.toString());

        }

    }

    @Test
    public void countVolumeProperties() {
        VolumePropertyDao volumePropertyDao = session.getVolumePropertyDao();
        List<VolumeProperty> list = volumePropertyDao.queryBuilder().where(VolumePropertyDao
                .Properties.Id.gt(0L)).list();
        Log.i(TAG, "Volume properties: " + list.size());
        for (VolumeProperty element : list) {
            Log.i(TAG, element.toString());
        }
    }

    @Test
    public void countConnectivityProperties() {
        ConnectivityPropertyDao dao = session.getConnectivityPropertyDao();
        List<ConnectivityProperty> list = dao.queryBuilder().where(ConnectivityPropertyDao
                .Properties.Id.gt(0L)).list();
        Log.i(TAG, "Connectivity properties: " + list.size());
        for (ConnectivityProperty element : list) {
            Log.i("ConnectivityProperty", element.toString());
        }
    }
}
