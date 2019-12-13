package io.batcloud.disruptor.common;

import com.lmax.disruptor.RingBuffer;

/**
 * @author zhuweimu
 * @date 2019/8/14 17:58
 */
public abstract class AbstractProducer<T> {

    public final RingBuffer<T> ringBuffer;

    public AbstractProducer(RingBuffer<T> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public abstract void onData(T event);

}
