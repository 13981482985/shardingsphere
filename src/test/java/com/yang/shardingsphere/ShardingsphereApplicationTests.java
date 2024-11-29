package com.yang.shardingsphere;

import com.yang.shardingsphere.entity.User;
import com.yang.shardingsphere.mapper.UserMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ShardingsphereApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void insertUsers() {
        for(int i=0; i<20; i++){
            User user = User.builder()
                    .userName("yang_" + (new Random().nextInt() % 1000))
                    .idCart("32422896549423" + i)
                    .password("454232" + i * (new Random().nextInt() % 100))
                    .build();
            userMapper.insert(user);
        }
    }

}
