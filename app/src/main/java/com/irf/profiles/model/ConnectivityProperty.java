package com.irf.profiles.model;

import com.irf.profiles.data.DaoSession;
import com.irf.profiles.data.VolumePropertyDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import com.irf.profiles.data.ConnectivityPropertyDao;

/**
 * Represent a connectivity property in a profile. A ConnectivityProperty can represent four types
 * of connections:
 * <ul>
 * <li>WiFi: state of the WiFi connection.</li>
 * <li>Bluetooth: state of the bluetooth connection.</li>
 * <li>NFC: state of the NFC connection.</li>
 * <li>Sync: global state of synchronization.</li>
 * </ul>
 */
@Entity(active = true)
public class ConnectivityProperty {
    /**
     * The connectivity property is of type Bluetooth.
     */
    public transient static final int CONN_TYPE_BLUETOOTH = 1;
    /**
     * The connectivity property is of type WiFi.
     */
    public transient static final int CONN_TYPE_WiFi = 2;
    /**
     * The connectivity property is of type GPS.
     */
    public transient static final int CONN_TYPE_GPS = 3;
    /**
     * The connectivity property is of type Mobile Data.
     */
    public transient static final int CONN_TYPE_MOBILE_DATA = 4;
    /**
     * The connectivity property is of type Sync.
     */
    public transient static final int CONN_TYPE_SYNC = 5;
    /**
     * The connectivity property is of type NFC.
     */
    public transient static final int CONN_TYPE_NFC = 6;

    // TAG for logging.
    private transient static final String TAG = ConnectivityProperty.class.getSimpleName();

    @Id(autoincrement = true)
    private long id;
    private long profileId;
    private int connectivityType;
    // Value of the property.
    private boolean value;
    // Indicates if the property has to be applied in the profile.
    private boolean active;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 79083208)
    private transient ConnectivityPropertyDao myDao;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 1703594866)
    public ConnectivityProperty(long id, long profileId, int connectivityType, boolean value,
                                boolean active) {
        this.id = id;
        this.profileId = profileId;
        this.connectivityType = connectivityType;
        this.value = value;
        this.active = active;
    }

    @Generated(hash = 179088954)
    public ConnectivityProperty() {
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
    @Generated(hash = 459958677)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getConnectivityPropertyDao() : null;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public int getConnectivityType() {
        return this.connectivityType;
    }

    public void setConnectivityType(int connectivityType) {
        this.connectivityType = connectivityType;
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

    @Override
    public String toString() {
        return "ConnectivityProperty{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", connectivityType=" + connectivityType +
                ", active=" + active +
                ", value=" + value +
                '}';
    }
}
