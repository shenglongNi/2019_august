package com.nsl.auguest.core.service;

import com.nsl.persistence.mapper.LitemallAdminMapper;
import com.nsl.persistence.model.LitemallAdmin;
import com.nsl.persistence.model.LitemallAdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Autowired
    private LitemallAdminMapper adminMapper;

    @Override
    public List<LitemallAdmin> all() {

        LitemallAdminExample example = new LitemallAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    @Override
    public LitemallAdmin queryAdminUserByName(String adminName) {
        LitemallAdminExample example = new LitemallAdminExample();

        example.or().andUsernameEqualTo(adminName);
        List<LitemallAdmin> litemallAdmins = adminMapper.selectByExample(example);

        if (litemallAdmins == null) {
            return null;
        }

        return litemallAdmins.get(0);
    }

    @Override
    public LitemallAdmin queryAdminUserById(Integer id) {


        return null;
    }

    @Override
    public void createAdminUser(LitemallAdmin litemallAdmin) {

    }
}
