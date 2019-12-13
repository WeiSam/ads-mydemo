package io.batcloud.task;

import lombok.extern.slf4j.Slf4j;
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
}
