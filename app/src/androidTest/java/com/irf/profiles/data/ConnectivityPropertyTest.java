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
        entity.setProfileId(1L);
        entity.setType(1);
        entity.setEnabled(false);
        entity.setActive(false);
        return entity;
    }

}
