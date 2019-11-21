package com.nsl.august.web;

import com.nsl.auguest.core.service.IAdminPermService;
import com.nsl.auguest.core.service.IAdminRoleService;
import com.nsl.auguest.core.service.IAdminUserService;
import com.nsl.august.web.config.FilterConfig;
import com.nsl.august.web.config.ShiroConfig;
import com.nsl.august.web.controller.AdminUserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MvcTest {

//    @LocalServerPort
//    private int localServerPort;


//    @Autowired
//    private TestRestTemplate restTemplate;
    @Autowired
    private MockMvc mockMvc;


    /*@Test
    public void testGetRoleList() {

        String url = "http://localhost:" + localServerPort + "/august/role/list";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);
    }*/


    @Test
    public void testGetRoleList() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/role/list"))
                .andDo(MockMvcResultHandlers.print())

            ;
    }

}
