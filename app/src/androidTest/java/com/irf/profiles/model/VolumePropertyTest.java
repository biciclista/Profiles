package com.irf.profiles.model;

import android.content.Context;
import android.media.AudioManager;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.irf.profiles.App;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation tests for VolumeProperty entity.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class VolumePropertyTest {
    Context mContext;
    AudioManager mAudioManager;

    @Before
    public void initialize() {
        mContext = App.getContext();
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    @Test
    public void applyNotActive() {
        // Save actual alarm volume.
        int alarmVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);

        // Create property not active, set a different volume and apply.
        VolumeProperty property = new VolumeProperty(1L, 1L, VolumeProperty.VOL_ALARM, 0, false);
        property.setValue(alarmVolume == 0 ? 1 : 0);

        assertEquals(alarmVolume, mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM));
    }
}
