package io.batcloud.disruptor.producer;

import com.lmax.disruptor.RingBuffer;
import io.batcloud.disruptor.common.AbstractProducer;
import io.batcloud.disruptor.event.UserEvent;
import org.springframework.beans.BeanUtils;

/**
 * @author zhuweimu
 * @date 2019/8/14 18:01
 */
public class UserProducer extends AbstractProducer<UserEvent> {

    public UserProducer(RingBuffer<UserEvent> ringBuffer) {
        super(ringBuffer);
    }

    @Override
    public void onData(UserEvent eventData) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try
        {
            // Get the entry in the Disruptor,for the sequence
            UserEvent event = ringBuffer.get(sequence);
            // Fill with data
            BeanUtils.copyProperties(eventData,event);
        }
        finally
        {
            ringBuffer.publish(sequence);
        }
    }

}
