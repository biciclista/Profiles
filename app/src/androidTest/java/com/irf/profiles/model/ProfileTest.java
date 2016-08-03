package com.irf.profiles.model;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.irf.profiles.App;
import com.irf.profiles.data.ProfileDao;
import com.irf.profiles.data.VolumePropertyDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by nacho on 2/08/16.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ProfileTest {

    private final String TEST_PROFILE = "Test Profile";

    @Before
    public void deleteProfile() {
        ProfileDao dao = App.getInstance().getDaoSession().getProfileDao();
        Profile profile = dao.queryBuilder().where(ProfileDao.Properties.Name.eq(TEST_PROFILE))
                .unique();

        if (profile != null) {
            List<VolumeProperty> volumeList = profile.getVolumeProperties();
            for (VolumeProperty volume : volumeList) {
                volume.delete();
            }
            profile.resetVolumeProperties();

            List<ConnectivityProperty> connectivityList = profile.getConnectivityProperties();
            for (ConnectivityProperty connectivity : connectivityList) {
                connectivity.delete();
            }
            profile.resetConnectivityProperties();

            profile.delete();
        }
    }

    @Test
    public void propertyCreation() {
        Profile profile = new Profile(TEST_PROFILE);

        profile.resetVolumeProperties();
        List<VolumeProperty> volumeProperties = profile.getVolumeProperties();
        assertEquals(4, volumeProperties.size());

        profile.resetConnectivityProperties();
        List<ConnectivityProperty> connectivityProperties = profile.getConnectivityProperties();
        assertEquals(4, connectivityProperties.size());
    }

}
