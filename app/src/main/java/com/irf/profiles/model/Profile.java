package com.irf.profiles.model;

import com.irf.profiles.App;
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
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

/**
 * A Profile sets a group of preferences. This are of two types:
 * <ul>
 * <li>Volume: manages the different volumes of the device.</li>
 * <li>Connectivity: manages some of the connection settings of the device.</li>
 * </ul>
 */
@Entity(active = true)
public class Profile {
    // TAG for logging.
    private transient static final String TAG = Profile.class.getSimpleName();

    @Id(autoincrement = true)
    private Long id;
    @NotNull
    @Unique
    private String name;
    // Indicates if this is the active profile.
    private boolean active;

    // Volume properties.
    @ToMany(referencedJoinProperty = "profileId")
    private List<VolumeProperty> volumeProperties;
    // Connectivity properties.
    @ToMany(referencedJoinProperty = "profileId")
    private List<ConnectivityProperty> connectivityProperties;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 89320040)
    private transient ProfileDao myDao;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 754527121)
    public Profile(Long id, @NotNull String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    @Generated(hash = 782787822)
    public Profile() {
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
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

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1626178392)
    public synchronized void resetConnectivityProperties() {
        connectivityProperties = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1212321456)
    public List<ConnectivityProperty> getConnectivityProperties() {
        if (connectivityProperties == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ConnectivityPropertyDao targetDao = daoSession.getConnectivityPropertyDao();
            List<ConnectivityProperty> connectivityPropertiesNew = targetDao
                    ._queryProfile_ConnectivityProperties(id);
            synchronized (this) {
                if (connectivityProperties == null) {
                    connectivityProperties = connectivityPropertiesNew;
                }
            }
        }
        return connectivityProperties;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 840957922)
    public synchronized void resetVolumeProperties() {
        volumeProperties = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1863447518)
    public List<VolumeProperty> getVolumeProperties() {
        if (volumeProperties == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VolumePropertyDao targetDao = daoSession.getVolumePropertyDao();
            List<VolumeProperty> volumePropertiesNew = targetDao._queryProfile_VolumeProperties(id);
            synchronized (this) {
                if (volumeProperties == null) {
                    volumeProperties = volumePropertiesNew;
                }
            }
        }
        return volumeProperties;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1351849779)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProfileDao() : null;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Creates a new profile with a name, persist it and creates its properties.
     *
     * @param name Profile name
     */
    public Profile(String name) {
        // Store name and initialize values.
        this.name = name;
        this.active = false;
        // Get DAO.
        ProfileDao dao = App.getInstance().getDaoSession().getProfileDao();
        dao.insert(this);
        // Create properties.
        createVolumeProperties();
        createConnectivityProperties();
    }

    private void createVolumeProperties() {
        // Create properties.
        VolumeProperty alarmVolume = new VolumeProperty(null, this.id, VolumeProperty.VOL_ALARM,
                0, false);
        VolumeProperty ringVolume = new VolumeProperty(null, this.id, VolumeProperty.VOL_RING, 0,
                false);
        VolumeProperty notificationVolume = new VolumeProperty(null, this.id, VolumeProperty
                .VOL_NOTIFICATION, 0, false);
        VolumeProperty mediaVolume = new VolumeProperty(null, this.id, VolumeProperty.VOL_MEDIA,
                0, false);

        // Get and cache property list.
        List<VolumeProperty> volumeProperties = this.getVolumeProperties();
        // Get DAO.
        VolumePropertyDao dao = daoSession.getVolumePropertyDao();

        // Persist properties.
        dao.insert(alarmVolume);
        volumeProperties.add(alarmVolume);
        dao.insert(ringVolume);
        volumeProperties.add(ringVolume);
        dao.insert(notificationVolume);
        volumeProperties.add(notificationVolume);
        dao.insert(mediaVolume);
        volumeProperties.add(mediaVolume);
    }

    private void createConnectivityProperties() {
        // Create properties.
        ConnectivityProperty wifi = new ConnectivityProperty(null, this.id, ConnectivityProperty.CONN_TYPE_WIFI, false, false);
        ConnectivityProperty bluetooth = new ConnectivityProperty(null, this.id, ConnectivityProperty.CONN_TYPE_WIFI, false, false);
        ConnectivityProperty nfc = new ConnectivityProperty(null, this.id, ConnectivityProperty.CONN_TYPE_WIFI, false, false);
        ConnectivityProperty sync = new ConnectivityProperty(null, this.id, ConnectivityProperty.CONN_TYPE_WIFI, false, false);

        // Get and cache property list.
        List<ConnectivityProperty> connectivityProperties = this.getConnectivityProperties();
        // Get DAO.
        ConnectivityPropertyDao dao = daoSession.getConnectivityPropertyDao();

        // Persist properties.
        dao.insert(wifi);
        connectivityProperties.add(wifi);
        dao.insert(bluetooth);
        connectivityProperties.add(bluetooth);
        dao.insert(nfc);
        connectivityProperties.add(nfc);
        dao.insert(sync);
        connectivityProperties.add(sync);
    }
}
