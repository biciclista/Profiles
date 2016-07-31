package com.irf.profiles.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
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
    // TAG for logging.
    private transient static final String TAG = VolumeProperty.class.getSimpleName();

    @Id(autoincrement = true)
    private long id;
    private long profileId;
    private int volumeType;
    // Value of the property.
    private int value;
    // Indicates if the property has to be applied in the profile.
    private boolean active;
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

    @Generated(hash = 444926219)
    public VolumeProperty(long id, long profileId, int volumeType, int value, boolean active) {
        this.id = id;
        this.profileId = profileId;
        this.volumeType = volumeType;
        this.value = value;
        this.active = active;
    }

    @Generated(hash = 1134627104)
    public VolumeProperty() {
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

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getVolumeType() {
        return this.volumeType;
    }

    public void setVolumeType(int volumeType) {
        this.volumeType = volumeType;
    }

    public long getProfileId() {
        return this.profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "VolumeProperty{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", volumeType=" + volumeType +
                ", active=" + active +
                ", value=" + value +
                '}';
    }
}
