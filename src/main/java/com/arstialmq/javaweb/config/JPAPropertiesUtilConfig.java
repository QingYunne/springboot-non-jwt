package com.arstialmq.javaweb.config;

import com.arstialmq.javaweb.utils.JPAPropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPAPropertiesUtilConfig {
    @Bean
    public JPAPropertiesUtil jpaPropertiesUtil () {
        return new JPAPropertiesUtil();
    }
}
