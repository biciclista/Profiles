package com.irf.profiles.data;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.irf.profiles.model.ConnectivityProperty;
import com.irf.profiles.data.ConnectivityPropertyDao;

public class ConnectivityPropertyTest extends AbstractDaoTestLongPk<ConnectivityPropertyDao, ConnectivityProperty> {

    public ConnectivityPropertyTest() {
        super(ConnectivityPropertyDao.class);
    }

    @Override
    protected ConnectivityProperty createEntity(Long key) {
        ConnectivityProperty entity = new ConnectivityProperty();
        entity.setId(key);
        //entity.setId();
        entity.setProfileId(1);
        entity.setConnectivityType(1);
        entity.setValue(true);
        entity.setActive(false);
        return entity;
    }

}
