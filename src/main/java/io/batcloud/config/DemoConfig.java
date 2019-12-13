package io.batcloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author zhuweimu
 * @date 2019/10/29 16:19
 */
@Data
@ConfigurationProperties(prefix = "purchase")
public class DemoConfig {

    List<Integer> milliseconds;
}
