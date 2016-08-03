package com.irf.profiles.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.irf.profiles.R;
import com.irf.profiles.model.Profile;
import com.irf.profiles.model.ProfileManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileListFragment extends ListFragment {
    // TAG for logging.
    private final static String TAG = "ProfileListFragment";
    // Constants for stored data.
    private final static String PROFILE_LIST = "ProfileList";
    private final static String IS_IN_ACTION_MODE = "IsInActionMode";
    public final static String PROFILE_ID = "ProfileId";

    // Profile manager.
    private ProfileManager mProfileManager;
    // Profile list.
    private ArrayList<Profile> mProfileList;
    // Adapter.
    private ProfileListAdapter mAdapter;
    // Action mode.
    private ActionMode mActionMode = null;
    private boolean isInActionMode = false;

    public ProfileListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);
        mProfileManager = ProfileManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get existing profiles.
        if (savedInstanceState == null) {
            // Get profiles.
            Log.d(TAG, "Getting task list from storage");
            //mProfileList = new ArrayList<>(Arrays.asList(ProfileManager.getProfileList
            // (getActivity())));
        } else {
            // Get tasks from saved instance.
            Log.d(TAG, "Generating task list from saved instance");
            //mProfileList = pSavedInstanceState.getParcelableArrayList(PROFILE_LIST);
            isInActionMode = savedInstanceState.getBoolean(IS_IN_ACTION_MODE);
        }

        mProfileList = new ArrayList<>(Arrays.asList(mProfileManager.getProfileList()));

        // Create adapter.
        Log.d(TAG, "Creating adapter with " + mProfileList.size() + " items");
        mAdapter = new ProfileListAdapter(this.getActivity(), mProfileList);

        // Assign adapter to ListView.
        Log.d(TAG, "Assigning adapter");
        this.setListAdapter(mAdapter);



    }
}
