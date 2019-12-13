package io.batcloud.disruptor.event;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhuweimu
 * @date 2019/8/14 17:49
 */
@Data
@Accessors(chain = true)
public class UserEvent {

    UserDto userDto;

    @Data
    @Accessors(chain = true)
    public static class UserDto {
        private String name;
        private int age;
    }
}
