package com.irf.profiles.ui;

import android.content.Context;
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
            imgSelected.setImageResource(android.support.design.R.drawable.abc_btn_check_to_on_mtrl_015);
        } else {
            imgSelected.setImageResource(android.support.design.R.drawable.abc_btn_check_to_on_mtrl_000);
        }

        return view;
    }

}
