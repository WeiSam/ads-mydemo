package io.batcloud.disruptor.producer;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import io.batcloud.disruptor.common.AbstractDisrutor;
import io.batcloud.disruptor.event.UserEvent;
import io.batcloud.disruptor.factory.UserEventFactory;
import io.batcloud.disruptor.handler.UserEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhuweimu
 * @date 2019/8/14 17:54
 */
@Component
public class UserDisruptor extends AbstractDisrutor<UserEvent,UserProducer> {

    @Autowired
    UserEventFactory userEventFactory;

    @Autowired
    UserEventHandler userEventHandler;


    @Override
    public Disruptor<UserEvent> initDisruptor() {
        Disruptor<UserEvent> disruptor = new Disruptor<>( userEventFactory, 1024, DaemonThreadFactory.INSTANCE);
        return disruptor;
    }

    @Override
    public EventHandler getEventHandler() {
        return userEventHandler;
    }
}
