package com.irf.profiles.model;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.irf.profiles.data.DaoSession;
import com.irf.profiles.data.VolumePropertyDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Represent a volume property in a profile. A VolumeProperty can represent four types of volume:
 * <ul>
 * <li>Ring: volume of the phone ringer.</li>
 * <li>Notifications: volume of the notifications.</li>
 * <li>Media: volume of multimedia applications.</li>
 * <li>Alarm: volume of alarms.</li>
 * </ul>
 */
@Entity(active = true)
public class VolumeProperty {
    /**
     * Ring volume subtype of volume property.
     */
    public transient static final int VOL_RING = AudioManager.STREAM_RING;
    /**
     * Notification volume subtype of volume property.
     */
    public transient static final int VOL_NOTIFICATION = AudioManager.STREAM_NOTIFICATION;
    /**
     * Alarm volume subtype of volume property.
     */
    public transient static final int VOL_ALARM = AudioManager.STREAM_ALARM;
    /**
     * Multimedia volume subtype of volume property.
     */
    public transient static final int VOL_MEDIA = AudioManager.STREAM_MUSIC;

    // TAG for logging.
    private transient static final String TAG = VolumeProperty.class.getSimpleName();

    @Id(autoincrement = true)
    private Long mId;
    private Long mProfileId;
    private int mVolumeType;
    // Value of the property.
    private int mValue;
    // Indicates if the property has to be applied in the profile.
    private boolean mActive;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1995043586)
    private transient VolumePropertyDao myDao;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 966976997)
    public VolumeProperty(Long mId, Long mProfileId, int mVolumeType, int mValue, boolean mActive) {
        this.mId = mId;
        this.mProfileId = mProfileId;
        this.mVolumeType = mVolumeType;
        this.mValue = mValue;
        this.mActive = mActive;
    }

    @Generated(hash = 1134627104)
    public VolumeProperty() {
    }

    @Override
    public String toString() {
        return "VolumeProperty{" +
                "mId=" + mId +
                ", mProfileId=" + mProfileId +
                ", mVolumeType=" + mVolumeType +
                ", mActive=" + mActive +
                ", mValue=" + mValue +
                '}';
    }

    /**
     * Applies the property. If property is mActive, sets the volume to mValue.
     *
     * @param pContext Android context.
     */
    public void apply(Context pContext) {
        if (mActive) {
            AudioManager audioManager = (AudioManager) pContext.getSystemService(Context
                    .AUDIO_SERVICE);
            Log.d(TAG, "Applying volume to stream (" + mActive + ") to " + mValue);
            audioManager.setStreamVolume(mVolumeType, mValue, 0);
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 490785352)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getVolumePropertyDao() : null;
    }

    public boolean getMActive() {
        return this.mActive;
    }

    public void setMActive(boolean mActive) {
        this.mActive = mActive;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int mValue) {
        this.mValue = mValue;
    }

    public int getMVolumeType() {
        return this.mVolumeType;
    }

    public void setMVolumeType(int mVolumeType) {
        this.mVolumeType = mVolumeType;
    }

    public Long getMProfileId() {
        return this.mProfileId;
    }

    public void setMProfileId(Long mProfileId) {
        this.mProfileId = mProfileId;
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

}
