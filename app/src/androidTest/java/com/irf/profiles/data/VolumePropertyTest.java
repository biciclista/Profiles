package com.irf.profiles.data;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.irf.profiles.model.VolumeProperty;
import com.irf.profiles.data.VolumePropertyDao;

public class VolumePropertyTest extends AbstractDaoTestLongPk<VolumePropertyDao, VolumeProperty> {

    public VolumePropertyTest() {
        super(VolumePropertyDao.class);
    }

    @Override
    protected VolumeProperty createEntity(Long key) {
        VolumeProperty entity = new VolumeProperty();
        entity.setId(key);
        entity.setProfileId(1L);
        entity.setType(1);
        entity.setValue(0);
        entity.setActive(false);
        return entity;
    }

}
