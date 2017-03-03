package com.rabbitmqperf.model;

import org.springframework.cloud.config.java.*;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
/**
 * Created by qshao on 3/3/17.
 */
public class CloudConfig extends AbstractCloudConfig {

    @Bean
    public ConnectionFactory  rabbitFactory(){
        return connectionFactory().rabbitConnectionFactory();
    }

}
