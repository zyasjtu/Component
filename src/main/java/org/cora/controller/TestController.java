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
}
