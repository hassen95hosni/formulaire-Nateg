package com.example.demo.rethink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class RethinkDBConfiguration {

    @Autowired
    private Environment env;

    public static String DBHOST = "127.0.0.1";

    @PostConstruct
    public void init() {
        this.DBHOST = this.env.getProperty("rethinkdb.covdb");
    }

    @Bean
    public RethinkDBConnectionFactory connectionFactory() {
        return new RethinkDBConnectionFactory(DBHOST);
    }

    @Bean
    DbInitializer dbInitializer() {
        return new DbInitializer();
    }
}
