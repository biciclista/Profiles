package com.irf.profiles.model;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.irf.profiles.data.ConnectivityPropertyDao;
import com.irf.profiles.data.DaoSession;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

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

    // Unique identifier.
    @Id(autoincrement = true)
    private Long mId;
    // Profile identifier.
    @NotNull
    private Long mProfileId;
    // Type of connectivity property.
    private int mConnectivityType;
    // Value of the property.
    private boolean mEnabled;
    // Indicates if the property has to be applied in the profile.
    private boolean mActive;
    /** Used for active entity operations. */
    @Generated(hash = 79083208)
    private transient ConnectivityPropertyDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 1927822148)
    public ConnectivityProperty(Long mId, @NotNull Long mProfileId, int mConnectivityType,
            boolean mEnabled, boolean mActive) {
        this.mId = mId;
        this.mProfileId = mProfileId;
        this.mConnectivityType = mConnectivityType;
        this.mEnabled = mEnabled;
        this.mActive = mActive;
    }

    @Generated(hash = 179088954)
    public ConnectivityProperty() {
    }

    @Override
    public String toString() {
        return "ConnectivityProperty{" +
                "id=" + mId +
                ", profileId=" + mProfileId +
                ", connectivityType=" + mConnectivityType +
                ", active=" + mActive +
                ", enabled=" + mEnabled +
                '}';
    }

    public void apply(Context pContext) {
        if (mActive) {
            boolean actuallyEnabled;

            switch (getMConnectivityType()) {
                case CONN_TYPE_BLUETOOTH:
                    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (bluetoothAdapter != null) {
                        // Get actual state.
                        int state = bluetoothAdapter.getState();
                        if (state == BluetoothAdapter.STATE_OFF || state == BluetoothAdapter
                                .STATE_TURNING_OFF) {
                            if (mEnabled) {
                                // If state is off and property is on, enable it.
                                Log.d(TAG, "Applying connectivity property: enabling BLUETOOTH");
                                bluetoothAdapter.enable();
                            }
                        } else if (state == BluetoothAdapter.STATE_ON || state ==
                                BluetoothAdapter.STATE_TURNING_ON) {
                            if (!mEnabled) {
                                // If state is on and property is off, disable it.
                                Log.d(TAG, "Applying connectivity property: disabling BLUETOOTH");
                                bluetoothAdapter.disable();
                            }
                        }
                    }
                    break;
                case CONN_TYPE_WiFi:
                    WifiManager wifiManager = (WifiManager) pContext.getSystemService(Context
                            .WIFI_SERVICE);
                    actuallyEnabled = wifiManager.isWifiEnabled();
                    if(actuallyEnabled != mEnabled){
                        Log.d(TAG, "Applying connectivity property WiFi: " + mEnabled);
                        wifiManager.setWifiEnabled(mEnabled);
                    }
                    /*if (!enabled && actuallyEnabled) {
                        // If state is on and property is off, disable it.
                        Log.d(TAG, "Applying connectivity property: disabling WiFi");
                        wifiManager.setWifiEnabled(false);
                    } else if (enabled && !actuallyEnabled) {
                        // If state is off and property is on, enable it.
                        Log.d(TAG, "Applying connectivity property: enabling WiFi");
                        wifiManager.setWifiEnabled(true);
                    }*/
                    break;
                case CONN_TYPE_SYNC:
                    actuallyEnabled = ContentResolver.getMasterSyncAutomatically();
                    if (actuallyEnabled != mEnabled) {
                        // If property is different than actual state, change it.
                        Log.d(TAG, "Applying connectivity property: Sync=" + mEnabled);
                        ContentResolver.setMasterSyncAutomatically(mEnabled);
                    }
                    break;
                case CONN_TYPE_NFC:

                    break;
            }
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
    @Generated(hash = 459958677)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getConnectivityPropertyDao() : null;
    }

    public boolean getMActive() {
        return this.mActive;
    }

    public void setMActive(boolean mActive) {
        this.mActive = mActive;
    }

    public boolean getMEnabled() {
        return this.mEnabled;
    }

    public void setMEnabled(boolean mEnabled) {
        this.mEnabled = mEnabled;
    }

    public int getMConnectivityType() {
        return this.mConnectivityType;
    }

    public void setMConnectivityType(int mConnectivityType) {
        this.mConnectivityType = mConnectivityType;
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
