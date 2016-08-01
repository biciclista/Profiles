package com.irf.profiles.data;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.irf.profiles.model.Profile;
import com.irf.profiles.data.ProfileDao;

public class ProfileTest extends AbstractDaoTestLongPk<ProfileDao, Profile> {

    public ProfileTest() {
        super(ProfileDao.class);
    }

    @Override
    protected Profile createEntity(Long key) {
        Profile entity = new Profile();
        entity.setMId(key);
        entity.setMName("Profile");
        entity.setMActive(false);
        return entity;
    }

}
