package com.irf.profiles.model;

import com.irf.profiles.model.Profile;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ProfileUnitTest {
    @Test
    public void testToString() throws Exception {

        Profile profile = new Profile(1L, "Profile", true);
        String toString = "Profile{id=1, name='Profile', active=true}";

        assertEquals(toString, profile.toString());
    }
}