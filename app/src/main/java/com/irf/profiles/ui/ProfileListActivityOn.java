package com.irf.profiles.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.irf.profiles.R;

public class ProfileListActivityOn extends AppCompatActivity implements ProfileListFragment.OnConfigureProfileListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void OnConfigureProfile(long id) {
        // Call profile settings activity.
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra(ProfileListFragment.PROFILE_ID, id);
        startActivity(intent);
    }
}
