package com.nsl.test;

import com.nsl.DbApplication;
import com.nsl.persistence.mapper.LitemallAdminMapper;
import com.nsl.persistence.model.LitemallAdmin;
import com.nsl.persistence.model.LitemallAdminExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DbApplication.class})
public class UserMapperTest {

    @Autowired
    public LitemallAdminMapper litemallAdminMapper;

    @Test
    public void testAdminMapperSelect() {
        LitemallAdminExample example = new LitemallAdminExample();

        example.or().andIdEqualTo(1);
        List<LitemallAdmin> litemallAdmins = litemallAdminMapper.selectByExample(example);
        System.out.println(litemallAdmins);

    }
}
