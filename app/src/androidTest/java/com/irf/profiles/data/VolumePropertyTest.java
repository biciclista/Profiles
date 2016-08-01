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
        entity.setMId(key);
        entity.setMVolumeType(1);
        entity.setMValue(0);
        entity.setMActive(false);
        return entity;
    }

}
