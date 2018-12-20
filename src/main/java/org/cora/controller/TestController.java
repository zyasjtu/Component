package org.cora.controller;

import com.alibaba.fastjson.JSON;
import org.cora.util.HttpClientUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Colin
 */
@RestController
@RequestMapping(method = RequestMethod.POST)
public class TestController {
    private static final Logger LOGGER = Logger.getLogger(TestController.class);
    
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    
    @RequestMapping("/input")
    public String input(String param1, String param2) {
        Map<String, String> inputMap = new HashMap<String, String>(2);
        inputMap.put("param1", param1);
        inputMap.put("param2", param2);
        return HttpClientUtils.doPost("http://99.48.24.243:8080/component/output", inputMap);
    }

    @RequestMapping("/output")
    public String output(String param1, String param2) {
        Map<String, String> outputMap = new HashMap<String, String>(2);
        outputMap.put("param1", param2);
        outputMap.put("param2", param1);
        return JSON.toJSONString(outputMap);
    }

    @RequestMapping(value = "/setSchedule.json")
    public String setSchedule(String cronExpression) {
        threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("set schedule!");
            }
        }, new CronTrigger(StringUtils.isBlank(cronExpression) ? "0/5 * * * * *" : cronExpression));
        return JSON.toJSONString(ResponseUtils.success());
    }

    @RequestMapping(value = "/resetSchedule.json")
    public String resetSchedule(String cronExpression) {
        threadPoolTaskScheduler.shutdown();
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("reset schedule!");
            }
        }, new CronTrigger(StringUtils.isBlank(cronExpression) ? "0/5 * * * * *" : cronExpression));
        return JSON.toJSONString(ResponseUtils.success());
    }

    @RequestMapping(value = "/shutdownSchedule.json")
    public String shutdownSchedule(String cronExpression) {
        threadPoolTaskScheduler.shutdown();
        return JSON.toJSONString(ResponseUtils.success());
    }
}
