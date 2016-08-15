package com.irf.profiles.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.irf.profiles.R;
import com.irf.profiles.model.Profile;

import java.util.ArrayList;

/**
 * Adapter for showing a single Profile in the list.
 */
public class ProfileListAdapter extends ArrayAdapter {
    private OnConfigureClickListener listener;

    ProfileListAdapter(Context context, ArrayList<Profile> profileList) {
        super(context, R.layout.profile_list_row, R.id.lblProfile, profileList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Profile profile = (Profile) this.getItem(position);
        View view = super.getView(position, convertView, parent);

        TextView lblProfile = (TextView) view.findViewById(R.id.lblProfile);
        lblProfile.setText(profile.getName());
        ImageView imgSelected = (ImageView) view.findViewById(R.id.imgSelected);
        if (profile.getActive()) {
            imgSelected.setImageResource(R.drawable.check);
        } else {
            imgSelected.setImageResource(R.drawable.empty);
        }

        final ImageView imgConfigure = (ImageView) view.findViewById(R.id.imgConfigure);
        imgConfigure.setTag(profile);

        imgConfigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile profile = (Profile) imgConfigure.getTag();
                Log.d("OnClickListener", profile.toString());

                if (listener != null) {
                    listener.onConfigureClick(profile.getId());
                }
            }
        });

        return view;
    }

    /**
     * Interface for listeners for configure a profile.
     */
    public interface OnConfigureClickListener {
        /**
         * Call when the image for configuring a profile is clicked.
         *
         * @param id Identifier of the profile clicked.
         */
        void onConfigureClick(long id);
    }

    /**
     * Sets the listener for the configure click action.
     *
     * @param listener Listener called when the user clicks the image for configuring a profile.
     */
    public void setOnConfigureClickListener(OnConfigureClickListener listener) {
        this.listener = listener;
    }
}
