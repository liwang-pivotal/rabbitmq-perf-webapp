package com.rabbitmqperf.controller;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by qshao on 3/3/17.
 */
//@RestController
//public class GreetingController {
//    @RequestMapping("/greeting")
//    public String rabbitMQConnectionInfo(@RequestParam(value="name", defaultValue="World") String name){
//        return new CloudConfig().rabbitFactory().getHost();
//    }
//}
@RestController
public class GreetingController {

    @Autowired
    private ConnectionFactory rabbitConnection;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return rabbitConnection.getHost();
    }
}