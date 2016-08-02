package com.irf.profiles.model;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.irf.profiles.data.DaoSession;
import com.irf.profiles.data.VolumePropertyDao;

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
    private Long id;
    @NotNull
    private Long profileId;
    private int type;
    // Value of the property.
    private int value;
    // Indicates if the property has to be applied in the profile.
    private boolean active;
    /** Used for active entity operations. */
    @Generated(hash = 1995043586)
    private transient VolumePropertyDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 1472166301)
    public VolumeProperty(Long id, @NotNull Long profileId, int type, int value, boolean active) {
        this.id = id;
        this.profileId = profileId;
        this.type = type;
        this.value = value;
        this.active = active;
    }

    @Generated(hash = 1134627104)
    public VolumeProperty() {
    }

    @Override
    public String toString() {
        return "VolumeProperty{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", type=" + type +
                ", active=" + active +
                ", value=" + value +
                '}';
    }

    /**
     * Applies the property. If property is active, sets the volume to value.
     *
     * @param pContext Android context.
     */
    public void apply(Context pContext) {
        if (active) {
            AudioManager audioManager = (AudioManager) pContext.getSystemService(Context
                    .AUDIO_SERVICE);
            Log.d(TAG, "Applying volume to stream (" + active + ") to " + value);
            audioManager.setStreamVolume(type, value, 0);
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 490785352)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getVolumePropertyDao() : null;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getProfileId() {
        return this.profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
