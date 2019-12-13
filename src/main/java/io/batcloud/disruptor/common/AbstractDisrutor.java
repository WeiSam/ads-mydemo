package io.batcloud.disruptor.common;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zhuweimu
 * @date 2019/8/14 14:22
 */
public abstract class AbstractDisrutor<E,P extends AbstractProducer> {

    public abstract Disruptor<E> initDisruptor();

    public abstract EventHandler getEventHandler();

    private P producer;

    @PostConstruct
    private void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Disruptor<E> eDisruptor = initDisruptor();
        eDisruptor.handleEventsWith(getEventHandler());
        eDisruptor.start();
        RingBuffer<E> ringBuffer = eDisruptor.getRingBuffer();

        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Class producer = (Class) parameterizedType.getActualTypeArguments()[1];
        Class[] paramType = {RingBuffer.class};
        Constructor constructor = producer.getConstructor(paramType);
        P p = (P) constructor.newInstance(ringBuffer);
        this.producer = p;
    }

    /**
     * 获取生产者
     * @return
     */
    public P getProducer(){
        return this.producer;
    }

    /**
     * 发送消息
     * @param e
     */
    public void sendData(E e){
        this.producer.onData(e);
    }

}
