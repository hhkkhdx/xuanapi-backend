package com.example.xuanapiinterface;

import com.example.xuanapiclientsdk.client.XuanApiClient;
import com.example.xuanapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XuanapiInterfaceApplicationTests {

    @Autowired
    private XuanApiClient xuanApiClient;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("xuan");
        String userNameByPost = xuanApiClient.getUserNameByPost(user);
        System.out.println(userNameByPost);
    }

}
