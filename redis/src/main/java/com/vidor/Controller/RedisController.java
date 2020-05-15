package com.vidor.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Value("${hot.config}")
    private String hotConfig;

    @GetMapping("/hot")
    public String showHotConfig(){
        return hotConfig;
    }
}
