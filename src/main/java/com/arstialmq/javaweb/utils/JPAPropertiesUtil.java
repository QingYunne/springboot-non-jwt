package com.arstialmq.javaweb.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class JPAPropertiesUtil {

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String DB_USER;

    @Value("${spring.datasource.password")
    private  String DB_PASS;

    public JPAPropertiesUtil() {
        System.out.println(DB_URL);
        System.out.println(DB_USER);
        System.out.println(DB_PASS);
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public String getDB_USER() {
        return DB_USER;
    }

    public String getDB_PASS() {
        return DB_PASS;
    }
}
