package io.batcloud.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean(name = "filter")
    public FilterBean getFilterBean(){
        return new FilterBean();
    }
}
