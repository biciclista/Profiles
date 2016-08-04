package com.irf.profiles.model;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.irf.profiles.App;
import com.irf.profiles.data.ProfileDao;

import java.util.List;
import java.util.Map;

/**
 * Main class for managing profiles in the device. It allows to add, modify, delete and apply
 * profiles.
 */
public class ProfileManager {
    // TAG for logging.
    private static final String TAG = "ProfileManager";

    // Instance of profile manager.
    private static ProfileManager manager = null;
    // Profile list.
    private Map<String, Profile> profileList = null;
    // Map profile identifiers to names.
    private Map<Long, String> profileNames = null;
    // Selected profile.
    private Profile selectedProfile = null;

    /**
     * Returns the unique instance of ProfileManager.
     */
    public static ProfileManager getInstance() {
        if (manager == null) {
            manager = new ProfileManager();
        }

        return manager;
    }

    /**
     * Loads the existing profiles.
     */
    private void loadProfiles() {
        profileList = new ArrayMap<>();
        profileNames = new ArrayMap<>();

        ProfileDao profileDao = App.getInstance().getDaoSession().getProfileDao();
        List<Profile> profileList = profileDao.loadAll();

        for (Profile profile : profileList) {
            Log.d(TAG, "Loading profile " + profile);
            this.profileList.put(profile.getName(), profile);
            profileNames.put(profile.getId(), profile.getName());
            if (profile.getActive()) {
                selectedProfile = profile;
                Log.d(TAG, "Active profile: " + profile);
            }
        }
    }

    /**
     * Gets the list of existing profiles.
     *
     * @return Array with the existing profiles.
     */
    public Profile[] getProfileList() {
        if (profileList == null) {
            loadProfiles();
        }

        Log.d(TAG, "Actual profile list = " + profileList);
        return profileList.values().toArray(new Profile[profileList.size()]);
    }

    /**
     * Adds a new profile.
     *
     * @param name Name of the new profile.
     * @return New created profile.
     */
    public Profile addProfile(String name) {
        Profile profile = null;
        if (!existsProfile(name)) {
            // Create new profile.
            profile = new Profile(name);
            // Add profile to list.
            profileList.put(name, profile);
            profileNames.put(profile.getId(), name);
        } else {
            // Duplicated name.
            throw new IllegalArgumentException("Duplicated profile name: " + name);
        }

        return profile;
    }

    /**
     * Checks if exist a profile with the given name.
     *
     * @param name Name of the profile to check.
     * @return True if exists a profile with the given name and false otherwise.
     */
    public boolean existsProfile(String name) {
        return profileList.containsKey(name);
    }

    /**
     * Gets a profile given its identifier.
     *
     * @param id Identifier of the profile to get.
     * @return Profile with given identifier or null if a profile doesn't exist with that
     * identifier.
     */
    public Profile getProfile(long id) {
        return profileList.get(profileNames.get(id));
    }
}
