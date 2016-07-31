package com.irf.profiles.model;

import com.irf.profiles.data.DaoMaster;
import com.irf.profiles.data.DaoSession;
import com.irf.profiles.data.ProfileDao;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ProfileUnitTest {
    @Test
    public void testToString() throws Exception {
        final String toString = "Profile{id=1, name='Profile', active=false}";

        Profile profile = new Profile();
        profile.setId(1);
        profile.setName("Profile");
        profile.setActive(false);
        assertEquals(toString, profile.toString());
    }

    @Test
    public void testNewProfile() throws Exception {
        Profile profile = new Profile();
        profile.setId(1);
        profile.setName("Profile");
        profile.setActive(false);


        Profile readProfile = new Profile();
        readProfile.setId(1);
        readProfile.refresh();

        assertEquals(profile.toString(), readProfile.toString());
    }
}