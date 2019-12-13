package io.batcloud.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import io.batcloud.disruptor.event.UserEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhuweimu
 * @date 2019/8/14 17:51
 */
@Component
public class UserEventHandler implements EventHandler<UserEvent> {
    @Override
    public void onEvent(UserEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("name = "+event.getUserDto().getName()+",age = "+event.getUserDto().getAge());
    }
}
