package io.batcloud.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zhuweimu
 * @date 2019/8/1 14:56
 */
@Service
@Slf4j
public class OrderTask {

    @Async
    public void testTask(int i){
        log.info("OrderTask.start"+i);
        try {
            Thread.sleep(1000*30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("OrderTask.end"+i);
    }

    @Async
    public void testProxy(){
        OrderTask orderTask = (OrderTask) AopContext.currentProxy();
        log.info("执行开始,name = "+orderTask.getClass().getName());
        try {
            Thread.sleep(1000*60);
        } catch (InterruptedException e) {
        }
        log.info("执行结束");
    }
}
