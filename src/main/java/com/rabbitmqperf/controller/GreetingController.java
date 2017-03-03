package com.rabbitmqperf.controller;

import com.google.gson.Gson;
import com.rabbitmq.perf.Scenario;
import com.rabbitmq.perf.ScenarioFactory;
import com.rabbitmq.tools.json.JSONReader;
import com.rabbitmqperf.model.VCAP_SERVICES;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    private final static Gson gson = new Gson();

    @RequestMapping("/greeting")
    public Map greeting(@RequestBody String scenarioConfig) {
        ConnectionFactory factory = new ConnectionFactory();
        Map<String, String> env = System.getenv();
        VCAP_SERVICES vcap_services = gson.fromJson(env.get("VCAP_SERVICES"), VCAP_SERVICES.class);
        Map scenarioJSON = (Map) new JSONReader().read(scenarioConfig);
        scenarioJSON.put("uri",vcap_services.getRabbits().get(0).getCredentials().getUri());
        Scenario scenario = ScenarioFactory.fromJSON(scenarioJSON,factory);
        try {
            scenario.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Map<String,Object>> results = new HashMap();
        results.put(scenario.getName(), scenario.getStats().results());
        return results;
        }
}