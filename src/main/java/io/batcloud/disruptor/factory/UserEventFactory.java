package io.batcloud.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import io.batcloud.disruptor.event.UserEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhuweimu
 * @date 2019/8/14 17:50
 */
@Component
public class UserEventFactory implements EventFactory<UserEvent> {
    @Override
    public UserEvent newInstance() {
        return new UserEvent();
    }
}
