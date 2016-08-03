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
public class ConnectivityPropertyTest {
    Context context;
    AudioManager audioManager;

    @Before
    public void initialize() {
        context = App.getContext();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Test
    public void applyNotActive() {
        // Save actual alarm volume.
        int alarmVolume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);

        // Create property not active, set a different volume and apply.
        VolumeProperty property = new VolumeProperty(1L, 1L, VolumeProperty.VOL_ALARM, 0, false);
        property.setValue(alarmVolume == 0 ? 1 : 0);
        property.apply(context);

        assertEquals(alarmVolume, audioManager.getStreamVolume(AudioManager.STREAM_ALARM));
    }

    @Test
    public void applyAlarm() {
        // Volume to set.
        int alarmVolume = (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) == 0 ? 1 : 0);

        // Create property active, set volume and apply.
        VolumeProperty property = new VolumeProperty(1L, 1L, VolumeProperty.VOL_ALARM, alarmVolume, true);
        property.apply(context);

        assertEquals(alarmVolume, audioManager.getStreamVolume(AudioManager.STREAM_ALARM));
    }

    @Test
    public void applyRing() {
        // Volume to set.
        int alarmVolume = (audioManager.getStreamVolume(AudioManager.STREAM_RING) == 0 ? 1 : 0);

        // Create property active, set volume and apply.
        VolumeProperty property = new VolumeProperty(1L, 1L, VolumeProperty.VOL_RING, alarmVolume, true);
        property.apply(context);

        assertEquals(alarmVolume, audioManager.getStreamVolume(AudioManager.STREAM_RING));
    }

    @Test
    public void applyNotification() {
        // Volume to set.
        int alarmVolume = (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) == 0 ? 1 : 0);

        // Create property active, set volume and apply.
        VolumeProperty property = new VolumeProperty(1L, 1L, VolumeProperty.VOL_NOTIFICATION, alarmVolume, true);
        property.apply(context);

        assertEquals(alarmVolume, audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION));
    }

    @Test
    public void applyMultimedia() {
        // Volume to set.
        int alarmVolume = (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0 ? 1 : 0);

        // Create property active, set volume and apply.
        VolumeProperty property = new VolumeProperty(1L, 1L, VolumeProperty.VOL_MEDIA, alarmVolume, true);
        property.apply(context);

        assertEquals(alarmVolume, audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }
}
