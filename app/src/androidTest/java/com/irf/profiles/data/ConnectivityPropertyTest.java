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
        entity.setMId(key);
        entity.setMProfileId(1L);
        entity.setMConnectivityType(1);
        entity.setMEnabled(true);
        entity.setMActive(false);
        return entity;
    }

}
