package com.irf.profiles.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    public final static String PROFILE_ID = "ProfileId";

    // Profile manager.
    private ProfileManager profileManager;
    // Profile list.
    private ArrayList<Profile> profileList;
    // Adapter.
    private ProfileListAdapter adapter;

    private OnConfigureProfileListener listener;

    public ProfileListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.setHasOptionsMenu(true);
        profileManager = ProfileManager.getInstance();
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
        profileList = new ArrayList<>(Arrays.asList(profileManager.getProfileList()));

        // Create adapter.
        Log.d(TAG, "Creating adapter with " + profileList.size() + " items");
        adapter = new ProfileListAdapter(this.getActivity(), profileList);
        // Set the listener for configure profile click.
        adapter.setOnConfigureClickListener(new ProfileListAdapter.OnConfigureClickListener() {
            @Override
            public void onConfigureClick(long id) {
                listener.OnConfigureProfile(id);
            }
        });

        // Assign adapter to ListView.
        Log.d(TAG, "Asigning adapter");
        this.setListAdapter(adapter);

        // Floating button for adding a new profile.
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProfile();
            }
        });
    }

    private void addProfile() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View content = inflater.inflate(R.layout.profile_add_dialog, null);
        final EditText txtName = (EditText) content.findViewById(R.id.txtName);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_profile_title);
        builder.setView(content);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = txtName.getText().toString().trim();
                try {
                    Profile profile = profileManager.addProfile(name);
                    adapter.add(profile);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getActivity(),
                            "There is a profile with this name", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);

        AlertDialog dialog = builder.create();

        dialog.show();

        final Button ok = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        ok.setEnabled(false);

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence pCharSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence pCharSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable pEditable) {
                String text = txtName.getText().toString();
                text = text.trim();

                if (text.equals("")) {
                    txtName.setError("Profile name can't be empty");
                    ok.setEnabled(false);
                } else if (profileManager.existsProfile(text)) {
                    txtName.setError("Duplicated profile name");
                    ok.setEnabled(false);
                } else {
                    ok.setEnabled(true);
                }
            }
        });
    }

    /**
     * Interface for listeners for configuring a profile.
     */
    public interface OnConfigureProfileListener {
        /**
         * Called when user wants to configure a profile.
         * @param id identifier of the profile to configure.
         */
        void OnConfigureProfile(long id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnConfigureProfileListener) {
            listener = (OnConfigureProfileListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnConfigureProfileListener");
        }
    }

}
