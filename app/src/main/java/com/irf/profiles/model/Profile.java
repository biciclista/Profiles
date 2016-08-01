package com.irf.profiles.model;

import com.irf.profiles.data.ConnectivityPropertyDao;
import com.irf.profiles.data.DaoSession;
import com.irf.profiles.data.ProfileDao;
import com.irf.profiles.data.VolumePropertyDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * A Profile sets a group of preferences. This are of two types:
 * <ul>
 * <li>Volume: manages the different volumes of the device.</li>
 * <li>Connectivity: manages some of the connection setings of the device.</li>
 * </ul>
 */
@Entity(active = true)
public class Profile {
    // TAG for logging.
    private transient static final String TAG = Profile.class.getSimpleName();

    @Id(autoincrement = true)
    private Long mId;
    @NotNull
    private String mName;
    // Indicates if this is the mActive profile.
    private boolean mActive;

    // Volume properties.
    @ToMany(referencedJoinProperty = "mProfileId")
    private List<VolumeProperty> mVolumeProperties;
    // Connectivity properties.
    @ToMany(referencedJoinProperty = "mProfileId")
    private List<ConnectivityProperty> mConnectivityProperties;

    /** Used for active entity operations. */
    @Generated(hash = 89320040)
    private transient ProfileDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 721195061)
    public Profile(Long mId, @NotNull String mName, boolean mActive) {
        this.mId = mId;
        this.mName = mName;
        this.mActive = mActive;
    }

    @Generated(hash = 782787822)
    public Profile() {
    }

    @Override
    public String toString() {
        return "Profile{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mActive=" + mActive +
                '}';
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

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2107583252)
    public synchronized void resetMConnectivityProperties() {
        mConnectivityProperties = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 395527603)
    public List<ConnectivityProperty> getMConnectivityProperties() {
        if (mConnectivityProperties == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ConnectivityPropertyDao targetDao = daoSession.getConnectivityPropertyDao();
            List<ConnectivityProperty> mConnectivityPropertiesNew = targetDao._queryProfile_MConnectivityProperties(mId);
            synchronized (this) {
                if(mConnectivityProperties == null) {
                    mConnectivityProperties = mConnectivityPropertiesNew;
                }
            }
        }
        return mConnectivityProperties;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2110984013)
    public synchronized void resetMVolumeProperties() {
        mVolumeProperties = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1764681641)
    public List<VolumeProperty> getMVolumeProperties() {
        if (mVolumeProperties == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VolumePropertyDao targetDao = daoSession.getVolumePropertyDao();
            List<VolumeProperty> mVolumePropertiesNew = targetDao._queryProfile_MVolumeProperties(mId);
            synchronized (this) {
                if(mVolumeProperties == null) {
                    mVolumeProperties = mVolumePropertiesNew;
                }
            }
        }
        return mVolumeProperties;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1351849779)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProfileDao() : null;
    }

    public boolean getMActive() {
        return this.mActive;
    }

    public void setMActive(boolean mActive) {
        this.mActive = mActive;
    }

    public String getMName() {
        return this.mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }
}
