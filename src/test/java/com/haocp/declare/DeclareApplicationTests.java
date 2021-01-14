package com.haocp.declare;

import com.haocp.declare.web.service.system.ISysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class DeclareApplicationTests {

    @Autowired
    private ISysMenuService menuService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testMenu(){
        Set<String> strings = menuService.selectMenuPermsByUserId(2L);
        System.out.println("-----------"+strings);
    }


}
