package com.nsl.test.service;

import com.nsl.auguest.core.CoreApplication;
import com.nsl.auguest.core.service.IAdminPermService;
import com.nsl.auguest.core.service.IAdminRoleService;
import com.nsl.auguest.core.service.IAdminUserService;
import com.nsl.persistence.model.LitemallAdmin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CoreApplication.class})
public class AdminUserServiceTest {


    @Autowired
    private IAdminUserService service;


    @Autowired
    private IAdminRoleService roleService;

    @Autowired
    private IAdminPermService permService;

    @Test
    public void testQueryAllAdmin() {
        List<LitemallAdmin> all = service.all();
        Assert.assertEquals(3, all.size());
    }

    @Test
    public void testQueryRoles() {
        Integer[] roleIds = {1, 3};

        Set<String> roleNames = roleService.qryRolesByRoleIds(roleIds);
        Assert.assertNotNull(roleNames);
        Assert.assertEquals(2, roleNames.size());
        Assert.assertTrue(roleNames.contains("推广管理员"));
        Assert.assertTrue(roleNames.contains("超级管理员"));
        String json = "";
    }

    @Test
    public void testQueryPerms() {
        Integer[] roleIds = {1, 3};
        Set<String> perms = permService.qryPermByRoleIds(roleIds);
        Assert.assertNotNull(perms);
        Assert.assertEquals(21, perms.size());

    }
}
