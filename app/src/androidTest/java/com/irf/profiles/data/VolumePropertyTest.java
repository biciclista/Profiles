package com.irf.profiles.data;

import com.irf.profiles.model.VolumeProperty;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

public class VolumePropertyTest extends AbstractDaoTestLongPk<VolumePropertyDao, VolumeProperty> {

    public VolumePropertyTest() {
        super(VolumePropertyDao.class);
    }

    @Override
    protected VolumeProperty createEntity(Long key) {
        VolumeProperty entity = new VolumeProperty();
        entity.setId(key);
        //entity.setId();
        entity.setProfileId(1L);
        entity.setVolumeType(1);
        entity.setValue(1);
        entity.setActive(false);
        return entity;
    }

}
