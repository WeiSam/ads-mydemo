package io.batcloud.service;

import io.batcloud.disruptor.event.UserEvent;
import io.batcloud.disruptor.producer.UserDisruptor;
import io.batcloud.disruptor.producer.UserProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zhuweimu
 * @date 2019/12/13 14:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DisruptorTest {

    @Autowired
    UserDisruptor userDisruptor;

    @Test
    public void testFun3() {
        userDisruptor.sendData(new UserEvent().setUserDto(new UserEvent.UserDto().setName("sam").setAge(18)));
    }
}
