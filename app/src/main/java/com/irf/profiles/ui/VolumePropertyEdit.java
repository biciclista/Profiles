package com.irf.profiles.ui;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.irf.profiles.R;
import com.irf.profiles.model.VolumeProperty;

/**
 * Created by nacho on 28/01/14.
 */
public class VolumePropertyEdit extends LinearLayout {

    // TAG for logging.
    private static final String TAG = VolumePropertyEdit.class.getSimpleName();
    // Volume property.
    private VolumeProperty property = null;
    // Controls for editing the property.
    private TextView lblName;
    private SeekBar skbValue;
    private CheckBox chkActive;

    public VolumePropertyEdit(Context context) {
        super(context);
    }

    public VolumePropertyEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VolumePropertyEdit(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VolumeProperty getProperty() {
        return property;
    }

    public void setProperty(VolumeProperty property) {
        this.property = property;
        bind();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Get controls.
        this.lblName = (TextView) findViewById(R.id.lblVolume);
        this.skbValue = (SeekBar) findViewById(R.id.skbVolume);
        this.chkActive = (CheckBox) findViewById(R.id.chkVolumeActive);
    }

    private void bind() {
        // Set volume name.
        switch (property.getType()) {
            case VolumeProperty.VOL_ALARM:
                lblName.setText(getContext().getString(R.string.volume_alarm_name));
                break;
            case VolumeProperty.VOL_MEDIA:
                lblName.setText(getContext().getString(R.string.volume_music_name));
                break;
            case VolumeProperty.VOL_NOTIFICATION:
                lblName.setText(getContext().getString(R.string.volume_notification_name));
                break;
            case VolumeProperty.VOL_RING:
                lblName.setText(getContext().getString(R.string.volume_ring_name));
                break;
        }

        // Set actual volume.
        final AudioManager audioManager = (AudioManager) getContext().getSystemService(Context
                .AUDIO_SERVICE);
        skbValue.setMax(audioManager.getStreamMaxVolume(property.getType()));
        skbValue.setProgress(property.getValue());
        skbValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar pSeekBar, int pProgress, boolean pFromUser) {
                if (pFromUser) {
                    property.setValue(pProgress);
                    Log.d(TAG, "Saving value to " + pProgress + " for VolumeProperty " + property
                            .getId());
                    property.update();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar pSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar pSeekBar) {

            }
        });

        // Set active.
        chkActive.setChecked(property.getActive());
        chkActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton pCompoundButton, boolean pIsChecked) {
                property.setActive(pIsChecked);
                Log.d(TAG, "Saving active state to " + pIsChecked + " for VolumeProperty " +
                        property.getId());
                property.update();
            }
        });
    }
}
